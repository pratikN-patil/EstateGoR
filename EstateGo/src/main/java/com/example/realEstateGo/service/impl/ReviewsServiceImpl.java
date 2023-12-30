package com.example.realEstateGo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realEstateGo.entity.Customer;
import com.example.realEstateGo.entity.Property;
import com.example.realEstateGo.entity.Reviews;
import com.example.realEstateGo.exception.ResourceNotFoundException;
import com.example.realEstateGo.repository.CustomerRepository;
import com.example.realEstateGo.repository.PropertyRepository;
import com.example.realEstateGo.repository.ReviewsRepository;
import com.example.realEstateGo.service.ReviewsService;

@Service
public class ReviewsServiceImpl implements ReviewsService {
	@Autowired
	ReviewsRepository reviewsRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	PropertyRepository propertyRepository;

	@Override
    public Reviews addNewReviews(Reviews r) {
        Customer c1 = customerRepository.findById(r.getReviewsCustomerId()).orElse(null);
        Property p1 = propertyRepository.findById(r.getReviewsPropertyId()).orElse(null);

        if (c1 != null && p1 != null) {
            r.setCustomer(c1);
            r.setProperty(p1);
            return reviewsRepository.save(r);
        } else {
            return null;
        }
    }

	@Override
    public Reviews updateReview(int reviewsId, Reviews updatedReview) throws ResourceNotFoundException {
        Reviews existingReview = reviewsRepository.findById(reviewsId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", " id", reviewsId));

        existingReview.setComment(updatedReview.getComment());
        existingReview.setRating(updatedReview.getRating());
       
        return reviewsRepository.save(existingReview);
    }

	@Override
	public List<String> getAllReviewss() {
	    List<Reviews> reviewsList = reviewsRepository.findAll();
	    
	    return reviewsList.stream()
	        .map(Reviews::getComment)
	        .collect(Collectors.toList());
	}


	@Override
    public Reviews findReviewsById(int reviewId) throws ResourceNotFoundException {
        Optional<Reviews> reviewOptional = reviewsRepository.findById(reviewId);

        if (reviewOptional.isPresent()) {
            return reviewOptional.get();
        } else {
            throw new ResourceNotFoundException("Review", " id", reviewId);
        }
    }

	

}
