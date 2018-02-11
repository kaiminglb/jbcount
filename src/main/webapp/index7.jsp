<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>grid的remote validation</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value='/resources/styles/kendo.common.min.css'/>"
	rel="stylesheet" />
<link href="<c:url value='/resources/styles/kendo.rtl.min.css'/>"
	rel="stylesheet" />
<%-- <link href="<c:url value='/resources/styles/kendo.default.min.css'/>"  
    rel="stylesheet" />  --%>
<link href="<c:url value='/resources/styles/kendo.blueopal.min.css'/>"
	rel="stylesheet" />
<link href="<c:url value='/resources/custom/custom.css'/>"
	rel="stylesheet" />

<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script src="<c:url value='/resources/js/jszip.min.js' />"></script>
<script src="<c:url value='/resources/js/kendo.all.min.js' />"></script>
<script
	src="<c:url value='/resources/js/messages/kendo.messages.zh-CN.min.js' />"></script>
<script
	src="<c:url value='/resources/js/cultures/kendo.culture.zh-CN.min.js' />"></script>
<script>
	kendo.culture("zh-CN");
</script>

</head>
<body>
	<a class="offline-button" href="#">Back</a>

	<div id="tabstrip"></div>

	<script>
		$(function() {
		});
	</script>


	<c:url value="/annCause/readAll" var="readUrl" />
	<c:url value="/annCause/create" var="createUrl" />
	<c:url value="/annCause/update" var="updateUrl" />
	<c:url value="/annCause/destroy" var="destroyUrl" />
	
	<c:url value="/annCause/checkByName" var="check" />
	<!-- 案由增删改查  -->

	<div id="example">
		<!-- 错误、成功 通知 -->
		<span id="centeredNotification" style="display:none;"></span>

		<div id="grid"></div>
		
		<div id="upload"></div>
		<script>
			//显示通知组件
			function onShow(e) {
				if (e.sender.getNotifications().length == 1) {//哪个组件发出的通知，就在其中间显示
					var element = e.element.parent(), // 
					eWidth = element.width(), //
					eHeight = element.height(), //
					wWidth = $(window).width(), //
					wHeight = $(window).height(), //
					newTop, newLeft;

					newLeft = Math.floor(wWidth / 2 - eWidth / 2);
					newTop = Math.floor(wHeight / 2 - eHeight / 2);

					e.element.parent().css({
						top : newTop,
						left : newLeft
					});
				}
			}
			
			//显示上传窗体
			function batchFromExcel(e) {
                e.preventDefault();
				console.info(this);//batchFromExcel执行的上下文，grid
                var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
                wnd.content(detailsTemplate(dataItem));
                wnd.center().open();
            }
			
			
			$(function() {
				//初始化通知插件
				var centered = $("#centeredNotification").kendoNotification({
					autoHideAfter : 4000,
					stacking : "down",
					show : onShow,
					button : true
				}).data("kendoNotification");

				//初始化上传窗体
			    var wnd = $("#upload")
                   .kendoWindow({
                       title: "案由批量上传",
                       modal: true,
                       visible: false,
                       resizable: false,
                       width: 300
                   }).data("kendoWindow");
				
				$("#grid").kendoGrid({
					dataSource : {
						requestEnd : function(e) {//结束提示成功
							var actionType = e.type;
							if (actionType == "create" || e.action == "update" || e.action == "destroy") {//
								console.info(actionType);
								centered.show("数据已提交 ", "info");
							}
						},
						error : function(e) {//远程异常
							//console.info(e);
							if (e.errors) {//model中定义errors接收异常信息，解析自定义异常信息得到
								//alert("Error: " + e.errors);
								//添加错误提示
								centered.show(e.errors.code + "-" + e.errors.msg, "error");
								// This will cancel any change done
								this.cancelChanges();
							}
						},
						//type: "odata", //微软特定的格式
						transport : {
							read : {
								url : "${readUrl}",
								contentType : "application/json",
								type : "POST",
								dataType : "json"
							},
							update : {
								url : "${updateUrl}",
								contentType : "application/json",
								type : "POST",
								dataType : "json"
							},
							destroy : {
								url : "${destroyUrl}",
								contentType : "application/json",
								type : "POST",
								dataType : "json"
							},
							create : {
								url : "${createUrl}",
								contentType : "application/json",
								type : "POST",
								dataType : "json"
							},
							parameterMap : function(options, operation) {
								//console.info(options);
								//读返回所有数据，无需传查询条件
								 if (operation !== "read") {
									 return kendo.stringify(options);
									 //return {models: kendo.stringify(options.models)};//增、删、改返回对象;schema没有data选项
								} 
								//return kendo.stringify(options);//查询参数
							}

						},
						schema : {//The configuration used to parse the remote service response.
							errors : function(response) {//接受异常的回调.response 返回封装异常的对象
								//console.info(this);
								//console.info(response);
								if (response.code && response.code !== 0) {//0代表正常；code异常代码,msg异常消息
									//return response.msg;
									return response;
								}
							},
							// 直接用Model接返回数据集的一条数据。服务端直接返回List或者一个model对应的对象
							//total : "total",
							//data : "data",
							//groups : "data",
							model : {//model The data item (model) configuration.
								id : "annCauseID",
								fields : {
									annCauseID : {
										editable : false,
										nullable : true
									},//不可编辑
									name : {
										type : "string",
										validation: {//验证规则
				                            required: true,
				                            //全 汉字 
				                            allchin: function(input){
				                            	if (input.is("[name='name']") && input.val() != "") {
				                            		var r = /^[\u4e00-\u9fa5]+$/.test(input.val());
				                                    if(r==false){ input.attr("data-allchin-msg", "案由名称必须为中文");}
				                            		return r;
				                            	}
				                            	return true;
				                            },
				                            //远程唯一验证
				                            custom: function(input) {
				                            	 if (input.is("[name='name']") && input.val() != "") {
				                            		 
				                            		 //input.attr("data-namevalidation-msg", "案由名称已添加");
                                                     var isValid;

                                                     input.attr("data-custom-msg", "checking...");
                                                     $.ajax({
                                                         // This is key, otherwise request will be made asynchronously and you won't get your response from the server when needed
                                                         url: "${check}", 
                                                         async: false, //必须同步，不然那返回值为undefined
                                                          type: 'POST',
	                           					          dataType: 'json',
	                           					          data: { p : input.val() },
			                           					  beforeSend: function () {
			                           						 //同步请求下,beforeSend无效
	                                                	  	//input.attr("data-custom-msg", "checking...");
			                           					  },
	                                                  })
                                                       // My action is a JsonResult that returns either true or false (no quotes, etc.)
                                                       .done(function (data) {
                                                    	   isValid = data;
                                                    	   if(isValid===false){input.attr("data-custom-msg", "案由名称已添加");}
                                                       })
                                                       .fail(function (request, status, error) {
                                                         isValid = true;//网络超时或异常验证通过，不让客户等待
                                                       })
                                                       .always(function (data) {
                                                    	   //input.attr("data-custom-msg", "案由名称已添加");
                                                        });
                                                       
                                                     console.info(isValid);
                                                     return isValid;
                                                 }

                                                 return true;
				                            }
				                         }//end validation
									},
									quanPin : {
										editable : false,
										type : "string"
									},
									jianPin : {
										editable : false,
										type : "string"
									}
								}
							}
						//end model
						},//end schema
						//pageSize : 10,//分页
						//serverPaging : true,//服务端分页
						//serverFiltering : true,//服务端过滤
					},//end dataSource

					height : 600,//grid高度
					/* filterable : {//grid row查询
						mode : "row"
					}, */
					//pageable : true,//展示分页组件
					pageable : {
						pageSize : 20,//客户端分页
						refresh : true
					},
					sortable : true,//排序 
					toolbar : [ "create" ,
					    {
						    name: "batchExcel",
						    text: "批量添加",
						    click: batchFromExcel}
					 	}            
					],//新建菜单
					editable : "popup",//弹出编辑

					columns : //显示列设置
					[ {
						field : "annCauseID",
						title : "ID",
						filterable : false,//a filter menu  display whether是否展示过滤菜单
						width : "50px",
						sortable : {
							initialDirection : "desc"
						},
						filterable : {//不显示查询
							cell : {showOperators : false}
						}
					}, {
						field : "name",width : "100px",title : "案由",//
						filterable : {
							cell : {operator : "contains",suggestionOperator : "contains"}//
						}
					}, {
						sortable : false,field : "quanPin",title : "全拼",width : "150px",//
						filterable : {
							cell : {showOperators : false}
						}
					}, {
						field : "jianPin",
						title : "简拼",
						width : "100px",
						filterable : {
							cell : {
								operator : "contains",
								suggestionOperator : "contains"
							}
						}
					}, {
						command : [ "edit", "destroy" ],
						title : "&nbsp;",
						width : "200px"
					} //编辑，删除
					]//end columns
				});//end gird
			});
			
		</script>
	</div>

</body>
</html>
