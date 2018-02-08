<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value='/resources/styles/kendo.common.min.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/styles/kendo.rtl.min.css'/>" rel="stylesheet" />
<%-- <link href="<c:url value='/resources/styles/kendo.default.min.css'/>"  
    rel="stylesheet" />  --%>
<link href="<c:url value='/resources/styles/kendo.blueopal.min.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/custom/custom.css'/>" rel="stylesheet" />

<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script src="<c:url value='/resources/js/jszip.min.js' />"></script>
<script src="<c:url value='/resources/js/kendo.all.min.js' />"></script>
<script src="<c:url value='/resources/js/messages/kendo.messages.zh-CN.min.js' />"></script>
<script src="<c:url value='/resources/js/cultures/kendo.culture.zh-CN.min.js' />"></script>
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
		$(function() {
		});

		$("#tabstrip").kendoTabStrip({
			dataTextField : "Name",
			dataContentUrlField : "ContentUrl",
			dataSource : [ {
				Name : "上传",
				ContentUrl : "html/upload.html"
			}, {
				Name : "审核",
				ContentUrl : "html/auditing.html"
			} ]
		}).data("kendoTabStrip").select(0);
	</script>


	<c:url value="/annCause/readAll" var="readUrl" />
	<c:url value="/annCause/create" var="createUrl" />
	<c:url value="/annCause/update" var="updateUrl" />
	<c:url value="/annCause/destroy" var="destroyUrl" />
	<!-- 案由增删改查  -->

	<div id="example">
		<!-- 错误通知 -->
		<span id="centeredNotification" style="display:none;"></span>

		<div id="grid"></div>
		<script>
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

			$(function() {
				var centered = $("#centeredNotification").kendoNotification({
					autoHideAfter : 4000,
					stacking : "down",
					show : onShow,
					button : true
				}).data("kendoNotification");

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
							if (e.errors) {//errors解析自定义异常信息得到
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
								/* if (operation !== "read"&& options.models) {
									 return {models: kendo.stringify(options.models)};//增、删、改返回对象
								} */
								return kendo.stringify(options);//查询参数
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
										type : "string"
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
						pageSize : 10,//分页
						serverPaging : true,//服务端分页
						serverFiltering : true,//服务端过滤
					},//end dataSource

					height : 600,//grid高度
					filterable : {//grid row查询
						mode : "row"
					},
					//pageable : true,//展示分页组件
					pageable : {
						//pageSize : 20,//客户端分页
						refresh : true
					},
					sortable : true,//排序 
					toolbar : [ "create" ],//新建菜单
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
