<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%

// MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
// if(memberVO ==null) {
// 	session.setAttribute("location", request.getRequestURI());
// 	response.sendRedirect(request.getContextPath() + "/front-end/member/homepage.jsp");
// 	return;
// }
%>


<header>
    <div class="header">
      <div class="row nav-holder">
        <div class="col-4"></div>
        <div class="col-4 align-self-center">
          <div class="d-flex justify-content-center align-items-center">
            <div class="logo-container">
              <a href="#">
                <img class="logo d-none d-xl-block" src="<%=request.getContextPath()%>/resources/images/logo.png" alt="logo">
              </a>
            </div>
            <div class="logo-text-container">
              <a href="#">
                <span class="logo-text">PETBOX</span>
              </a>
            </div>
          </div>
        </div>
        <div class="col-1"></div>
        <div class="col-3 d-flex justify-content-center align-items-center login-icon-holder">
          <!-- 未登入前 -->
          <div class="col-1 align-self-center d-flex justify-content-center">
            <a href="#modalCenter" data-toggle="modal" id="login-modal">
            <i class="fas fa-sign-in-alt i-white"></i>
            </a>
          </div>
        </div>
      </div>

      <div class="row nav">
        <div class="col-lg-2 text-center">
        </div>
        <div class="col-lg-8 col-sm-12 text-center">
          <ul class="nav nav-pills nav-fill">
            <li class="nav-item">
              <a class="nav-link " href="<%=request.getContextPath()%>/front-end/store/store.html">店家</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/front-end/product/product.html">商城</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/front-end/blog/HomePage.jsp">部落格</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/front-end/donation/donation.jsp">公益</a>
            </li>
        </div>
        <div class="col-lg-2 text-center">

        </div>
      </div>
    </div>
  </header>


  <!-- Modal -->
  <div id="modalCenter" class="modal fade" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header row">
          <div class="col-lg-4"></div>
          <div class="col-lg-4 d-flex justify-content-center">
            <ul class="nav nav-tabs">
              <li class="nav-item">
                <a href="#login" class="nav-link active" data-toggle="tab">
                  <h5 class="modal-title text-center">登入</h5>
                </a>
              </li>
              <li class="nav-item">
                <a href="#signup" class="nav-link" data-toggle="tab" id="signup-link">
                  <h5 class="modal-title text-center">註冊</h5>
                </a>
              </li>
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
              <form action="<%=request.getContextPath()%>/member/controller" method="post" id="login-form"
                class="main-form">

                <div class="row justify-content-center">
                  <div class="col-6 text-left">
                    <div class="form-group">
                      <label for="login-email">電子郵件</label>
                      
                      <input type="text" name="email" id="login-email" class="form-control ${not empty errorMsgs.loginEmail ? 'is-invalid' : ''}" required>
                      <div class="${not empty errorMsgs.loginEmail ? 'invalid-feedback' : ''}">
                      ${empty errorMsgs.loginEmail ? '' : errorMsgs.loginEmail}</div>
                    </div>

                    <div class="form-group">
                      <label for="login-password">密碼</label>
                      <input type="password" name="password" id="login-password" class="form-control ${not empty errorMsgs.loginPassword ? 'is-invalid' : ''}" required>
                      <div class="${not empty errorMsgs.loginPassword ? 'invalid-feedback' : ''}">
                      ${empty errorMsgs.loginPassword ? '' : errorMsgs.loginPassword}</div>

                    </div>
                    <div class="modal-footer no-padding justify-content-start">
                      <input type="hidden" name="action" value="login">
                      <button type="submit" class="btn btn-primary no-margin">登入</button>
                      <a href="<%=request.getContextPath()%>/front-end/member/forgotPassword.jsp" class="ml-auto"
                        id="forgot-password">忘記密碼?</a>
                    </div>
                  </div>
                </div>

              </form>
            </div>

            <!-- SIGN UP FORM -->
            <div class="tab-pane fade" id="signup">
              <form action="<%=request.getContextPath()%>/member/controller" method="post" id="sign-up-form"
                class="main-form">

                <div class="row justify-content-around">
                  <div class="col-lg-4 text-left">
                    <div class="form-group">
                      <label for="name">姓名*</label>
                      <input type="text" name="name" id="name" value="${new_member.name}" class="form-control 
                      ${not empty errorMsgs.signupName ? 'is-invalid' : ''}" required>
                      <div class="${not empty errorMsgs.signupName ? 'invalid-feedback' : 'valid-feedback'}">
                            ${empty errorMsgs.signupName ? '' : errorMsgs.signupName}</div>
                    </div>
                    <div class="form-group">
                      <label for="email">電子郵件*</label>
                      <input type="text" name="email" value="${new_member.email}" id="email" class="form-control 
                      ${not empty errorMsgs.signupEmail ? 'is-invalid' : ''}" required>
                      <div class="${not empty errorMsgs.signupEmail ? 'invalid-feedback' : 'valid-feedback'}">
                            ${empty errorMsgs.signupEmail ? '' : errorMsgs.signupEmail}</div>
                    </div>
                    <div class="form-group">
                      <label for="phone_num">電話號碼</label>
                      <input type="text" value="${new_member.phone_num}" name="phone_num" id="phone_num" class="form-control 
                      ${not empty errorMsgs.signupPhone_num ? 'is-invalid' : ''}">
                      <div class="${not empty errorMsgs.signupPhone_num ? 'invalid-feedback' : ''}">
                            ${empty errorMsgs.signupPhone_num ? '' : errorMsgs.signupPhone_num}</div>
                    </div>

                    <div class="form-group">
                      <label for="address">地址</label>
                      <input type="text" value="${new_member.address}" name="address" id="address" class="form-control 
                      ${not empty errorMsgs.signupAddress ? 'is-invalid' : ''}">
                      <div class="${not empty errorMsgs.signupAddress ? 'invalid-feedback' : 'valid-feedback'}">
                            ${empty errorMsgs.signupAddress ? '' : errorMsgs.signupAddress}</div>
                    </div>

                    <div class="form-group">
                      <label for="birthday">出生日期</label>
                      <input type="text" placeholder="yyyy-mm-dd" name="birthday" id="birthday" data-date="yyyy-mm-dd"
                        class="form-control datepicker-here" data-position="top right" />
                    </div>

                    <div class="form-group">
                      <label for="sex">性別</label>
                      <select name="sex" id="sex" class="form-control">
                        <option value="">選項...</option>
                        <option value="男"  ${(new_member.sex=="男" ) ? 'selected' :'' }>男性</option>
                        <option value="女"  ${(new_member.sex=="女" ) ? 'selected' :'' }>女性</option>
                        <option value="其他"  ${(new_member.sex=="其他" ) ? 'selected' :'' }>其他</option>
                      </select>
                    </div>
                  </div>
                  <div class="col-lg-4 text-left">
                    <div class="form-group">
                      <label for="password">密碼*</label>
                      <input type="password" name="password" id="password" class="form-control 
                      ${not empty errorMsgs.signupPassword ? 'is-invalid' : ''}" required>
                      <div class="${not empty errorMsgs.signupPassword ? 'invalid-feedback' : ''}">
                            ${empty errorMsgs.signupPassword ? '' : errorMsgs.signupPassword}</div>
                    </div>
                    <div class="form-group">
                      <label for="confirm">確認密碼*</label>
                      <input type="password" name="confirm" id="confirm" class="form-control 
                      ${not empty errorMsgs.signupConfirm ? 'is-invalid' : ''}" required>
                      <div class="${not empty errorMsgs.signupConfirm ? 'invalid-feedback' : ''}">
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