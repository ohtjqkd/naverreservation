package kr.or.connect.naverreservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.naverreservation.dao.MainDao;
import kr.or.connect.naverreservation.dto.Category;
import kr.or.connect.naverreservation.dto.MainContentDto;
import kr.or.connect.naverreservation.service.MainService;

@Service
public class MainServiceImpl implements MainService {
	@Autowired
	MainDao mainContent;
	
	
	@Override
	@Transactional
	public int getCount() {
		// TODO Auto-generated method stub
		return mainContent.countAll();
	}
	
	@Override
	@Transactional
	public int getCount(int categoryId) {
		// TODO Auto-generated method stub
		return mainContent.countByCategoryId(categoryId);
	}

	@Override
	@Transactional
	public List<MainContentDto> getContents(Integer start) {
		
		return mainContent.selectAllContents(start, MainService.LIMIT);
	}
	
	@Override
	@Transactional
	public List<MainContentDto> getContents(int categoryId,Integer start) {
		
		return mainContent.selectContentsByCategoryId(categoryId, start, MainService.LIMIT);
	}
	
	@Override
	@Transactional
	public List<Category> getCategory() {
		
		return mainContent.selectCategory();
	}
	
	@Override
	public List<MainContentDto> getPromotion() {
		// TODO Auto-generated method stub
		return mainContent.selectPromotions();
	}

}
