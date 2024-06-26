package com.lamnguyen.server.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeClassification {
    public static int classifyTime(String fromStr , String toStr) {
        LocalDateTime from = LocalDateTime.parse(fromStr);
        LocalDateTime to = LocalDateTime.parse(toStr);

        long daysBetween = ChronoUnit.DAYS.between(from, to);
        long weeksBetween = ChronoUnit.WEEKS.between(from, to);
        long monthsBetween = ChronoUnit.MONTHS.between(from, to);

        /*
        day is 0
        week is 1
        month is 2
         */

        if (monthsBetween > 0) {
           return 2;
        } else if (weeksBetween > 0) {
            return 1;
        }
        return 0;
    }
}
