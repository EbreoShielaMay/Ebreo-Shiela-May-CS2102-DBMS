import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/Bookstore_db";
    private static final String USER = "root";
    private static final String PASSWORD = "superpass";

    public Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    public boolean registerUser(String username, String password, String address) {
        String query = "INSERT INTO users (username, password, address) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, address);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
            return false;
        }
    }

    public boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            return false;
        }
    }

    public int getWalletBalance(String username) {
        String query = "SELECT wallet_balance FROM users WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("wallet_balance");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching wallet balance: " + e.getMessage());
        }
        return 0;
    }

    public boolean addWalletBalance(String username, int amount) {
        String query = "UPDATE users SET wallet_balance = wallet_balance + ? WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, amount);
            stmt.setString(2, username);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating wallet balance: " + e.getMessage());
            return false;
        }
    }

    public List<Book> getBooksByType(String type) {
        String query = "SELECT * FROM book WHERE type = ?";
        List<Book> books = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("bookID"),
                    rs.getString("title"),
                    rs.getString("type"),
                    rs.getString("category"),
                    0, // Price is handled in specific tables
                    0  // Stock only applies to physical books
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }
        return books;
    }

    public List<Book> getBooksByCategoryAndType(String category, String type) {
        String query = "SELECT b.bookID, b.title, b.type, b.category, " +
                       "COALESCE(e.price, p.price) AS price, p.stock " +
                       "FROM book b " +
                       "LEFT JOIN eBook e ON b.bookID = e.bookID " +
                       "LEFT JOIN PhysicalBook p ON b.bookID = p.bookID " +
                       "WHERE b.category = ? AND b.type = ?";
        List<Book> books = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category); // Match exact category name
            stmt.setString(2, type);     // Match exact type ('Physical' or 'eBook')
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("bookID"),
                    rs.getString("title"),
                    rs.getString("type"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock") // Will be 0 for eBooks
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }
        return books;
    }
    
    
    public boolean buyBook(int bookID, String type, String username) {
        String walletQuery = "SELECT wallet_balance FROM users WHERE username = ?";
        String priceQuery = "SELECT COALESCE(e.price, p.price) AS price, p.stock " +
                            "FROM book b " +
                            "LEFT JOIN eBook e ON b.bookID = e.bookID " +
                            "LEFT JOIN PhysicalBook p ON b.bookID = p.bookID " +
                            "WHERE b.bookID = ?";
        String updateWallet = "UPDATE users SET wallet_balance = wallet_balance - ? WHERE username = ?";
        String updateStock = "UPDATE PhysicalBook SET stock = stock - 1 WHERE bookID = ?";
    
        try (Connection conn = connect();
             PreparedStatement walletStmt = conn.prepareStatement(walletQuery);
             PreparedStatement priceStmt = conn.prepareStatement(priceQuery);
             PreparedStatement updateWalletStmt = conn.prepareStatement(updateWallet);
             PreparedStatement updateStockStmt = conn.prepareStatement(updateStock)) {
            
            conn.setAutoCommit(false); // Start transaction
    
            // Check wallet balance
            walletStmt.setString(1, username);
            ResultSet walletRS = walletStmt.executeQuery();
            if (!walletRS.next()) return false;
            int walletBalance = walletRS.getInt("wallet_balance");
    
            // Check price and stock
            priceStmt.setInt(1, bookID);
            ResultSet priceRS = priceStmt.executeQuery();
            if (!priceRS.next()) return false;
            double price = priceRS.getDouble("price");
            int stock = priceRS.getInt("stock");
    
            if (walletBalance < price) {
                System.out.println("Insufficient balance.");
                return false;
            }
            if (type.equalsIgnoreCase("Physical") && stock <= 0) {
                System.out.println("Out of stock.");
                return false;
            }
    
            // Deduct from wallet
            updateWalletStmt.setDouble(1, price);
            updateWalletStmt.setString(2, username);
            updateWalletStmt.executeUpdate();
    
            // Deduct stock if Physical book
            if (type.equalsIgnoreCase("Physical")) {
                updateStockStmt.setInt(1, bookID);
                updateStockStmt.executeUpdate();
            }
    
            conn.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            System.out.println("Error during purchase: " + e.getMessage());
            return false;
        }
    }
    public int getUserID(String username) {
        String query = "SELECT userID FROM users WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("userID");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user ID: " + e.getMessage());
        }
        return -1; // Return -1 or handle error appropriately
    }

    public int getLastInsertedOrderID() {
        String query = "SELECT MAX(orderID) AS lastID FROM orders";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("lastID");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching last inserted order ID: " + e.getMessage());
        }
        return -1; // Return -1 if no ID found
    }
    
    public Book getBookDetails(int bookID) {
        String query = "SELECT b.bookID, b.title, b.type, b.category, " +
                       "COALESCE(e.price, p.price) AS price, p.stock " +
                       "FROM book b " +
                       "LEFT JOIN eBook e ON b.bookID = e.bookID " +
                       "LEFT JOIN PhysicalBook p ON b.bookID = p.bookID " +
                       "WHERE b.bookID = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                    rs.getInt("bookID"),
                    rs.getString("title"),
                    rs.getString("type"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching book details: " + e.getMessage());
        }
        return null;
    }

    public boolean saveOrder(int userID, int bookID, double price) {
        String query = "INSERT INTO orders (userID, bookID, price) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, bookID);
            stmt.setDouble(3, price);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error saving order: " + e.getMessage());
            return false;
        }
        
    }

    public String getReceipt(int orderID) {
        String query = """
                       SELECT o.orderID, u.username, b.title, 
                              COALESCE(e.price, p.price) AS price, o.purchaseDate 
                       FROM orders o 
                       JOIN users u ON o.userID = u.userID 
                       JOIN book b ON o.bookID = b.bookID 
                       LEFT JOIN eBook e ON b.bookID = e.bookID 
                       LEFT JOIN PhysicalBook p ON b.bookID = p.bookID 
                       WHERE o.orderID = ?""";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return String.format("""
                       Receipt:
                       Order ID: %d
                       Buyer Name: %s
                       Book Title: %s
                       Price: %.2f
                       Purchase Date: %s
                       """,
                       rs.getInt("orderID"),
                       rs.getString("username"),
                       rs.getString("title"),
                       rs.getDouble("price"),
                       rs.getTimestamp("purchaseDate"));
            } else {
                System.out.println("DEBUG: No data found for orderID " + orderID);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching receipt: " + e.getMessage());
        }
        return "Receipt not found.";
    }
    
}
