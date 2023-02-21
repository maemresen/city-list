package com.maemresen.city.list.domain.service.repository;

import com.maemresen.city.list.domain.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByToken(UUID token);

	void deleteByUserId(Long userId);
}
