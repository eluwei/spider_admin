package com.junfly.water.mapper.sys;

import com.junfly.water.entity.sys.SmobileRolemenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmobileRolemenuMapper {
    /**
     * 查询符合条件的所有记录
     *
     * @param smobileRolemenu
     * @return List<SmobileRolemenu>
     */
    List<SmobileRolemenu> smobileRolemenuByParentId(SmobileRolemenu smobileRolemenu);
}
