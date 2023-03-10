package com.maemresen.city.list.domain.entity;

import com.maemresen.city.list.domain.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(schema = "PUBLIC", name = "CITY")
@Entity
public class City extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@OneToOne(orphanRemoval = true)
	@JoinColumn(name = "PHOTO_FILE_ID")
	private File photoFile;

	@OneToMany(mappedBy = "commentedCity")
	private List<CityComment> comments;

}
