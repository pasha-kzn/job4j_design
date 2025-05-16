package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String start = null;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(" ");
                String statusCode = elements[0];
                String time = elements[1];
                if (("400".equals(statusCode) || "500".equals(statusCode)) && start == null) {
                    start = time;
                } else if (("200".equals(statusCode) || "300".equals(statusCode)) && start != null) {
                    writer.println(start + "; " + time + ";");
                    start = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}