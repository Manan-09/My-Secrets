package dev.manan.mysecrets.dto;

import dev.manan.mysecrets.entity.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretResponseDTO extends AuditableEntity {
    private String username;
    private Map<String, String> secretDecoded;
}
