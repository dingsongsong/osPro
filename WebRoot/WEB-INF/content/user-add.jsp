<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
td,#showInfo {
	font-size: 13px;
}
</style>
<script type="text/javascript" src="Js/jquery-1.11.0.js"></script>
<script type="text/javascript">
	$(function() {
		var flag = true;
		var t;
		var k;
		$("#zhuce").click(function() {
			$("#msg").hide();
			$(this).next().toggle("normal");
		});
		$("#operate").click(function() {
			$("#msg").hide();
			$("#showTime").hide();
			$.ajax({
				url : "user!operate.action",
				type : "GET",
				dataType : "html",
				success : function(msg) {
					$("#showInfo").empty();
					$("#showInfo").show().html(msg);
				}
			});
			if ($("#showInfo").find("div").length == 0) {
				return false;
			}
			$(this).next("div").toggle("normal");
			$("#start").html("开始").css("color", "green");
		});
		function run() {
			if ($("#showInfo").find("div:visible").length == 0) {
				$("#showInfo").find("div:first").show();
			} else {
				if ($("#showInfo").find("div:hidden").length == 0) {
					return false;
				} else {
					$("#showInfo").find("div:visible:last").next().show();
				}
			}
		}
		$("#start").click(function() {
			$("#msg").hide();
			if (flag) {
				if ($("#showInfo").find("div:hidden").length == 0) {
					$("#showInfo").find("div").hide();
					k = -1;
				}
				flag = false;
				$(this).html("停止").css("color", "red");
				t = setInterval(function() {
					run();
					k++;
					$("#showTime").show().html("当前时刻:" + k);
					if ($("#showInfo").find("div:last").is(":visible")) {
						$("#start").html("开始").css("color", "green");
						flag = true;
						clearInterval(t);
					}
				}, 1000);
			} else {
				flag = true;
				$(this).html("继续").css("color", "green");
				clearInterval(t);
			}
		});
		$("#end").click(function() {
			$("#msg").hide();
			$("#showInfo div").show();
			$("#start").html("开始").css("color", "green");
			$("#showTime").hide();
		});
		$("#btn").click(
				function() {
					var state = true;
					$(":text").each(
							function() {
								if (!$(this).val()
										|| $(this).val() < 1
										|| !(/^[1-9][0-9]*/g
												.test($(this).val()))) {
									state = false;
									$("#msg").show().css(
											{
												"top" : $(this).offset().top
														- $(this).height()
														+ "px",
												"left" : $(this).offset().left
														+ $(this).width()
														+ "px"
											}).html("填写不小于1的整数！！！");
									return false;
								}
							});
					if (state) {
						$("#myForm").trigger("submit");
					}
				});
		$("#loadXML").click(function() {
			$("#msg").hide();
			if (($(this).html()).replace(/^\s+|\s+$/g, "") == "加载XML文件") {
				$(this).html("撤销XML文件");
				$.ajax({
					url : "user!loadXML.action",
					type : "GET",
					data : {
						"method" : "join"
					}
				});

			} else {
				$(this).html("加载XML文件");
				$.ajax({
					url : "user!loadXML.action",
					type : "GET",
					data : {
						"method" : "quit"
					}
				});
			}
			$("#showTime").hide();
			$("#showInfo").empty();
			$("#operate").next("div").hide("normal");
		});
		$(":reset").click(function() {
			$("#msg").hide();
		});
	});
</script>
</head>
<body>
	<fieldset id="myField"
		style="padding: 10px 10px 10px 10px;border: 3px solid #666;width: 320px;">
		<legend style="font-size: 13px;font-weight: bold;">读写平等的读写平等问题---by司航航,丁松松</legend>
		<div style="padding-right: 6px">
			<div id="showTime"
				style="width: 106px;border: 2px solid #ffa200;border-bottom-width: 0px;border-right-width:0px;height: 20px;font-weight: bold;font-size: 14px;padding: 3px;text-align: center;display: none;float: left;">当前时刻:1</div>
			<div id="loadXML"
				style="cursor: pointer;width: 190px;border: 2px solid #ffa200;border-bottom-width: 0px;height: 20px;font-size: 14px;padding: 3px;text-align: center;float: right;">
				<c:choose>
					<c:when test="${empty sessionScope.xmlUsers}">加载XML文件</c:when>
					<c:otherwise>撤销XML文件</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div style="border: 2px solid #666;float: left;">
			<div style="width: 290px;">
				<div id="zhuce"
					style="padding: 5px;text-align: center;font-weight: bold;cursor: pointer;width: 300px;">添加读者写者</div>
				<div
					style="padding: 20px 5px 0px 20px;width: 290px;display: none;border-top: 1px dashed #666;">
					<form action="user!add.action" method="post" id="myForm">
						<table>
							<tbody>
								<tr>
									<td>ID:</td>
									<td><input type="text" name="user.id"></td>
								</tr>
								<tr>
									<td>到达时间:</td>
									<td><input type="text" name="user.arriveTime"></td>
								</tr>
								<tr>
									<td>服务时间:</td>
									<td><input type="text" name="user.seriveTime"></td>
								</tr>
								<tr>
									<td>用户类型:</td>
									<td><select name="user.type">
											<option value="1">读者</option>
											<option value="2">写者</option>
									</select></td>
								</tr>
								<tr>
									<td colspan="2" align="right"><input type="button"
										id="btn" value="添加"
										style="background-color: white;font-weight: bold;"> <input
										type="reset" value="重置"
										style="background-color: white;font-weight: bold;"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div style="border-top: 2px solid #666;">
				<div id="operate"
					style="width: 300px;padding: 5px;text-align: center;font-weight: bold;cursor: pointer;">运行</div>
				<div style="display: none;">
					<div id="start"
						style="width: 300px;padding: 5px;text-align: center;font-weight: bold;cursor: pointer;color: green;border-top: 1px dashed #666;">开始</div>
					<div id="end"
						style="width: 300px;padding: 5px;text-align: center;font-weight: bold;cursor: pointer;border-top: 1px dashed #666;">
						结束</div>
				</div>
			</div>
		</div>
		<div id="showInfo"></div>
	</fieldset>
	<div
		style="padding: 5px 5px 5px 5px;width: 100px;border: solid 2px #ffa200;background-color:#fff;display: none;position: absolute;color: red;font-weight: bold;"
		id="msg"></div>
</body>
</html>