package com.maemresen.city.list.domain.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseUuidEntity extends BaseEntity {

	@Column(unique = true, nullable = false)
	private UUID uuid;

	@PrePersist
	public void prePersist() {
		setUuid(UUID.randomUUID());
	}
}
