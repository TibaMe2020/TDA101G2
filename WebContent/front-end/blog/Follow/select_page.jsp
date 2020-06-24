<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Follow: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Follow: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Follow : Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllFollow.jsp'>List all Follows.</a><br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Follow/Follow.do" >
        <b>輸入關注id (如FID00001):</b>
        <input type="text" name="follow_id">
        <input type="hidden" name="action" value="getOneFollow">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Follow/Follow.do" >
        <b>輸入會員id (如MB00001):</b>
        <input type="text" name="member_id">
        <input type="hidden" name="action" value="getByMemberId">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Follow/Follow.do" >
        <b>查詢關注我的人,請輸入我的會員id (如MB00001):</b>
        <input type="text" name="member_id">
        <input type="hidden" name="action" value="getAllFollowMe">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="service" scope="page" class="com.blog.follow.model.FollowService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Follow/Follow.do" >
       <b>選擇關注id:</b>
       <select size="1" name="follow_id">
         <c:forEach var="followVO" items="${service.all}" > 
          <option value="${followVO.follow_id}">${followVO.follow_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneFollow">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>關注管理</h3>

<ul>
  <li><a href='addFollow.jsp'>Add a new Follow.</a></li>
</ul>

</body>
</html>