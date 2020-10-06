package kr.or.connect.naverreservation.dao;

public class ReservationDaoSqls {
	static final String SELECT_RESERVATIONS_BY_EMAIL = "SELECT reservation_info.*,reservation_info.id AS reservation_info_id, reservation_info.cancel_flag AS cancelYn, used_flag AS usedYn, sum(product_price.price*(100-product_price.discount_rate)/100) AS total_price FROM reservation_info INNER JOIN product ON product.id = reservation_info.product_id JOIN product_price ON product_price.product_id = product.id WHERE reservation_email = :reservationEmail";
	static final String SELECT_RESERVATIONS_TOTAL_PRICE = "SELECT ";
	static final String SELECT_RESERVATION_INFO_BY_RESERVATION_INFO_ID = "SELECT product.*, product.description AS product_description FROM product JOIN reservation_info ON product.id = reservation_info.product_id JOIN display_info ON display_info.id = reservation_info.display_info_id WHERE reservation_info.id = :reservationInfoId;";
}
