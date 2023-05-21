package com.cog.promotion.service;

import com.cog.promotion.domain.IsEligibleForPromotion;

public interface PromotionService {

	IsEligibleForPromotion isEligible(Integer employeeId);

}
