package com.maemresen.city.list.domain.service.model.prop.security.path;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class PathAllowStatus {
	private String[] all;
	private Map<HttpMethod, String[]> byHttpMethod;
}
