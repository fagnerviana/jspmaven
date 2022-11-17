<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Fagner Viana</h1>


<%
 out.print("Seu sucesso garantido");
%>


  <form action="ServletLogin" method="post">

  <input name="nome">
  <input name ="idade"> 
  
  <input type="submit" value="Enviar">
  
  </form>


</body>
</html>