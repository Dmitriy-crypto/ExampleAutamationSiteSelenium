package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class WriterRider {
//;lklk,;lk,pookpk
    public static void writerToFilesLinks(String link, long start_time_Long, String name, int keyStart, String url) {

        Date start_time = new Date(start_time_Long);

        try (FileWriter writer = new FileWriter("" + name, true)) {
            if (keyStart == 0) {
                String line_Enter = "\n---------------------------************************************----------------------------\n";

                writer.write(line_Enter);

                writer.write(start_time + " from  >> " + url);

                writer.write("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");

                writer.write(link);
                writer.append("\n");

                writer.flush();
            } else {
                writer.write(link);
                writer.append("\n");
                writer.flush();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
