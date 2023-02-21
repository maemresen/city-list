package com.maemresen.city.list.domain.entity;

import com.maemresen.city.list.domain.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "REFRESH_TOKEN", schema = "PUBLIC")
public class RefreshToken extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "USER_ID", unique = true)
	private User user;

	@Column(nullable = false, unique = true)
    private UUID token;

    @Column(name= "EXPIRY_DATE", nullable = false)
    private LocalDateTime expiryDate;
}
