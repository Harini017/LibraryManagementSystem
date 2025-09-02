import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryService service = new LibraryService();

        while (true) {
            System.out.println("\n=== Library Management ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add User");
            System.out.println("4. View Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // clear buffer
                    service.addBook(title, author, year);
                }
                case 2 -> {
                    List<Book> books = service.getAllBooks();
                    System.out.println("=== Books ===");
                    for (Book b : books) {
                        System.out.println(b.getId() + " | " + b.getTitle() + " | " + b.getAuthor() + " | " + b.getYear());
                    }
                }
                case 3 -> {
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    service.addUser(name);
                }
                case 4 -> service.viewUsers();
                case 5 -> {
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // clear buffer
                    System.out.print("Enter borrow date (YYYY-MM-DD): ");
                    Date borrowDate = Date.valueOf(scanner.nextLine());
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    Date dueDate = Date.valueOf(scanner.nextLine());
                    service.borrowBook(userId, bookId, borrowDate, dueDate);
                }
                case 6 -> {
                    System.out.print("Enter borrow record ID: ");
                    int borrowId = scanner.nextInt();
                    scanner.nextLine(); // clear buffer
                    System.out.print("Enter return date (YYYY-MM-DD): ");
                    Date returnDate = Date.valueOf(scanner.nextLine());
                    service.returnBook(borrowId, returnDate);
                }
                case 7 -> {
                    System.out.println("👋 Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }
}
