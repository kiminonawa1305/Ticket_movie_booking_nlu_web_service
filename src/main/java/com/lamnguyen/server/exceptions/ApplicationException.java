package com.lamnguyen.server.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationException extends RuntimeException {
    private final ErrorCode errorCode;

    public ApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public static record ErrorCode(int code, String message) {
        public static final ErrorCode USER_NON_EXIST = new ErrorCode(404, "Tài khoản không tồn tại");
        public static final ErrorCode USER_IS_BLOCKED = new ErrorCode(405, "Tài khoản đã bị khóa");
        public static final ErrorCode ID_NON_EXIST = new ErrorCode(404, "Mã phim không tồn tại");
        public static final ErrorCode MOVIE_EXIST = new ErrorCode(404, "Phim đã tồn tại");
        public static final ErrorCode MOVIE_NOT_FOUND = new ErrorCode(404, "Phim không tồn tại");
        public static final ErrorCode ROOM_NON_EXIST = new ErrorCode(404, "Phòng không tồn tại");
        public static final ErrorCode SHOWTIME_CONFLICT = new ErrorCode(404, "Xung đột lịch chiếu");
    }
}

