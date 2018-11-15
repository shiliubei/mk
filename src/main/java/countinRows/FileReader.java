package countinRows;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;


public class FileReader {

    static final Logger userLogger = LogManager.getLogger(FileReader.class);
    public static void main(String[] args) {
        String fileName = "//C:/ChineseFirst/files/file.txt";
        int count = 0;

        try{
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            userLogger.info("file opened");
            while ((strLine = br.readLine()) != null){
                System.out.println(strLine);
                count++;
            }

            br.close();
            userLogger.info("file closed");
        }catch (IOException e){
            System.out.println("Ошибка");
        }
        System.out.println("Number of rows = " + count);
    }
}
