package dev.manan.mysecrets.repo;

import dev.manan.mysecrets.entity.Secret;
import dev.manan.mysecrets.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecretRepo extends MongoRepository<Secret, String> {
    Optional<Secret> findByUsername(String username);
}
