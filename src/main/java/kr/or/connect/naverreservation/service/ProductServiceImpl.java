package kr.or.connect.naverreservation.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.naverreservation.dao.ProductDao;
import kr.or.connect.naverreservation.dto.Comment;
import kr.or.connect.naverreservation.dto.DisplayInfo;
import kr.or.connect.naverreservation.dto.DisplayInfoResponse;
import kr.or.connect.naverreservation.dto.Product;
import kr.or.connect.naverreservation.dto.ProductResponse;
import kr.or.connect.naverreservation.dto.PromotionResponse;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;
	
	@Override
	@Transactional
	public DisplayInfo getDisplayInfo(Integer displayInfoId) {
		return productDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	@Transactional
	public List<Comment> getComments(Integer displayInfoId) {
		return productDao.selectComments(displayInfoId);
	}
	
	@Override
	@Transactional
	public String getExtraImg(Integer displayInfoId, Integer start) {
		if(!productDao.hasSaveFile(displayInfoId,start)) return null;
		
		return productDao.selectEtcSaveFileName(displayInfoId,start);
	}

	@Override
	@Transactional
	public DisplayInfoResponse getDisplayInfoResponse(Integer displayInfoId) {
		
		return productDao.getDisplayInfoResponse(displayInfoId);
	}

	@Override
	@Transactional
	public ProductResponse getProductResponse(Integer categoryId, Integer start) {
		ProductResponse productResponse = new ProductResponse();
		List<Product> products = productDao.selectProducts(categoryId, start);
		Integer totalCount = productDao.countProducts(categoryId);
		productResponse.setItems(products);
		productResponse.setTotalCount(totalCount);
		return productResponse;
	}
	
	
	

	@Override
	public Product getProducts(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PromotionResponse getPromotionResponse() {
			
		return productDao.getPromotionResponse();
	}

	@Override
	public void init() {
		
		
	}


}
