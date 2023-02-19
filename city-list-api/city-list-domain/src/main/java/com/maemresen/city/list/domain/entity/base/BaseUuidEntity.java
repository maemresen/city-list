package com.maemresen.city.list.domain.entity.base;

import com.maemresen.city.list.domain.util.EntityUtils;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseUuidEntity extends BaseEntity {

	@Column(unique = true, nullable = false)
	private UUID uuid;

	@PrePersist
	public void prePersist() {
		if (uuid == null) {
			setUuid(EntityUtils.generateUUID());
		}
	}
}
