package dev.manan.mysecrets.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditableEntity implements Serializable {
    protected Long createdTime;
    protected Long updatedTime;

    public void initializeAuditFields() {
        Long currentTime = System.currentTimeMillis();
        if(isNull(createdTime)) {
            createdTime = currentTime;
        }
        updatedTime = currentTime;
    }

    public static final String UPDATED_TIME = "updatedTime";
}
