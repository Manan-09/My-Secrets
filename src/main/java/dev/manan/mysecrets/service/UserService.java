package dev.manan.mysecrets.service;

import dev.manan.mysecrets.dto.CreateUserDTO;
import dev.manan.mysecrets.entity.User;
import dev.manan.mysecrets.repo.UserMongoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMongoRepo userMongoRepo;
    private final PasswordEncoder passwordEncoder;

    @Cacheable(value = "User", key = "#username")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMongoRepo.findByUsername(username).orElseThrow();
    }

    public User createUser(CreateUserDTO createUserDTO) {
        User user = User.from(createUserDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMongoRepo.insert(user);
    }
}
