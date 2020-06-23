<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.donation.npo_info.model.*"%>
    

<%
  Npo_infoVO npo_infoVO = (Npo_infoVO) request.getAttribute("npo_infoVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>Insert title here</title>
</head>
  <style>
    @import url(https://fonts.googleapis.com/css?family=Roboto:400,300,600,400italic);
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      -webkit-box-sizing: border-box;
      -moz-box-sizing: border-box;
      -webkit-font-smoothing: antialiased;
      -moz-font-smoothing: antialiased;
      -o-font-smoothing: antialiased;
      font-smoothing: antialiased;
      text-rendering: optimizeLegibility;
    }

    body {
      font-family: "Roboto", Helvetica, Arial, sans-serif;
      font-weight: 100;
      font-size: 12px;
      line-height: 30px;
      color: #777;
      background: #FFFFFF;
    }

    .container {
      max-width: 400px;
      width: 100%;
      margin: 0 auto;
      position: relative;
    }



    #contact {
      /* background: #F9F9F9;  中間框框的顏色*/
      padding: 25px;
      margin-top: 150px;
      box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    }

  h3.h3c {
      display: block;
      font-size: 30px;
      font-weight: 300;
      margin-bottom: 10px;
      text-align: center;
    }

    fieldset {
      border: medium none !important;
      margin: 0 0 10px;
      min-width: 100%;
      padding: 0;
      width: 100%;
    }

    #contact input[type="text"],

    #contact textarea {
      width: 100%;
      border: 1px solid #ccc;
      background: #FFF;
      margin: 0 0 5px;
      padding: 10px;
    }

    #contact input[type="text"]:hover, 

     #contact textarea:hover { 
       -webkit-transition: border-color 0.3s ease-in-out; 
       -moz-transition: border-color 0.3s ease-in-out; 
       transition: border-color 0.3s ease-in-out; 
       border: 1px solid #aaa; */
     } */

    #contact textarea {
      height: 100px;
      max-width: 100%;
      resize: none;
    }

 input.bc {
      cursor: pointer;
      width: 100%;
      border: none;
      background: #5ec562;
      color: #FFF;
      margin: 0 0 5px;
      padding: 10px;
      font-size: 15px;
    }
 input.bc:hover {
      background: #43A047;
    }
 button.back {
        cursor: pointer;
        width: 100%;
        border: none;
        background: #00BBFF;
        color: #FFF;
        margin: 0 0 5px;
        padding: 10px;
        font-size: 15px;
      }
   button.back:hover {
        background: #02a4de;
      }

    .copyright {
      text-align: center;
    }

    #contact input:focus,
    #contact textarea:focus {
      outline: 0;
      border: 1px solid #aaa;
    }

    ::-webkit-input-placeholder {
      color: #888;
    }

    :-moz-placeholder {
      color: #888;
    }

    ::-moz-placeholder {
      color: #888;
    }

    :-ms-input-placeholder {
      color: #888;
    }
    span.error{
    color:red;
    }
  </style>
<body>

  <div class="container" id="contact">
  <FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/Npo/npo.do" name="form1" enctype="multipart/form-data">
    <h3 class="h3c">公益團體修改</h3>
    <fieldset>
	公益團體編號:<font color=red><b>*</b></font><%=npo_infoVO.getNpo_id()%>
	</fieldset>
    <fieldset>
      <input  name="npo_name" type="text" tabindex="1" value="<%=npo_infoVO.getNpo_name()%>" autofocus>
      <span class="error">${errors.npo_name}</span>
    </fieldset>
    <fieldset>
		<input type="file" name="npo_image" size="45"	value="<%=npo_infoVO.getNpo_image()%>" />
    </fieldset>
    <fieldset>
      <textarea id="tv" name="npo_description" type="text" tabindex="3" ><%=npo_infoVO.getNpo_description()%></textarea>
      <span class="error">${errors.npo_description}</span>
    </fieldset>
    <fieldset>
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="npo_id" value="<%=npo_infoVO.getNpo_id()%>"> 
      <input type="submit" class="bc"  value="送出修改">
     </fieldset>
    <fieldset>
<!--       <button name="submit" class="back" type="submit" >返回</button> -->
    </fieldset>
  </FORM>
      <fieldset>
       <a href="<%=request.getContextPath()%>/back-end/donationTest.jsp"><button  class="back"  >返回</button></a>
     </fieldset>
</div>
</body>

</html>