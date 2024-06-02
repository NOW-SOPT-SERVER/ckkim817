package org.sopt.springPractice.auth.redis.service.dto;

public record AccessTokenDTO(
        String accessToken
) {
    public static AccessTokenDTO of(String accessToken) {
        return new AccessTokenDTO(accessToken);
    }
}
