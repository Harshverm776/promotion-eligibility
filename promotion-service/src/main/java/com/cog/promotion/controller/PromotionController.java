package com.cog.promotion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cog.promotion.domain.IsEligibleForPromotion;
import com.cog.promotion.service.PromotionService;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public ResponseEntity<IsEligibleForPromotion> isEligible(@RequestParam Integer employeeId) {
		return new ResponseEntity<>(promotionService.isEligible(employeeId), HttpStatus.OK);
	}
}
