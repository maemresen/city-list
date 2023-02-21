package com.maemresen.city.list.domain.entity;

import com.maemresen.city.list.domain.entity.base.BaseUuidEntity;
import jakarta.persistence.Column;
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
@Table(schema = "PUBLIC", name = "USER")
@Entity
public class User extends BaseUuidEntity {

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;

	@Column(name = "USERNAME")
	private String username;

	private String password;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;
}
