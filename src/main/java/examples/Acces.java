package examples;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Acces {
    private String path = "//C:/Job_Projects/files/nasa.txt";
    private RandomAccessFile file;

    public static void main(String[] args) {
        String pathName = "//C:/Job_Projects/files/digits.txt";
        File f = new File(pathName);
        long len = f.length();
        String row = "";
        String fra;
        System.out.println("length: " + len);
        try {
            RandomAccessFile raf = new RandomAccessFile("//C:/Job_Projects/files/digits.txt", "r");
            String str = "7";
            int b = raf.read();
            boolean checkStart = false;
            boolean checkEnd = false;
            System.out.println("Let's start");
            System.out.println((char) raf.read());
            while (checkStart == false && b != -1) {
                str = String.valueOf((char) raf.read());
                System.out.println(str);
                if (str.equals("6")) {
                    System.out.println("if");
                    checkStart = true;
                }
            }
            while (checkEnd == false && b != -1) {
                str = String.valueOf((char) raf.read());
                if (str.equals("8")) {
                    checkEnd = true;
                }
                row += str;
            }
            System.out.println(row);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
