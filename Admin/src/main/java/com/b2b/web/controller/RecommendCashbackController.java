package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.RecommendCashback;
import com.b2b.common.domain.SysLog;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.RecommendCashbackService;
import com.b2b.web.util.SessionHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by shenhui on 2017/8/11.
 */
@RequestMapping("recommendcashback")
@Controller
public class RecommendCashbackController {
    private static final Logger logger = LoggerFactory.getLogger(RecommendCashbackController.class);
    private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    @Autowired
    private RecommendCashbackService recommendCashbackService;
    @Autowired
    private LogService logService;
    @RequestMapping(value = "/recommendcashbackList.htm")
    @ResponseBody
    public ModelAndView list(HttpServletRequest request) {
        int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
        PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
        if(null == personUser.getCityId()){
            return new ModelAndView("noCity");
        }
        ModelAndView mv = new ModelAndView("huodong/cashbackList");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");

        Date startTime = null;
        Date endTime = null;

        if (StringUtils.isNotBlank(startTimeStr)) {
            startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
            mv.addObject("startTime", startTimeStr);
        }

        if (StringUtils.isNotBlank(endTimeStr)) {
            endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
            mv.addObject("endTime", endTimeStr);
        }
        String companyName = request.getParameter("companyName");
        mv.addObject("companyName", companyName);
        PageHelper.startPage(currentPage, 30);
        List<RecommendCashback> recommendCashbacks =this.recommendCashbackService.findByCondition(companyName,startTime,endTime,personUser.getCityId());
        PageInfo<RecommendCashback> info = new PageInfo<RecommendCashback>(recommendCashbacks);
        Page<RecommendCashback> page = new Page<RecommendCashback>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
        mv.addObject("page",page);
        TestController.getMenuPoint(mv, request);
        return mv;
    }

    @RequestMapping("addRemark.do")
    @ResponseBody
    public String addRemark(HttpServletRequest request,RecommendCashback recommendCashback){
        try {
            PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
            if(null == personUser.getCityId()){
                return "你还未设置默认城市，联系管理员设置";
            }
            this.recommendCashbackService.update(recommendCashback);

            return "200";
        } catch (Exception e) {
            logger.error("添加备注失败"+e.getMessage());
            return "201";
        }
    }

    @RequestMapping("dev.do")
    @ResponseBody
    public String dodev(HttpServletRequest request,RecommendCashback recommendCashback){
        try {
            PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
            if(null == personUser.getCityId()){
                return "你还未设置默认城市，联系管理员设置";
            }
            recommendCashback.setType(1);
            this.recommendCashbackService.update(recommendCashback);
            this.saveLog(request.getSession(),recommendCashback,"发放",personUser.getCityId());
            return "200";
        } catch (Exception e) {
            logger.error("发放失败"+e.getMessage());
            return "201";
        }
    }

    private void saveLog(HttpSession session, RecommendCashback dto, String content, Integer cityId){
        try{
            SysLog sysLog = new SysLog();
            sysLog.setContent(content);
            sysLog.setCreateTime(new Date());
            sysLog.setUserId(SessionHelper.getUserId(session));
            sysLog.setCityId(cityId);
            sysLog.setDataType("推荐返佣金");
            sysLog.setDataId(dto.getId().toString());

            String dataContent = new Gson().toJson(dto,
                    new TypeToken<RecommendCashback>() {
                    }.getType());

            sysLog.setDataContent(dataContent);

            logService.createLog(sysLog);
        }catch(Exception e){
            logger.error("保存日志失败",e);
        }
    }

}
