package hsu.readme.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import static hsu.readme.exception.ErrorMessage.*;

@Getter
@ToString
public enum ExceptionEnum {
    DUPLICATE_USER_NAME_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCode.DUPLICATE_USER_NAME_ERROR, EXISTED_USER_NAME),
    DUPLICATE_USER_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCode.DUPLICATE_USER_EMAIL_ERROR, EXISTED_USER_EMAIL),
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E001"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E002"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S001"),
    SECURITY_01(HttpStatus.UNAUTHORIZED, "E002", "권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
