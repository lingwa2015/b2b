package com.b2b.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.ItemVariety;
import com.b2b.common.domain.SnackPackageType;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.PersonUser;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.ItemService;
import com.b2b.service.ItemVarietyService;
import com.b2b.service.LogService;
import com.b2b.service.SnackPackageTypeService;
import com.b2b.web.util.FileUtil;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Controller
@RequestMapping("snackPackageType")
public class SnackPackageTypeController {
	private static final Logger logger = LoggerFactory.getLogger(SnackPackageTypeController.class);
	@Autowired
	private SnackPackageTypeService snackPackageTypeService;
	
	@Autowired
	LogService logService;
	
	@Value("${item_image_path}")
	private String imagePath;
	
	@RequestMapping(value = "/snackPackageTypeList.htm")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request) {
		Page<SnackPackageType> page = snackPackageTypeService.findPage();
		return new ModelAndView("snackPackage/typelist", "page", page);
	}
	
	@RequestMapping(value = "/snackPackageTypeAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add() {
		ItemVariety dto = new ItemVariety();
		return new ModelAndView("snackPackage/typeedit", "dto", dto);
	}
	
	@RequestMapping(value = "/snackPackageTypeUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView update(@RequestParam("id")Integer id) {
		SnackPackageType dto = snackPackageTypeService.findById(id);
		return new ModelAndView("snackPackage/typeedit", "dto", dto);
	}
	
	
	@RequestMapping(value = "/itemImage.htm")
	public String showImage(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String imageName = request.getParameter("imageName");
			// String name = genImageName(index);
			if(imageName==""){
				logger.warn("图片名称为空");
				return null;
			}
			String path = imagePath + imageName;
			File file = new File(path);
			if (!file.exists()) {
				logger.warn("没有找到对应的文件,path:" + path);
				return null;
			}
			long size = file.length();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(imageName.getBytes("UTF-8"), "ISO-8859-1"));
			response.addHeader("Content-Length", "" + size);
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value="save.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView save(@RequestParam MultipartFile myfile,SnackPackageType dto,HttpServletRequest request){
		String result = "操作成功";
		try{
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if (!myfile.isEmpty()) {
				File file = new File(imagePath);
				if (!file.exists()) {
					file.mkdir();
				}
				try {
					String oldName = myfile.getOriginalFilename();
					String name = FileUtil.genUploadFileName(oldName);
					FileUtils.copyInputStreamToFile(myfile.getInputStream(),
							new File(imagePath, name));
					dto.setImgPath(name);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("上传图片失败", e);
					result = "操作失败";
				}
			}
			if(dto.getId()!=null&&dto.getId()>0){
				dto.setUpdatedTime(new Date());
				dto.setUpdatedUserid(personUser.getId());
				this.snackPackageTypeService.updateSnackPackageType(dto);
				this.saveLog(request.getSession(),dto, "修改零食包类型，名称："+dto.getSpValue());
			}else{
				dto.setCreatedTime(new Date());
				dto.setCreatedUserid(personUser.getId());
				dto.setUpdatedTime(dto.getCreatedTime());
				dto.setUpdatedUserid(personUser.getId());
				this.snackPackageTypeService.insert(dto);
				this.saveLog(request.getSession(),dto, "添加零食包类型，名称："+dto.getSpValue());
			}
		}catch(Exception e){
            logger.error("保存零食包类型失败",e);
            result = "操作零食包类型失败，原因："+e.getMessage();
		}

		//return result;
		ModelAndView view = new ModelAndView("redirect:/snackPackageType/snackPackageTypeList.htm");
		return view;
	}
	
	@RequestMapping(value="delete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(int id,HttpServletRequest request) {
		String result = "操作成功";
		try{
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(id!=0){
				SnackPackageType snackPackageType = new SnackPackageType();
				snackPackageType.setId(id);
				snackPackageType.setStatus(Constant.DELETE_STATUS);
				snackPackageType.setUpdatedTime(new Date());
				snackPackageType.setUpdatedUserid(personUser.getId());
				snackPackageTypeService.deleteSnackPackageType(snackPackageType);
				this.saveLog(request.getSession(),snackPackageType, "删除零食包类型，id："+id);
			}
		}catch(Exception e){
            logger.error("零食包类型失败",e);
            result = "操作失败，原因："+e.getMessage();
		}

		return result;
	}
	
	private void saveLog(HttpSession session,SnackPackageType dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.SnackPackageType.getName());
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<SnackPackageType>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
