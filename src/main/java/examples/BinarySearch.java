package examples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class BinarySearch {

    static final Logger logger = LogManager.getLogger(BinarySearch.class);

    public static void main(String[] args) throws FileNotFoundException {
        String path = "//C:/Job_Projects/files/digits.txt";
        File fth = new File(path);
        RandomAccessFile raf = new RandomAccessFile(fth, "r");
        long max = fth.length();
        System.out.println("bytes count= " + max);
        int val = 1;
        if (val<takeValue(max-1, raf)&& val > takeValue(0, raf)) {
            for (long i = search(val, 0, max, raf) + 1; i < max; i++) {
                if (takeValue(i, raf) > val)
                    System.out.printf("%s, ", takeValue(i, raf));
            }
        } else {
            logger.info("No values found");
        }
    }

    private static long search(int val, long min, long max, RandomAccessFile f) {

        if (min > max) return -1;
        long mid = min + (max - min) / 2;

        if (val < takeValue(mid, f)) {
            return search(val, min, mid - 1, f);
        } else if (val > takeValue(mid, f)) {
            return search(val, mid + 1, max, f);
        } else {
            return mid;
        }
    }

    private static long takeValue(long position, RandomAccessFile f) {
        long value = -1;
        try {
            f.seek(position);
            value = Integer.parseInt(String.valueOf((char) f.read()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}

