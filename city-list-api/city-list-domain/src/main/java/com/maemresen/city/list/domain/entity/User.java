package com.maemresen.city.list.domain.entity;

import com.maemresen.city.list.domain.entity.base.BaseUuidEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER")
@Entity
public class User extends BaseUuidEntity {

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;

	private String userName;

	private String password;

	private String firstName;

	private String lastName;
}
