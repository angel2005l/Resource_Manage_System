<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>产品信息弹窗</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- this page specific styles -->
<link rel="stylesheet"
	href="<%=basePath%>css/compiled/personal-info.css" type="text/css"
	media="screen" />

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
html {
	height: auto;
}
</style>
</head>
<body>
	<!-- main container .wide-content is used for this layout without sidebar :)  -->
	<div class="content wide-content">
		<div class="container-fluid">
			<div class="settings-wrapper" id="pad-wrapper">
				<!-- avatar column -->
				<div class="span1 avatar-box"></div>
				<!-- edit form column -->
				<div class="span7 personal-info">
					<form id="tableForm">
						<div class="field-box">
							<label>产品名称:</label> <input class="span5 inline-input" name="product_name" type="text" placeholder="请输入产品名称..." />
						</div>
						<div class="field-box">
							<label>产品型号:</label> <input class="span5 inline-input" 
								name="item_no" type="text" placeholder="请输入产品型号..." />
						</div>
						<div class="field-box">
							<label>产品原价:</label> <input class="span5 inline-input"
								name="original_price" type="text" placeholder="请输入产品原价..." />
						</div>
						<div class="field-box">
							<label>产品售价:</label> <input class="span5 inline-input"
								name="price" type="text" placeholder="请输入产品售价..." />
						</div>
						<div class="field-box">
							<label>序号:</label> <input class="span5 inline-input" type="text" name = "sort"
								placeholder="请输入序号..." />
						</div>
						<div class="field-box">
							<label>产品详情</label>
							<textarea class="span5" name="info" rows="2" placeholder="请输入产品详情"></textarea>
						</div>
						<div class="field-box">
							<label>备注</label>
							<textarea class="span5" name="remark" rows="2" placeholder="请输入产品备注"></textarea>
						</div>
						<div class="field-box">
							<label>所属产品分类</label>
							<div class="ui-select">
								<select id="tid" name="tid">
									<option value="" selected="selected">请选择产品分类</option>
									<option value="1">常规医疗用品</option>
									<option value="2">智能化医疗用品</option>
								</select>
							</div>
						</div>
						<div class="field-box">
							<!--<label>状态:</label>
								<input type="radio" name="newsTypeStatus" value="1" />正常
								<input type="radio" name="newsTypeStatus" value="2" />锁定-->
							<label>状态:</label> <label style="width: 20%;"><input
								type="radio" name="status" value="1" checked="checked" />正常</label> <label
								style="width: 20%; float: left;"><input type="radio"
								name="status" value="2" />锁定</label>
						</div>
						<div class="alert alert-info">
							<i class="icon-exclamation-sign"></i>请认真填写产品信息
						</div>
						<div class="field-box actions">
							<input id="sumbit_form" type="button" class="btn-flat primary" value="保存" /> <input
								id="close_win" type="button" class="btn-flat danger" value="取消" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- end main container -->

	<!-- scripts -->
	<script type="text/javascript" src="<%=basePath %>js/jquery.form.js"></script>
	<script>
		var index = parent.layer.getFrameIndex(window.name);
		$("#sumbit_form").on("click",function(){
			$("#tableForm").ajaxSubmit({
				url:'<%=basePath %>productManage?method=product_ins',
				type:'post',
				dataType:'json',
				success:function(result){
					alert(result.msg);
					if(result.code == 0){
						parent.location.href='<%=basePath %>productManage?method=product_sel';
						parent.layer.close(index);
					}else{
						return;
					}
				},
				error:function(){
					alert("服务未响应");
				}
			});
		});
		
		
		$("#close_win").on("click", function() {
			parent.layer.close(index);
		})
	</script>
</body>

</html>