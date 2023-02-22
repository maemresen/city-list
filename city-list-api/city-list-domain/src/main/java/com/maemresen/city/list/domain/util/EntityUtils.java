package com.maemresen.city.list.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Utility methods for Entity related operations
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityUtils {

	/**
	 * Unique random UUID generator method
	 *
	 * @return a unique random UUID value
	 */
	public static UUID generateUUID() {
		return UUID.randomUUID();
	}
}
