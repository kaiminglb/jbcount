/** 

 * @Title: UploadController.java

 * @Package com.km.control

 * @Description: 上传Controller

 * @author hulikaimen@gmail.com

 * @date 2017年10月7日 上午12:15:26

 * @version V1.0 

 */
package com.km.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.km.common.FileUtils;
import com.km.common.HandleWordHelper;
import com.km.model.AnnInfo;
import com.km.service.AnnInfoService;

/**
 * @author PipiLu
 * @version 创建时间：2017年10月7日 上午12:15:26 类说明
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {
	@Autowired
	private AnnInfoService annInfoService;
	@Autowired
	HandleWordHelper handleWordHelper;
	
	//private String prefixDocPath = "d:/ann/word";
	@Value("${ann.word}")
	private String prefixDocPath;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(@RequestParam List<MultipartFile> files) {
		if (files != null && files.size() > 0) {
			// Save the files
			for (MultipartFile file : files) {
				if (!FileUtils.isDoc(file.getOriginalFilename()))
					continue;

				// 保存文件到指定位置
				// 文件保存路径
				// String filePath =
				// request.getSession().getServletContext().getRealPath("/") +
				// "upload/"
				// + file.getOriginalFilename();
				//若文件夹不存在，创建
				
				String dateStr = FileUtils.getDateStr();
				// relativePath 相对路径 比如 {yyyyMMdd}/xxxxx.docx
				String relativePath = dateStr + "/" + file.getOriginalFilename();
				// 判断文件夹{yyyyMMdd}是否存在
				File directory = new File( prefixDocPath + "/" + dateStr );
				//若是文件，则删除
				if(directory.exists() && directory.isFile())	directory.delete();
				//若目录不存在，则创建
				if(!directory.exists()) directory.mkdirs();
				String filePath = prefixDocPath + "/" + relativePath;
				
				// 转存成功
				if (saveFile(file, filePath)) {
					// 存数据库
					AnnInfo annInfo = new AnnInfo(relativePath);
					annInfoService.saveOrUpdate(annInfo);
					// TODO 此处需验证能获取id吗
					Integer id = annInfo.getAnnInfoID();
					//异步处理word
					handleWordHelper.batchWord(id, filePath);
				}
			}
		}

		// Return an empty string to signify success
		return "";
	}

	/**
	 * 
	 * @Description: 保存上传文件MultipartFile到指定位置
	 * @param file
	 *            MultipartFile
	 * @param filePath
	 *            转存的相对路径，比如 20171008/xxxxx.docx
	 * @return 是否保存成功
	 */
	private boolean saveFile(MultipartFile file, String filePath) {
		// 判断file是否为空
		if (!file.isEmpty()) {
			try {
				File localFile = new File(filePath);
				// 同名文件,代表已上传，不处理
				if (localFile.exists())
					return false;
				// 转存文件
				file.transferTo(localFile);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody String remove(@RequestParam String[] fileNames) {
		// Remove the files
		for (String fileName : fileNames) {
			// 判断对应目录下文件是否存在
			// relativePath 相对路径 比如 {yyyyMMdd}/xxxxx.docx
			String relativePath = FileUtils.getDateStr() + "/"
					+ fileName;
			// prefixPath从配置文件中读取，或者读取虚拟路径的实际路径
			String filePath = prefixDocPath + "/" + relativePath;
			File f = new File(filePath);
			// 删除
			if(f.exists() && f.isFile()) f.delete();
			//从数据库中删除对应的数据
			annInfoService.deleteAnnInfoByWord(relativePath);
		}
		// Return an empty string to signify success
		return "";
	}
}
