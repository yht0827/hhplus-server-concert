package kr.hhplus.be.server.interfaces.token.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.hhplus.be.server.application.TokenFacade;
import kr.hhplus.be.server.interfaces.token.dto.TokenRequest;
import kr.hhplus.be.server.interfaces.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenFacade tokenFacade;

    @Operation(summary = "대기열 토큰 발급", description = "유저의 대기열 토큰을 발급합니다.", tags = {"Token API"})
    @PostMapping("/create")
    public ResponseEntity<TokenResponse> create(@RequestBody final TokenRequest tokenRequest) {
        TokenResponse response = TokenResponse.toDto(tokenFacade.createToken(tokenRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
