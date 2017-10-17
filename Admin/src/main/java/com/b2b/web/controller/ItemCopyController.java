package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.*;
import com.b2b.web.util.FileUtil;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/itemCopy")
public class ItemCopyController {

    private static final Logger logger = LoggerFactory
            .getLogger(ItemCopyController.class);

    @Value("${item_image_path}")
    private String imagePath;

    @Autowired
    private CityService cityService;

    @Autowired
    ItemCategoryService itemCategoryService;

    @Autowired
    ItemTasteService itemTasteService;

    @Autowired
    ItemVarietyService itemVarietyService;

    @Autowired
    ItemService itemService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    LogService logService;



    @RequestMapping(value = "/itemCopy.htm", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView add(HttpServletRequest request) {
        Item dto = new Item();

        ModelAndView view = new ModelAndView("item/itemCopy");
        view.addObject("dto", dto);
        view.addObject("itemTasteSize",0);
        PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
        if(null == personUser.getCityId()){
            return new ModelAndView("noCity");
        }
        String cityId =request.getParameter("cityId");
        if (cityId == null) {
            view.addObject("cityId", "1");
        } else {
            view.addObject("cityId", cityId);
        }
        List<City> cityList = this.cityService.findAllOpenCity();
        view.addObject("cityList", cityList);

        String idStr = request.getParameter("id");
        if (idStr != null) {
            Integer id = Integer.valueOf(idStr);
            dto = itemService.findById(id);
            if(dto==null){
                return new ModelAndView("notCity");
            }
            String catId=request.getParameter("catId");
            String itemName=request.getParameter("itemNames");
            if (dto.getPlace() == null) {
                dto.setPlace("国产");
            }
            view.addObject("dto", dto);
            view.addObject("itemNames", itemName);
            if (catId == null) {
                view.addObject("catId", 0);
            } else {
                view.addObject("catId", catId);
            }

            dto.setCategoryId(getCategoryByName(dto.getCategoryId(), personUser.getCityId(), 0));
            dto.setCategorylevelId(getCategoryByName(dto.getCategorylevelId(), personUser.getCityId(), dto.getCategoryId()));

            List<ItemTaste> itemTaste = this.itemTasteService.findByItemId(id);
            view.addObject("itemTasteList", itemTaste);
            view.addObject("itemTasteSize", itemTaste.size());
            this.fillCommonData(view, personUser.getCityId());
            this.fillLevelCommonData(view, personUser.getCityId());
            String varietyName = this.itemService.findVarietyName(id);
            view.addObject("varietyName", varietyName);
            List<Supplier> suppliers = this.supplierService.findByCityId(personUser.getCityId());
            view.addObject("suppliers", suppliers);
            return view;

        }


        this.fillCommonData(view, personUser.getCityId());
        this.fillLevelCommonData(view, personUser.getCityId());
        //this.fillVarietyCommonData(view);
        List<Supplier> suppliers = this.supplierService.findByCityId(personUser.getCityId());
        view.addObject("suppliers", suppliers);
        return view;
    }

    private void fillCommonData(ModelAndView view,Integer cityId) {
        List<ItemCategory> catList = new ArrayList<ItemCategory>();
        catList = itemCategoryService.findAllOneCatsByCityId(cityId);
        view.addObject("catList", catList);

    }

    private void fillLevelCommonData(ModelAndView view, Integer cityId) {
        List<ItemCategory> catLevelList = new ArrayList<ItemCategory>();
        catLevelList = itemCategoryService.findAllTwoCatsByCityId(cityId);
        view.addObject("catLevelList", catLevelList);

    }

    @RequestMapping(value = "/isHad.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String isHad(HttpServletRequest request) {
        PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
        if(null == personUser.getCityId()){
            return "201";
        }

        String itemName=request.getParameter("itemName").replace("*", "");
        Item item = this.itemService.findByItemNameAndCityId(itemName, personUser.getCityId());
        if (item != null) {
            return "200";
        }

        return "201";
    }

    @RequestMapping(value = "save.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView save(@RequestParam MultipartFile myfile, @RequestParam MultipartFile myfile1, Item dto,
                             HttpServletRequest request) {
        String result = "操作成功";

        try{
            PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
            if(null == personUser.getCityId()){
                return new ModelAndView("noCity");
//                return "你还未设置默认城市，联系管理员设置";
            }

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
                    dto.setImgPath("http://assets.lingwa.com.cn/images/item/"+name);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("上传图片失败", e);
                    result = "操作失败";
//                    return result;
                }
            }
            if (!myfile1.isEmpty()) {
                File file = new File(imagePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                try {
                    String oldName = myfile1.getOriginalFilename();
                    String name = FileUtil.genUploadFileName(oldName);
                    FileUtils.copyInputStreamToFile(myfile1.getInputStream(),
                            new File(imagePath, name));
                    dto.setBigImgPath("http://assets.lingwa.com.cn/images/item/"+name);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("上传图片失败", e);
                    result = "操作失败";
//                    return result;
                }
            }

            String name = request.getParameter("itemVarietyname");
            int rowCount = Integer.parseInt(StringUtils.defaultString(
                    request.getParameter("rowCount"), "0"));
            List<ItemTaste> itemTaste = new ArrayList<ItemTaste>();
            StringBuffer buffer = new StringBuffer();
            int a = 0;
            for (int i = 0; i < rowCount; i++) {
                ItemTaste itemTaste2 = new ItemTaste();
                String taste = request.getParameter("itemTaste" + i);
                String barcode = request.getParameter("barcode" + i);
                if(StringUtils.isNotEmpty(taste)){
                    itemTaste2.setTasteName(taste);
                }
                if(StringUtils.isNotEmpty(barcode)){
                    itemTaste2.setBarcode(barcode);
                    if(a==0){
                        buffer.append(barcode);
                        a=1;
                    }else{
                        buffer.append(","+barcode);
                    }
                }
                if(itemTaste2.getTasteName()!=null || itemTaste2.getBarcode()!=null){
                    itemTaste.add(itemTaste2);
                }
            }
            dto.setBarcode(buffer.toString());
            if(StringUtils.isNotEmpty(name)){
                ItemVariety itemVariety = this.itemVarietyService.findByName(name,personUser.getCityId());
                if(null!=itemVariety){
                    dto.setItemVariety(itemVariety.getItemvarietyId());
                }else{
                    ItemVariety variety = new ItemVariety();
                    variety.setItemvarietyName(name);
                    variety.setCityId(personUser.getCityId());
                    variety.setCreatedTime(new Date());
                    variety.setCreatedUserid(personUser.getId());
                    variety.setUpdatedTime(variety.getCreatedTime());
                    variety.setUpdatedUserid(personUser.getId());
                    this.itemVarietyService.create(variety);
                    dto.setItemVariety(variety.getItemvarietyId());
                }
            }

            if (dto.getActualPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualPrice()
                        .toString());
                dto.setPrice(price);
            }

            if (dto.getActualCostPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualCostPrice()
                        .toString());
                dto.setCostPrice(price);
            }

            if (dto.getActualSalePrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualSalePrice()
                        .toString());
                dto.setSalePrice(price);
            }

            if (dto.getActualSaleCostPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualSaleCostPrice()
                        .toString());
                dto.setSaleCostPrice(price);
            }


            if (dto.getActualBuyPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualBuyPrice()
                        .toString());
                dto.setBuyPrice(price);
            }

            if (dto.getActualPurchasePrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualPurchasePrice()
                        .toString());


                dto.setPurchasePrice(price);
            }

            if (dto.getActualnotaxInclusiveBuyPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualnotaxInclusiveBuyPrice().toString());

                dto.setNotaxInclusiveBuyPrice(price);
            }

            if (dto.getActualnotaxInclusiveCostPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualnotaxInclusiveCostPrice()
                        .toString());


                dto.setNotaxInclusiveCostPrice(price);
            }

            if (dto.getActualnotaxInclusiveSaleCostPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualnotaxInclusiveSaleCostPrice()
                        .toString());


                dto.setNotaxInclusiveSaleCostPrice(price);
            }
            if (dto.getActualMarketPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualMarketPrice()
                        .toString());
                dto.setMarketPrice(price);
            }
            if (dto.getActualcsPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualcsPrice()
                        .toString());
                dto.setCsPrice(price);
            }
            if (dto.getActualjdPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualjdPrice()
                        .toString());
                dto.setJdPrice(price);
            }
            if (dto.getActualtmPrice() != null) {
                long price = NumberTool.str2Double2Fen(dto.getActualtmPrice()
                        .toString());
                dto.setTmPrice(price);
            }
            dto.setCityId(personUser.getCityId());

            ArrayList<Integer> list = new ArrayList<Integer>();
            String[] supplierids = request.getParameterValues("supplier");
            for (String string : supplierids) {
                int supplierId = Integer.parseInt(string);
                list.add(supplierId);
            }

            if (dto.getProperty() == null) {
                dto.setProperty(1);
            }

            if (dto.getIsdown() == null) {
                dto.setIsdown(1);
            }

            dto.setId(null);
            dto.setCreatedTime(new Date());
            dto.setCreatedUserid(personUser.getId());
            dto.setUpdatedTime(dto.getCreatedTime());
            dto.setUpdatedUserid(personUser.getId());
            dto.setRecommend(0);
            itemService.create(dto,itemTaste,list);
            this.saveLog(request.getSession(),dto, "添加商品，名称："+dto.getItemName());

        }catch(Exception e){
            logger.error("保存商品失败",e);
//            return "保存商品失败";
        }
        String catId=request.getParameter("catId");
        String itemName=request.getParameter("itemNames");
        ModelAndView view = new ModelAndView("redirect:/item/itemList.htm");
//        if(!catId.equals("$catId")){
//            view.addObject("categoryId", catId);
//        }
//        if(!itemName.equals("$itemNames")){
//            view.addObject("itemName", itemName);
//        }
        return view;
    }

    private void saveLog(HttpSession session, Item dto, String content){
        try{
            SysLog sysLog = new SysLog();
            sysLog.setContent(content);
            sysLog.setCreateTime(new Date());
            sysLog.setUserId(SessionHelper.getUserId(session));
            sysLog.setCityId(dto.getCityId());
            sysLog.setDataType(LogDataTypeEnum.ITEM.getName());
            if(dto.getId()!=null){
                sysLog.setDataId(dto.getId().toString());
            }

            String dataContent = new Gson().toJson(dto,
                    new TypeToken<Item>() {
                    }.getType());

            sysLog.setDataContent(dataContent);

            logService.createLog(sysLog);
        }catch(Exception e){
            logger.error("保存日志失败",e);
        }
    }


    /**
     * 根据简称返回简称
     * */
    @RequestMapping(value = "/autoItemNameQuery.do")
    @ResponseBody
    public  List<HashMap<String, Object>> autoItemNameQuery(HttpServletRequest request) {
        PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
        if(null == curUser.getCityId()){
            return new ArrayList<HashMap<String, Object>>();
        }
        return this.itemService.findItemNames(request.getParameter("itemName"), Integer.valueOf(request.getParameter("cityId")));
    }

    private Integer getCategoryByName(Integer categoryId, Integer cityId, Integer parentId) {

        if (categoryId != null) {
            ItemCategory category = this.itemCategoryService.findById(categoryId);
            if (category != null) {
                category = this.itemCategoryService.findByNameAndCityId(category.getCategoryName(), cityId, parentId);
                if (category != null) {
                    return category.getId();
                }
            }
        }
        return null;
    }

}
