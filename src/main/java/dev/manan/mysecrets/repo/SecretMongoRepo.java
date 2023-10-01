package dev.manan.mysecrets.repo;

import dev.manan.mysecrets.entity.Secret;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecretMongoRepo extends MongoRepository<Secret, String> {
    Optional<Secret> findByUsername(String username);
}
