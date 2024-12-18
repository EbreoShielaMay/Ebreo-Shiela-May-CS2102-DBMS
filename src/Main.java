import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Display display = new Display();
        Database db = new Database();
        Scanner scanner = new Scanner(System.in);
        String currentUser = null;
       try {
            while (true) {
                display.displayMainMenu();
                int choice;

                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    // Handle non-integer input
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
                    scanner.nextLine(); // Clear invalid input
                    continue;
                }

                switch (choice) {
                    case 1: // Sign Up
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        System.out.print("Enter address: ");
                        String address = scanner.nextLine();

                        if (db.registerUser(username, password, address)) {
                            display.displayRS();
                        } else {
                            System.out.println("Registration failed. Username might already exist.");
                        }
                        break;

                    case 2: // Log In
                        System.out.print("Enter username: ");
                        username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();

                        if (db.validateLogin(username, password)) {
                            display.displayLS();
                            currentUser = username;
                            loggedInMenu(scanner, display, db, currentUser); // Navigate to logged-in menu
                        } else {
                            System.out.println("Invalid credentials. Please try again.");
                        }
                        break;

                    case 3: // Exit
                        System.out.println("Exiting... Goodbye!");
                        scanner.close(); // Close the scanner
                        System.exit(0);   // Terminate the program
                        break;

                    default:
                        display.displayError(); // Invalid menu choice
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Optional: Print stack trace for debugging
        } finally {
            if (scanner != null) {
                scanner.close(); // Ensure scanner is closed to avoid resource leaks
            }
        }
    }

    public static void loggedInMenu(Scanner scanner, Display display, Database db, String currentUser) {
        try {
            while (true) {
                display.logMenu();
                int choice;
        
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    // Handle non-integer input for menu choice
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    scanner.nextLine(); // Clear invalid input
                    continue;
                }
        
                switch (choice) {
                    case 1: // See Book Categories
                        display.displayBookCategories();
                        String bookTypeInput = scanner.nextLine().toUpperCase();
        
                        if (bookTypeInput.equals("P") || bookTypeInput.equals("E")) {
                            String type = bookTypeInput.equals("P") ? "Physical" : "eBook";
        
                            // Display categories based on the book type
                            if (type.equals("Physical")) {
                                display.displayPhysicalBookCategory();
                            } else {
                                display.displayEbookCategory();
                            }
        
                            // Map user input to the correct category
                            String category = null;
                            switch (scanner.nextLine()) {
                                case "1":
                                    category = "Programming";
                                    break;
                                case "2":
                                    category = "Hobbies";
                                    break;
                                case "3":
                                    category = "Comics / Manga";
                                    break;
                                case "4":
                                    category = "English Lit";
                                    break;
                                case "6":
                                    category = "Science";
                                    break;
                                case "7":
                                    category = "Kids";
                                    break;
                                default:
                                    System.out.println("Invalid category. Returning to menu.");
                                    continue; // Stay in the menu
                            }
        
                            // Retrieve books from the database
                            List<Book> books = db.getBooksByCategoryAndType(category, type);
        
                            // Debugging outputs to verify the retrieved data
                            if (books.isEmpty()) {
                                System.out.println("No books available in this category.");
                                System.out.println("DEBUG: Type = " + type + ", Category = " + category);
                            } else {
                                System.out.println("Available books:");
                                for (Book book : books) {
                                    System.out.printf("%d. %s (Price: %.2f, Stock: %s)%n",
                                            book.getBookID(), book.getTitle(), book.getPrice(),
                                            (type.equals("Physical") ? book.getStock() : "N/A"));
                                }
        
                                System.out.print("Enter the book ID to purchase or 0 to cancel: ");
                                int bookID;
        
                                try {
                                    bookID = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid book ID.");
                                    scanner.nextLine(); // Clear invalid input
                                    continue;
                                }
        
                                if (db.buyBook(bookID, type, currentUser)) {
                                    int userID = db.getUserID(currentUser); // Add this method in Database
                                    Book purchasedBook = db.getBookDetails(bookID);
                                    db.saveOrder(userID, bookID, purchasedBook.getPrice());
                                    
                                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");    
                                    System.out.println("\nPurchase successful!\n\n");
                                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                                    int orderID = db.getLastInsertedOrderID(); // Add this method in Database
                                    System.out.println(db.getReceipt(orderID));
                                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                                } else {
                                    System.out.println("\nPurchase failed.\n\n");
                                }
                                
                            }
                        } else {
                            System.out.println("Invalid book type. Returning to menu.");
                        }
                        break;
        
                    case 2: // Add Balance
                        System.out.print("Enter amount to add: ");
                        int amount;
        
                        try {
                            amount = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid amount.");
                            scanner.nextLine(); // Clear invalid input
                            continue;
                        }
        
                        if (db.addWalletBalance(currentUser, amount)) {
                            System.out.println("Added " + amount + " to wallet.");
                        } else {
                            System.out.println("Failed to add balance.");
                        }
                        break;
        
                    case 3: // View Wallet
                        int balance = db.getWalletBalance(currentUser);
                        display.displayWallet();
                        System.out.println("Wallet balance: " + balance);
                        break;
        
                    case 4: // Exit
                        System.out.println("Returning to main menu...");
                        return;
        
                    default:
                        display.displayError();
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Optional: Print stack trace for debugging
        }
    }
}
