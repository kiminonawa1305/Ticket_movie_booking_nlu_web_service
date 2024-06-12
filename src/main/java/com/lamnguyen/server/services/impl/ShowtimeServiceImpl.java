package com.lamnguyen.server.services.impl;

import com.google.gson.Gson;
import com.lamnguyen.server.enums.ChairStatus;
import com.lamnguyen.server.models.dto.PriceBoardDTO;
import com.lamnguyen.server.models.dto.ShowtimeDTO;
import com.lamnguyen.server.models.dto.ShowtimeByCinemaResponse;
import com.lamnguyen.server.models.dto.TimeSeat;
import com.lamnguyen.server.models.entity.Cinema;
import com.lamnguyen.server.models.entity.Movie;
import com.lamnguyen.server.models.entity.PriceBoard;
import com.lamnguyen.server.models.entity.Showtime;
import com.lamnguyen.server.models.response.MovieDetailResponseRestApi;
import com.lamnguyen.server.repositories.ShowtimeRepository;
import com.lamnguyen.server.services.ShowtimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Showtime findById(int id) {
        return showtimeRepository.findById(id).orElse(null);
    }

    @Override
    public List<ShowtimeDTO> findShowTimeDTOByMovieId(int id) {
        List<Showtime> showtimes = showtimeRepository.findByAvailIsTrueAndMovie_Id(id);
        return showtimes.stream().map(this::convert).toList();
    }

    @Override
    public List<ShowtimeByCinemaResponse> findShowtimeByCinema(int id) {
        List<Showtime> showtimes = showtimeRepository.findByAvailIsTrueAndMovie_Id(id);
        Map<Integer, ShowtimeByCinemaResponse> showtimeMap = new HashMap<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        showtimes.forEach(showtime -> {
            Movie movie = showtime.getMovie();
            Cinema cinema = showtime.getRoom().getCinema();
            ShowtimeByCinemaResponse showtimeByCinemaResponse = showtimeMap.get(cinema.getId());
            if (showtimeByCinemaResponse == null) {
                showtimeByCinemaResponse = ShowtimeByCinemaResponse
                        .builder()
                        .urlImageCinema(cinema.getUrlImage())
                        .nameCinema(cinema.getName())
                        .addressCinema(cinema.getAddress())
                        .detailAddressCinema(cinema.getDetailAddress())
                        .timeSeats(new ArrayList<>())
                        .build();
            }

            RestTemplate restTemplate = new RestTemplate();
            String data = restTemplate.getForObject("https://www.omdbapi.com/?apikey=c3d0a99f&i=" + movie.getIdApi(), String.class);
            MovieDetailResponseRestApi restApi = new Gson().fromJson(data, MovieDetailResponseRestApi.class);
            String time = restApi.getRuntime().replace(" min", "");

            int totalAvailable = showtime.getChairShowTimes().stream().filter(chairShowTime -> chairShowTime.getStatus().equals(ChairStatus.AVAILABLE)).toList().size();

            TimeSeat timeSeat = TimeSeat.builder()
                    .showtimeId(showtime.getId())
                    .availableSeat(totalAvailable)
                    .totalSeat(showtime.getChairShowTimes().size())
                    .start(dateTimeFormatter.format(showtime.getStart()))
                    .end(dateTimeFormatter.format(showtime.getStart().plusMinutes(Long.parseLong(time))))
                    .build();
            showtimeByCinemaResponse.getTimeSeats().add(timeSeat);
            showtimeMap.put(cinema.getId(), showtimeByCinemaResponse);
        });

        return showtimeMap.values().stream().toList();
    }


    @Override
    public PriceBoardDTO getPriceBoard(Integer showtimeId) {
        PriceBoard priceBoard = showtimeRepository.findPriceBoard(showtimeId);
        return convert(priceBoard);
    }

    private ShowtimeDTO convert(Showtime showtime) {
        return modelMapper.map(showtime, ShowtimeDTO.class);
    }

    private PriceBoardDTO convert(PriceBoard priceBoard) {
        return modelMapper.map(priceBoard, PriceBoardDTO.class);
    }
}
