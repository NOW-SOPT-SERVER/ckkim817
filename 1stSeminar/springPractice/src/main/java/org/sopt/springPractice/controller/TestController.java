package org.sopt.springPractice.controller;

import org.sopt.springPractice.controller.dto.APIResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "1차 세미나 테스트 API 입니다!";
    }

    @GetMapping("/json")
    public APIResponse testJson() {
        return APIResponse.create("1차 세미나 테스트 API - JSON");
    }
}
