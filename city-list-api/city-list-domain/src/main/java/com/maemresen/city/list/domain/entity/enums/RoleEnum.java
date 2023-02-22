package com.maemresen.city.list.domain.entity.enums;

import com.maemresen.city.list.domain.util.constants.RoleNames;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {
	ROLE_ADMIN(RoleNames.ROLE_ADMIN),
	ROLE_ALLOW_EDIT(RoleNames.ROLE_ALLOW_EDIT),
	ROLE_READ_ONLY(RoleNames.ROLE_READ_ONLY);

	private final String value;
}
