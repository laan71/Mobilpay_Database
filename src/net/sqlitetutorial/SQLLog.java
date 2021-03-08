package net.sqlitetutorial;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLLog {

    public static void Log(String method, String variable) {

        if ("false".equals(variable)) {
            variable = "none";
        }

        FileWriter fw = null;

        try {
            fw = new FileWriter("src/SQL_Quiries.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());

            bw.write("Date: " + formatter.format(date) + " - Query: " + method + " variable used " + variable);
            bw.newLine();
            bw.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
