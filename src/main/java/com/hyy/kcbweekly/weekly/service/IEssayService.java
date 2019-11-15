package com.hyy.kcbweekly.weekly.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyy.kcbweekly.weekly.entity.Essay;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cenbihua
 * @since 2019-11-13
 */
public interface IEssayService extends IService<Essay> {
    IPage<Essay> selectPageExt(Essay essay, int page, int pageSize);

    Essay selectNear(Integer id, String flag);

    Essay selectLast();
}
