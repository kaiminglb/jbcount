

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
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


	<c:url value="/annCause/read" var="readUrl" />
	<c:url value="/annCause/createItem" var="createUrl" />
	<c:url value="/annCause/updateItem" var="updateUrl" />
	<c:url value="/annCause/destroy" var="destroyUrl" />
	<!-- 案由增删改查  -->

	<div id="example">
		<!-- 错误通知 -->
		<span id="centeredNotification" style="display:none;"></span>

		<div id="grid"></div>
		<script>
			function onShow(e) {
				if (e.sender.getNotifications().length == 1) {
					var element = e.element.parent(), eWidth = element.width(), eHeight = element
							.height(), wWidth = $(window).width(), wHeight = $(
							window).height(), newTop, newLeft;

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
							total : "total",
							data : "data",
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
						pageSize : 10,
						serverPaging : true,
						serverFiltering : true,
					},//end dataSource

					height : 600,//grid高度
					/* filterable : {//grid查询
						mode : "row"
					}, */
					//pageable : true,//展示分页组件
					pageable : {
						//pageSize : 20,
						refresh : true
					},
					sortable : true,
					toolbar : [ "create" ],//新建菜单
					editable : "popup",//弹出编辑

					columns : //显示列设置
					[ {
						field : "annCauseID",
						filterable : false,//a filter menu  display whether
						width : "50px"
					}, {
						field : "name",
						width : "100px",
						title : "案由",
						filterable : {
							cell : {
								operator : "contains",
								suggestionOperator : "contains"
							}
						}
					}, {
						sortable : false,
						field : "quanPin",
						title : "全拼",
						width : "60px",
						filterable : {
							cell : {
								showOperators : false
							}
						}
					}, {
						field : "jianPin",
						title : "简拼",
						width : "60px",
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
					]
				//end columns

				});//end gird

			});
		</script>
	</div>


</body>
</html>
