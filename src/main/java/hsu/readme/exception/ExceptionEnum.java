package hsu.readme.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import static hsu.readme.exception.ErrorMessage.*;

@Getter
@ToString
public enum ExceptionEnum {
    //User SignUp
    DUPLICATE_USER_NAME_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCode.DUPLICATE_USER_NAME_ERROR, EXISTED_USER_NAME),
    DUPLICATE_USER_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCode.DUPLICATE_USER_EMAIL_ERROR, EXISTED_USER_EMAIL),
    EMPTY_USER_UNIVERSITY_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCode.EMPTY_USER_UNIVERSITY_ERROR, EMPTY_USER_UNIVERSITY),
    EMPTY_USER_MAJOR_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCode.EMPTY_USER_MAJOR_ERROR, EMPTY_USER_MAJOR),
    USER_NOT_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCode.USER_NOT_EXIST_ERROR, USER_NOT_EXIST),
    USER_ALREADY_DOC_LIKE(HttpStatus.BAD_REQUEST, ErrorCode.USER_ALREADY_DOC_LIKE_ERROR, USER_DOC_LIKE),

    LOGIN_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCode.LOGIN_EMAIL_ERROR, WRONG_EMAIL),
    LOGIN_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCode.LOGIN_PASSWORD_ERROR, WRONG_PASSWORD),
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E001"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E002"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E001"),
    SECURITY_01(HttpStatus.UNAUTHORIZED, "E002", "권한이 없습니다."),
    METHODARGUMENTNOTVALIDEXCEPTION(HttpStatus.BAD_REQUEST, "EV001", NOT_VALIDATE_FORM),

    RECRUIT_NOT_EXISTED(HttpStatus.BAD_REQUEST, ErrorCode.RECRUIT_NOT_EXIST_ERROR, RECRUIT_NOT_EXIST);

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
