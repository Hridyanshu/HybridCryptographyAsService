<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="cryptography.*" %>  
<%  
	MultipartRequest m = new MultipartRequest(request, "C:\\Users\\Hridyanshu\\eclipse-workspace\\HybridCryptography\\WebContent\\uploads", 5000000);
	String fileName = m.getFilesystemName("chooseFile");
	String choice = m.getParameter("operation");
	String key = m.getParameter("key");
	if(choice.equals("encrypt")) {
		Encryption.encrypt(fileName, key);
		fileName = "encrypted-" + fileName;
		session.setAttribute("fileName",fileName);
		response.sendRedirect("download");
	}
	else if(choice.equals("decrypt")) {
		Decryption.decrypt(fileName, key);
		fileName = "decrypted-" + fileName;
		session.setAttribute("fileName",fileName);
		response.sendRedirect("download");
	}
%>