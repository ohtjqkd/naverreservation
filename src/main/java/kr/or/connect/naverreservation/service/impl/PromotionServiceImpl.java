package kr.or.connect.naverreservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.naverreservation.dao.ProductDao;
import kr.or.connect.naverreservation.dao.PromotionDao;
import kr.or.connect.naverreservation.dto.Promotion;
import kr.or.connect.naverreservation.dto.PromotionResponse;
import kr.or.connect.naverreservation.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	
	@Autowired
	PromotionDao promotionDao;
	
	@Autowired
	ProductDao productDao;
	
	@Override
	@Transactional
	public PromotionResponse getPromotionResponse() {
		PromotionResponse promotionResponse =  new PromotionResponse();
		List<Promotion> promotions = promotionDao.selectPromotions();
		for(Promotion promotion : promotions) {
			Integer productId = promotion.getProductId();
			String productImageUrl = productDao.selectProductImageUrl(productId);
			promotion.setProductImageUrl(productImageUrl);
		}
		promotionResponse.setItems(promotions);
		return promotionResponse;
	}

}
