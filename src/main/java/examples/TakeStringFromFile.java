package examples;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TakeStringFromFile {
    public static void main(String[] args) throws IOException, ParseException {
        String path = "//C:/Job_Projects/files/nasalines.txt";
        RandomAccessFile raf = new RandomAccessFile(path, "r");
        File f = new File(path);
        long max = f.length();
        //parseLine(80, raf);

        System.out.println(takeValue(0, raf));
        findEnd(120, raf, f);
        System.out.println(findStart(120, raf));

        String foundDate = "01/Aug/1995:00:00:12";

        DateFormat format = new SimpleDateFormat("d/MMM/yyyy:H:m:s", Locale.ENGLISH);
        System.out.println(format);
        Date date = format.parse(foundDate);
        boolean found = false;
        long start = 0;
        long end = max;
        long mid = (max + start) / 2;

        while (true) {
            Date parseDate = getDate.getLine(mid, raf, f);
            mid = (max + start) / 2;
            if (date > parseDate) {
                start = mid;
            }
            if (date < parseDate) {
                max = mid;
            } else {
                found = true;
            }

        }
        getLine(250, raf, f);
    }

    public static LogLine getLine(long position, RandomAccessFile raf, File f) throws IOException {
        //String line = new String();
        int start = (int) findStart(position, raf);
        int end = (int) findEnd(position, raf, f);
        byte[] buffer = new byte[end - start];
        //logger.info("No values found");
        //^(?:[^\s]+\s){3}\[([^\]]*)\].*$
        try {
            raf.seek(start);
        } catch (IOException e) {
            e.printStackTrace();
        }
        raf.read(buffer, 0, end - start);
        String text = new String(buffer, "US-ASCII");
        System.out.println(text);
        return new LogLine(text, start, end);

    }

    private static class LogLine {
        private final String line;
        private final long startPos;
        private final long endPos;

        LogLine(String line, long endPos, long startPos) {
            this.line = line;
            this.startPos = startPos;
            this.endPos = endPos;
        }

        long getStartPos() {
            return this.startPos;
        }

        long getEndPos() {
            return this.endPos;
        }

        final Date getDate() throws ParseException {
            //String foundDate = "01/Aug/1995:00:00:12";
            DateFormat format = new SimpleDateFormat("d/MMM/yyyy:H:m:s", Locale.ENGLISH);
            final String regex = "^(?:[^\\s]+\\s){3}\\[([^\\]]*)\\].*";
            final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(line);
            matcher.find();
            Date date = format.parse(matcher.group(1));
            System.out.println(format);
            return date;
        }

    }

    private static long findEnd(long position, RandomAccessFile raf, File f) {
        long max = f.length();
        if (!takeValue(position, raf).equals("\n") && position != max) {
            while (!takeValue(position, raf).equals("\n") && position != max) {
                System.out.println(takeValue(position, raf));
                position++;
            }
            System.out.println("line end found, position" + position);
        }
        return position;
    }

    private static long findStart(long position, RandomAccessFile raf) {
        if (!takeValue(position, raf).equals("\n") && position != 0) {
            while (!takeValue(position, raf).equals("\n") && position != 0) {
                System.out.println(takeValue(position, raf));
                position--;
            }
            System.out.println("found");
        }
        return position;
    }

    private static String takeValue(long position, RandomAccessFile raf) {
        String value = " ";
        try {
            raf.seek(position);
            value = String.valueOf(String.valueOf((char) raf.read()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}
