package com.codecool.battleship.dao;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;
import static com.codecool.battleship.util.Constant.*;

public class DAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int EMAIL_TABLE_INDEX = 2;
    private final static int SUBSCRIBED_TABLE_INDEX = 3;


    public String[][] getDatabase() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(DATA_FILE));
        String[][] file = new String[1][2];
        int i = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(";");
            if (line[0].length() != 0) {
                file[i][0] = line[0];
                file[i][1] = line[1];
                file = Arrays.copyOf(file, file.length + 1);
                file[file.length - 1] = new String[2];
                i++;
                continue;
            }
        }
        file = Arrays.copyOf(file, file.length - 1);
        return file;
    }

    public String[] readIds() {
        String[] file = new String[]{};
        try {
            Scanner scanner = new Scanner(new File(DATA_FILE));
            int i = 0;
            while (scanner.hasNextLine()) {
                file = Arrays.copyOf(file, file.length + 1);
                file[i] = scanner.nextLine().split(";")[0];
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    public String[] readEmails() {
        String[] file = new String[]{};
        try {
            Scanner scanner = new Scanner(new File(DATA_FILE));
            int i = 0;
            while (scanner.hasNextLine()) {
                file = Arrays.copyOf(file, file.length + 1);
                file[i] = scanner.nextLine().split(";")[2];
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void writeNewCustomer(String[] newCustomer) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));
            StringBuilder toWrite = new StringBuilder();
            for (String f : newCustomer
            ) {
                if (toWrite.length() > 0) {
                    toWrite.append(";");
                }
                toWrite.append(f);
            }
            if (br.readLine() != null){
                Files.write(Path.of(DATA_FILE), "\n".toString().getBytes(), StandardOpenOption.APPEND);
            }
            Files.write(Path.of(DATA_FILE), toWrite.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String[][] customers) {
        try {
            FileChannel.open(Path.of(DATA_FILE), StandardOpenOption.WRITE)
                    .truncate(0).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String[] f : customers
        ) {
            writeNewCustomer(f);
        }
    }
}
