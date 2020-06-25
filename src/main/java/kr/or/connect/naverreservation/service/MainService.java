package kr.or.connect.naverreservation.service;

import java.util.List;

import kr.or.connect.naverreservation.dto.Category;
import kr.or.connect.naverreservation.dto.MainContentDto;


public interface MainService {
	public static final Integer LIMIT = 4;
	public int getCount();
	public int getCount(int categoryId);
	public List<MainContentDto> getContents(Integer start);
	public List<MainContentDto> getContents(int categoryId,Integer start);
	public List<Category> getCategory();
	public List<MainContentDto> getPromotion();
}
