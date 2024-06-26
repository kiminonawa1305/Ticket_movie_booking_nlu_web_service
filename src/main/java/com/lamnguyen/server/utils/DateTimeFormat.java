package com.lamnguyen.server.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeFormat {
    public static LocalDateTime generateStartDate(String dateStr) {
        LocalDate date;
        if (dateStr != null) date = LocalDate.parse(dateStr);
        else date = LocalDate.now();
        if (date.equals(LocalDate.now()))
            return LocalDateTime.now().plusMinutes(-5);
        else return LocalDateTime.of(date, LocalTime.MIN);
    }

    public static LocalDateTime convertStringToLocalDateTime(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(dateStr, formatter);
    }
}
