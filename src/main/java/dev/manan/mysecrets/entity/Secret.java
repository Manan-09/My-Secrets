package dev.manan.mysecrets.entity;

import dev.manan.mysecrets.dto.SecretRequestDTO;
import dev.manan.mysecrets.dto.SecretResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
//@RedisHash("Secret")
public class Secret extends AuditableEntity{
    @Id
    private String username;
    private Map<String,String> secretsEncoded;
    @Transient
    private Map<String, String> secretDecoded;

    public static Secret from(SecretRequestDTO secretRequestDTO) {
        Secret secret = new Secret();
        secret.setSecretDecoded(secretRequestDTO.getSecretDecoded());
        secret.initializeAuditFields();
        return secret;
    }

    public SecretResponseDTO toResponse() {
        SecretResponseDTO secretResponseDTO = new SecretResponseDTO();
        secretResponseDTO.setUsername(this.username);
        secretResponseDTO.setSecretDecoded(this.secretDecoded);
        secretResponseDTO.setCreatedTime(this.createdTime);
        secretResponseDTO.setUpdatedTime(this.updatedTime);
        return secretResponseDTO;
    }
}
