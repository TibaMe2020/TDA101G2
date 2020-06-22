<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.member.model.*, com.notification.model.*"%>
<%@page
	import="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap,java.util.Arrays"%>
<%@page import="com.admin.model.AdminVO"%>
<%@page import="com.admin.model.AdminService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Admin Management</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<link href="https://unpkg.com/filepond/dist/filepond.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/adminManagement.css">
</head>

<% 
		AdminService adSvc = new AdminService();
		List<AdminVO> admins = adSvc.getAll();
		pageContext.setAttribute("admins", admins);
		AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
		String admin_id = adminVO.getAdmin_id();
		admins.removeIf( ad -> ad.getAdmin_id().equals(admin_id));
	%>


<body>
	<header class="bg-white">
		<div class="container-fluid">
			<div class="row header">
				<div
					class="col-2 d-flex align-items-center justify-content-center border-right border-bottom">
					<img src="<%=request.getContextPath()%>/resources/images/admin.svg"
						alt="admin"> &nbsp;
					<h5 class="text-center admin-tab">Admin</h5>
				</div>
				<div
					class="col-10 d-flex align-items-center justify-content-center border-bottom">
					<h3 class="title-tab">管理員帳號</h3>
				</div>
			</div>
		</div>

	</header>

	<div class="container-fluid">
		<div class="row content-height">
			<div class="col-2">
				<%@ include file="sidebar.jsp"%>
			</div>
			<div class="table-container col-9">
				<table class="table table-fluid" id="myTable">
					<thead>
						<tr>
							<th>管理員編號</th>
							<th>管理員帳號</th>
							<th>創建時間</th>
							<th>移除</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div class="d-flex justify-content-center">
									<a href="#add-modal" data-toggle="modal" class="new-admin">
										<i class="fas fa-plus"></i> <span>新增管理員</span>
									</a>
								</div>
							</td>
						</tr>
						<c:forEach var="admin" items="${admins}">
							<tr>
								<td class="admin_id">${admin.admin_id}</td>
								<td>${admin.admin_account}</td>
								<td><fmt:formatDate value="${admin.create_time}" pattern="yyyy-MM-dd"/></td>
								<td><a href="#delete-modal" data-toggle="modal"
									class="btn btn-danger text-white delete-action"> 移除 </a>
									</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div id="delete-modal" class="modal fade">
				<div class="modal-dialog modal-md modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header d-flex justify-content-center">
							<div class="d-flex flex-grow-0">
								<button type="button" class="close invisible"
									data-dismiss="modal" aria-hidden="true">&times;</button>
							</div>
							<div class="d-flex flex-grow-1 justify-content-center">
								<h4 class=" modal-title">移除</h4>
							</div>
							<div class="d-flex flex-grow-0">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							</div>
						</div>

						<div class="modal-body">
							<div class="container-fluid">
								<div class="row justify-content-center">
									<div class="col-10 d-flex justify-content-center">
										<div>
											<b>您確定要移除此管理員帳號嗎?</b>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="modal-footer border-0">
							<div class="container-fluid">
								<div class="row justify-content-center">
									<div class="col-5 d-flex justify-content-center">

										<form METHOD="post"
											action="<%=request.getContextPath()%>/admin/controller">
											<input type="hidden" name="action" value="delete"> <input
												type="hidden" name="admin_id" value="" id="hidden_id">
											<!-- <input type="submit" class="btn btn-danger" value="停權"> -->
											<input type="submit" class="btn btn-danger" value="移除">
										</form>
									</div>
									<div class="col-5 d-flex justify-content-center">
										<input type="button" class="btn btn-secondary cancel-delete"
											value="取消">
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>

			<div id="add-modal" class="modal fade">
				<div class="modal-dialog modal-md modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header d-flex justify-content-center">
							<div class="d-flex flex-grow-0">
								<button type="button" class="close invisible"
									data-dismiss="modal" aria-hidden="true">&times;</button>
							</div>
							<div class="d-flex flex-grow-1 justify-content-center">
								<h4 class="modal-title">新增管理員</h4>
							</div>
							<div class="d-flex flex-grow-0">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							</div>
						</div>

						<div class="modal-body">
							<div class="container-fluid">
								<div class="row justify-content-center">
									<div class="col-10 d-flex justify-content-center">
										<form action="<%=request.getContextPath()%>/admin/controller"
											method="post" id="signup-form" class="main-form">

											<div class="row justify-content-center">
												<div class="col-12 text-left">
													<div class="form-group">
														<label for="admin-account">帳號</label>
														<input type="text" name="admin_account" id="admin-account"
															class="form-control ${not empty errorMsgs.admin_account ? 'is-invalid' : ''}" required>
														<div class="${not empty errorMsgs.admin_account ? 'invalid-feedback' : ''}">
                            ${empty errorMsgs.admin_account ? '' : errorMsgs.admin_account}</div>
													</div>

													<div class="form-group">
														<label for="admin-password">密碼</label>
														<input type="password" name="admin_password" id="admin-password" 
														class="form-control ${not empty errorMsgs.admin_password ? 'is-invalid' : ''}" required>
														<div class="${not empty errorMsgs.admin_password ? 'invalid-feedback' : ''}">
                            ${empty errorMsgs.admin_password ? '' : errorMsgs.admin_password}</div>
													</div>
												</div>


											</div>
											<div class="row justify-content-center">
												<div class="col-6 d-flex justify-content-start">

													 <form METHOD="post" action="<%=request.getContextPath()%>/admin/controller">
                                <input type="hidden" name="action" value="signup">
                                <input type="submit" class="btn btn-success" value="新增">
                          </form> 
												</div>
												<div class="col-6 d-flex justify-content-end">
													<input type="button"
														class="btn btn-secondary cancel-delete" value="取消">
												</div>
											</div>

										</form>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous">
		
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous">
		
	</script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous">
		
	</script>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js">
		
	</script>

	<script>
		if(${not empty errorMsgs.admin_password ? 'true' : 'false'} || ${not empty errorMsgs.admin_account ? 'true' : 'false'}) {
			$('a.new-admin').click();
			
		}
		
		$('input.cancel-delete').on('click', function() {
			$(this).closest('.modal').find('button.close').click();
		})

		$(document).ready(function() {
			$('.delete-action').on('click', function() {
				let id = $(this).closest('tr').find('.admin_id').text();
				$('#hidden_id').val(id);
			})

			$('#myTable').DataTable({
				searching : false,
				lengthChange : false,
				info : false,
				ordering : false,
				"language" : {
					"paginate" : {
						"previous" : "上一頁",
						"next" : "下一頁"
					}
				}
			});

		})
	</script>

</body>

</html>