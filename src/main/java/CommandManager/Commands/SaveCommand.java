package main.java.CommandManager.Commands;

import main.java.Model.CollectionManager;

import java.io.IOException;

public class SaveCommand extends Command {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Сохраняет коллекцию в файл
     */
    public void execute(String argument) {
        try {
            collectionManager.checkFileWritable();
            collectionManager.saveCollection();
        } catch (IOException e) {
            System.out.println("Ошибка сохранения коллекции в файл : " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }
}
