package kr.or.connect.naverreservation.dao;

public class MainDaoSqls {
	static final String SELECT_COUNT_ALL = "SELECT count(*) FROM product";
	static final String SELECT_COUNT_CATEGORY = "SELECT count(*) FROM product WHERE category_id = :categoryId";
	static final String SELECT_CONTENT_BY_CATEGORY = "SELECT product.description, product.content, product_image.id, product_image.product_id FROM product JOIN product_image ON product.id = product_image.product_id WHERE product.category_id = :categoryId AND product_image.type = 'th' limit :start, :limit";
	static final String SELECT_ALL_CONTENTS = "SELECT product.description, product.content, product_image.id, product_image.product_id, display_info.place_name FROM product JOIN product_image ON product.id = product_image.product_id JOIN display_info ON product.id = display_info.product_id WHERE product_image.type = 'th' limit :start, :limit";
	static final String SELECT_ALL_PROMOTIONS = "SELECT product.description, product.content, product_image.id, product_image.product_id, display_info.place_name FROM product JOIN promotion ON product.id = promotion.product_id JOIN product_image ON product.id = product_image.product_id JOIN display_info ON product.id = display_info.product_id WHERE product_image.type = 'th'";
	static final String SELECT_ALL_CATEGORY = "SELECT * FROM category";
	
}
