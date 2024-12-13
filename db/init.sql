create database Bookstore_db;

use bookstore_db;

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


-- Add books to the `book` table
INSERT INTO book (title, type, category) VALUES 
-- Physical Books: Programming
('Java Programming 101', 'Physical', 'Programming'),
('Python made easy', 'Physical', 'Programming'),
('Learning to be a programmer!', 'Physical', 'Programming'),
('How to code: Guide to programming', 'Physical', 'Programming'),

-- Physical Books: Hobbies
('Art Crazed', 'Physical', 'Hobbies'),
('Handwork guide', 'Physical', 'Hobbies'),
('Homemade meals love', 'Physical', 'Hobbies'),
('Painter\'s Guide', 'Physical', 'Hobbies'),

-- Physical Books: Comics/Manga
('One Punch Man', 'Physical', 'Comics / Manga'),
('Demon Slayer', 'Physical', 'Comics / Manga'),
('Tokyo Ghoul', 'Physical', 'Comics / Manga'),
('Death Note', 'Physical', 'Comics / Manga'),
('Attack on Titan', 'Physical', 'Comics / Manga'),

-- Physical Books: English Lit
('Lord of the Flies', 'Physical', 'English Lit'),
('Pride and Prejudice', 'Physical', 'English Lit'),
('Emma', 'Physical', 'English Lit'),
('To Kill a Mockingbird', 'Physical', 'English Lit'),
('The Woman in White', 'Physical', 'English Lit'),

-- Physical Books: Science
('On the Origin of Species', 'Physical', 'Science'),
('Breathless: The Scientific Race to Defeat a Deadly Virus', 'Physical', 'Science'),
('Astrophysics for People in a Hurry', 'Physical', 'Science'),
('Bad Science', 'Physical', 'Science'),

-- Physical Books: Kids
('Matilda', 'Physical', 'Kids'),
('Diary of a Wimpy Kid', 'Physical', 'Kids'),
('The Giving Tree', 'Physical', 'Kids'),
('Madeline', 'Physical', 'Kids'),

-- eBooks: Programming
('The Pragmatic Programmer', 'eBook', 'Programming'),
('Code: The Hidden Language of Computer Hardware and Software', 'eBook', 'Programming'),
('The Art of Game Design: A Book of Lenses, Third Edition', 'eBook', 'Programming'),
('Hacker\'s Delight', 'eBook', 'Programming'),

-- eBooks: Hobbies
('Stitch \'N Bitch Crochet: The Happy Hooker', 'eBook', 'Hobbies'),
('The Sewing Book', 'eBook', 'Hobbies'),
('Lego Gear Bots', 'eBook', 'Hobbies'),
('Four Season Harvest', 'eBook', 'Hobbies'),
('Drawing on the Right Side of the Brain', 'eBook', 'Hobbies'),

-- eBooks: Comics/Manga
('The Villainess Turns the Hourglass', 'eBook', 'Comics / Manga'),
('Wind Breaker', 'eBook', 'Comics / Manga'),
('The Lady Knight', 'eBook', 'Comics / Manga'),
('Study Group', 'eBook', 'Comics / Manga'),
('Villain\'s Crush', 'eBook', 'Comics / Manga'),

-- eBooks: English Lit
('Harry Potter and the Chamber of Secrets', 'eBook', 'English Lit'),
('Fantastic Beasts', 'eBook', 'English Lit'),
('The Great Gatsby', 'eBook', 'English Lit'),
('Miss Peregrine\'s House for Peculiars', 'eBook', 'English Lit'),

-- eBooks: Science
('Science Encyclopedia Vol.1', 'eBook', 'Science'),
('Cosmos', 'eBook', 'Science'),
('The Elegant Universe', 'eBook', 'Science'),
('Six Easy Pieces', 'eBook', 'Science'),

-- eBooks: Kids
('Cinderella', 'eBook', 'Kids'),
('Pinocchio', 'eBook', 'Kids'),
('Princess and the Frog', 'eBook', 'Kids'),
('The Giving Tree', 'eBook', 'Kids');


-- Add eBook data to `eBook` table
INSERT INTO eBook (bookID, price) VALUES 
(27, 480.00),
(28, 234.00),
(29, 333.00),
(30, 199.00),
(31, 212.00),
(32, 320.00),
(33, 146.00),
(34, 330.00),
(35, 376.00),
(36, 176.00),
(37, 234.00),
(38, 188.00),
(39, 269.00),
(40, 299.00),
(41, 599.00),
(42, 389.00),
(43, 237.00),
(44, 345.00),
(45, 200.00),
(46, 255.00),
(47, 210.00),
(48, 199.00),
(49, 125.00),
(50, 125.00),
(51, 125.00),
(52, 150.00);

-- Add physical book data to `PhysicalBook` table
INSERT INTO PhysicalBook (bookID, price, stock) VALUES 
(1, 250.00, 10),
(2, 300.00, 20),
(3, 300.00, 24),
(4, 400.00, 20),
(5, 200.00, 19),
(6, 178.00, 15),
(7, 109.00, 21),
(8, 399.00, 20),
(9, 439.00, 34),
(10, 499.00, 25),
(11, 489.00, 28),
(12, 469.00, 16),
(13, 487.00, 33),
(14, 499.00, 28),
(15, 359.00, 12),
(16, 238.00, 10),
(17, 389.00, 27),
(18, 369.00, 25),
(19, 479.00, 30),
(20, 365.00, 12),
(21, 400.00, 18),
(22, 125.00, 22),
(23, 280.00, 30),
(24, 498.00, 50),
(25, 289.00, 34),
(26, 230.05, 30);


