package dev.manan.mysecrets.repo;

import dev.manan.mysecrets.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoRepo extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
