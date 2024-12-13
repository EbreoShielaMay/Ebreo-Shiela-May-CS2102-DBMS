# 📚 D'Bookstore

Welcome to **D'Bookstore**! This is a Java-based console application designed for book lovers. It allows users to browse, purchase books, and manage their wallet balance with the power of MySQL database integration.

---

## 🎯 Features
- **🧑‍💻 User Registration/Login**: Securely register and log in.
- **📖 Browse Books**: Explore books by category and type (Physical or eBook).
- **💰 Wallet Management**: Add balance to your wallet.
- **🛒 Purchase Books**: Buy eBooks or physical books and generate receipts.

---

## 🛠️ Technologies Used
- **☕ Java** (Object-Oriented Programming)
- **🐬 MySQL** (Database Management System)
- **📡 JDBC** (Database Connectivity)

---

## 🧩 How It Works
### Workflow:
1. **🏠 Main Menu**: Sign up, log in, or exit.
2. **🔐 Logged-in Menu**:
   - Browse books by type and category.
   - Add balance or check wallet.
3. **📦 Purchases**:
   - Deduct wallet balance.
   - Update book stock (for physical books).
   - Save orders and generate receipts.

📷 Check out how it works: 

![System Flow Diagram](https://via.placeholder.com/600x400.png?text=System+Flow+Diagram)

---

## 🚀 Getting Started
1. **📥 Clone the Repository**:
   ```bash
   git clone https://github.com/EbreoShielaMay/Ebreo-Shiela-May-CS2102-DBMS.git
   ```
2. **🔧 Set Up MySQL**:
   - Import the database schema:
     ```sql
         CREATE TABLE book (
            bookID INT AUTO_INCREMENT PRIMARY KEY,
            title VARCHAR(255) NOT NULL,
            type ENUM('Physical', 'eBook') NOT NULL,
            category VARCHAR(20) NOT NULL
         );

         CREATE TABLE eBook (
            bookID INT PRIMARY KEY,
            price DECIMAL(10, 2) NOT NULL,
            FOREIGN KEY (bookID) REFERENCES book(bookID)
         );

         CREATE TABLE PhysicalBook (
            bookID INT PRIMARY KEY,
            price DECIMAL(10, 2) NOT NULL,
            stock INT NOT NULL,
            FOREIGN KEY (bookID) REFERENCES book(bookID)
         );

         CREATE TABLE users (
            userID INT AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(50) NOT NULL UNIQUE,
            password VARCHAR(50) NOT NULL,
            address VARCHAR(100),
            wallet_balance DECIMAL(10, 2) DEFAULT 0
         );

         CREATE TABLE orders (
            orderID INT AUTO_INCREMENT PRIMARY KEY,
            userID INT,
            bookID INT,
            purchaseDate DATETIME DEFAULT CURRENT_TIMESTAMP,
            price DOUBLE,
            FOREIGN KEY (userID) REFERENCES users(userID),
            FOREIGN KEY (bookID) REFERENCES book(bookID)
         );

     ```
3. **▶️ Run the Program**:
   - Compile and run `Main.java` using your favorite IDE or terminal.

---

## 📝 Schema Overview
- **🗂️ Users**: Manage login and wallet balances.
- **📚 Books**: Store book details.
- **📜 Orders**: Record purchases.

---

## 📌 Key Commands
| Action              | Command         |
|---------------------|-----------------|
| **▶️ Run Program**     | `java Main`     |
| **❌ Exit Program**    | Select `3` from main menu |


---

Made with ❤️ for book enthusiasts.

