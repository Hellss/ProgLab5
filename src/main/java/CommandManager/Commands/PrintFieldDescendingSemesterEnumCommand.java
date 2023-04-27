package main.java.CommandManager.Commands;
import main.java.Model.CollectionManager;

import main.java.Model.StudyGroup;
import java.util.Comparator;


public class PrintFieldDescendingSemesterEnumCommand extends Command {
    private final CollectionManager collectionManager;

    public PrintFieldDescendingSemesterEnumCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    /**
     * Выводит значения поля semesterEnum всех элементов коллекции в порядке убывания.
     * Если какие-то элементы имеют значение поля semesterEnum, равное null, они будут пропущены.
     */
    @Override
    public void execute(String argument) {
        collectionManager.getStudyGroupsStream()
                .filter(studyGroup -> studyGroup.getSemesterEnum() != null)
                .sorted(Comparator.comparing(StudyGroup::getSemesterEnum).reversed())
                .map(StudyGroup::getSemesterEnum)
                .distinct()
                .forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "вывести значения поля semesterEnum всех элементов в порядке убывания";
    }
}
