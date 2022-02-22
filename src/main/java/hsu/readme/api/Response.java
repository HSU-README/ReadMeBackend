package hsu.readme.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.annotation.Order;

@Data
@AllArgsConstructor
@Builder
@JsonSerialize
public class Response<T> {
    protected String code;
    protected String message;
    @JsonProperty("result")
    protected T result;

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
        this.result = null;
    }

    public static<T> Response<T> response(String code, String message){
        return response(code, message, null);
    }

    public static<T> Response<T> response(String code, String message, T result){
        return Response.<T>builder()
                .code(code)
                .message(message)
                .result(result)
                .build();
    }
}
