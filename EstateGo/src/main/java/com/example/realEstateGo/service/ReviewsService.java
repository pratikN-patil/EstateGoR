package com.example.realEstateGo.service;

import java.util.List;

import com.example.realEstateGo.entity.Reviews;
import com.example.realEstateGo.exception.ResourceNotFoundException;

public interface ReviewsService {
	public Reviews addNewReviews(Reviews b);

	Reviews updateReview(int reviewId, Reviews updatedReview) throws ResourceNotFoundException;

	public List<String> getAllReviewss();

	public Reviews findReviewsById(int id) throws ResourceNotFoundException;

}
