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
    }
}

