package com.junfly.water.service.sys;

import com.junfly.water.entity.sys.SmobileSlide;

import java.util.List;

public interface SmobileSlideService {
	
	List<SmobileSlide> smobileslideList(SmobileSlide smobileslide);

	/**
	 * 根据ID查询记录
	 * @param id
	 * @return SmobileSlide
	 */
	SmobileSlide smobileslideGetById(String id);

	/**
	 * 添加记录
	 * @param smobileslide
	 * @return
	 */
	boolean smobileslideInsert(SmobileSlide smobileslide);

	/**
	 * 修改记录
	 * @param smobileslide
	 * @return
	 */
	boolean smobileslideUpdate(SmobileSlide smobileslide);

	/**
	 * 修改记录所有字段
	 * @param smobileslide
	 * @return
	 */
	boolean smobileslideUpdateAll(SmobileSlide smobileslide);

	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	boolean smobileslideDelete(String id);
}
