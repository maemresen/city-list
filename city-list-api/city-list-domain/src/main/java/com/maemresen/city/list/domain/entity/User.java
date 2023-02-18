package com.maemresen.city.list.domain.entity;

import com.maemresen.city.list.domain.entity.base.BaseUuidEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User extends BaseUuidEntity {

	private Role role;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
}
