package com.hyy.kcbweekly.weekly.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyy.kcbweekly.weekly.anno.Token;
import com.hyy.kcbweekly.weekly.entity.Essay;
import com.hyy.kcbweekly.weekly.entity.ResultInfo;
import com.hyy.kcbweekly.weekly.mapper.EssayMapper;
import com.hyy.kcbweekly.weekly.service.IEssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cenbihua
 * @since 2019-11-13
 */
@RestController
@RequestMapping("//essay")
public class EssayController {
    @Autowired
    IEssayService iessayService;
    @Autowired
    EssayMapper essayMapper;

    // 添加文章
    @Token
    @ResponseBody
    @RequestMapping(value = "/addEssay", method = RequestMethod.POST)
    public ResultInfo addEssay(@RequestBody Map map, HttpServletRequest request, HttpServletResponse response) {
        //获取参数
        String title = (String) map.get("title");
        String author = (String) map.get("author");
        String release_time = (String) map.get("release_time");
        String content = (String) map.get("content");

        Essay essay = new Essay();
        essay.setAuthor(author);
        essay.setTitle(title);
        essay.setReleaseTime(release_time);
        essay.setContent(content);
        ResultInfo resultInfo = new ResultInfo();
        try {
            essayMapper.insert(essay);
        } catch (Exception e) {
            resultInfo.setMsg("添加失败");
        }
        resultInfo.setMsg("添加成功");
        resultInfo.setData(essay);
        resultInfo.setCode(response.getStatus());
        return resultInfo;
    }

    // 修改文章
    @Token
    @ResponseBody
    @RequestMapping(value = "/updateEssay", method = RequestMethod.POST)
    public ResultInfo updateEssayById(@RequestBody Map map, HttpServletRequest request, HttpServletResponse response) {
        //获取参数
        String title = (String) map.get("title");
        String author = (String) map.get("author");
        String release_time = (String) map.get("release_time");
        String content = (String) map.get("content");
        //String bid = (String) map.get("id");
        Integer id = (Integer) map.get("id");

        Essay essay = new Essay();
        essay.setAuthor(author);
        essay.setTitle(title);
        essay.setReleaseTime(release_time);
        essay.setContent(content);
        essay.setId(id);

        ResultInfo resultInfo = new ResultInfo();
        try {
            //essayMapper.insert(essay);
            ;
            iessayService.updateById(essay);
        } catch (Exception e) {
            resultInfo.setMsg("修改失败");
        }
        resultInfo.setMsg("修改成功");
        resultInfo.setData(essay);
        resultInfo.setCode(response.getStatus());
        return resultInfo;
    }

//    @ResponseBody
//    @RequestMapping(value = "/getEssays", method = RequestMethod.GET)
//    public ResultInfo getEssays(HttpServletRequest request) {
//        List<Essay> list = essayMapper.selectList(new QueryWrapper<>());
//        return ResultInfo.success().add(list);
//
//    }

    @Token
    // 查询所有文章
    @RequestMapping(value = "/findEssays", method = RequestMethod.POST)
    @ResponseBody
    public List<Essay> findAllEssay(@RequestBody Map map, HttpServletRequest request) {
        Essay essay = new Essay();
//        Integer uid = (Integer) map.get("pageNum");
//        Integer sizedd = (Integer) map.get("pageSize");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        //获取参数
        String title = (String) map.get("title");
        String author = (String) map.get("author");
        String release_time = (String) map.get("release_time");

        IPage<Essay> essayss = this.iessayService.page(new Page<>(pageNum, pageSize),
                new QueryWrapper<Essay>().lambda()
                        .like(!StringUtils.isEmpty(author), Essay::getAuthor, author)
                        //.eq(Essay::getAutho)
                        .like(!StringUtils.isEmpty(title), Essay::getTitle, title)
                        .like(!StringUtils.isEmpty(release_time), Essay::getAuthor, release_time)
        );

//  total：总数，size：当前分页条数，current：当前页数，pages：有几页，searchCount：搜索计数？，records：数据

        // return Essayss;
        return essayss.getRecords();
    }

    // 官网显示的
    @Token
    @RequestMapping("/showEassay")
    @ResponseBody
    public Essay showEassay(HttpServletRequest request, HttpServletResponse response) {
        String sid = (String) request.getParameter("id");
        //       Integer id = Integer.parseInt((String) request.getParameter("id"));
        String flag = (String) request.getParameter("flag");
        Essay essay = new Essay();
        if (null == sid && null == flag) {
            essay = iessayService.selectLast();
            return essay;
        }
        Integer id = Integer.parseInt((String) request.getParameter("id"));
        essay = iessayService.selectNear(id, flag);
        return essay;
    }
}



