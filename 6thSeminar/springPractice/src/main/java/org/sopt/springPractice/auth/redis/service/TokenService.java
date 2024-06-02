package org.sopt.springPractice.auth.redis.service;

import lombok.RequiredArgsConstructor;
import org.sopt.springPractice.auth.redis.domain.Token;
import org.sopt.springPractice.auth.redis.repository.RedisTokenRepository;
import org.sopt.springPractice.auth.redis.service.dto.AccessTokenDTO;
import org.sopt.springPractice.common.dto.ErrorMessage;
import org.sopt.springPractice.common.jwt.JwtTokenProvider;
import org.sopt.springPractice.common.jwt.JwtValidationType;
import org.sopt.springPractice.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RedisTokenRepository redisTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AccessTokenDTO reissueAccessToken(Long userId) {
        Token token = redisTokenRepository.findById(userId).orElseThrow(
                () -> new UnauthorizedException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND)
        );

        JwtValidationType validationType = jwtTokenProvider.validateToken(token.getRefreshToken());

        if (validationType == JwtValidationType.EXPIRED_JWT_TOKEN) {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);
        } else if (validationType != JwtValidationType.VALID_JWT) {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);
        }

        String newAccessToken = jwtTokenProvider.newAccessToken(token.getRefreshToken());

        return AccessTokenDTO.of(newAccessToken);
    }
}
