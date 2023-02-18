package com.maemresen.city.list.domain.entity;

import com.maemresen.city.list.domain.entity.base.BaseUuidEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "ROLE")
@Entity
public class Role extends BaseUuidEntity {

	@Column(unique = true, nullable = false)
	private String name;
}
