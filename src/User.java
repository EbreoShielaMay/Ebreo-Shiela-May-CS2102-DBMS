public class User {
    private int userID;
    private String username;
    private String password;
    private String address;
    private int walletBalance;

    // Constructor
    public User(int userID, String username, String password, String address, int walletBalance) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.address = address;
        this.walletBalance = walletBalance;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        this.walletBalance = walletBalance;
    }
}
