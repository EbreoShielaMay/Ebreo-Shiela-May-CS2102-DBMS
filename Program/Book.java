public class Book {
    private int bookID;
    private String title;
    private String type;
    private String category;
    private double price;
    private int stock; // Only for physical books

    // Constructor
    public Book(int bookID, String title, String type, String category, double price, int stock) {
        this.bookID = bookID;
        this.title = title;
        this.type = type;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Getters and Setters
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
