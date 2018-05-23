<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<title>新闻内容管理</title>

		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<link rel="stylesheet" href="<%=basePath %>theme/default/layer.css" />
		<link rel="stylesheet" href="<%=basePath %>css/compiled/tables.css" type="text/css" media="screen" />

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
							<h4>新闻信息管理</h4>
						</div>
					</div>

					<div class="row-fluid filter-block">
						<div class="pull-right">
							<input type="text" class="search" />
							<a class="btn-flat success new-product">查询</a>
							<a class="btn-flat success new-product" onclick="addNews()">添加新闻</a>
						</div>
					</div>

					<div class="row-fluid">
						<table class="table table-hover">
							<thead>
								<tr>
									<th class="span1">序号</th>
									<th class="span2"><span class="line"></span>标题</th>
									<th class="span4"><span class="line"></span>主要内容</th>
									<th class="span1"><span class="line"></span>状态</th>
									<th class="span2"><span class="line"></span>添加时间</th>
									<th class="span2"><span class="line"></span>操作</th>
								</tr>
							</thead>
							<tbody>
								<!-- row -->
								<c:forEach items="${data.data }" var="b" varStatus="s">
									<tr>
										<td>${s.count }</td>
										<td>${b.title }</td>
										<td>${b.main_content }</td>
										<td><span
										<c:choose>
										<c:when test="${b.status==0 }">class="label label-important" </c:when>
										<c:when test="${b.status==2 }">class="label label-warning"</c:when>
										<c:otherwise>class="label label-success"</c:otherwise>
									</c:choose>><tag:enum
												className="CommonEnum">${b.status }</tag:enum></span></td>
									<td><fmt:formatDate value="${b.add_time }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><c:if test="${b.status!=0 }">
											<ul class="actions">
												<li><a onclick="edit(${b.id })">编辑</a></li>
												<li class="last"><a onclick="del(${b.id })">删除</a></li>
											</ul>
										</c:if></td>
									</tr>
								</c:forEach>
								<tr>
									<td>1</td>
									<td>培护宁智能集尿器荣获2017中国设计红星奖</td>
									<td>被誉为工业设计界“奥斯卡”的中国设计红星奖颁奖典礼在北京举行，新海科技集团有限公司旗下品牌培护宁产品PHN-B100智能集尿</td>
									<td><span class="label label-success">正常</span></td>
									<td>2017-12-25</td>
									<td>
										<ul class="actions">
											<li>
												<a href="#">编辑</a>
											</li>
											<li class="last">
												<a href="#">删除</a>
											</li>
										</ul>
									</td>
								</tr>
								<!-- row -->
								<tr>
									<td>2</td>
									<td>培护宁智能集尿器荣获2017中国设计红星奖</td>
									<td>被誉为工业设计界“奥斯卡”的中国设计红星奖颁奖典礼在北京举行，新海科技集团有限公司旗下品牌培护宁产品PHN-B100智能集尿</td>
									<td><span class="label label-important">删除</span></td>
									<td>2017-12-25</td>
									<td>
										<!--<ul class="actions">
											<li>
												<a href="javascript:void(0);" >编辑</a>
											</li>
											<li class="last">
												<a href="#">删除</a>
											</li>
										</ul>-->
									</td>
								</tr>
								<tr>
									<td>3</td>
									<td>培护宁智能集尿器荣获2017中国设计红星奖</td>
									<td>被誉为工业设计界“奥斯卡”的中国设计红星奖颁奖典礼在北京举行，新海科技集团有限公司旗下品牌培护宁产品PHN-B100智能集尿</td>
									<td><span class="label label-warning">锁定</span></td>
									<td>2017-12-25</td>
									<td>
										<ul class="actions">
											<li>
												<a href="#">编辑</a>
											</li>
											<li class="last">
												<a href="#">删除</a>
											</li>
										</ul>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- end products table -->
			</div>
		</div>
		<script type="text/javascript">
			function addNews() {
				layer.open({
					type: 2,
					title:'新闻信息添加',
					area: ['400px', '500px'],
					shadeClose: false, //点击遮罩关闭
					content: 'view/news/addLayer.jsp'
//					content:'grids.html'
				});
			}
			
			function edit(id){
				layer.open({
					type:2,
					title:'新闻信息修改',
					area : [ '400px', '500px' ],
					shadeClose : false, //点击遮罩关闭
					content: '<%=basePath %>newsManage?method=news_sel_id&id='+id
				})			
			}
			function del(id){
				$.ajax({
					url:'<%=basePath%>newsManage?method=news_del',
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
				});
			}
			
			
			
			
			
			
			
			
			
			
			
			
		</script>

	</body>

</html>