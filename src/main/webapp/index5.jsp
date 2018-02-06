

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


	<c:url value="/annCause/readCodeByKey" var="readUrl" />

	<!-- 案由combox  -->
	<input id="annCause" placeholder="输入案由拼音缩写" style="width: 100%;" />

	<!-- js -->
	<script>
		$("#annCause").kendoComboBox({
			dataSource : {
				schema : {
					//"total":"total",
					data : "data"
				},
				serverFiltering : true,
				transport : {
					parameterMap : function(options, action) {
						if (action === "read") {
							return kendo.stringify(
								options.filter.filters[0]
							);
						} else {
							return kendo.stringify(options);
						}
					},
					read : {
						contentType : "application/json",
						type : "POST",
						url : "${readUrl}"
					/* data:  {
					     //key: this.text().trim()//$().val();
					     key:function(){
					     	console.info(this);
					     } 
					}*/
					}
				}
			},//end schema
			autoBind : false,
			minLength : 3.0,
			placeholder : "输入案由拼音缩写",
			dataValueField : "annCauseID",
			dataTextField : "name",
			filter : "contains"
		});
	</script>



</body>
</html>
