package kr.or.connect.naverreservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.naverreservation.dto.Comment;
import kr.or.connect.naverreservation.dto.CommentImage;
import kr.or.connect.naverreservation.dto.DetailDto;
import kr.or.connect.naverreservation.dto.DisplayInfo;
import kr.or.connect.naverreservation.dto.DisplayInfoImage;
import kr.or.connect.naverreservation.dto.DisplayInfoResponse;
import kr.or.connect.naverreservation.dto.Product;
import kr.or.connect.naverreservation.dto.ProductImage;
import kr.or.connect.naverreservation.dto.ProductPrice;
import kr.or.connect.naverreservation.dto.ProductResponse;
import kr.or.connect.naverreservation.dto.Promotion;
import kr.or.connect.naverreservation.dto.PromotionResponse;

import static kr.or.connect.naverreservation.dao.ProductDaoSqls.*;

@Repository
public class ProductDao {
	NamedParameterJdbcTemplate jdbc;
	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	//display method//
	public DisplayInfoResponse getDisplayInfoResponse(Integer displayInfoId) {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		double totalScore = 0;
		double averageScore = 0;
		Integer commentsSize;
		DisplayInfo displayInfo = selectDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = selectDisplayInfoImage(displayInfoId);
		
		Integer productId = displayInfo.getProductId();
		List<Comment> comments = selectComments(productId);
		List<ProductPrice> productPrices = selectProductPrices(productId);
		List<ProductImage> productImages = selectProductImages(productId);
		
		commentsSize = comments.size();
		for(Comment comment:comments) {
			totalScore += comment.getScore();
		}
		System.out.println(productImages.get(0).getSaveFileName());
		if(commentsSize != 0) {
			averageScore = totalScore/commentsSize;
		}
		//test
		for(ProductImage pi:productImages) {
			System.out.println(pi.getSaveFileName());
		}
		
		displayInfoResponse.setAverageScore(Double.parseDouble(String.format("%.1f",averageScore)));
		displayInfoResponse.setComments(comments);
		displayInfoResponse.setDisplayInfo(displayInfo);
		displayInfoResponse.setDisplayInfoImage(displayInfoImage);;
		displayInfoResponse.setProductImages(productImages);
		displayInfoResponse.setProductPrices(productPrices);
		return displayInfoResponse;
	}
	public DisplayInfo selectDisplayInfo(Integer displayInfoId) {
		RowMapper<DisplayInfo> displayInfoMapper = new BeanPropertyRowMapper<DisplayInfo>(DisplayInfo.class);
		DisplayInfo displayInfo = jdbc.queryForObject(SELECT_DISPLAY_INFO, Collections.singletonMap("displayInfoId",displayInfoId), displayInfoMapper);
		System.out.println("displayInfoId="+displayInfo.getId());
		return jdbc.queryForObject(SELECT_DISPLAY_INFO, Collections.singletonMap("displayInfoId",displayInfoId), displayInfoMapper);
	}
	
	public List<ProductImage> selectProductImages(Integer productId){
		RowMapper<ProductImage> productImageMapper = new BeanPropertyRowMapper<ProductImage>(ProductImage.class);
		return jdbc.query(SELECT_PRODUCTIMAGES_BY_PRODUCT_ID, Collections.singletonMap("productId", productId), productImageMapper);
	}

	public List<Comment> selectComments(Integer productId) {
		RowMapper<Comment> commentMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
		List<Comment> comments = jdbc.query(SELECT_COMMENTS_BY_PRODUCT_ID, Collections.singletonMap("productId", productId), commentMapper);
		
		for(Comment comment: comments) {
			Integer commentId = comment.getCommentId();
			comment.setCommentImages(selectCommentImages(commentId));
			System.out.println(comment.getScore());
		}
		return comments; 
	}
	
	public List<CommentImage> selectCommentImages(Integer commentId){
		RowMapper<CommentImage> commentImageMapper = new BeanPropertyRowMapper<CommentImage>(CommentImage.class);
		return jdbc.query(SELECT_COMMENTIMAGES_BY_COMMENT_ID, Collections.singletonMap("commentId", commentId), commentImageMapper);
	}
	
	public List<ProductPrice> selectProductPrices(Integer productId){
		RowMapper<ProductPrice> productPriceMapper = new BeanPropertyRowMapper<ProductPrice>(ProductPrice.class);
		return jdbc.query(SELECT_PRODUCTPRICES_BY_PRODUCT_ID, Collections.singletonMap("productId", productId), productPriceMapper);
	}
	
	public DisplayInfoImage selectDisplayInfoImage(Integer displayInfoId) {
		RowMapper<DisplayInfoImage> displayInfoImageMapper = new BeanPropertyRowMapper<DisplayInfoImage>(DisplayInfoImage.class);
		return jdbc.queryForObject(SELECT_DISPLAYINFOIMAGE_BY_DISPLAY_ID, Collections.singletonMap("displayInfoId", displayInfoId), displayInfoImageMapper);
	}
	
	
	
	
	//product method//
	
	public List<Product> selectProducts(Integer categoryId, Integer start) {
		RowMapper<Product> productMapper = new BeanPropertyRowMapper<Product>(Product.class);
		List<Product> products;
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(categoryId == null) {
			System.out.println("selectProducts without categoryId");
			params.put("start", start);
			params.put("limit", SELECT_PRODUCT_LIMIT);
			products = jdbc.query(SELECT_PRODUCT_ALL_BY_CATEGORY_ID, params,productMapper);
		} else {
			System.out.println("selectProducts with categoryId");
			String clause = "WHERE category.id = " + categoryId;
			params.put("categoryId", categoryId);
			params.put("start", start);
			params.put("limit", SELECT_PRODUCT_LIMIT);
			products = jdbc.query(SELECT_PRODUCT_BY_CATEGORY_ID, params, productMapper);
		}
		for(Product product : products) {
			Integer productId = product.getProductId();
			product.setProductImageUrl(selectProductImageUrl(productId));
		}
		return products;
	}
	
	public Integer countProducts(Integer categoryId) {
		Integer count;
		if(categoryId == null) {
			System.out.println("count without categoryId");
			count = jdbc.queryForObject(SELECT_COUNT_PRODUCT_ALL, Collections.singletonMap("categoryId", "'1','2','3','4','5'"), Integer.class);
		} else {
			count = jdbc.queryForObject(SELECT_COUNT_PRODUCT_ALL_BY_CATEGORY_ID, Collections.singletonMap("categoryId",categoryId), Integer.class);
		}
		return count;
	}

	public String selectProductImageUrl (Integer productId) {
		List<ProductImage> productImages = selectProductImages(productId);
		return selectProductImages(productId).get(0).getSaveFileName();
	}
	
	public PromotionResponse getPromotionResponse() {
		PromotionResponse promotionResponse = new PromotionResponse();
		promotionResponse.setItems(selectPromotions());
		return promotionResponse;
	}
	
	public List<Promotion> selectPromotions(){
		RowMapper<Promotion> promotionMapper = new BeanPropertyRowMapper<Promotion>(Promotion.class);
		List<Promotion> promotions = jdbc.query(SELECT_PROMOTIONS, promotionMapper); 
		for(Promotion promotion : promotions) {
			Integer productId = promotion.getProductId();
			promotion.setProductImageUrl(selectProductImageUrl(productId));
		}
		return promotions;
	}
	
	
	
	
	public String selectEtcSaveFileName(Integer displayInfoId, Integer start) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("limit", ETC_LIMIT);
		return jdbc.queryForObject(SELECT_SAVE_FILE_NAME, map, String.class);
	}
	
	public boolean hasSaveFile(Integer displayInfoId, Integer start) {
		return selectEtcSaveFileName(displayInfoId, start) != null;
	}
	
	
}
