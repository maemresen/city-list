package com.maemresen.city.list.domain.entity;

import com.maemresen.city.list.domain.entity.base.BaseUuidEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseUuidEntity {

	private String name;
}
