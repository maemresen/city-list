package com.maemresen.city.list.domain.entity;

import com.maemresen.city.list.domain.entity.base.BaseUuidEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@Table(schema = "PUBLIC", name = "FILE")
@Entity
public class File extends BaseUuidEntity {
}
