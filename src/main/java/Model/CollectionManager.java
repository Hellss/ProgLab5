package main.java.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.Exceptions.StudyGroupNotFoundException;

public class CollectionManager {
    private int currentId;
    private final ArrayDeque<StudyGroup> studyGroups = new ArrayDeque<>();
    private final String fileName;
    private Date lastInitTime;
    private int size;

    public String getType() {
        return studyGroups.getClass().toString();
    }


    public CollectionManager(String fileName) throws IOException {
        this.fileName = fileName;
        this.currentId = 0;
        loadCollection();
    }

    public void add(StudyGroup studyGroup) {
        studyGroups.add(studyGroup);
        size++;
    }

    public void updateById(Integer id, StudyGroup newStudyGroup) {
        for (StudyGroup studyGroup : studyGroups) {
            if (studyGroup.getId().equals(id)) {
                studyGroups.remove(studyGroup);
                studyGroups.add(newStudyGroup);
                return;
            }
        }
        throw new StudyGroupNotFoundException("StudyGroup with id = " + id + " not found.");
    }

    public StudyGroup getById(Integer id) {
        for (StudyGroup studyGroup : studyGroups) {
            if (studyGroup.getId() == id) {
                return studyGroup;
            }
        }
        return null; // если элемент с таким id не найден
    }

    public StudyGroup removeHead() {
        if (studyGroups.isEmpty()) {
            System.out.println("Коллекция пуста");
            return null;
        }
        StudyGroup head = studyGroups.getFirst();
        studyGroups.remove(head);
        System.out.println("Первый элемент коллекции удалён.");
        return head;
    }


    public boolean removeById(Integer id) {
        if (studyGroups.removeIf(studyGroup -> studyGroup.getId().equals(id))) {
            size--;
            return true;
        }
        return false;
    }

    public void clear() {
        studyGroups.clear();
        size = 0;
    }

    public Queue<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    public Date getLastInitTime() {
        return lastInitTime;
    }

    public int getSize() {
        return size;
    }

    public void loadCollection() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            Gson gson = new Gson();
            StudyGroup[] studyGroupsArray = gson.fromJson(json.toString(), StudyGroup[].class);
            clear();

            HashSet<Integer> set = new HashSet<>();

            for (StudyGroup studyGroup : studyGroupsArray) {
                Person admin = studyGroup.getGroupAdmin();
                if (studyGroup.getId() == null || studyGroup.getId() <= 0 ||
                        studyGroup.getName() == null || studyGroup.getName().length() == 0 ||
                        studyGroup.getCoordinates() == null ||
                        studyGroup.getCreationDate() == null ||
                        studyGroup.getStudentsCount() != null && studyGroup.getStudentsCount() <= 0 ||
                        studyGroup.getFormOfEducation() == null ||
                        studyGroup.getCoordinates().getX() == null || studyGroup.getCoordinates().getX() > 117 ||
                        admin == null ||
                        admin.getName() == null || admin.getName().isEmpty() ||
                        admin.getHeight() != null && admin.getHeight() <= 0 ||
                        admin.getWeight() != null && admin.getWeight() < 0 ||
                        admin.getPassportID() == null || admin.getPassportID().length() < 6 ||
                        admin.getLocation() != null &&
                               (admin.getLocation().getX() == null || admin.getLocation().getY() == null || admin.getLocation().getName().length() > 283)) {
                    throw new IOException("проблема с данными полей");
                }
                if (!set.add(studyGroup.getId())) {
                    throw new IOException("проблема с уникальностью id");
                }
            }

            for (StudyGroup studyGroup : studyGroupsArray) {
                studyGroups.add(studyGroup);
                size++;

                if (studyGroup.getId() > currentId)
                {
                    currentId = studyGroup.getId();
                }
            }
            lastInitTime = new Date();
            System.out.println("Коллекция загружена. Количество элементов: " + size);
        } catch (FileNotFoundException e) {
            System.out.println("Файл \"" + fileName + "\" не найден. Будет создан новый файл при сохранении.");
        } catch (JsonSyntaxException e) {
            System.out.println( "Ошибка при чтении файла \"" + fileName + "\": некорректный формат JSON.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла \"" + fileName + "\": сломана коллекция: " + e.getMessage());
        }
    }

    public void saveCollection() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(studyGroups.toArray());
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        }
        System.out.println("Коллекция сохранена в файл \"" + fileName + "\".");
    }

    public void checkFileWritable() throws IOException {
        File file = new File(fileName);
        if (file.exists() && !file.canWrite()) {
            throw new IOException("Нет прав на запись в файл \"" + fileName + "\".");
        }
    }

    public Integer generateNextId() {
        currentId++;
        return currentId;
    }
    public List<StudyGroup> getSortedStudyGroups() {
        List<StudyGroup> sortedGroups = new ArrayList<>(studyGroups);
        sortedGroups.sort(null);
        return sortedGroups;
    }

    public List<Semester> getUniqueSemesterEnums() {
        return studyGroups.stream()
                .map(StudyGroup::getSemesterEnum)
                .distinct()
                .collect(Collectors.toList());
    }

    public Stream<StudyGroup> getStudyGroupsStream() {
        return studyGroups.stream();
    }

    public int removeLower(StudyGroup studyGroup) {
        int initialSize = studyGroups.size();
        studyGroups.removeIf(group -> group.compareTo(studyGroup) < 0);
        return initialSize - studyGroups.size();
    }

    public int removeGreater(StudyGroup studyGroup) {
        int initialSize = studyGroups.size();
        studyGroups.removeIf(sg -> sg.compareTo(studyGroup) > 0);
        return initialSize - studyGroups.size();
    }
}