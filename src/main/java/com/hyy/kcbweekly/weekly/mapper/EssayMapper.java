package com.hyy.kcbweekly.weekly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyy.kcbweekly.weekly.entity.Essay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cenbihua
 * @since 2019-11-13
 */
public interface EssayMapper extends BaseMapper<Essay> {
    List<Essay> selectPageExt(Page<Essay> page, @Param("essay") Essay Essay);

    // 查找前一条
    Essay selectBefore(Integer id);

    // 查找后一条
    Essay selectAfter(Integer id);


}
