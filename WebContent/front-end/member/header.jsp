<%@page import="com.member.model.MemberService"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.notification.model.NotiVO"%>
<%@page import="com.notification.model.NotiService"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/vendors/air_datepicker/datepicker.min.css">
<%-- <link href="<%=request.getContextPath()%>/front-end/member/css/headerNfooter.css" rel="stylesheet"> --%>



<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	MemberService mbSvc = new MemberService();
	Map<String, List<String>> notiMap = new HashMap<>();
	String member_id = null;
	int read = 1;
	if (memberVO != null) {
		member_id = memberVO.getMember_id();
		memberVO = mbSvc.getOne(member_id);
		session.setAttribute("memberVO", memberVO);
		try {
			NotiService nSvc = new NotiService();
			List<NotiVO> notifications = nSvc.findByMembId(member_id);
			for (NotiVO noti : notifications) {
				if (noti.getNotification_class() < 6)
					read = 0;

				int noti_class = noti.getNotification_class();
				List<String> list = new ArrayList<>();

				String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(noti.getCreate_time());
				list.add(formattedDate);
				switch (noti_class) {
				case 1:
					list.add("您的包裹已送達指定門市，請記得去領取");
					break;
				case 2:
					list.add("訂單已完成，請去購買清單查看訂單明細");
					break;
				case 3:
					list.add("您曾經幫助過的公益團體發表了近期的成果");
					break;
				case 4:
					list.add("恭喜通過成為商城賣家，您可以開始提供賣家服務");
					break;
				case 5:
					list.add("恭喜通過成為店家，您可以開始提供實體服務");
					break;
				case 6:
					list.add("您的包裹已送達指定門市，請記得去領取");
					break;
				case 7:
					list.add("訂單已完成，請去購買清單查看訂單明細");
					break;
				case 8:
					list.add("您曾經幫助過的公益團體發表了近期的成果");
					break;
				case 9:
					list.add("恭喜通過成為商城賣家，您可以開始提供賣家服務");
					break;
				case 10:
					list.add("恭喜通過成為店家，您可以開始提供實體服務");
					break;
				}

				notiMap.put(noti.getNotification_id(), list);
			}

		} catch (Exception e) {
			List<String> list = new ArrayList<>();
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			list.add(today);
			list.add("您還沒有任何通知");
			notiMap.put("NO00000", list);
		}
		pageContext.setAttribute("read", read);

		if (notiMap.isEmpty()) {
			List<String> list = new ArrayList<>();
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			list.add(today);
			list.add("您還沒有任何通知");
			notiMap.put("NO00000", list);
		}

		pageContext.setAttribute("notiMap", notiMap);
	}
%>

<%if (memberVO != null) {%>
<header>
	<div class="header">
		<div class="row nav-holder">
			<div class="col-4"></div>
			<div class="col-4 align-self-center">
				<div class="d-flex justify-content-center align-items-center">
					<div class="logo-container">
						<a href="<%=request.getContextPath()%>/index.jsp"> <img
							class="logo d-none d-xl-block"
							src="<%=request.getContextPath()%>/resources/images/logo.png"
							alt="logo">
						</a>
					</div>
					<div class="logo-text-container">
						<a href="<%=request.getContextPath()%>/index.jsp"> <span
							class="logo-text">PETBOX</span>
						</a>
					</div>
				</div>
			</div>
			<div class="col-1"></div>
			<div
				class="col-3 align-self-center d-flex justify-content-around align-content-center flex-wrap nav-icon-holder">

				<a href="<%=request.getContextPath()%>/front-end/product/ShoppingCart.jsp" class="d-flex align-items-center text-white"><i
					class="fas fa-shopping-cart i-white em-md text-white"></i></a> 
					<a href="#" class="dropdown-toggle d-flex align-items-center text-white noti-drop-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">
					<i class="fas fa-bell i-white em-md text-white"></i>
					<span id="noti-dot">${(read == 0) ? '●' : ''}</span></a>
				<ul class="dropdown-menu notify-drop dropdown-menu-right"
					style="min-width: 330px;">
					<div class="notify-drop-title">
						<div class="row no-margin">
							<div class="col-3"></div>
							<div class="col-6">
								通知
								<!-- jsp 動態產生新的通知 -->
								<!--<span id="noti-number">(2)</span> -->
							</div>
							<div class="col-3">
								<a
									href="<%=request.getContextPath()%>/front-end/member/notiOverview.jsp"
									class="noti-more">More...</a>
							</div>
						</div>
					</div>
					<hr class="hr-margin">
					<div class="drop-content">

						<c:if test="${ not empty notiMap }">
							<c:forEach var="noti" items="${ notiMap.entrySet() }" begin="0"
								end="3">
								<li>
									<div class="col-12" style="min-height: 73px;">
										<p>${noti.getValue().get(1)}</p>
										<hr>
									</div>
								</li>
							</c:forEach>
						</c:if>
					</div>
				</ul>


				<a
					href="<%=request.getContextPath()%>/front-end/member/updateInfo.jsp"
					class="d-flex align-items-center text-white"> <i
					class="fas fa-user-circle i-white em-md text-white"></i>
				</a> <a
					href="<%=request.getContextPath()%>/member/controller?action=logout"
					class="d-flex align-items-center text-white"> <i
					class="fas fa-sign-out-alt i-white text-white"></i>
				</a>

			</div>


		</div>

		<div class="row nav">
			<div class="col-lg-2 text-center"></div>
			<div class="col-lg-8 col-sm-12  text-center">
				<ul class="nav nav-pills nav-fill">
					<li class="nav-item"><a class="nav-link "
						href="<%=request.getContextPath()%>/front-end/store/store.jsp">店家</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/front-end/product/ProductHomepage.jsp">商城</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/front-end/blog/HomePage.jsp">部落格</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/front-end/donation/donation.jsp">公益</a>
					</li>
				</ul>
			</div>
			<div class="col-lg-2 text-center"></div>
		</div>
	</div>
</header>

<%
	} else {
%>
<header>
	<div class="header">
		<div class="row nav-holder">
			<div class="col-4"></div>
			<div class="col-4 align-self-center">
				<div class="d-flex justify-content-center align-items-center">
					<div class="logo-container">
						<a href="#"> <img class="logo d-none d-xl-block"
							src="<%=request.getContextPath()%>/resources/images/logo.png"
							alt="logo">
						</a>
					</div>
					<div class="logo-text-container">
						<a href="#"> <span class="logo-text">PETBOX</span>
						</a>
					</div>
				</div>
			</div>
			<div class="col-1"></div>
			<div
				class="col-3 d-flex justify-content-center align-items-center login-icon-holder">
				<!-- 未登入前 -->
				<div class="col-1 align-self-center d-flex justify-content-center">
					<a href="#modalCenter" data-toggle="modal" id="login-modal"> <i
						class="fas fa-sign-in-alt i-white"></i>
					</a>
				</div>
			</div>
		</div>

		<div class="row nav">
			<div class="col-lg-2 text-center"></div>
			<div class="col-lg-8 col-sm-12 text-center">
				<ul class="nav nav-pills nav-fill">
					<li class="nav-item"><a class="nav-link "
						href="<%=request.getContextPath()%>/front-end/store/store.jsp">店家</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/front-end/product/ProductHomepage.jsp">商城</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/front-end/blog/HomePage.jsp">部落格</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/front-end/donation/donation.jsp">公益</a>
					</li>
			</div>
			<div class="col-lg-2 text-center"></div>
		</div>
	</div>
</header>


<!-- Modal -->
<div id="modalCenter" class="modal fade account-modal" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header row">
				<div class="col-lg-4"></div>
				<div class="col-lg-4 d-flex justify-content-center">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a href="#login" class="nav-link active"
							data-toggle="tab">
								<h5 class="modal-title text-center">登入</h5>
						</a></li>
						<li class="nav-item"><a href="#signup" class="nav-link"
							data-toggle="tab" id="signup-link">
								<h5 class="modal-title text-center">註冊</h5>
						</a></li>
					</ul>
				</div>
				<div class="col-lg-4">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
			</div>

			<div class="modal-body">
				<div class="tab-content">
					<!-- LOGIN FORM -->
					<div class="tab-pane fade show active" id="login">
						<form action="<%=request.getContextPath()%>/member/controller"
							method="post" id="login-form" class="main-form">

							<div class="row justify-content-center">
								<div class="col-6 text-left">
									<div class="form-group">
										<label for="login-email">電子郵件</label> <input type="text"
											name="email" id="login-email"
											class="form-control ${not empty errorMsgs.loginEmail ? 'is-invalid' : ''}"
											required>
										<div
											class="${not empty errorMsgs.loginEmail ? 'invalid-feedback' : ''}">
											${empty errorMsgs.loginEmail ? '' : errorMsgs.loginEmail}</div>
									</div>

									<div class="form-group">
										<label for="login-password">密碼</label> <input type="password"
											name="password" id="login-password"
											class="form-control ${not empty errorMsgs.loginPassword ? 'is-invalid' : ''}"
											required>
										<div
											class="${not empty errorMsgs.loginPassword ? 'invalid-feedback' : ''}">
											${empty errorMsgs.loginPassword ? '' : errorMsgs.loginPassword}</div>

									</div>
									<div class="modal-footer no-padding justify-content-start">
										<input type="hidden" name="action" value="login">
										<button type="submit" class="btn btn-primary no-margin">登入</button>
										<a
											href="<%=request.getContextPath()%>/front-end/member/forgotPassword.jsp"
											class="ml-auto" id="forgot-password">忘記密碼?</a>
									</div>
								</div>
							</div>

						</form>
					</div>

					<!-- SIGN UP FORM -->
					<div class="tab-pane fade" id="signup">
						<form action="<%=request.getContextPath()%>/member/controller"
							method="post" id="sign-up-form" class="main-form">

							<div class="row justify-content-around">
								<div class="col-lg-4 text-left">
									<div class="form-group">
										<label for="name">姓名*</label> <input type="text" name="name"
											id="name" value="${new_member.name}"
											class="form-control 
                      ${not empty errorMsgs.signupName ? 'is-invalid' : ''}"
											required>
										<div
											class="${not empty errorMsgs.signupName ? 'invalid-feedback' : 'valid-feedback'}">
											${empty errorMsgs.signupName ? '' : errorMsgs.signupName}</div>
									</div>
									<div class="form-group">
										<label for="email">電子郵件*</label> <input type="text"
											name="email" value="${new_member.email}" id="email"
											class="form-control 
                      ${not empty errorMsgs.signupEmail ? 'is-invalid' : ''}"
											required>
										<div
											class="${not empty errorMsgs.signupEmail ? 'invalid-feedback' : 'valid-feedback'}">
											${empty errorMsgs.signupEmail ? '' : errorMsgs.signupEmail}</div>
									</div>
									<div class="form-group">
										<label for="phone_num">電話號碼</label> <input type="text"
											value="${new_member.phone_num}" name="phone_num"
											id="phone_num"
											class="form-control 
                      ${not empty errorMsgs.signupPhone_num ? 'is-invalid' : ''}">
										<div
											class="${not empty errorMsgs.signupPhone_num ? 'invalid-feedback' : ''}">
											${empty errorMsgs.signupPhone_num ? '' : errorMsgs.signupPhone_num}</div>
									</div>

									<div class="form-group">
										<label for="address">地址</label> <input type="text"
											value="${new_member.address}" name="address" id="address"
											class="form-control 
                      ${not empty errorMsgs.signupAddress ? 'is-invalid' : ''}">
										<div
											class="${not empty errorMsgs.signupAddress ? 'invalid-feedback' : 'valid-feedback'}">
											${empty errorMsgs.signupAddress ? '' : errorMsgs.signupAddress}</div>
									</div>

									<div class="form-group">
										<label for="birthday">出生日期</label> <input type="text"
											placeholder="yyyy-mm-dd" name="birthday" id="birthday"
											data-date="yyyy-mm-dd" class="form-control datepicker-here"
											data-position="top right" />
									</div>

									<div class="form-group">
										<label for="sex">性別</label> <select name="sex" id="sex"
											class="form-control">
											<option value="">選項...</option>
											<option value="男" ${(new_member.sex=="男" ) ? 'selected' :'' }>男性</option>
											<option value="女" ${(new_member.sex=="女" ) ? 'selected' :'' }>女性</option>
											<option value="其他"
												${(new_member.sex=="其他" ) ? 'selected' :'' }>其他</option>
										</select>
									</div>
								</div>
								<div class="col-lg-4 text-left">
									<div class="form-group">
										<label for="password">密碼*</label> <input type="password"
											name="password" id="password"
											class="form-control 
                      ${not empty errorMsgs.signupPassword ? 'is-invalid' : ''}"
											required>
										<div
											class="${not empty errorMsgs.signupPassword ? 'invalid-feedback' : ''}">
											${empty errorMsgs.signupPassword ? '' : errorMsgs.signupPassword}</div>
									</div>
									<div class="form-group">
										<label for="confirm">確認密碼*</label> <input type="password"
											name="confirm" id="confirm"
											class="form-control 
                      ${not empty errorMsgs.signupConfirm ? 'is-invalid' : ''}"
											required>
										<div
											class="${not empty errorMsgs.signupConfirm ? 'invalid-feedback' : ''}">
											${empty errorMsgs.signupConfirm ? '' : errorMsgs.signupConfirm}</div>
									</div>

									<input type="hidden" name="action" value="signup">

									<button type="submit" class="btn btn-primary">註冊</button>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>


<%
	}
%>