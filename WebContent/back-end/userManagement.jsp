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
  <title>User Management</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
  <link href="https://unpkg.com/filepond/dist/filepond.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/userManagement.css">
</head>

<body>
<%
		MemberService mbSvc = new MemberService();
		List<MemberVO> members = mbSvc.getAll();
		pageContext.setAttribute("members", members);
		
		Map<String, String> mbMap = new HashMap<>();
		try{
		
		for (MemberVO mb : members) {
			int member_state = mb.getMember_state();
			switch(member_state) {
			case 0:
				mbMap.put(mb.getMember_id().toString(), "尚未通過信箱驗證");
				break;
			case 1:
				mbMap.put(mb.getMember_id().toString(), "一般會員");
				break;
			case 2:
				mbMap.put(mb.getMember_id().toString(), "提出賣家申請");
				break;
			case 3:
				mbMap.put(mb.getMember_id().toString(), "商品賣家");
				break;
			case 4:
				mbMap.put(mb.getMember_id().toString(), "店家");
				break;
			case 5:
				mbMap.put(mb.getMember_id().toString(), "停權");
				break;
			case 6:
				mbMap.put(mb.getMember_id().toString(), "停權");
				break;
			case 7:
				mbMap.put(mb.getMember_id().toString(), "停權");
				break;
			case 8:
				mbMap.put(mb.getMember_id().toString(), "停權");
				break;
			case 9:
				mbMap.put(mb.getMember_id().toString(), "停權");
				break;
			}
		}
		}catch(Exception e) {
			mbMap.put("error", "發生錯誤");
		}
		pageContext.setAttribute("mbMap",mbMap);
	%>
  <header class="bg-white">
    <div class="container-fluid">
      <div class="row header">
        <div class="col-2 d-flex align-items-center justify-content-center border-right border-bottom">
          <img src="<%=request.getContextPath()%>/resources/images/admin.svg" alt="admin">
          &nbsp;
          <h5 class="text-center admin-tab">
            Admin
          </h5>
        </div>
        <div class="col-10 d-flex align-items-center justify-content-center border-bottom">
          <h3 class="title-tab">
            使用者管理
          </h3>
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
              <th>會員編號</th>
              <th>會員姓名</th>
              <th>會員信箱</th>
              <th>會員狀態</th>
              <th>加入時間</th>
              <th>停權</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="member" items="${members}">
              <c:if test="${member.member_state > 0}">
                <tr>
                  <td>${member.member_id}</td>
                  <td>${member.name}</td>
                  <td>${member.email}</td>
                  <td>${mbMap.get(member.member_id)}</td>
                  <td><fmt:formatDate value="${member.create_time}" pattern="yyyy/MM/dd"/></td>
                  <td>
                    <form METHOD="post" action="<%=request.getContextPath()%>/member/controller">
                      <input type="hidden" name="action" value="update_state">
                      <input type="hidden" name="member_id" value="${ member.member_id }">
                      <c:if test="${member.member_state < 5}">
                        <input type="hidden" name="member_state" value="${member.member_state + 5}">
                        <input type="submit"  class="btn btn-danger" value="停權">
                      </c:if>
                      <c:if test="${member.member_state >= 5}">
                        <input type="hidden" name="member_state" value="${member.member_state - 5}">
                        <input type="submit" class="btn btn-info" value="復權">
                      </c:if>
                    </form>
                  </td>
                </tr>
		        	</c:if>
	        	</c:forEach>
            
          </tbody>
        </table>
      </div>


    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
    integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
  </script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
  </script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
    integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous">
  </script>
  <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js">
  </script>

  <script>
	//D避免重新整理的時候重新送出form表單
// 		if ( window.history.replaceState ) {
// 	 	 	window.history.replaceState( null, null, window.location.href );
// 		}
    $(document).ready(function () {

      $('#myTable').DataTable({
        searching: false,
        lengthChange: false,
        info: false,
        "language": {
          "paginate": {
            "previous": "上一頁",
            "next": "下一頁"
          }
        }
      });

    })
  </script>

</body>

</html>