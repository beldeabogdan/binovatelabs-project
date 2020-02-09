package com.beldeabogdan.binovate.factories;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ErrorResponseFactory {

    public static HttpClientErrorException genericNotFound() {
        return generic(HttpStatus.NOT_FOUND, "Resource not found");
    }

    public static HttpClientErrorException genericForbidden() {
        return generic(HttpStatus.FORBIDDEN, "Forbidden");
    }

    public static HttpClientErrorException genericUnprocessableEntity(){
        return generic(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable entity");
    }

    public static HttpClientErrorException generic(HttpStatus httpStatus, String statusText) {
        return HttpClientErrorException.create(
                httpStatus,
                statusText,
                new HttpHeaders(),
                null,
                null
        );
    }
}
