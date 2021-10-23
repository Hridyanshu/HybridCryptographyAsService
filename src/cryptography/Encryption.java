package cryptography;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Encryption {
    public static void encrypt(String fileName, String key) throws IOException {
        FileReader fr = new FileReader("C:\\Users\\Hridyanshu\\eclipse-workspace\\HybridCryptography\\WebContent\\uploads\\" + fileName);
        String newFileName = "encrypted-" + fileName;
    	FileWriter fw = new FileWriter("C:\\Users\\Hridyanshu\\eclipse-workspace\\HybridCryptography\\WebContent\\uploads\\" + newFileName);
        int i;
        String temp = "", temp2;
        int counter = 1;
        while ((i=fr.read()) != -1) {
            if((char) i == ' ' || (char)i == '\n') {
                if(counter==1) {
                    temp2 = AES.encryption(temp, key);
                    fw.write(temp2);
                    fw.write((char)i);
                    counter++;
                }
                else if(counter==2) {
                    temp2 = RC4.encrypt(temp, key);
                    fw.write(temp2);
                    fw.write((char)i);
                    counter++;
                }
                else if(counter==3) {
                    temp2 = DES.encryption(temp, key);
                    fw.write(temp2);
                    fw.write((char)i);
                    counter=1;
                }
                temp = "";
                continue;
            }
            temp = temp + (char)i;
        }
        if(temp!=""){
            if(counter==1) {
                temp2 = AES.encryption(temp, key);
                fw.write(temp2);
            }
            else if(counter==2) {
                temp2 = RC4.encrypt(temp, key);
                fw.write(temp2);
            }
            else {
                temp2 = DES.encryption(temp, key);
                fw.write(temp2);
            }
        }
        fr.close();
        fw.close();
    }
}