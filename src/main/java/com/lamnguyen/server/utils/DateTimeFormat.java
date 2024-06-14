package com.lamnguyen.server.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeFormat {
    public static LocalDateTime generateStartDate(String dateStr) {
        LocalDate date;
        if (dateStr != null) date = LocalDate.parse(dateStr);
        else date = LocalDate.now();
        if (date.equals(LocalDate.now()))
            return LocalDateTime.now().plusMinutes(-5);
        else return LocalDateTime.of(date, LocalTime.MIN);
    }
}
