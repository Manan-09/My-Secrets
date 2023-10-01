package dev.manan.mysecrets.controller;

import dev.manan.mysecrets.dto.SecretRequestDTO;
import dev.manan.mysecrets.dto.SecretResponseDTO;
import dev.manan.mysecrets.entity.Secret;
import dev.manan.mysecrets.service.SecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static dev.manan.mysecrets.utils.Constants.USERNAME;

@RestController
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
public class SecretController {

    private final SecretService secretService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello(HttpServletResponse response) {
        return ResponseEntity.ok("Hello"+response.getHeader(USERNAME));
    }

    @PostMapping("/secrets")
    public ResponseEntity<SecretResponseDTO> saveSecret(@RequestBody SecretRequestDTO secretRequestDTO, HttpServletResponse response) {
        return ResponseEntity.ok(secretService.saveSecret(response.getHeader(USERNAME), secretRequestDTO));
    }

    @GetMapping("/secrets")
    public ResponseEntity<SecretResponseDTO> saveSecret(HttpServletResponse response) {
        return ResponseEntity.ok(secretService.fetchSecret(response.getHeader(USERNAME)));
    }
}
