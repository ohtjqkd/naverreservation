package kr.or.connect.naverreservation.dao;

public class ProductDaoSqls {
	
	//displayInfo sqls//
	static final String SELECT_DISPLAY_INFO = "SELECT display_info.*,product.content, product.description, product.event, product.category_id, category.name FROM display_info LEFT JOIN product ON display_info.product_id = product.id RIGHT JOIN category ON category.id = product.category_id WHERE display_info.id = :displayInfoId";
			
	static final String SELECT_PRODUCTIMAGES_BY_PRODUCT_ID = "SELECT *,file_info.id AS file_info_id,product_image.id AS product_image_id FROM file_info INNER JOIN product_image ON file_info.id = product_image.file_id WHERE product_image.product_id = :productId";
//	static final String SELECT_PRODUCTIMAGES_BY_PRODUCT_ID = "SELECT file_info.id AS file_info_id, file_info.save_file_name, file_info.content_type, file_info.delete_flag, file_info.create_date, file_info.modify_date, product_image.id AS product_image_id, product.id AS product_id, file_info.file_name FROM file_info INNER JOIN product_image ON product_image.file_id = file_info.id INNER JOIN product ON product_image.product_id = product.id WHERE product.id = :productId";
	
	static final String SELECT_COMMENTS_BY_PRODUCT_ID = "SELECT reservation_user_comment.comment, product.description, reservation_user_comment.id AS comment_id, reservation_user_comment.create_date, reservation_user_comment.modify_date, product.id AS product_id, reservation_info.reservation_date, reservation_info.reservation_email, reservation_info.id AS reservation_info_id, reservation_info.reservation_name, reservation_info.reservation_tel AS reservation_telephone, reservation_user_comment.score FROM reservation_user_comment INNER JOIN product ON product.id = reservation_user_comment.product_id INNER JOIN reservation_info ON reservation_info.id = reservation_user_comment.reservation_info_id WHERE product.id = :productId";
	
	static final String SELECT_COMMENTIMAGES_BY_COMMENT_ID = "SELECT *, reservation_user_comment_image.id AS image_id,file_info.id AS fileId FROM file_info INNER JOIN reservation_user_comment_image ON reservation_user_comment_image.file_id = file_info.id INNER JOIN reservation_user_comment ON reservation_user_comment.id = reservation_user_comment_image.reservation_user_comment_id WHERE reservation_user_comment.id = :commentId";
	
	static final String SELECT_PRODUCTPRICES_BY_PRODUCT_ID = "SELECT product_price.id AS product_price_id, product_price.price, product_price.create_date, product_price.modify_date, product_price.price_type_name, product_price.discount_rate, product.id AS product_id FROM product_price INNER JOIN product ON product_price.product_id = product.id WHERE product.id = :productId";
	
	static final String SELECT_DISPLAYINFOIMAGE_BY_DISPLAY_ID = "SELECT file_info.content_type, file_info.create_date, file_info.delete_flag, display_info.id AS display_info_id, display_info_image.id AS display_info_image_id, file_info.id AS file_info_id, file_info.file_name, file_info.modify_date, file_info.save_file_name FROM file_info INNER JOIN display_info_image ON display_info_image.file_id = file_info.id INNER JOIN display_info ON display_info.id = display_info_image.display_info_id WHERE display_info.id = :displayInfoId";

	
	
	//productList sqls//
	static final String SELECT_PRODUCT_BY_CATEGORY_ID = "SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id FROM product INNER JOIN display_info ON product.id = display_info.product_id WHERE category_id = :categoryId limit :start, :limit";

	static final String SELECT_PRODUCT_ALL_BY_CATEGORY_ID = "SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id FROM product INNER JOIN display_info ON product.id = display_info.product_id limit :start, :limit";
	
	static final String SELECT_COUNT_PRODUCT_ALL_BY_CATEGORY_ID = "SELECT count(display_info.id) FROM product JOIN display_info ON display_info.product_id = product.id WHERE product.category_id = :categoryId";

	static final String SELECT_COUNT_PRODUCT_ALL = "SELECT count(display_info.id) FROM product JOIN display_info ON display_info.product_id = product.id";
	
	
	static final String SELECT_PROMOTIONS = "SELECT promotion.id, product.id AS product_id FROM promotion JOIN product ON product.id = promotion.product_id";

	static final String SELECT_SAVE_FILE_NAME = "SELECT file_info.save_file_name from display_info INNER JOIN product ON display_info.product_id = product.id INNER JOIN product_image ON product.id = product_image.product_id RIGHT OUTER JOIN file_info ON product_image.file_id = file_info.id WHERE display_info.id = :displayInfoId AND product_image.type = 'ma'";
	
	static final String SELECT_ETC_SAVE_FILE_NAME_ = "SELECT file_info.save_file_name from display_info INNER JOIN product ON display_info.product_id = product.id LEFT OUTER JOIN product_image ON product.id = product_image.product_id LEFT OUTER JOIN file_info ON product_image.file_id = file_info.id WHERE display_info.id = :displayInfoId AND product_image.type = 'et'";
	
	
	static final String SELECT_CONTENT_BY_CATEGORY = "SELECT product.description, product.content, product_image.id, product_image.product_id FROM product JOIN product_image ON product.id = product_image.product_id WHERE product.category_id = :categoryId AND product_image.type = 'th' limit :start, :limit";
	
	static final String SELECT_ALL_CONTENTS = "SELECT product_image.id, product_image.product_id, display_info.place_name FROM product JOIN product_image ON product.id = product_image.product_id JOIN display_info ON product.id = display_info.product_id WHERE product_image.type = 'th' limit :start, :limit";
	
	static final String SELECT_ALL_CATEGORY = "SELECT * FROM category";

	static final int ETC_LIMIT = 1;

	static final Integer SELECT_PRODUCT_LIMIT = 4;
}
