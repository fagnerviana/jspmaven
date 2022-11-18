<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curso JSP</title>
</head>
<body>

<h1>Seja bem vindo ao nosso sistema</h1>

<form action="ServletLogin" method="post">

<table>
  <tr>
  <td><label>Login</label></td>
  <td><input name="Login" type="text">
  </td>
  </tr>
  
  <tr>
   
     <td><label>Senha</label></td>
   <td>
    <input name ="Senha" type="password">
   </td> 
 </tr>
 
 <tr>
 <td>
  <input type="submit" value="Enviar">
 </td>
 </tr>

</table> 
 
  <h4>${msg}</h4>
</form>


</body>
</html>