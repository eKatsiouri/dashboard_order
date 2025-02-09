# Spring Boot User Orders Project

This project is a simple Spring Boot application that implements a user authentication system with a dashboard displaying order details. The application follows the MVC pattern and uses Hibernate for persistence.

---

## 📌 Project Overview

- **User Authentication**: Users can log in using Spring Security.
- **Dashboard**:
  - For **Admin users**, the dashboard will show all order details.
  - For **Regular users**, the dashboard will show only their own order details.
- **Order Details**: Orders and their associated products are fetched from a repository using Hibernate.
- **Database**: The application uses a MySQL database.

---

## 🛠 Technology Stack

- **Backend**: Spring Boot (17)
- **Database**: MySQL
- **Persistence Layer**: Hibernate (JPA)
- **Authentication**: Spring Security
- **Frontend**: Thymeleaf templates

---

## 🚀 Installation and Setup

### 1️⃣ Clone the repository  
```bash
git clone https://github.com/eKatsiouri/order_products.git
cd order_products
```

2️⃣ Set up the Database
You will need to set up a database for this project.


```bash
-- Create the User table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Create the Order table
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    total_price DOUBLE NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create the Product table
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL,
    stock_quantity INT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Create the OrderProduct table to link orders and products
CREATE TABLE order_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price_at_order_time DOUBLE NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```


3️⃣ Configuration
Make sure your database connection properties are set up correctly in src/main/resources/application.properties.

4️⃣ Run the Application
Run this command in the root directory of your project (where the build.gradle file is located):

```bash
./gradlew build
```
This will compile the code, run the tests, and create a .jar file in the build/libs directory.

Once the build completes successfully, you can run the Spring Boot application using:

```bash
./gradlew bootRun
```
5️⃣ Access the application

Once the application is running, you should be able to access it in your browser.
By default, Spring Boot runs on port 8080 unless otherwise configured.

🛑 Admin User Initialization (on startup)

When the application starts, the system checks if an admin user exists in the database.

If no admin user exists, it will create an admin user with the following hardcoded credentials:

Username: admin

Password: adminpassword

Role: ADMIN


The same process applies for a regular user:

Username: user

Password: userpassword

Role: USER

The admin and user are created automatically by the application using the CommandLineRunner interface.

🔹 Usage

Login Page:

Visit /login to authenticate

You can also navigate to the register page to create your own user and insert data in the database.

Dashboard:

After successful login, users will be redirected to the dashboard. The dashboard will show:

Username of the logged-in user.

Order Details: Orders related to the logged-in user.

Logout button to end the session.


🗂 Dummy Data for Database Setup
```
-- Insert dummy orders
INSERT INTO orders (order_date, status, total_price, user_id) VALUES
(NOW(), 'PENDING', 300.0, 1),
(NOW(), 'PROCESSING', 250.0, 2);

-- Insert dummy order-product relations
INSERT INTO order_products (order_id, product_id, quantity, price_at_order_time) VALUES
(1, 1, 2, 19.99),
(1, 2, 1, 29.99),
(1, 3, 1, 49.99),
(2, 5, 3, 29.99),
(2, 4, 1, 99.99);

-- Insert dummy products
INSERT INTO products (name, description, price, stock_quantity) VALUES
('Product 1', 'Description for product 1', 19.99, 50),
('Product 2', 'Description for product 2', 29.99, 30),
('Product 3', 'Description for product 3', 49.99, 15),
('Product 4', 'Description for product 4', 99.99, 20),
('Product 5', 'Description for product 5', 9.99, 100),
('Product 6', 'Description for product 6', 39.99, 25),
('Product 7', 'Description for product 7', 59.99, 10),
('Product 8', 'Description for product 8', 79.99, 5),
('Product 9', 'Description for product 9', 129.99, 8),
('Product 10', 'Description for product 10', 149.99, 12);
```
📌 Notes:

Ensure your database is running before starting the application.
Modify the database connection settings in application.properties to match your local setup.
After running the application, you can log in with the default admin credentials or register a new user.
👨‍💻 Contributors

Efi Katsiouri
GitHub Profile
