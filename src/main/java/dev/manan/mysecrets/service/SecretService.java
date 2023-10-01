package dev.manan.mysecrets.service;

import dev.manan.mysecrets.dto.SecretRequestDTO;
import dev.manan.mysecrets.dto.SecretResponseDTO;
import dev.manan.mysecrets.entity.Secret;
import dev.manan.mysecrets.repo.SecretMongoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class SecretService {

    private final EncryptionService encryptionService;
    private final SecretMongoRepo secretMongoRepo;

    @CacheEvict(value = "SecretResponseDTO", key = "#username")
    public SecretResponseDTO saveSecret(String username, SecretRequestDTO secretRequestDTO) {
        Secret secret = fetchSecretRaw(username);
        if( nonNull(secret) ) {
            secret.setSecretDecoded(secretRequestDTO.getSecretDecoded());
        } else {
            secret = Secret.from(secretRequestDTO);
            secret.setUsername(username);
        }
        secret.initializeAuditFields();
        secret.setSecretsEncoded(encodeData(secret.getSecretDecoded()));
        secretMongoRepo.save(secret);
        return secret.toResponse();
    }

    @Cacheable(value = "SecretResponseDTO", key = "#username")
    public SecretResponseDTO fetchSecret(String username) {
        Secret secret = secretMongoRepo.findByUsername(username).orElseThrow();
        secret.setSecretDecoded(decodeData(secret.getSecretsEncoded()));
        return secret.toResponse();
    }

    private Secret fetchSecretRaw(String username) {
        return secretMongoRepo.findByUsername(username).orElse(null);
    }

    private Map<String, String> encodeData(Map<String, String> secretData) {
        Map<String ,String> encodedData = new HashMap<>();
        for (Map.Entry<String, String> entry : secretData.entrySet()) {
            encodedData.put(encryptionService.encrypt(entry.getKey()), encryptionService.encrypt(entry.getValue()));
        }
        return encodedData;
    }

    private Map<String, String> decodeData(Map<String, String> secretData) {
        Map<String ,String> decodedData = new HashMap<>();
        for (Map.Entry<String, String> entry : secretData.entrySet()) {
            decodedData.put(encryptionService.decrypt(entry.getKey()), encryptionService.decrypt(entry.getValue()));
        }
        return decodedData;
    }

}
