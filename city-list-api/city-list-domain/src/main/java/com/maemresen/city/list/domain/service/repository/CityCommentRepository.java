package com.maemresen.city.list.domain.service.repository;

import com.maemresen.city.list.domain.entity.CityComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityCommentRepository extends JpaRepository<CityComment, Long> {
}
