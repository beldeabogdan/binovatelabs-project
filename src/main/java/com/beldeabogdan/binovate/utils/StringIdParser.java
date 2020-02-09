package com.beldeabogdan.binovate.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class StringIdParser {
    public static Integer parse(String s) {
        if (null == s) {
            throw HttpClientErrorException.create(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Unprocessable entity",
                    new HttpHeaders(),
                    null,
                    null
            );
        }

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw HttpClientErrorException.create(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Unprocessable entity",
                    new HttpHeaders(),
                    null,
                    null
            );
        }
    }
}
