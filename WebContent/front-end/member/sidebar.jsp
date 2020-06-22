<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="bg-light" id="sidebar-wrapper">
          <div class="d-flex justify-content-center">
            <div class="avatar" 
            style="background:url('<%=request.getContextPath()%>/member/profileImage?member_id=${memberVO.member_id}');
            background-position: center;background-size: 100px;"></div>
          </div>
          <div class="sidebar-heading">一般會員</div>
          <div class="list-group list-group-flush">
            <a href="<%=request.getContextPath()%>/front-end/member/shoppingList.jsp" class="list-group-item list-group-item-action bg-light">
              <i class="fas fa-list sidebar-icon"></i>
              購買清單
            </a>
            <a href="<%=request.getContextPath()%>/front-end/member/donationRecord.jsp" class="list-group-item list-group-item-action bg-light">
              <i class="fas fa-file-alt sidebar-icon"></i>
              &nbsp公益紀錄
            </a>
            <a href="<%=request.getContextPath()%>/front-end/member/notiOverview.jsp" class="notifications list-group-item list-group-item-action bg-light">
              <i class="fas fa-bell sidebar-icon"></i>
              &nbsp通知總覽</a>
            <a href="<%=request.getContextPath()%>/front-end/member/submitApplication.jsp" class="list-group-item list-group-item-action bg-light">
              <i class="fas fa-user-check"></i>&nbsp賣家認證</a>
            <a href="<%=request.getContextPath()%>/front-end/member/updateInfo.jsp" class="list-group-item list-group-item-action bg-light">
              <i class="fas fa-edit"></i> 修改資料</a>
          </div>
          <div class="sidebar-heading">賣家中心</div>
          <div class="list-group list-group-flush">
<!--             <a class="list-group-item list-group-item-action bg-light"> -->
<!--               <i class="fas fa-clipboard-list sidebar-icon"></i>&nbsp 銷售總覽 -->
<!--             </a> -->
<%--             <a href="<%=request.getContextPath()%>/front-end/member/productList.jsp" class="list-group-item list-group-item-action bg-light"> --%>
<!--               <i class="fas fa-table sidebar-icon"></i> -->
<!--               銷售統計 -->
<!--             </a> -->

<!--             <a href="#submenu1" data-toggle="collapse" aria-expanded="false" -->
<!--               class="bg-light list-group-item list-group-item-action flex-column"> -->
<!--               <div class="d-flex align-items-center justify-content-between"> -->
<!--                 <div>&nbsp&nbsp</div> -->
<!--                 <div> -->
<!--                   <i class="fas fa-list-ol sidebar-icon"></i> -->
<!--                   <span class="menu-collapsed">後台管理</span> -->
<!--                 </div> -->
<!--                 <i class="fas fa-caret-down"></i> -->
<!--               </div> -->
<!--             </a> -->
            <!-- Submenu content -->
<!--             <div id='submenu1' class="collapse sidebar-submenu"> -->
              <a href="<%=request.getContextPath()%>/front-end/member/productList.jsp" 
              class="list-group-item list-group-item-action bg-light">
              <i class="fas fa-list-ol sidebar-icon"></i>
                <span class="menu-collapsed">商品列表</span>
              </a>
              <a href="<%=request.getContextPath()%>/front-end/member/orderList.jsp" 
              class="list-group-item list-group-item-action bg-light">
              <i class="fas fa-clipboard-list sidebar-icon"></i>
                <span class="menu-collapsed">商品訂單</span>
              </a>
              <a href="<%=request.getContextPath()%>/front-end/member/serviceList.jsp" 
              class="list-group-item list-group-item-action bg-light">
              <i class="fas fa-table sidebar-icon"></i>
                <span class="menu-collapsed">服務列表</span>
              </a>
<!--             </div> -->
          </div>
        </div>