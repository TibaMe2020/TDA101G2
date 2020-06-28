<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="bg-light" id="sidebar-wrapper">
	<div class="d-flex justify-content-center">
		<div class="avatar"
			style="background:url('<%=request.getContextPath()%>/member/profileImage?member_id=${memberVO.member_id}');
            background-position: center;background-size: 100px;"></div>
	</div>
	<div>${memberVO.email }</div>
	<div class="sidebar-heading">一般會員</div>
	
	<c:set var="member_state" value="${memberVO.member_state}" />
	
	<div class="list-group list-group-flush">
		<a
			href="<%=request.getContextPath()%>/front-end/member/shoppingList.jsp"
			class="list-group-item list-group-item-action bg-light"> <i
			class="fas fa-list sidebar-icon"></i> 購買清單
		</a> <a
			href="<%=request.getContextPath()%>/front-end/member/donationRecord.jsp"
			class="list-group-item list-group-item-action bg-light"> <i
			class="fas fa-file-alt sidebar-icon"></i> &nbsp公益紀錄
		</a> <a
			href="<%=request.getContextPath()%>/front-end/member/notiOverview.jsp"
			class="notifications list-group-item list-group-item-action bg-light">
			<i class="fas fa-bell sidebar-icon"></i> &nbsp通知總覽
		</a>
		<c:if test="${member_state != 4}">
			<a
				href="<%=request.getContextPath()%>/front-end/member/submitApplication.jsp"
				class="list-group-item list-group-item-action bg-light"> <i
				class="fas fa-user-check"></i>&nbsp賣家認證
			</a>
		</c:if>
		<a
			href="<%=request.getContextPath()%>/front-end/member/updateInfo.jsp"
			class="list-group-item list-group-item-action bg-light"> <i
			class="fas fa-edit"></i> 修改資料
		</a>
	</div>
	
	<c:if
		test="${(member_state >= 3 && member_state <= 4) || not empty memberVO.bank_account}">
		<div class="sidebar-heading">賣家中心</div>
		<div class="list-group list-group-flush">
<!-- 		一種是可以透過該會員是否有新增過商品來判斷，另一種是在送出申請後就可以新增商品，但必須經過審核，才可以在商品瀏覽畫面看到 -->
			<c:if test="${member_state >= 3 || not empty memberVO.bank_account}">
				<a
					href="<%=request.getContextPath()%>/front-end/member/productList.jsp"
					class="list-group-item list-group-item-action bg-light"> <i
					class="fas fa-list-ol sidebar-icon"></i> <span
					class="menu-collapsed">商品列表</span>
				</a>

				<a
					href="<%=request.getContextPath()%>/front-end/member/orderList.jsp"
					class="list-group-item list-group-item-action bg-light"> <i
					class="fas fa-clipboard-list sidebar-icon"></i> <span
					class="menu-collapsed">商品訂單</span>
				</a>
			</c:if>
			<c:if test="${member_state == 4}">
				<a href="<%=request.getContextPath()%>/front-end/member/serviceList.jsp"
					class="list-group-item list-group-item-action bg-light"> <i
					class="fas fa-table sidebar-icon"></i> <span class="menu-collapsed">服務列表</span>
				</a>
			</c:if>
		</div>
	</c:if>
</div>