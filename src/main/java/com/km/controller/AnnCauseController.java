/**
 * @Title: AnnCauseController.java
 * @Package com.km.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hulikaimen@gmail.com
 * @date 2018年1月16日 上午1:07:18
 * @version V1.0
 */
package com.km.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.km.common.PinyinUtils;
import com.km.common.ResultUtils;
import com.km.common.enums.ResultEnum;
import com.km.common.exception.CustomException;
import com.km.model.AnnCause;
import com.km.model.DataSourceRequest;
import com.km.model.DataSourceResult;
import com.km.model.Result;
import com.km.model.SimpleDataSourceResult;
import com.km.service.AnnCauseService;

/**
 * @author PipiLu
 * @version 创建时间：2018年1月16日 上午1:07:18
 * 类说明
 */
@Controller
@RequestMapping(value = "/annCause")
public class AnnCauseController {
    @Autowired
    AnnCauseService annCauseService;

    // 放上傳案由excel的位置
    @Value("${annCause}")
    private String uploadDirPath;

    @RequestMapping(value = {"/", "/mgr"}, method = RequestMethod.GET)
    public String index() {
        return "AnnCause";
    }


    //1 增、删、改、一般读

    /**
     * 返回所有对象的查询
     */
    @RequestMapping(value = "/readAll", method = RequestMethod.POST)
    public @ResponseBody
    List<AnnCause> read() {
        return annCauseService.getList();
    }

    /**
     * 插入仅返回一个对象，客户端datasource只用model来接，没total、data选项
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    AnnCause create(@RequestBody Map<String, Object> model) {
        AnnCause annCause = new AnnCause();

        String name = (String) model.get("name");
        annCause.setName(name);
        annCause.setJianPin(PinyinUtils.converterToFirstSpell(name));
        annCause.setQuanPin(PinyinUtils.getPingYin(name));
        annCauseService.saveOrUpdate(annCause);
        return annCause;
    }

    /**
     * 普通更新仅返回一个对象,客户端datasource只用model来接，没total、data选项
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    AnnCause update(@RequestBody Map<String, Object> model) throws CustomException {
        AnnCause annCause = new AnnCause();

        String name = ((String) model.get("name")).trim();
        annCause.setAnnCauseID((Integer) model.get("annCauseID"));
        annCause.setName(name);
        annCause.setJianPin(PinyinUtils.converterToFirstSpell(name));
        annCause.setQuanPin(PinyinUtils.getPingYin(name));
        annCauseService.saveOrUpdate(annCause);
        return annCause;
    }


    /**
     * 带分页、查询条件的查询，服务端分页
     */
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public @ResponseBody
    DataSourceResult read(@RequestBody DataSourceRequest request) {
        return annCauseService.getList(request);
    }

    /**
     * 插入，返回包装对象（含有List）；数据在data属性对应列表中；客户端datasource需配置data项接收一列表
     */
    @RequestMapping(value = "/createItem", method = RequestMethod.POST)
    public @ResponseBody
    SimpleDataSourceResult createItem(@RequestBody Map<String, Object> model) {
        AnnCause annCause = new AnnCause();

        String name = (String) model.get("name");
        annCause.setName(name);
        annCause.setJianPin(PinyinUtils.converterToFirstSpell(name));
        annCause.setQuanPin(PinyinUtils.getPingYin(name));
        annCauseService.saveOrUpdate(annCause);
        return new SimpleDataSourceResult(annCause);
    }


    /**
     * 更新，返回包装对象（含有List））；数据在data属性对应列表中；客户端datasource需配置data项接收一列表
     */
    @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
    public @ResponseBody
    SimpleDataSourceResult updateItem(@RequestBody Map<String, Object> model) throws CustomException {
        AnnCause annCause = new AnnCause();

        String name = ((String) model.get("name")).trim();
        annCause.setAnnCauseID((Integer) model.get("annCauseID"));
        annCause.setName(name);
        annCause.setJianPin(PinyinUtils.converterToFirstSpell(name));
        annCause.setQuanPin(PinyinUtils.getPingYin(name));
        annCauseService.saveOrUpdate(annCause);
        return new SimpleDataSourceResult(annCause);
    }

    @RequestMapping(value = "/destroy", method = RequestMethod.POST)
    public @ResponseBody
    AnnCause destroy(@RequestBody Map<String, Object> model) {
        AnnCause target = new AnnCause();
        target.setAnnCauseID((Integer) model.get("annCauseID"));
        annCauseService.delete(target);
        return target;
    }

    //2 根据拼音简称，查询案由
    @RequestMapping(value = "/readCode", method = RequestMethod.POST)
    public @ResponseBody
    DataSourceResult readCode(@RequestBody DataSourceRequest request) {
        //若查询条件为空或者分页信息为空，返回空的DataSourceResult
        /*
         * 客户端处理，没查询关键字不提交request
		if(request.getFilter().getFilters().isEmpty()){
			DataSourceResult result = new DataSourceResult();
			result.setAggregates(null);
			result.setData(null);
			result.setTotal(0);
			return result;
		}
		*/
        //正常查询返回
        return annCauseService.getList(request);
    }

    /**
     * 只传一个filter过来，用model去接需要的查询关键字
     */
    @RequestMapping(value = "/readCodeByKey", method = RequestMethod.POST)
    public @ResponseBody
    SimpleDataSourceResult readCodeByKey(@RequestBody Map<String, Object> model) {
        //正常查询返回
        String key = (String) model.get("value");

        return new SimpleDataSourceResult(annCauseService.findAnnCauseByJianPin(key));
    }

    /**
     * @param model
     * @return 设定文件
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping(value = "/checkByName", method = RequestMethod.POST)
    public @ResponseBody
    Boolean checkByName(String p) {
        //正常查询返回
//		Result r;
//		if(annCauseService.checkExistedByAnnCauseName(p)){
//			r = ResultUtils.error(ResultEnum.ANNCAUSEEXISTED);
//		}else{
//			r= ResultUtils.success();
//		}
        return !annCauseService.checkExistedByAnnCauseName(p);
    }


    // TODO 从excel批量导入案由
    @RequestMapping(value = "/createBatchFromExcel", method = RequestMethod.POST)
    public @ResponseBody
    Result createBatch(HttpServletRequest request,
                       @RequestParam MultipartFile file) {
        // 如果为excel 2007以上格式文件，转存；否则不做处理;2003的放弃支持
        if (!FileUtils.isXlsx(file.getOriginalFilename()))
            return null;

        // 不同容器下，可能得到空值；用配置的方式兼容性最好
        // String uploadPath = request.getServletContext().getRealPath("./")
        // + File.separator + "upload";
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }


        // 转存文件
        file.transferTo(localFile);

        // 处理excel

        Result result = ResultUtils.success();
        return null;
    }
    // TODO 从excel批量导入案由


}
