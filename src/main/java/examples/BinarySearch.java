package examples;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BinarySearch {


    public static void main(String[] args) {
        String path = "//C:/Job_Projects/files/digits.txt";
        File f = new File(path);
        long min = 0;
        long max = f.length();
        System.out.println("bytes count= " + max);
        int val = 3;
        for (long i = search(val) + 1; i < max; i++) {
            if (takeValue(i) > val) {
                System.out.println(takeValue(i));
            } else {
                System.out.println(" ");
            }

        }

    }

    public static long search(int val) {
        String path = "//C:/Job_Projects/files/digits.txt";
        File f = new File(path);
        long max = f.length();
        return search(val, 0, max);
    }

    private static long search(int val, long min, long max) {

        if (min > max) return -1;

        long mid = min + (max - min) / 2;

        if (val < takeValue(mid)) {
            return search(val, min, mid - 1);
        } else if (val > takeValue(mid)) {
            return search(val, mid + 1, max);
        } else {
            return mid;
        }
    }

    private static long takeValue(long position) {
        String path = "//C:/Job_Projects/files/digits.txt";
        int value = -1;
        try {
            RandomAccessFile raf = new RandomAccessFile(path, "r");
            raf.seek(position);
            value = Integer.valueOf(String.valueOf((char) raf.read()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}
