<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.donation.donation_form_info.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

    
<%
    Donation_form_infoService DonationFormSvc = new Donation_form_infoService();
    List<Donation_form_infoVO> list = DonationFormSvc.getAll();
    pageContext.setAttribute("list",list);
    System.out.print("list" + list);
%>
<% 
 String donator_name =request.getParameter("donator_name");
	String donation_phone_num =request.getParameter("donation_phone_num");
    Donation_form_infoService DonationFormSvcc = new Donation_form_infoService();
	List<Donation_form_infoVO> listt = DonationFormSvcc.getSelect(donator_name,donation_phone_num);
    pageContext.setAttribute("listt",listt);
    System.out.print("listt" + listt);
  %>  
 <%
  Donation_form_infoVO donation_form_infoVO = (Donation_form_infoVO) request.getAttribute("donation_form_infoVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<jsp:useBean id ="npoSvc" scope="page" class="com.donation.npo_info.model.Npo_infoService"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
  h1{
    font-size: 30px;
    color: #fff;
    text-transform: uppercase;
    font-weight: 300;
    text-align: center;
    margin-bottom: 15px;
  }
  table{
    width:100%;
    table-layout: fixed;
  }
  .tbl-header{
    background-color: rgba(255,255,255,0.3);
   }
  .tbl-content{
    height:300px;
    overflow-x:auto;
    margin-top: 0px;
    border: 1px solid rgba(255,255,255,0.3);
  }
  th{
    padding: 20px 15px;
    text-align: left;
    font-weight: 500;
    font-size: 12px;
    color: #fff;
    text-transform: uppercase;
  }
  td{
    padding: 15px;
    text-align: left;
    vertical-align:middle;
    font-weight: 300;
    font-size: 12px;
    color: #fff;
    border-bottom: solid 1px rgba(255,255,255,0.1);
  }
  .tbl-content tbody :hover {
  	/* background: 	#00CACA; */
    background:#000000;
  }
/* tr.tr1:hover{
  background-color: rgba(255,255,255,0.3);

} */

  /* demo styles */

  @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
  body{
    /* background: -webkit-linear-gradient(left, #FFD9EC, #25b7c4);
    background: linear-gradient(to right, #FFD9EC, #25b7c4); */
 background-color: #FFBD9D;
    font-family: 'Roboto', sans-serif;
  }
  section{
    margin: 50px;
  }
  /* for custom scrollbar for webkit browser*/

  ::-webkit-scrollbar {
      width: 6px;
  }
  ::-webkit-scrollbar-track {
      -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
  }
  ::-webkit-scrollbar-thumb {
      -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
  }
  a.aback{
    position: absolute;
    right: 984px;
  }
</style>
</head>
<body>
<section>
    <!--for demo wrap-->
    <h1>捐款紀錄</h1>
    <div class="tbl-header">
      <table cellpadding="0" cellspacing="0" border="0">
        <thead>
        
          <tr class="tr1">
            <th>捐款日期</th>
            <th>捐款地區</th>
            <th>捐款人</th>
            <th>捐款金額</th>
            <th>付款方式</th>
          </tr>
        </thead>
      </table>
    </div>
    <div class="tbl-content">
      
      <table cellpadding="0" cellspacing="0" border="0">
      <c:forEach var="donation_form_infoVO" items="${listt}" >
        
        <tbody>
       
          <tr>
<%--             <td><%=donation_form_infoVO.getCreate_time()%></td> --%>
<%--             <td>${npoSvc.getOneNpo_info(donation_form_infoVO.npo_id).npo_name}</td> --%>
<%--             <td><%=donation_form_infoVO.getDonator_name()%></td> --%>
<%--             <td><%=donation_form_infoVO.getDonation_money()%></td> --%>
<%--             <td><%=donation_form_infoVO.getPayment()%></td> --%>
 			<td>${donation_form_infoVO.create_time}</td>
            <td>${npoSvc.getOneNpo_info(donation_form_infoVO.npo_id).npo_name}</td>
            <td>${donation_form_infoVO.donator_name}</td>
            <td>${donation_form_infoVO.donation_money}</td>
            <td>${donation_form_infoVO.payment}</td>
          </tr>
          </tr>
<!--           <tr> -->
<!--             <td>AAD</td> -->
<!--             <td>AUSENCO</td> -->
<!--             <td>$2.38</td> -->
<!--             <td>-0.01</td> -->
<!--             <td>-1.36%</td> -->
<!--           </tr> -->
<!--           <tr> -->
<!--             <td>AAX</td> -->
<!--             <td>ADELAIDE</td> -->
<!--             <td>$3.22</td> -->
<!--             <td>+0.01</td> -->
<!--             <td>+1.36%</td> -->
<!--           </tr> -->
        
        </tbody>
        </c:forEach>
      </table>
      
    </div>
    			<a class="aback" href="<%=request.getContextPath()%>/front-end/donation/myMain/donation.jsp">返回公益首頁</a>	
    
  </section>
</body>
<!--   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  
  <script src="JS/table.js"></script>
</html>