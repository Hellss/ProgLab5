package main.java.CommandManager.Commands;

import java.io.*;

import main.java.Exceptions.CommandExecutionException;
import main.java.CommandManager.CommandManager;

import java.util.HashSet;

public class ExecuteScriptCommand extends Command {
    private final CommandManager commandManager;
    private HashSet<String> set = new HashSet<String>();

    public ExecuteScriptCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void Reset() { set.clear(); }

    @Override
    public String getDescription() {
        return "считывает и исполняет скрипт из указанного файла";
    }

    @Override
    public String getFormat() {return "file_name";}

    @Override
    public void execute(String argument) throws CommandExecutionException {
        if (!set.add(argument)) {
            throw new CommandExecutionException("Бесконечная рекурсия");
        }
        File file = new File(argument);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] commandParts = line.trim().split("\\s+");
                Command command = commandManager.getCommand(commandParts[0]);
                if (command == null) {
                    throw new CommandExecutionException("Неизвестная команда: " + commandParts[0]);
                }
                String arg = commandParts.length > 1 ? commandParts[1] : "";

                if (command.getFormat().equals("{element}")) {
                    String[] commandArgs = new String[13];
                    int num = 0;
                    while ((line = reader.readLine()) != null && num < 13) {
                        commandArgs[num] = line;
                        num += 1;
                    }
                    command.execute(arg, commandArgs);
                } else {
                    command.execute(arg);
                }
            }
            System.out.println("Скрипт " + argument + " выполнен.");
        } catch (FileNotFoundException e) {
            throw new CommandExecutionException("Файл не найден: \"" + argument + "\"");
        } catch (IOException e) {
            throw new CommandExecutionException("Ошибка при чтении файла: " + e.getMessage());
        }

    }
}
