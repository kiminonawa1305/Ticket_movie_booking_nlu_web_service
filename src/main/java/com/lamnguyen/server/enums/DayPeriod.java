package com.lamnguyen.server.enums;

import java.time.LocalDateTime;
import java.time.LocalTime;

public enum DayPeriod {
    MORNING(LocalTime.of(5, 0), LocalTime.of(9, 59)),  // Từ 5:00 sáng đến 9:59 sáng
    NOON(LocalTime.of(10, 0), LocalTime.of(12, 59)),    // Từ 10:00 trưa đến 12:59 trưa
    AFTERNOON(LocalTime.of(13, 0), LocalTime.of(17, 59)), // Từ 1:00 chiều đến 5:59 chiều
    EVENING(LocalTime.of(18, 0), LocalTime.of(21, 59)),   // Từ 6:00 tối đến 9:59 tối
    NIGHT(LocalTime.of(22, 0), LocalTime.of(4, 59));      // Từ 10:00 tối đến 4:59 sáng hôm sau

    private final LocalTime startTime;
    private final LocalTime endTime;

    DayPeriod(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isWithinPeriod(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();
        if (this == NIGHT) {
            // Đối với NIGHT, cần xử lý khoảng thời gian qua nửa đêm
            return (time.isAfter(startTime) || time.equals(startTime)) ||
                    (time.isBefore(endTime) || time.equals(endTime));
        }
        return (time.isAfter(startTime) || time.equals(startTime)) &&
                (time.isBefore(endTime) || time.equals(endTime));
    }

    public static DayPeriod getPeriod(LocalDateTime dateTime) {
        for (DayPeriod period : values()) {
            if (period.isWithinPeriod(dateTime)) {
                return period;
            }
        }
        throw new IllegalArgumentException("Thời gian không hợp lệ: " + dateTime);
    }

    @Override
    public String toString() {
        return name() + " (" + startTime + " - " + endTime + ")";
    }
}
