package com.maemresen.city.list.domain.service.repository;

import com.maemresen.city.list.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUuid(UUID uuid);

	Optional<User> findByUsername(String username);

}
