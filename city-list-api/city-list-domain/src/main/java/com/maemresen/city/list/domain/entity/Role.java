package com.maemresen.city.list.domain.entity;

import com.maemresen.city.list.domain.entity.base.BaseEntity;
import com.maemresen.city.list.domain.entity.enums.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(schema = "PUBLIC", name = "ROLE")
@Entity
public class Role extends BaseEntity {

	@Column(unique = true, nullable = false)
	@Enumerated(value = EnumType.STRING)
	private RoleEnum name;
}
