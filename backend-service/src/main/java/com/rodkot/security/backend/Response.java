package com.rodkot.security.backend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "Обертка над ответом, содержащая код ошибки и ее описание")
public class Response<T> {
    @Schema(description = "Код ошибки")
    ErrorCode errorCode;

    @Schema(description = "Описание ошибки")
    String errorMessage;

    @Schema(description = "Тело ответа")
    T data;

    public static <T> Response<T> withData(T data) {
        return new Response<>(null, null, data);
    }

    public static <T> Response<T> withError(ErrorCode errorCode, String errorMessage) {
        return new Response<>(errorCode, errorMessage, null);
    }

    public static Response<Void> withoutErrors() {
        return new Response<>(null, null, null);
    }
}
