package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.RecommendRedback;
import com.b2b.common.domain.SysLog;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.RecommendRedbackService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by shenhui on 2017/8/11.
 */
@RequestMapping("recommendredback")
@Controller
public class RecommendRedbackController {
    private static final Logger logger = LoggerFactory.getLogger(RecommendRedbackController.class);
    private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    @Autowired
    private RecommendRedbackService recommendRedbackService;
    @Autowired
    private LogService logService;
    @RequestMapping(value = "/recommendredbackList.htm")
    @ResponseBody
    public ModelAndView list(HttpServletRequest request) {
        int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
        PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
        if(null == personUser.getCityId()){
            return new ModelAndView("noCity");
        }
        ModelAndView mv = new ModelAndView("huodong/redbackList");
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
        List<RecommendRedback> recommendCashbacks =this.recommendRedbackService.findByCondition(companyName,startTime,endTime,personUser.getCityId());
        PageInfo<RecommendRedback> info = new PageInfo<RecommendRedback>(recommendCashbacks);
        Page<RecommendRedback> page = new Page<RecommendRedback>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
        mv.addObject("page",page);
        TestController.getMenuPoint(mv, request);
        return mv;
    }

    @RequestMapping("addRemark.do")
    @ResponseBody
    public String addRemark(HttpServletRequest request,RecommendRedback recommendRedback){
        try {
            PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
            if(null == personUser.getCityId()){
                return "你还未设置默认城市，联系管理员设置";
            }
            this.recommendRedbackService.update(recommendRedback);

            return "200";
        } catch (Exception e) {
            logger.error("添加备注失败"+e.getMessage());
            return "201";
        }
    }

    @RequestMapping("dev.do")
    @ResponseBody
    public String dodev(HttpServletRequest request, @RequestParam("id")Integer id){
        try {
            PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
            if(null == personUser.getCityId()){
                return "你还未设置默认城市，联系管理员设置";
            }
            RecommendRedback recommendRedback = this.recommendRedbackService.findById(id);
            if (recommendRedback.getType()==1){
                return "201";
            }
            this.recommendRedbackService.fahongbao(recommendRedback);
            this.saveLog(request.getSession(),recommendRedback,"发放",personUser.getCityId());
            return "200";
        } catch (Exception e) {
            logger.error("发放失败"+e.getMessage());
            return "202";
        }
    }
    
    @RequestMapping("delete.do")
    @ResponseBody
    public String delete(HttpServletRequest request, @RequestParam("id")Integer id){
        try {
            PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
            if(null == personUser.getCityId()){
                return "你还未设置默认城市，联系管理员设置";
            }
            RecommendRedback recommendRedback = this.recommendRedbackService.findById(id);
            if (recommendRedback.getType()==1){
                return "201";
            }
            recommendRedback.setStatus(0);
            this.recommendRedbackService.update(recommendRedback);
            this.saveLog(request.getSession(),recommendRedback,"发放",personUser.getCityId());
            return "200";
        } catch (Exception e) {
            logger.error("发放失败"+e.getMessage());
            return "202";
        }
    }

    private void saveLog(HttpSession session, RecommendRedback dto, String content, Integer cityId){
        try{
            SysLog sysLog = new SysLog();
            sysLog.setContent(content);
            sysLog.setCreateTime(new Date());
            sysLog.setUserId(SessionHelper.getUserId(session));
            sysLog.setCityId(cityId);
            sysLog.setDataType("推荐返红包");
            sysLog.setDataId(dto.getId().toString());

            String dataContent = new Gson().toJson(dto,
                    new TypeToken<RecommendRedback>() {
                    }.getType());

            sysLog.setDataContent(dataContent);

            logService.createLog(sysLog);
        }catch(Exception e){
            logger.error("保存日志失败",e);
        }
    }

}
