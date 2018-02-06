/** 



* @Title: ExtractHelper.java

* @Package com.km.common

* @Description: 从文档中提取信息工具类

* @author hulikaimen@gmail.com

* @date 2017年11月17日 下午11:44:12

* @version V1.0 

*/ 
package com.km.common;

import org.springframework.stereotype.Component;

import com.km.model.AnnInfo;

/**
 * @author PipiLu
 * @version 创建时间：2017年11月17日 下午11:44:12
 * 类说明 从文档中提取对象信息。
 * 需根据已有的书记员、案由等信息处理
 */
@Component
public class ExtractHelper {
	public static String NORESULT = "没找到";
	/** 根据文本提取AnnInfo的字段
	 * 当事人 party;
	 * 		正文首行
	 * 		易吉生、张小红：
	 * 		特殊情况 被执行人周府亮及相关利害关系人：
	 * 承办法官姓名 courtJudge，与author有对应关系
	 * 		1、承办人：江朝丽
	 * 		2 金融庭联系人：蒋思斯（，或者空格）
	 * 		3 没有
	 * author 作者 如 民一庭
	 * 		金融庭联系人：蒋思斯 这种情况有、否则只有通过承办法官关联
	 * 公告类型 Integer categoryID字段
	 * 		匹配关键字，根据公告名称分为4种类型  开庭 判决 上诉  其他
	 * 公告名称 String title
	 * 		匹配关键字  其他公告里面分了好多种
	 * 案由 String annCauseName
	 * 		匹配关键字
	 * 文档中日期 wordDate
	 * 
	 * 最终格式 20170818—民一庭—王健—劳务合同纠纷—张德达判决公告
	 * 
	 * */
	public AnnInfo extractFromText(String text){
		AnnInfo annInfo = new AnnInfo();
		//当事人 party   截取 第一个中文：与前一个回车之间
		annInfo.setParty(TxtUtils.extractParty(text));
		//承办法官姓名 courtJudge
		annInfo.setCourtJudge(TxtUtils.extractCourtJudge(text));
		//author 作者 如 民一庭
		String author = TxtUtils.extractAuthor(text);
		//TODO 从缓存中查
		if(NORESULT.equals(author))
			 author = null;
		annInfo.setAuthor(author);
		//
		
		//
		
		return null;
	}
}
