package com.junfly.water.service.sys.impl;

import com.junfly.water.entity.sys.SmobileSlide;
import com.junfly.water.mapper.sys.SmobileSlideMapper;
import com.junfly.water.service.sys.SmobileSlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("smobileSlideService")
public class SmobileSlideServiceImpl implements SmobileSlideService{
	
	@Autowired
	private SmobileSlideMapper smobileslideDao;

	
	/**
	 * 查询符合条件的所有记录
	 * @param smobileslide
	 * @return List<SmobileSlide>
	 */
	public List<SmobileSlide> smobileslideList(SmobileSlide smobileslide){
		List<SmobileSlide> smobileSlides = smobileslideDao.smobileSlideList(smobileslide);
		return smobileSlides;
	}
	
	/**
	 * 根据ID查询记录
	 * @param id
	 * @return SmobileSlide
	 */
	public SmobileSlide smobileslideGetById(String id){
		return smobileslideDao.smobileSlideGetById(id);
	}
	
	/**
	 * 添加记录
	 * @param smobileslide
	 * @return
	 */
	public boolean smobileslideInsert(SmobileSlide smobileslide){
		return smobileslideDao.smobileslideInsert(smobileslide) >= 1 ? true : false;
	}
	
	/**
	 * 修改记录
	 * @param smobileslide
	 * @return
	 */
	public boolean smobileslideUpdate(SmobileSlide smobileslide){
		return smobileslideDao.smobileslideUpdate(smobileslide) >= 1 ? true : false;
	}
	
	/**
	 * 修改记录所有字段
	 * @param smobileslide
	 * @return
	 */
	public boolean smobileslideUpdateAll(SmobileSlide smobileslide){
		return smobileslideDao.smobileslideUpdateAll(smobileslide) >= 1 ? true : false;
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	public boolean smobileslideDelete(String id){
		return smobileslideDao.smobileslideDelete(id) >= 1 ? true : false;
	} 
}
