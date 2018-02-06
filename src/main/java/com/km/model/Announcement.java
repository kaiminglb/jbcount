/** 

* @Title: Announcement.java

* @Package com.km.model

* @Description: 已发布公告信息实体类，映射原表News_GG

* @author hulikaimen@gmail.com

* @date 2017-7-13 上午2:56:38

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author PipiLu
 * @version 创建时间：2017-7-13 上午2:56:38
 * 类说明  已发布通告类
 */
/**     
 * Simple to Introduction       
 * @ClassName:    [Announcement] 公告类
 * @Package:      [com.km.model]    
 * @Description:  [一句话描述该类的功能]     
 * @Author:       [狐狸糊涂]     
 * @CreateDate:   [2017-7-13 上午2:56:38]     
 * @UpdateUser:   [狐狸糊涂]     
 * @UpdateDate:   [2017-7-13 上午2:56:38]     
 * @UpdateRemark: [说明本次修改内容]    
 * @Version:      [v1.0]   
 *      
 */
@Entity
@Table(name="News_GG")
public class Announcement {
	//id需要手动赋值
	private Integer id;
	//公告类型
	private Integer categoryID;
	
	//公告名称
	private String title;
	//author 作者 如 民一庭
	private String author;
	
	//添加时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedDate;
	
	//发布时间
	@Temporal(TemporalType.TIMESTAMP) 
	private Date releaseDate;
	
	//点击量 默认为0
	private Integer clickCount;
	
	//1为已发布,默认为0
	private Integer approved;
	
	//sortNum排序号同id
	private Integer sortNum;
	//公告开始时间、即发布时间
	@Temporal(TemporalType.DATE) 
	private Date anStart;
	
	//公告结束时间=开始时间+15天
	@Temporal(TemporalType.DATE) 
	private Date anEnd;
	
	//当事人
	private String party;
	
	//承办法官姓名
	private String courtJudge;
	
	//发布人,统一为"审判管理办公司"
	private String publisher;
	
	//打开方式，openType 默认为1
	private Integer openType;
	
	//html文件 项目路径 正反斜杠都可以
	private String htmlFilePath;
	//word原文件 项目路径
	private String wordFilePath;
	
	//pdf文件名,wordurl字段。不要路径
	private String pdfFileName;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(generator="custom")
    @GenericGenerator(name="custom", strategy="assigned")
	@Column(name="NewsID")
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the categoryID
	 */
	@Column(name="CategoryID", nullable = false)
	public Integer getCategoryID() {
		return categoryID;
	}

	/**
	 * @param categoryID the categoryID to set
	 */
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	/**
	 * @return the title
	 */
	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	@Column(name="zuozhe",nullable = false)
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the addedDate
	 */
	@Column(name = "addedDate", nullable = false, insertable = true, updatable = false)
	public Date getAddedDate() {
		return addedDate;
	}

	/**
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	/**
	 * @return the releaseDate
	 */
	@Column(name = "releaseDate", nullable = false, insertable = true, updatable = false)
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the clickCount
	 */
	@Column(nullable = false)
	public Integer getClickCount() {
		return clickCount;
	}

	/**
	 * @param clickCount the clickCount to set
	 */
	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	/**
	 * @return the approved
	 */
	@Column(nullable = false)
	public Integer getApproved() {
		return approved;
	}

	/**
	 * @param approved the approved to set
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	/**
	 * @return the sortNum
	 */
	@Column(nullable = false)
	public Integer getSortNum() {
		return sortNum;
	}

	/**
	 * @param sortNum the sortNum to set
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * @return the anStart
	 */
	@Column(name = "GS_StartTime",nullable = false,insertable = true,updatable = false)
	public Date getAnStart() {
		return anStart;
	}

	/**
	 * @param anStart the anStart to set
	 */
	public void setAnStart(Date anStart) {
		this.anStart = anStart;
	}

	/**
	 * @return the anEnd
	 */
	@Column(name = "GS_EndTime",nullable = false,insertable = true , updatable = false)
	public Date getAnEnd() {
		return anEnd;
	}

	/**
	 * @param anEnd the anEnd to set
	 */
	public void setAnEnd(Date anEnd) {
		this.anEnd = anEnd;
	}

	/**
	 * @return the party当事人
	 */
	@Column(name = "dangshiren",nullable = false)
	public String getParty() {
		return party;
	}

	/**
	 * @param party the party to set
	 */
	public void setParty(String party) {
		this.party = party;
	}

	/**
	 * @return the courtJudge 承办法官
	 */
	@Column(name = "cbfg",nullable = false)
	public String getCourtJudge() {
		return courtJudge;
	}

	/**
	 * @param courtJudge the courtJudge to set
	 */
	public void setCourtJudge(String courtJudge) {
		this.courtJudge = courtJudge;
	}

	/**
	 * @return the publisher
	 */
	@Column(name = "faburen",nullable = false)
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the openType
	 */
	@Column(nullable = false)
	public Integer getOpenType() {
		return openType;
	}

	/**
	 * @param openType the openType to set
	 */
	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	/**
	 * @return the htmlFilePath
	 */
	@Column(name = "OpenFilePath")
	public String getHtmlFilePath() {
		return htmlFilePath;
	}

	/**
	 * @param htmlFilePath the htmlFilePath to set
	 */
	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}

	/**
	 * @return the wordFilePath
	 */
	@Column(name = "SourceFile_Path")
	public String getWordFilePath() {
		return wordFilePath;
	}

	/**
	 * @param wordFilePath the wordFilePath to set
	 */
	public void setWordFilePath(String wordFilePath) {
		this.wordFilePath = wordFilePath;
	}

	/**
	 * @return the pdfFileName
	 */
	@Column(name = "wordurl")
	public String getPdfFileName() {
		return pdfFileName;
	}

	/**
	 * @param pdfFileName the pdfFileName to set
	 */
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	
	
	
}
