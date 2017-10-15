package com.junfly.water.mapper.sys;

import com.junfly.water.entity.sys.SmobileSlide;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmobileSlideMapper{
	/**
	 * 查询符合条件的所有记录
	 * @param smobileslide
	 * @return List<SmobileSlide>
	 */
	@SuppressWarnings("unchecked")
	List<SmobileSlide> smobileSlideList(SmobileSlide smobileslide);

	/**
	 * 根据ID查询记录
	 * @param id
	 * @return SmobileSlide
	 */
	SmobileSlide smobileSlideGetById(String id);

	/**
	 * 添加记录
	 * @param smobileslide
	 * @return
	 */
	int smobileslideInsert(SmobileSlide smobileslide);

	/**
	 * 修改记录
	 * @param smobileslide
	 * @return
	 */
	int smobileslideUpdate(SmobileSlide smobileslide);

	/**
	 * 修改记录所有字段
	 * @param smobileslide
	 * @return
	 */
	int smobileslideUpdateAll(SmobileSlide smobileslide);

	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	int smobileslideDelete(String id);
}
