package main.java;

import java.io.IOException;
import java.util.Scanner;

import main.java.CommandManager.CommandManager;
import main.java.Model.CollectionManager;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0)
        {
            System.out.println("Файл не введен");
            return;
        }
        try {
            String fileName = args[0];
            if (fileName == null) {
                System.out.println("Файл не найден");
                System.exit(0);
            }
            CollectionManager collectionManager = new CollectionManager(fileName);
            CommandManager commandManager = new CommandManager(collectionManager, new Scanner(System.in));
            commandManager.run();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}