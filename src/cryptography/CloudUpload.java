package cryptography;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CloudUpload {
	public static int credentialsUpdate(String fileName, String accesskey, String secretkey, String bucketname) {
    	int result=-1;
		try {
			FileWriter fw = new FileWriter("C:\\Users\\Hridyanshu\\.aws\\config");
	        fw.write("[profile eb-cli]");
	        fw.write("\n");
	        fw.write("aws_access_key_id = ");
	        fw.write(accesskey);
	        fw.write("\naws_secret_access_key = ");
	        fw.write(secretkey);
	        fw.write("\n\n[default]");
	        fw.close();
	        
	        FileWriter fw2 = new FileWriter("C:\\Users\\Hridyanshu\\.aws\\credentials");
	        fw2.write("[default]\n");
	        fw2.write("aws_access_key_id = ");
	        fw2.write(accesskey);
	        fw2.write("\naws_secret_access_key = ");
	        fw2.write(secretkey);
	        fw2.close();
	        
	        result = executeCommand(bucketname, fileName);
    	}catch(Exception e) {
    		e.getStackTrace();
    	}
		System.out.println("\nResult: " + result);
    	return result;
	}
	public static int executeCommand(String bucketname, String filename) throws IOException {
		String source = "C:\\Users\\Hridyanshu\\eclipse-workspace\\HybridCryptography\\WebContent\\uploads\\" + filename;
		String destination = " s3://" + bucketname + "/";
		String command = "aws s3 cp " + source + destination;
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
		builder.redirectErrorStream(true);
		Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            if(line.contains("failed"))
            	return -1;
        }
        return 0;
	}
}