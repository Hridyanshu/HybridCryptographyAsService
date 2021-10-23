<%    
  String filename = session.getAttribute("fileName").toString();
  String filepath = "C:\\Users\\Hridyanshu\\eclipse-workspace\\HybridCryptography\\WebContent\\uploads\\";   
  response.setContentType("APPLICATION/OCTET-STREAM");   
  response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
  
  java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath + filename);  
            
  int i;   
  while ((i=fileInputStream.read()) != -1) {  
    out.write(i);   
  }   
  fileInputStream.close();
%> 