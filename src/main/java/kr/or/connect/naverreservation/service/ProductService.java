package kr.or.connect.naverreservation.service;

import java.util.List;

import kr.or.connect.naverreservation.dto.Comment;
import kr.or.connect.naverreservation.dto.DisplayInfo;
import kr.or.connect.naverreservation.dto.DisplayInfoResponse;
import kr.or.connect.naverreservation.dto.Product;
import kr.or.connect.naverreservation.dto.ProductResponse;
import kr.or.connect.naverreservation.dto.PromotionResponse;


public interface ProductService {
	public void init();
	
	public DisplayInfoResponse getDisplayInfoResponse(Integer displayInfoId);
	
	public ProductResponse getProductResponse(Integer categoryId, Integer start);

	public PromotionResponse getPromotionResponse();

	public DisplayInfo getDisplayInfo(Integer displayInfoId);

	public List<Comment> getComments(Integer displayInfoId);
	
	public String getExtraImg(Integer displayInfoId,Integer start);
	
	public Product getProducts(Integer categoryId);


}
