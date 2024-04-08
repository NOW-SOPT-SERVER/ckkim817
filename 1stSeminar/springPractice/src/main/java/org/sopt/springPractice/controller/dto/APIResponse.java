package org.sopt.springPractice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class APIResponse {
    private String content;

    public static APIResponse create(String content) {
        return new APIResponse(content);
    }
}
