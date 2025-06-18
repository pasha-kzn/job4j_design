package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> botPhrases = readPhrases();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS");
        Scanner input = new Scanner(System.in);
        boolean run = true;
        boolean isSilent = false;
        while (run) {
            String usersPhrase = input.nextLine();
            log.add(String.format("%s stupid user asks:  %s", sdf.format(new Date()), usersPhrase));
            switch (usersPhrase) {
                case OUT:
                    run = false;
                    break;
                case STOP:
                    isSilent = true;
                    break;
                case CONTINUE:
                    isSilent = false;
                    String readyToAnswer = "Готов отвечать дальше";
                    System.out.println(readyToAnswer);
                    log.add(String.format("%s  smart bot answers: %s", sdf.format(new Date()), readyToAnswer));
                    break;
                default:
                    if (!isSilent) {
                        String botAnswer = botPhrases.get(new Random().nextInt(botPhrases.size()));
                        System.out.println(botAnswer);
                        log.add(String.format("%s smart bot answers: %s", sdf.format(new Date()), botAnswer));
                    }
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> botPhrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.botAnswers, Charset.forName("UTF-8")))) {
            botPhrases = reader.lines()
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botPhrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/console_chat_log.txt", "data/bot_answers.txt");
        consoleChat.run();
    }
}
