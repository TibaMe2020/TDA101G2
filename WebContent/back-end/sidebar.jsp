<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="sidebar-wrapper">
          <div class="list-group list-group-flush d-flex align-items-center justify-content-center flex-column"
            id="admin-account-holder">
            <div class="d-flex align-items-center justify-content-center">
              <i class="fas fa-user-cog fa-3x"></i>
            </div>
            <div>
              Admin account
            </div>
          </div>
          <div class="list-group list-group-flush">
            <a href="<%=request.getContextPath()%>/back-end/adminStatistics.jsp"
              class="list-group-item list-group-item-action d-flex">
              <i class="fas fa-list-alt sidebar-icon"></i>
              <div>&nbsp數據統計</div>
            </a>
            <a href="<%=request.getContextPath()%>/back-end/userManagement.jsp"
              class="list-group-item list-group-item-action d-flex">
              <i class="fas fa-users sidebar-icon"></i>&nbsp會員管理
            </a>
            <a href="<%=request.getContextPath()%>/back-end/sellerVerification.jsp"
              class="list-group-item list-group-item-action d-flex">
              <i class="fas fa-user-check sidebar-icon"></i>
              &nbsp賣家審核</a>
            <a href="<%=request.getContextPath()%>/back-end/store/select_page.jsp"
              class="list-group-item list-group-item-action d-flex">
              <i class="fas fa-search sidebar-icon"></i>
              &nbsp店家查詢</a>

            <a href="#submenu1" data-toggle="collapse" aria-expanded="false"
              class="list-group-item list-group-item-action flex-column justify-content-between ">
              <div class="d-flex">
                <div>
                  <i class="fas fa-hands-helping sidebar-icon"></i>
                  <span class="menu-collapsed">公益管理</span>
                </div>
                <div class="flex-fill"></div>
                <i class="fas fa-caret-down"></i>
              </div>
            </a>
            
            <!-- Submenu content -->
            <div id='submenu1' class="collapse sidebar-submenu">
              <a href="<%=request.getContextPath()%>/back-end/npo.jsp"
                class="list-group-item list-group-item-action text-center">
                <span class="menu-collapsed">公益團體</span>
              </a>
              <a href="<%=request.getContextPath()%>/back-end/adopt.jsp"
                class="list-group-item list-group-item-action text-center">
                <span class="menu-collapsed">動物認養</span>
              </a>
              <a href="<%=request.getContextPath()%>/back-end/result.jsp"
                class="list-group-item list-group-item-action text-center">
                <span class="menu-collapsed">捐款成果</span>
              </a>
            </div>
          </div>
          <div class="list-group list-group-flush d-flex flex-column invisible">
            <a href="#" class="list-group-item list-group-item-action">
              0
            </a>
            <a href="#" class="list-group-item list-group-item-action">0
            </a>
            <a href="#" class="list-group-item list-group-item-action">0
            </a>
            <a href="#" class="list-group-item list-group-item-action">0
            </a>
            <a href="#" class="list-group-item list-group-item-action">0
            </a>
          </div>

          <div class="list-group list-group-flush d-flex flex-column">
            <a href="<%=request.getContextPath()%>/back-end/adminManagement.jsp"
              class="list-group-item list-group-item-action d-flex flex-fill">
              <i class="fas fa-user-cog sidebar-icon"></i>
              <div>&nbsp管理員帳號</div>
            </a>
            <a href="<%=request.getContextPath()%>/member/controller?action=logout"
              class="list-group-item list-group-item-action d-flex flex-fill">
              <i class="fas fa-sign-out-alt sidebar-icon"></i>&nbsp登出
            </a>
          </div>
        </div>