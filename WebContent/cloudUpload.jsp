<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="cryptography.*" %> 

<%    
  String filename = session.getAttribute("fileName").toString();
  String accesskey = request.getParameter("accesskey");
  String secretkey = request.getParameter("secretkey");
  String bucketname = request.getParameter("bucketname");
  int result = CloudUpload.credentialsUpdate(filename,accesskey,secretkey,bucketname);
  System.out.println("\n\nResult: " + result);
  if(result==-1) {
	  response.sendRedirect("error.html");
  }
%>


<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
		<script>
			alert("File uploaded to cloud successfully!");
			window.location = "index.html";
		</script>
	</head>
</html>