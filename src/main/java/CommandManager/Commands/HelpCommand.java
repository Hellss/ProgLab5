package main.java.CommandManager.Commands;

import java.util.Map;

public class HelpCommand extends Command {
    private final Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String argument) {
        StringBuilder sb = new StringBuilder();
        sb.append("Список доступных команд:").append(System.lineSeparator());
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue().getFormat()).append(" ").append(entry.getValue().getDescription()).append(System.lineSeparator());
        }
        System.out.println(sb.toString().trim());
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }
}
