<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" --%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:url value="/annCause/read" var="readUrl" />
<c:url value="/annCause/create" var="createUrl" />
<c:url value="/annCause/update" var="updateUrl" />
<c:url value="/annCause/destroy" var="destroyUrl" />
<!-- 案由增删改查  -->

<div id="example">
	<div id="grid"></div>
	<script>
                $(document).ready(function() {
                    $("#grid").kendoGrid({
                        dataSource: {
                            //type: "odata", //微软特定的格式
                            transport: {
                            	read:  {
                                    url: "${readUrl}",
                                },
                                update: {
                                    url: "${updateUrl}",
                                },
                                destroy: {
                                    url: "${destroyUrl}",
                                },
                                create: {
                                    url: "${createUrl}",
                                },
                                parameterMap: function(options, operation) {
                                    if (operation !== "read" && options.models) {
                                        return {models: kendo.stringify(options.models)};
                                    }
                                }
                            },
                            
                            schema: {//The configuration used to parse the remote service response.
                                model: {//model The data item (model) configuration.
                                	id:"id",
                                    fields: {
                                        id: { editable: false,type: "number" },//不可编辑
                                        name: { type: "string" },
                                        quanPin: { editable: false,type: "string" },
                                        jianPin: { editable: false,type: "string" }
                                    }
                                }
                            },
                            pageSize: 20,
                            serverPaging: true,
                            serverFiltering: true,
                        },//end dataSource
                        
                        height: 600,//grid高度
                        filterable: {//grid查询
                            mode: "row"
                        },
                        pageable: true,//展示分页组件
                        toolbar: ["create"],//新建菜单
                        editable: "popup",//弹出编辑
                        { command: ["edit", "destroy"], title: "&nbsp;", width: "200px" }],//编辑，删除
                        
                        columns://显示列设置
                        [{
                            field: "id",
                            filterable:false,//a filter menu  display whether
                            width: "50px",
                            filterable: {//不显示查询
                                cell: {
                                    showOperators: false
                                }
                            }
                        },
                        {
                            field: "name",
                            width: "100px",
                            title: "案由",
                            filterable: {
                                cell: {
                                    operator: "contains",
                                    suggestionOperator: "contains"
                                }
                            }
                        },{
                            field: "quanPin",
                            title: "全拼",
                            width: "60px",
                            filterable: {
                                cell: {
                                    showOperators: false
                                }
                            }
                        },{
                            field: "jianPin",
                            title: "简拼",
                            width: "60px",
                            filterable: {
                                cell: {
                                    operator: "contains",
                                    suggestionOperator: "contains"
                                }
                            }
                        }]
                    });
                });
            </script>
</div>

