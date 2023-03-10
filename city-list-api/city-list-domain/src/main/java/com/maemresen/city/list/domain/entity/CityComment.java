package com.maemresen.city.list.domain.entity;

import com.maemresen.city.list.domain.entity.base.BaseEntity;
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
@Table(schema = "PUBLIC", name = "CITY_COMMENT")
@Entity
public class CityComment extends BaseEntity {


	@ManyToOne
	@JoinColumn(name = "commenter_user_id")
	private User commentUser;

	@ManyToOne
	@JoinColumn(name = "commented_city_id")
	private City commentedCity;
}
