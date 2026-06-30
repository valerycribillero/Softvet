package com.reviews.reviews_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reviews.reviews_service.model.Reviews;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {


}
