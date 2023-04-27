package main.java.CommandManager.Commands;

public abstract class Command {
    public void execute(String argument) {};

    public void execute(String argument, String[] args) {};

    public String getDescription() { return ""; }
    public String getFormat() { return ""; }

    public void Reset() {}
}