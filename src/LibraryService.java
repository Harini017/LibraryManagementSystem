import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    // Add a book
    public void addBook(String title, String author, int year) {
        String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, year);
            stmt.executeUpdate();
            System.out.println("✅ Book added: " + title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Add User
    public void addUser(String name) {
        String sql = "INSERT INTO users (name) VALUES (?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            System.out.println("✅ User added: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Users
    public void viewUsers() {
        String sql = "SELECT * FROM users";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("=== Users ===");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrowBook(int userId, int bookId, Date borrowDate, Date dueDate) {
    String sql = "INSERT INTO borrowed (user_id, book_id, borrow_date, due_date) VALUES (?, ?, ?, ?)";
    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, userId);
        stmt.setInt(2, bookId);
        stmt.setDate(3, borrowDate);
        stmt.setDate(4, dueDate);
        stmt.executeUpdate();
        System.out.println("✅ Book borrowed successfully!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void returnBook(int borrowId, Date returnDate) {
    String sql = "UPDATE borrowed SET return_date = ? WHERE id = ?";
    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setDate(1, returnDate);
        stmt.setInt(2, borrowId);
        stmt.executeUpdate();
        System.out.println(" Book returned successfully!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
