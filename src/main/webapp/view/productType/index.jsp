<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>产品分类管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=basePath%>theme/default/layer.css" />
<link rel="stylesheet" href="<%=basePath%>css/compiled/tables.css"
	type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
	<div class="container-fluid">
		<div id="pad-wrapper">

			<!-- products table-->
			<!-- the script for the toggle all checkboxes from header is located in js/theme.js -->
			<div class="table-wrapper products-table section">
				<div class="row-fluid head">
					<div class="span12">
						<h4>产品分类管理</h4>
					</div>
				</div>

				<div class="row-fluid filter-block">
					<div class="pull-right">
						<input type="text" class="search" /> <a
							class="btn-flat success new-product">查询</a> <a
							class="btn-flat success new-product" onclick="addProductType()">添加产品类型</a>
					</div>
				</div>

				<div class="row-fluid">
					<table class="table table-hover">
						<thead>
							<tr>
								<th class="span1">序号</th>
								<th class="span3"><span class="line"></span>分类名称</th>
								<th class="span3"><span class="line"></span>分类小图标</th>
								<th class="span1"><span class="line"></span>排序</th>
								<th class="span1"><span class="line"></span>状态</th>
								<th class="span2"><span class="line"></span>操作</th>
							</tr>
						</thead>
						<tbody>
							<!-- row -->
							<c:forEach items="${data.data.list }" var="b" varStatus="s">
								<tr>
									<td>${s.count }</td>
									<td>${b.type_name }</td>
									<td></td>
									<td>${b.sort }</td>
									<td><span
										<c:choose>
										<c:when test="${b.status==0 }">class="label label-important" </c:when>
										<c:when test="${b.status==2 }">class="label label-warning"</c:when>
										<c:otherwise>class="label label-success"</c:otherwise>
									</c:choose>>
											<tag:enum className="CommonEnum">${b.status }</tag:enum>
									</span></td>
									<td><c:if test="${b.status!=0 }">
											<ul class="actions">
												<li><a onclick="edit(${b.id })">编辑</a></li>
												<li class="last"><a onclick="del(${b.id })">删除</a></li>
											</ul>
										</c:if></td>
								</tr>
							</c:forEach>
							<!-- row -->
						</tbody>
					</table>
				</div>
			</div>
			<!-- end products table -->
		</div>
	</div>
	<script type="text/javascript">
		function addProductType() {
			layer.open({
				type : 2,
				title : '产品分类添加',
				area : [ '400px', '473px' ],
				shadeClose : false, //点击遮罩关闭
				content : 'view/productType/addLayer.jsp'
			//					content:'grids.html'
			});
		}
		function edit(id){
			layer.open({
				type:2,
				title:'产品分类修改',
				area : [ '400px', '473px' ],
				shadeClose : false, //点击遮罩关闭
				content: '<%=basePath %>productManage?method=product_type_sel_id&id='+id
			})			
		}
		function del(id){
			$.ajax({
				url:'<%=basePath%>productManage?method=product_type_del',
				type:'post',
				dataType:'json',
				data:{"id":id},
				success:function(result){
					alert(result.msg)
					location.reload();
				},
				error:function(){
					alert("服务未响应");
				}
			})
		}
	</script>

</body>

</html>