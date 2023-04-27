package main.java.CommandManager;

import main.java.CommandManager.Commands.*;
import main.java.Model.CollectionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final CollectionManager collectionManager;
    private Scanner scanner;
    private static boolean isRunning = true;

    public CommandManager(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
        initCommands();
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    private void initCommands() {
        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager, scanner));
        commands.put("update", new UpdateCommand(collectionManager, scanner));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager, scanner));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(this));
        commands.put("exit", new ExitCommand(this));
        commands.put("remove_head", new RemoveHeadCommand(collectionManager));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager, scanner));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager, scanner));
        commands.put("print_ascending", new PrintAscendingCommand(collectionManager));
        commands.put("print_unique_semester_enum", new PrintUniqueSemesterEnumCommand(collectionManager));
        commands.put("print_field_descending_semester_enum", new PrintFieldDescendingSemesterEnumCommand(collectionManager));
    }

    public void run() {
        try {
            while (isRunning) {
                System.out.print("Введите команду: ");
                String[] input = scanner.nextLine().trim().split("\\s+", 2);

                String commandName = input[0];
                String argument = input.length > 1 ? input[1] : "";
                Command command = commands.get(commandName);
                if (command == null) {
                    System.out.println("Команда \"" + commandName + "\" не найдена. Введите \"help\" для списка команд.");
                } else {
                    try {
                        command.execute(argument);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Ошибка выполнения команды \"" + commandName + "\": " + e.getMessage());
                    }
                    if (commandName.equals("execute_script"))
                        commands.get("execute_script").Reset();
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nПрекращение ввода");
        }
    }

    public void stop() {
        isRunning = false;
    }
}