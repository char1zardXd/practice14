import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static final String FILEPATH = "diary.txt";

    public static void writeNote() {
        System.out.println("Введіть дату (день.місяць.рік): ");
        String date = scanner.nextLine();

        System.out.println("Додайте новий запис: ");
        String message = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH, true))){
            writer.write("[ " + date + " ] " + message);
            writer.newLine();
            System.out.println("Запис додано");
            System.out.println();
        }catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static void readNotes () {
        File file = new File(FILEPATH);
        if (!file.exists()) {
            System.out.println("Файла нема!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))){
            String line;
            System.out.println("Виводимо записи...");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println();
        }catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static void deleteNote() {
        File file = new File(FILEPATH);
        if (!file.exists()) {
            System.out.println("Файла нема!");
            return;
        }

        System.out.println("Введіть дату для видалення: ");
        String deleteMessage = scanner.nextLine();
        String target = "[ " + deleteMessage + " ]";
        String content = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(target)) {
                    content += line + "\n";
                }
            }
        }catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH))){
            writer.write(content);
            writer.newLine();
            System.out.println("Запис видаленно!");
        }catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Додати запис");
            System.out.println("2. Прочитати записи");
            System.out.println("3. Видалити запис за датою");
            System.out.println("0. Вихід");
            int choice;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch (InputMismatchException e) {
                System.out.println("Помилка: " + e.getMessage());
                scanner.nextLine();
                continue;
            }

            if (choice == 1) {
                writeNote();
            } else if (choice == 2) {
                readNotes();
            } else if (choice == 3) {
                deleteNote();
            } else if (choice == 0) {
                System.out.println("Вихід");
                break;
            }
        }
        System.out.println();
    }
}
