package main.java.CommandManager.Commands;
import main.java.CommandManager.CommandManager;

public class ExitCommand extends Command {
    private final CommandManager commandManager;


    public ExitCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String argument) {
        System.out.println("Завершение программы...");
        commandManager.stop();
    }

    @Override
    public String getDescription() {
        return "завершить программу (без сохранения в файл)";
    }
}
