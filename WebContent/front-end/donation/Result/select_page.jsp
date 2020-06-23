<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Result: Home</title>

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
   <tr><td><h3>IBM Result: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Result: Home</p>

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
  <li><a href='listAllResult.jsp'>List</a> all Results.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Result/result.do" >
        <b>輸入成果編號 (如RID00001):</b>
        <input type="text" name="result_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  

  <jsp:useBean id="ResultSvc" scope="page" class="com.donation.donation_result.model.Donation_resultService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Result/result.do" >
       <b>選擇成果月份:</b>
       <select size="1" name="result_id">
         <c:forEach var="donation_resultVO" items="${ResultSvc.all}" > 
          <option value="${donation_resultVO.result_id}">${donation_resultVO.result_month}
         </c:forEach>   
 	 
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Result/result.do" >
       <b>選擇年份:</b>
       <select size="1" name="result_id">
         <c:forEach var="donation_resultVO" items="${ResultSvc.all}" > 
          <option value="${donation_resultVO.result_id}">${donation_resultVO.create_time}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addResult.jsp'>Add</a> a new Result.</li>
</ul>

</body>
</html>