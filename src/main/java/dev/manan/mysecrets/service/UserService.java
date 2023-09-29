package dev.manan.mysecrets.service;

import dev.manan.mysecrets.entity.User;
import dev.manan.mysecrets.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    User fetchUserByUsername(String userName) {
        return userRepo.findByUsername(userName).orElseThrow();
    }
}
