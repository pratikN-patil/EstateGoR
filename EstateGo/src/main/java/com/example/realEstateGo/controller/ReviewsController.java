package com.example.realEstateGo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.realEstateGo.entity.Reviews;
import com.example.realEstateGo.exception.ResourceNotFoundException;
import com.example.realEstateGo.service.ReviewsService;
import com.example.realEstateGo.service.impl.ReviewsServiceImpl;

import jakarta.validation.Valid;

@RestController
public class ReviewsController {
	@Autowired
	private ReviewsService reviewsService;

	@PostMapping("/reviews")
	public ResponseEntity<Object> addNewReview(@Valid @RequestBody Reviews review) {
		try {
			Reviews addedReview = reviewsService.addNewReviews(review);
			return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to add review: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<Object> updateReview(@PathVariable int reviewId, @Valid @RequestBody Reviews updatedReview) {
		try {
			Reviews review = reviewsService.updateReview(reviewId, updatedReview);
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			Map<String, String> map = new HashMap<>();
			map.put("errorMessage", ex.getMessage());
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/reviews/view")
	public List<String> getAllReviews() {
		return reviewsService.getAllReviewss();
	}

	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Object> getReviewById(@PathVariable int reviewId) throws ResourceNotFoundException {
		Reviews review = reviewsService.findReviewsById(reviewId);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

}
