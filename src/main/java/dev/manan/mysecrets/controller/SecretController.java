package dev.manan.mysecrets.controller;

import dev.manan.mysecrets.dto.SecretRequestDTO;
import dev.manan.mysecrets.dto.SecretResponseDTO;
import dev.manan.mysecrets.entity.Secret;
import dev.manan.mysecrets.service.SecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
public class SecretController {

    private final SecretService secretService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/secrets")
    public ResponseEntity<SecretResponseDTO> saveSecret(@RequestBody SecretRequestDTO secretRequestDTO) {
        return ResponseEntity.ok(secretService.saveSecret(secretRequestDTO));
    }

    @GetMapping("/secrets/{username}")
    public ResponseEntity<SecretResponseDTO> saveSecret(@PathVariable String username) {
        return ResponseEntity.ok(secretService.fetchSecret(username));
    }
}
