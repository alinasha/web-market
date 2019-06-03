package com.vsu.webmarket.data.repositories;

import com.vsu.webmarket.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByToken(String token);

    Optional<User> findByLoginIgnoreCaseAndPassword(String login, String password);

    boolean existsByLoginIgnoreCase(String login);

    boolean existsByEmailIgnoreCase(String email);

    List<User> getAllByIdIsNotNull();
}
