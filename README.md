# 📚 Bookstore Management System 

The Bookstore Management System is a console-based application that simulates a digital bookstore. 🏦 Users can register, manage their wallets 💳, browse book categories 📚, and purchase physical books 📖 or eBooks 🟡. The program integrates with a MySQL database 📊 to handle user data, book details, and transaction records. 

---

## 🔄 Features

- **User Management** 👤
  - Sign up with a username, password, and address.
  - Log in with credentials. 
  - View and add wallet balance. 💸

- **Book Browsing and Purchasing** 📚
  - Browse books by type: Physical 📖 or eBook 🟡.
  - Filter books by category (Programming 🔄, Hobbies ⚒️, Comics/Manga 🎨, etc.).
  - Purchase books, with automatic wallet deduction 💳 and stock updates 📦 for physical books.

- **Receipts** 📏
  - Generate detailed receipts with buyer information and book details. 

- **Database Integration** 📊
  - Persistent storage of user profiles, book records, and transaction data. 
  - Real-time updates to wallet balances and book stock. 📈

---

## 🔬 System Overview

### 🔖 Classes

- **`Book`**: Stores book details like ID, title, type, category, price, and stock. 
- **`User`**: Manages user profiles and wallets. 💳
- **`Database`**: Handles all interactions with the MySQL database. 📊
- **`Display`**: Renders menus 🔀, messages, and receipts. 📝
- **`Main`**: Controls program flow and user interactions. 🔄

### 🔬 Database Schema

#### 🗍 Tables

- **`users`**: Stores user data (username, password, address, wallet balance). 
- **`book`**: General book details (ID, title, type, category). 📚
- **`eBook`**: Price details for digital books. 🟡
- **`PhysicalBook`**: Stock and price details for physical books. 📖

#### 🔧 Operations

- **User Management**:
  - `registerUser`: Adds a new user. 🔑
  - `validateLogin`: Verifies user credentials. 🔐
  - `getWalletBalance` and `addWalletBalance`: Manage wallet balances. 💸
- **Book Management**:
  - `getBooksByType` and `getBooksByCategoryAndType`: Retrieve books based on type and category. 📖
  - `buyBook`: Processes purchases, updating wallet 💳 and stock levels 📦.
  - `getBookDetails`: Fetches details for receipts. 📏

---

## 🚀 Application Workflow

### ⚙️ Startup Menu

1. **Sign up** 🔑: Create a new account. 
2. **Log in** 🔐: Access existing account. 
3. **Exit** ❌: Exit the application. 

### 🔄 Logged-In Menu

1. **Browse Books** 📚
2. **Add Balance** 💳
3. **View Wallet** 💸
4. **Exit** ❌

#### 🔎 Browsing Books

- Choose book type:
  - `P` for Physical Book 📖
  - `E` for eBook 🟡
- Select category:
  - Programming 🔄
  - Hobbies ⚒️
  - Comics/Manga 🎨
  - English Literature 📖
  - Science ⚛️
  - Kids 🎮

#### Example Output:

```
101. Introduction to Java (Price: $25.99, Stock: 5)
102. Learn Python Fast (Price: $19.99, Stock: 0) [Out of Stock]
201. Modern Algorithms (Price: $15.50)
```

Enter the book ID to purchase. 📏

### 💸 Purchasing Books

- **Validation**:
  - Check wallet balance. 💳
  - Verify stock availability (for physical books). 📦
- **Process**:
  - Deduct price from wallet. 💸
  - Reduce stock (physical books only). 📦
- **Receipt**:
  - Display buyer name, book details, and price. 📏

#### Adding Wallet Balance 💳

- Enter the amount to add. 🔄
- Updated balance is displayed. 💸

#### Viewing Wallet Balance 💸

- Displays the current wallet balance. 🔑

#### Exiting

- **From Logged-In Menu**: Log out 🔐 and return to the main menu. 🔄
- **From Main Menu**: Exit the program completely. ❌

---

## 📊 Database Usage

### 🔑 User Data

- Store user information during registration. 
- Verify credentials during login. 🔐
- Manage wallet balances. 💳

### 📖 Book Data

- Fetch books by type and category. 
- Update stock 📦 and price upon purchase. 

### 📏 Purchase Records

- Generate detailed receipts with data fetched from the database. 📏

---

## 🔄 Example Database Entries

### 📖 Books Table:

| ID   | Title                | Type      | Category     | Price   | Stock |
|------|----------------------|-----------|--------------|---------|-------|
| 101  | Introduction to Java | Physical  | Programming  | $25.99  | 5     |
| 102  | Learn Python Fast    | Physical  | Programming  | $19.99  | 0     |
| 201  | Modern Algorithms    | eBook     | Programming  | $15.50  | N/A   |

---

## 🔧 Getting Started

### ⚙️ Prerequisites

- **Java Development Kit (JDK)** 📚
- **MySQL Database** 📊

### 💼 Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/bookstore-management-system.git
   ```
2. Set up the MySQL database using the provided schema. 📈
3. Update database connection settings in the `Database` class. 🛠️
4. Compile and run the program. 🚀

---

## 📧 Contact

For questions or feedback, reach out to [23-05896@g.batstate-u.edu.ph]. 📢

 
