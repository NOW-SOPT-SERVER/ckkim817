package org.sopt.springPractice.auth.redis.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.springPractice.auth.PrincipalHandler;
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
@RequestMapping("/api/v1/member")
public class ReissueAccessTokenController {

    private final org.sopt.springPractice.auth.redis.service.ReissueAccessTokenService reissueAccessTokenService;
    private final PrincipalHandler principalHandler;

    @PostMapping("/reissue-Token")
    public ResponseEntity<AccessTokenDTO> reissueAccessToken() {
        Long userId = principalHandler.getUserIdFromPrincipal();
        AccessTokenDTO newAccessTokenResponse = reissueAccessTokenService.reissueAccessToken(userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newAccessTokenResponse);
    }
}
