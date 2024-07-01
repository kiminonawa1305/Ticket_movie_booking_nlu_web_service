package com.lamnguyen.server.services.impl;

import com.google.gson.Gson;
import com.lamnguyen.server.enums.ChairStatus;
import com.lamnguyen.server.exceptions.ApplicationException;
import com.lamnguyen.server.models.dto.*;
import com.lamnguyen.server.models.entity.*;
import com.lamnguyen.server.models.response.MovieDetailResponse;
import com.lamnguyen.server.models.response.MovieDetailResponseRestApi;
import com.lamnguyen.server.models.response.ShowtimeManagerResponse;
import com.lamnguyen.server.repositories.RoomRepository;
import com.lamnguyen.server.repositories.ShowtimeRepository;
import com.lamnguyen.server.services.ChairShowtimeService;
import com.lamnguyen.server.services.MovieDetailService;
import com.lamnguyen.server.services.MovieService;
import com.lamnguyen.server.services.ShowtimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private final MovieDetailService movieDetailService;
    private final ChairShowtimeService chairShowtimeService;
    @Autowired
    private ModelMapper modelMapper;

    public ShowtimeServiceImpl() {
        this.movieDetailService = new MovieDetailServiceImpl();
        this.chairShowtimeService = new ChairShowtimeServiceImpl();
    }

    @Override
    public List<Showtime> getAllShowtime() {
        return showtimeRepository.findAll();
    }

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
            String data = restTemplate.getForObject(MovieService.URL_API + movie.getIdApi(), String.class);
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
    public ShowtimeManagerResponse addShowtime(ShowtimeDTO showtimeDTO) {
        MovieDetailResponse movieDetailResponse = movieDetailService.getMovieDetail(0, showtimeDTO.getMovieId(), showtimeDTO.getStart().toString());
        Room room = roomRepository.findById(showtimeDTO.getRoomId())
                .orElseThrow(() -> new ApplicationException(ApplicationException.ErrorCode.ROOM_NON_EXIST));

        int durationMovie = Integer.parseInt(movieDetailResponse.getDuration().replace(" min", ""));
        LocalDateTime timeCheck = showtimeDTO.getStart().plusMinutes(-durationMovie - 10);

        List<Showtime> showtimeList = showtimeRepository.findByRoom_IdAndAvailIsTrueAndMovie_IdAndStartLessThanEqual(showtimeDTO.getRoomId(), showtimeDTO.getMovieId(), timeCheck);
        if (!showtimeList.isEmpty())
            throw new ApplicationException(ApplicationException.ErrorCode.SHOWTIME_CONFLICT);

        Showtime showtime = Showtime.builder()
                .start(showtimeDTO.getStart())
                .avail(true)
                .movie(Movie.builder().id(showtimeDTO.getMovieId()).build())
                .room(room)
                .build();
        showtime = showtimeRepository.save(showtime);

        List<ChairShowtimeDTO> chairShowtimeDTOS = chairShowtimeService.addChairShowtime(showtime.getId(), room);

        return ShowtimeManagerResponse.builder()
                .id(showtime.getId())
                .roomId(room.getId())
                .movieId(showtimeDTO.getMovieId())
                .movieName(movieDetailResponse.getTitle())
                .roomName(room.getName())
                .start(showtime.getStart())
                .end(showtime.getStart().plusMinutes(durationMovie))
                .duration(durationMovie)
                .totalSear(chairShowtimeDTOS.size())
                .totalSeatAvailable(chairShowtimeDTOS.size())
                .build();
    }

    private ShowtimeDTO convert(Showtime showtime) {
        return modelMapper.map(showtime, ShowtimeDTO.class);
    }

    private PriceBoardDTO convert(PriceBoard priceBoard) {
        return modelMapper.map(priceBoard, PriceBoardDTO.class);
    }
}
