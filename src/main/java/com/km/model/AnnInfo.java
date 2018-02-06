/** 

* @Title: AnnInfo.java

* @Package com.km.model

* @Description: 公告信息类

* @author hulikaimen@gmail.com

* @date 2017-9-25 下午4:50:17

* @version V1.0 

*/ 
package com.km.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author PipiLu
 * @version 创建时间：2017-9-25 下午4:50:17
 * 类说明   初步处理，等待发布的通告信息
 */
/**     
 * Simple to Introduction       
 * @ClassName:    [AnnInfo]
 * @Package:      [com.km.model]    
 * @Description:  [一句话描述该类的功能]     
 * @Author:       [狐狸糊涂]     
 * @CreateDate:   [2017-9-25 下午4:50:17]     
 * @UpdateUser:   [狐狸糊涂]     
 * @UpdateDate:   [2017-9-25 下午4:50:17]     
 * @UpdateRemark: [说明本次修改内容]    
 * @Version:      [v1.0]   
 *      
 */
@Entity
@Table(name="t_annInfo")
public class AnnInfo {
	//
	private Integer annInfoID;
	/**公告类型*/
	private Integer categoryID;
	
	/**公告名称 如：张三判决公告 */
	private String title;
	/**author 作者部门 如 民一庭*/
	private String author;
	/**annCauseName 案由 */
	private String annCauseName;
	
	

	/**添加时间*/
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedDate;
	
	/**文档里时间 二○一七年九月十二日 需转为 20170912 */
	@Temporal(TemporalType.DATE)
	private Date wordDate;
//	//发布时间
//	@Temporal(TemporalType.TIMESTAMP) 
//	private Date releaseDate;
	
//	//点击量 默认为0
//	private Integer clickCount;
	
	
	/**1为已发布的标识,默认为0*/
	private Integer approved;
	
//	//sortNum排序号同id
//	private Integer sortNum;
	//公告开始时间、即发布时间
//	@Temporal(TemporalType.DATE) 
//	private Date anStart;
//	
//	//公告结束时间=开始时间+15天
//	@Temporal(TemporalType.DATE) 
//	private Date anEnd;
	
	/**当事人*/
	private String party;
	
	/**承办法官姓名*/
	private String courtJudge;
	
	/**发布人,统一为"审判管理办公室"*/
	private String publisher;
	
	//打开方式，openType 默认为1
//	private Integer openType;
	
	/**html文件 项目相对路径 正反斜杠都可以*/
	private String htmlFilePath;
	/**word原文件 项目相对路径*/
	private String wordFilePath;
	
	/**pdf文件名,wordurl字段，去掉开头的第一个'/' */
	private String pdfFileName;
	/**txt文件名*/
	private String txtFileName;
	
	
	

	@Id
	@GeneratedValue
	public Integer getAnnInfoID() {
		return annInfoID;
	}

	public void setAnnInfoID(Integer annInfoID) {
		this.annInfoID = annInfoID;
	}

	public String getAnnCauseName() {
		return annCauseName;
	}

	public void setAnnCauseName(String annCauseName) {
		this.annCauseName = annCauseName;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	
	public Date getWordDate() {
		return wordDate;
	}

	public void setWordDate(Date wordDate) {
		this.wordDate = wordDate;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column(name = "addedDate", nullable = false, insertable = true, updatable = false)
	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getCourtJudge() {
		return courtJudge;
	}

	public void setCourtJudge(String courtJudge) {
		this.courtJudge = courtJudge;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getHtmlFilePath() {
		return htmlFilePath;
	}

	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}

	public String getWordFilePath() {
		return wordFilePath;
	}

	public void setWordFilePath(String wordFilePath) {
		this.wordFilePath = wordFilePath;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	
	public String getTxtFileName() {
		return txtFileName;
	}

	public void setTxtFileName(String txtFileName) {
		this.txtFileName = txtFileName;
	}

	/**
	 * 无参构造方法
	* <p>Title: </p>   
	* <p>Description: </p>
	 */
	public AnnInfo(){
		super();
		this.addedDate = new Date();
		this.approved = 0 ;
		this.publisher = "审判管理办公室";
	}
	
	/**   
	* <p>Title: </p>   
	* <p>Description: word原文件 项目相对路径的构造函数 </p>   
	* @param wordFilePath   比如 {yyyyMMdd}/xxxxx.docx
	*/
	public AnnInfo(String wordFilePath) {
		super();
		this.wordFilePath = wordFilePath;
		
		this.addedDate = new Date();
		this.approved = 0 ;
		this.publisher = "审判管理办公室";
	}
	

	@Override
	public String toString() {
		return "AnnInfo [annInfoID=" + annInfoID + ", categoryID=" + categoryID
				+ ", title=" + title + ", author=" + author + ", annCauseName="
				+ annCauseName + ", addedDate=" + addedDate + ", wordDate="
				+ wordDate + ", approved=" + approved + ", party=" + party
				+ ", courtJudge=" + courtJudge + ", publisher=" + publisher
				+ ", htmlFilePath=" + htmlFilePath + ", wordFilePath="
				+ wordFilePath + ", pdfFileName=" + pdfFileName
				+ ", txtFileName=" + txtFileName + "]";
	}
	
	
}
