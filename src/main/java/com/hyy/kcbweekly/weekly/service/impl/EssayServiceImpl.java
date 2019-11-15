package com.hyy.kcbweekly.weekly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyy.kcbweekly.weekly.entity.Essay;
import com.hyy.kcbweekly.weekly.mapper.EssayMapper;
import com.hyy.kcbweekly.weekly.service.IEssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cenbihua
 * @since 2019-11-13
 */
@Service
public class EssayServiceImpl extends ServiceImpl<EssayMapper, Essay> implements IEssayService {

    @Autowired
    EssayMapper essayMapper;

    @Override
    public IPage<Essay> selectPageExt(Essay essay, int page, int pageSize) throws RuntimeException {
        try {
            Page<Essay> p = new Page<>(page, pageSize);
            p.setRecords(essayMapper.selectPageExt(p, essay));
            return p;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Essay selectNear(Integer id, String flag) {
        Essay essay = new Essay();
        if ("-1".equals(flag)) {
            essay = essayMapper.selectBefore(id);
            //return essay;
        } else if ("1".equals(flag)) {
            essay = essayMapper.selectAfter(id);
        }
        return essay;
    }

    //查最后一条文章
    @Override
    public Essay selectLast() {
        QueryWrapper<Essay> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 1");
//        Essay essay =
        return essayMapper.selectOne(wrapper);
    }

}
