package com.junfly.water.mapper;

import com.junfly.water.entity.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by bj on 2016/11/24.
 */
@Mapper
public interface DictMapper {

    /**
     * 根据code查找字典信息
     * @param dictcode
     * @return
     */
    List<Dict> findDictByCode(@Param("dictcode") String dictcode);
}
