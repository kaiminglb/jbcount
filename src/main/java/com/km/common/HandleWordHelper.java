/** 

 * @Title: HandleWordUtils.java

 * @Package com.km.common

 * @Description: jacob处理word转化

 * @author hulikaimen@gmail.com

 * @date 2017年10月29日 下午4:50:52

 * @version V1.0 

 */
package com.km.common;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.km.model.AnnInfo;
import com.km.service.AnnInfoService;

/**
 * @author PipiLu
 * @version 创建时间：2017年10月29日 下午4:50:52 类说明 用jacob处理word转化
 * 类说明 1、设置word字体 
 * 		 2、word转html,pdf
 * 		 3、提取公告信息
 */
@Component("handleWordHelper")
public class HandleWordHelper {
	@Autowired
	private AnnInfoService annInfoService;

	// word文档
	private Dispatch doc = null;
	// word运行程序对象
	private ActiveXComponent word = null;
	// 所有word文档集合
	private Dispatch documents = null;

	@Value("${ann.pdf}")
	private String prefixPdfPath;
	@Value("${ann.txt}")
	private String prefixTxtPath;
	@Value("${ann.html}")
	private String prefixHtmlPath;
	
	// 0保存为word
	public static final int WORD = 0;
	// 8 代表word保存成html
	public static final int WORD_HTML = 8;
	// 代表word保存成pdf
	public static final int WORD_PDF = 17;
	// 代表word保存成txt
	public static final int WORD_TXT = 7;

	/**
	 *        * 另存为其他格式文档       * 
	 *      * saveFormat |  Member name                 Description 
	 *      *     0      |  wdFormatDocument            Microsoft Word format. 
	 *      *     1      |  wdFormatTemplate            Microsoft Word template 
	 * format.
	 *  *     2      |  wdFormatText                Microsoft Windows text
	 *  format.      
	 *      3      |  wdFormatTextLineBreaks      Microsoft Windows
	 *  text format with line breaks preserved. 
	 *      *     4      |  wdFormatDOSText
	 *              Microsoft DOS text format. 
	 *      *     5      |  wdFormatDOSTextLineBreaks
	 *    Microsoft DOS text with line breaks preserved. 
	 *      *     6      |  wdFormatRTF
	 *                  Rich text format (RTF).      
	 *      7      |  wdFormatEncodedText         Encoded text format.      
	 *      7      |  wdFormatUnicodeText         Unicode text format.      
	 *      8      |  wdFormatHTML                Standard HTML format.      
	 *      9      |  wdFormatWebArchive          Web archive format.      
	 *      10     |  wdFormatFilteredHTML        Filtered HTML format. 
	 *      *  
	 *    11     |  wdFormatXML                 Extensible Markup Language
	 *  (XML) format. 
	 * 
	 * @Description: 异步把指定的word转html,txt,pdf
	 * @param id
	 *            对应的公告id
	 * @param docPath
	 *            word文件的绝对路径
	 * @return 设定文件
	 */
	@Async
	public  void batchWord(Integer id, String docPath) {
		ComThread.InitMTA(true);// 线程启动
		// 文本内容
		String content;
		// 各种文件保存路径
		String pdfPath = prefixPdfPath + FileUtils.wordToPdfFilePath(docPath);
		String txtPath = prefixTxtPath + FileUtils.wordToTxtFilePath(docPath);
		String htmlPath = prefixHtmlPath
				+ FileUtils.wordToHtmlFilePath(docPath);

		long start = System.currentTimeMillis();
		System.out.println(docPath + "开始转化......");
		// 启动word程序
		word = new ActiveXComponent("Word.Application");
		// 设置程序不可见
		word.setProperty("Visible", new Variant(false));
		// word.setProperty("Visible", new Variant(true));
		// 禁用宏
		word.setProperty("AutomationSecurity", new Variant(3));
		// 获取所有文档
		documents = word.getProperty("Documents").toDispatch();
		// 获取当前打开的文档
		/*  是否进行转换ConfirmConversions  */
		/*  是否只读  */
//		doc = Dispatch.call(documents, "Open", docPath, true, false).toDispatch();
		doc = Dispatch.call(documents, "Open", docPath).toDispatch();
		System.out.println("打开文档--" + docPath);
		// 转化
		try {

			File pdfFile = new File(pdfPath);
			File txtFile = new File(txtPath);
			File htmlFile = new File(htmlPath);
			// 若目标文件夹不存在，则创建
			File pdfDir = pdfFile.getParentFile();
			File txtDir = txtFile.getParentFile();
			File htmlDir = htmlFile.getParentFile();
			if (!pdfDir.exists()) {
				pdfDir.mkdirs();
			} else {// 若目标文件存在删除
				if (pdfFile.exists())
					pdfFile.delete();
			}

			if (!txtDir.exists()) {
				txtDir.mkdirs();
			} else {// 若目标文件存在删除
				if (txtFile.exists())
					txtFile.delete();
			}

			if (!htmlDir.exists()) {
				htmlDir.mkdirs();
			} else {// 若目标文件存在删除
				if (htmlFile.exists())
					htmlFile.delete();
			}
			// 1、调整word的字体、样式
			// 1.1 取得word文件的内容
			Dispatch wordContent = Dispatch.get(doc, "Content").toDispatch();
			// 1.2 设置段落的样式
			// 所有段落
			Dispatch paragraphs = Dispatch.get(wordContent, "Paragraphs")
					.toDispatch();
			// 一共的段落数
			int paragraphCount = Dispatch.get(paragraphs, "Count").toInt();
			// 一、二段为标题，设置字体样式；其余另一个字体
			for (int i = 1; i <= paragraphCount; i++) {
				// 当前段
				Dispatch currentParagraph = Dispatch.call(paragraphs, "Item",
						new Variant(i)).toDispatch();
				Dispatch currentParagraphRange = Dispatch.get(currentParagraph,
						"Range").toDispatch();
				Dispatch font = Dispatch.get(currentParagraphRange, "Font")
						.toDispatch();
				if (i == 1 || i == 2) {
					Dispatch.put(font, "Bold", new Variant(true)); // 设置为黑体
					Dispatch.put(font, "Italic", new Variant(false)); // 设置为斜体
					Dispatch.put(font, "Name", new Variant("方正小标宋_GBK")); //
					Dispatch.put(font, "Size", new Variant(26)); // 一号
				} else {
					Dispatch.put(font, "Bold", new Variant(false)); // 设置为黑体
					Dispatch.put(font, "Italic", new Variant(false)); // 设置为斜体
					Dispatch.put(font, "Name", new Variant("方正仿宋_GBK")); //
					Dispatch.put(font, "Size", new Variant(16)); // 三号
				}
			}
			/** 
			Dispatch currentParagraph = Dispatch.call(paragraphs, "Item",
					new Variant(1)).toDispatch();
			Dispatch currentParagraphRange = Dispatch.get(currentParagraph,
					"Range").toDispatch();
			Dispatch font = Dispatch.get(currentParagraphRange, "Font")
					.toDispatch();
			Dispatch.put(font, "Bold", new Variant(true)); // 设置为黑体
			Dispatch.put(font, "Italic", new Variant(false)); // 设置为斜体
			Dispatch.put(font, "Name", new Variant("方正小标宋_GBK")); //
			Dispatch.put(font, "Size", new Variant(26)); // 一号
			*/
			
			// 1.3 保存word
			Dispatch.call(doc, "Save");
//			Dispatch.call(doc, "SaveAs", "d:/test.docx", 0);
			System.out.println(docPath + "字体大小已设置");

			// 2、调用另存为pdf、txt、html命令
			// Dispatch.call(doc, "SaveAs", outFileName,new Variant(17));
			Dispatch.call(doc, "SaveAs", pdfPath, WORD_PDF);
			// 调用另存为txt命令
			Dispatch.call(doc, "SaveAs", txtPath, WORD_TXT);
			// 调用另存为html命令
			Dispatch.call(doc, "SaveAs", htmlPath, WORD_HTML);

			System.out.println(docPath + "转化成功");

			// 从文本中提取有用信息
			content = FileUtils.extractTxt(txtPath);
			//TODO从文本中提取有用信息到对象

			// TODO保存文件存数据库
			// AnnInfo annInfo = annInfoService.queryAnnInfoById(id);
			// annInfo.setHtmlFilePath("");
			/** 去掉 FileUtils.wordToPdfFilePath(docPath) 返回字符串的首字母'/' */
			// annInfo.setPdfFileName("");
			// annInfo.setTxtFileName("");
			//
			// System.out.println(docPath + "数据库保存成功");
		} catch (Exception e) {
			// TODO: handle exception 记录日志信息，时间 哪个文档处理异常
			System.out.println(e);
		} finally {
			// 关闭当前文档，word，以及进程
			// 关闭文档且不保存
			// Dispatch.call(doc, "Close", false);
			System.out.println("关闭Word文档");
			Dispatch.call(doc, "Close", true);
			doc = null;
			System.out.println("关闭Word进程");
			if (word != null) {
				word.invoke("Quit", new Variant[] {});
				word = null;
			}
			documents = null;
			ComThread.Release();
			ComThread.quitMainSTA();
		}

		long end = System.currentTimeMillis();

		System.out.println("转存、提取公告信息耗时：" + (end - start) + "ms.");
	}
}
