package com.lamnguyen.server.services.impl;

import com.google.gson.Gson;
import com.lamnguyen.server.enums.ChairStatus;
import com.lamnguyen.server.models.dto.PriceBoardDTO;
import com.lamnguyen.server.models.dto.ShowtimeDTO;
import com.lamnguyen.server.models.dto.ShowtimeByCinemaResponse;
import com.lamnguyen.server.models.dto.TimeSeat;
import com.lamnguyen.server.models.entity.*;
import com.lamnguyen.server.models.response.MovieDetailResponseRestApi;
import com.lamnguyen.server.repositories.MovieRepository;
import com.lamnguyen.server.repositories.RoomRepository;
import com.lamnguyen.server.repositories.ShowtimeRepository;
import com.lamnguyen.server.services.CinemaService;
import com.lamnguyen.server.services.ShowtimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private RoomRepository roomRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Showtime findById(int id) {
        return showtimeRepository.findById(id).orElse(null);
    }

    @Override
    public List<ShowtimeDTO> findShowTimeDTOByMovieId(int id, LocalDateTime currentDate) {
        List<Showtime> showtimes = showtimeRepository.findByAvailIsTrueAndStartBetweenAndMovie_Id(currentDate, LocalDateTime.of(currentDate.toLocalDate(), LocalTime.MAX), id);
        return showtimes.stream().map(this::convert).toList();
    }

    @Override
    public List<ShowtimeByCinemaResponse> findShowtimeByCinema(int id, LocalDateTime currentDate) {
        List<Showtime> showtimes = showtimeRepository.findByAvailIsTrueAndStartBetweenAndMovie_Id(currentDate, LocalDateTime.of(currentDate.toLocalDate(), LocalTime.MAX), id);
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

    @Override
    public Showtime addShowtime(ShowtimeDTO showtimeDTO) {
        Movie movie = movieRepository.findById(showtimeDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Room room = roomRepository.findById(showtimeDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Showtime showtime = Showtime.builder()
                .start(showtimeDTO.getStart())
                .avail(showtimeDTO.isAvail())
                .movie(movie)
                .room(room)
                .build();

        return showtimeRepository.save(showtime);
    }


    private ShowtimeDTO convert(Showtime showtime) {
        return modelMapper.map(showtime, ShowtimeDTO.class);
    }

    private PriceBoardDTO convert(PriceBoard priceBoard) {
        return modelMapper.map(priceBoard, PriceBoardDTO.class);
    }
}
