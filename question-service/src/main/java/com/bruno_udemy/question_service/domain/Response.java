package com.bruno_udemy.question_service.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {

    private Long id;
    private String response;

}
