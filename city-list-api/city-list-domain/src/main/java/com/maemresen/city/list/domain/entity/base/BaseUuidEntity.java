package com.maemresen.city.list.domain.entity.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BaseUuidEntity extends BaseEntity {


	private UUID uuid;

	@Override
	public void prePersist() {
		setUuid(UUID.randomUUID());
	}
}
