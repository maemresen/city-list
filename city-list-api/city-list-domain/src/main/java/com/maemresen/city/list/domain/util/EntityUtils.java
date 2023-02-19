package com.maemresen.city.list.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityUtils {

	public static UUID generateUUID(){
		return UUID.randomUUID();
	}
}
