package org.sopt.springPractice.auth.redis.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.springPractice.auth.PrincipalHandler;
import org.sopt.springPractice.auth.redis.service.TokenService;
import org.sopt.springPractice.auth.redis.service.dto.AccessTokenDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/token")
public class TokenController {

    private final TokenService tokenService;
    private final PrincipalHandler principalHandler;

    @PostMapping("/reissue")
    public ResponseEntity<AccessTokenDTO> reissueAccessToken() {
        Long userId = principalHandler.getUserIdFromPrincipal();
        AccessTokenDTO newAccessTokenResponse = tokenService.reissueAccessToken(userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newAccessTokenResponse);
    }
}
