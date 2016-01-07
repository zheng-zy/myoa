<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$('#parentid').combotree({
			url : '${pageContext.request.contextPath}/deptController/allTree',
			parentField : 'parentid',
			lines : true,
			panelHeight : 'auto',
			value : '${dept.parentid}',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});

		$('#form').form({
			url : '${pageContext.request.contextPath}/deptController/edit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<th>编号</th>
					<td><input name="id" type="text" class="span2" value="${dept.id}" readonly="readonly"></td>
					<th>中文名称</th>
					<td><input name="cnname" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value="${dept.cnname}"></td>
				</tr>
				<tr>
					<th>英文名称</th>
					<td><input name="enname" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value="${dept.enname}"></td>
					<th>上级部门</th>
					<td><select id="parentid" name="parentid" style="width: 140px; height: 29px;"></select><img src="${pageContext.request.contextPath}/style/images/extjs_icons/cut_red.png" onclick="$('#parentid').combotree('clear');" /></td>
				</tr>
				<tr>
					<th>部门描述</th>
					<td colspan="3"><textarea name="deptdescribe" rows="" cols="" class="span5">${dept.deptdescribe}</textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>