package main.java.Model;

import java.util.Date;
import java.util.Scanner;

/**
 * Класс-помощник для создания объекта StudyGroup из вводимых пользователем значений полей.
 */

public class StudyGroupBuilder {

    private final Scanner scanner;

    public StudyGroupBuilder(Scanner scanner) {
        this.scanner = scanner;
    }

    public StudyGroup build(Integer id, String[] args) {
        StudyGroup studyGroup = new StudyGroup();
        if (args.length < 13)
            return null;

        studyGroup.setId(id);


        //System.out.print("Введите имя группы: ");
        studyGroup.setName(args[0]);
        if (studyGroup.getName().isBlank()) {
            //System.out.println("Имя группы не может быть пустым. Команда не выполнена");
            return null;
        }

        Coordinates coordinates = new Coordinates();
        //System.out.print("Введите координату x: ");
        try {
            coordinates.setX(Double.parseDouble(args[1]));
            if (coordinates.getX() > 117) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Координата x должна быть числом и быть меньше или равна 117. Команда не выполнена.");
            return null;
        }

        //System.out.print("Введите координату y: ");
        try {
            coordinates.setY(Integer.parseInt(args[2]));
        } catch (NumberFormatException e) {
            System.out.println("Координата y должна быть целым числом. Команда не выполнена.");
            return null;
        }

        studyGroup.setCoordinates(coordinates);

        Date creationDate = new Date();
        studyGroup.setCreationDate(creationDate);

        //System.out.print("Введите количество студентов: ");
        try {
            String input = args[3];
            if (!input.isBlank()) {
                int studentsCount = Integer.parseInt(input);
                if (studentsCount > 0) {
                    studyGroup.setStudentsCount(studentsCount);
                } else {
                    System.out.println("Количество студентов должно быть больше 0. Команда не выполнена.");
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Количество студентов должно быть целым числом. Команда не выполнена.");
            return null;
        }

        //System.out.println("Выберите форму обучения (FULL_TIME_EDUCATION, DISTANCE_EDUCATION, EVENING_CLASSES): ");
        try {
            studyGroup.setFormOfEducation(FormOfEducation.valueOf(args[4].toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("Неправильный ввод. Пожалуйста, повторите команду.");
            return null;
        }

        //System.out.println("Выберите номер семестра (FIRST, FOURTH, SIXTH, EIGHTH) или нажмите Enter, чтобы пропустить: ");
        String input = args[5];
        if (!input.isBlank()) {
            try {
                studyGroup.setSemesterEnum(Semester.valueOf(input.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Неправильный ввод. Пожалуйста, повторите команду.");
                return null;
            }
        }

        Person groupAdmin = new Person();
        //System.out.print("Введите имя администратора группы: ");
        groupAdmin.setName(args[6]);
        if (groupAdmin.getName().isBlank()) {
            System.out.println("Имя администратора группы не может быть пустым. Команда не выполнена.");
            return null;
        }

        //System.out.print("Введите рост администратора группы (целое число, больше 0): ");
        try {
            String inputHeight = args[7];
            if (!inputHeight.isBlank()) {
                Long height = Long.parseLong(inputHeight);
                if (height > 0) {
                    groupAdmin.setHeight(height);
                } else {
                    System.out.println("Рост должен быть больше 0. Команда не выполнена.");
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Рост должен быть целым числом. Команда не выполнена.");
            return null;
        }

        //System.out.print("Введите вес администратора группы (вещественное число, больше 0): ");
        try {
            String inputWeight = args[8];
            if (!inputWeight.isBlank()) {
                Double weight = Double.parseDouble(inputWeight);
                if (weight > 0) {
                    groupAdmin.setWeight(weight);
                } else {
                    System.out.println("Вес должен быть больше 0. Команда не выполнена.");
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Вес должен быть вещественным числом. Команда не выполнена.");
            return null;
        }

        //System.out.print("Введите номер паспорта администратора группы (длина не менее 6 символов): ");
        String passportID = args[9];
        if (passportID == null)
            return null;
        if (passportID.length() < 6) {
            System.out.print("Номер паспорта должен содержать не менее 6 символов. Команда не выполнена.");
            return null;
        }

        groupAdmin.setPassportID(passportID);

        Location location = new Location();
        //System.out.print("Введите координату x местоположения администратора группы: ");
        try {
            location.setX(Double.parseDouble(args[10]));
        } catch (NumberFormatException e) {
            System.out.println("Координата x должна быть числом. Команда не выполнена.");
            return null;
        }

        //System.out.print("Введите координату y местоположения администратора группы: ");
        try {
            location.setY(Float.parseFloat(args[11]));
        } catch (NumberFormatException e) {
            System.out.println("Координата y должна быть числом. Команда не выполнена.");
            return null;
        }

        //System.out.print("Введите название местоположения администратора группы (длина не более 283 символов): ");
        String locationName = args[12];
        if (locationName == null)
            return null;
        if (locationName.length() > 283) {
            System.out.println("Длина названия местоположения не может превышать 283 символа. Команда не выполнена.");
            return null;
        }
        location.setName(locationName);
        groupAdmin.setLocation(location);

        studyGroup.setGroupAdmin(groupAdmin);

        return studyGroup;
    }

    public StudyGroup build(Integer id) {
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setId(id);

        while (studyGroup.getName() == null || studyGroup.getName().isBlank()) {
            System.out.print("Введите имя группы: ");
            studyGroup.setName(scanner.nextLine().trim());
            if (studyGroup.getName().isBlank()) {
                System.out.println("Имя группы не может быть пустым.");
            }
        }

        Coordinates coordinates = new Coordinates();
        while (coordinates.getX() == null) {
            System.out.print("Введите координату x (число <= 117): ");
            try {
                Double x = Double.parseDouble(scanner.nextLine().trim());
                if (x > 117) {
                    throw new NumberFormatException();
                }
                coordinates.setX(x);
            } catch (NumberFormatException e) {
                System.out.println("Координата x должна быть числом и быть меньше или равна 117.");
            }
        }

        boolean f = true;
        while (f) {
            System.out.print("Введите координату y (целое число): ");
            try {
                coordinates.setY(Integer.parseInt(scanner.nextLine().trim()));
                f = false;
            } catch (NumberFormatException e) {
                System.out.println("Координата y должна быть целым числом. Повторите ввод еще раз");
            }
        }

        studyGroup.setCoordinates(coordinates);

        Date creationDate = new Date();
        studyGroup.setCreationDate(creationDate);

        while (studyGroup.getStudentsCount() == null) {
            System.out.print("Введите количество студентов (целое число > 0): ");
            try {
                String input = scanner.nextLine().trim();
                int studentsCount = Integer.parseInt(input);
                if (studentsCount > 0) {
                    studyGroup.setStudentsCount(studentsCount);
                } else {
                    System.out.println("Количество студентов должно быть больше 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Количество студентов должно быть целым числом. ");
            }
        }

        while (studyGroup.getFormOfEducation() == null) {
            System.out.println("Выберите форму обучения (FULL_TIME_EDUCATION, DISTANCE_EDUCATION, EVENING_CLASSES): ");
            try {
                studyGroup.setFormOfEducation(FormOfEducation.valueOf(scanner.nextLine().trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Неправильный ввод. Пожалуйста, повторите ввод.");
            }
        }

        f = true;
        while (f) {
            System.out.println("Выберите номер семестра (FIRST, FOURTH, SIXTH, EIGHTH) или нажмите Enter, чтобы пропустить: ");
            String input = scanner.nextLine().trim();
            if (!input.isBlank()) {
                try {
                    studyGroup.setSemesterEnum(Semester.valueOf(input.toUpperCase()));
                    f = false;
                } catch (IllegalArgumentException e) {
                    System.out.println("Неправильный ввод. Пожалуйста, повторите ввод.");
                }
            } else {
                f = false;
            }
        }

        Person groupAdmin = new Person();
        while (groupAdmin.getName() == null || groupAdmin.getName().isBlank()) {
            System.out.print("Введите имя администратора группы (не пустое): ");
            groupAdmin.setName(scanner.nextLine().trim());
            if (groupAdmin.getName().isBlank()) {
                System.out.println("Имя администратора группы не может быть пустым.");
            }
        }

        while (groupAdmin.getHeight() == null) {
            System.out.print("Введите рост администратора группы (целое число, больше 0): ");
            try {
                String inputHeight = scanner.nextLine().trim();
                if (!inputHeight.isBlank()) {
                    Long height = Long.parseLong(inputHeight);
                    if (height > 0) {
                        groupAdmin.setHeight(height);
                    } else {
                        System.out.println("Рост должен быть больше 0.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Рост должен быть целым числом.");
            }
        }

        while (groupAdmin.getWeight() == null) {
            System.out.print("Введите вес администратора группы (вещественное число, больше 0): ");
            try {
                String inputWeight = scanner.nextLine().trim();
                if (!inputWeight.isBlank()) {
                    Double weight = Double.parseDouble(inputWeight);
                    if (weight > 0) {
                        groupAdmin.setWeight(weight);
                    } else {
                        System.out.println("Вес должен быть больше 0.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Вес должен быть вещественным числом.");
            }
        }

        String passportID = "";
        while (passportID.length() < 6) {
            System.out.print("Введите номер паспорта администратора группы (длина не менее 6 символов): ");
            passportID = scanner.nextLine().trim();
            if (passportID.length() < 6) {
                System.out.println("Номер паспорта должен содержать не менее 6 символов.");
            }
        }
        groupAdmin.setPassportID(passportID);

        Location location = new Location();
        while (location.getX() == null) {
            System.out.print("Введите координату x местоположения администратора группы: ");
            try {
                location.setX(Double.parseDouble(scanner.nextLine().trim()));
            } catch (NumberFormatException e) {
                System.out.println("Координата x должна быть числом.");
            }
        }
        while (location.getY() == null) {
            System.out.print("Введите координату y местоположения администратора группы: ");
            try {
                location.setY(Float.parseFloat(scanner.nextLine().trim()));
            } catch (NumberFormatException e) {
                System.out.println("Координата y должна быть числом.");
            }
        }
        System.out.print("Введите название местоположения администратора группы (длина не более 283 символов): ");
        String locationName = scanner.nextLine().trim();
        while (locationName.length() > 283) {
            System.out.println("Длина названия местоположения не может превышать 283 символа.");
            System.out.print("Введите название местоположения администратора группы (длина не более 283 символов): ");
            locationName = scanner.nextLine().trim();
        }
        location.setName(locationName);
        groupAdmin.setLocation(location);

        studyGroup.setGroupAdmin(groupAdmin);

        return studyGroup;
    }
}
