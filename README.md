# Java Console E-Commerce Engine

A robust, console-driven e-commerce backend developed in Java. This project implements a full retail management lifecycle, including user authentication, transaction processing, and a custom-built persistent storage layer designed to run seamlessly on both Windows and Ubuntu/Linux environments.

## 🚀 Key Features

### 👤 User Management
* **Authentication Flow:** Complete registration and login system with data validation.
* **Security Guardrails:** Implements defensive programming to prevent null inputs and ensure unique account credentials.
* **Profile Persistence:** Automatically saves and loads user profiles across different application sessions.

### 🛒 Order & Transaction Logic
* **Transaction Tracking:** Records customer purchase data, including credit card numbers and unique customer IDs.
* **History Retrieval:** Logic to filter and display order history specific to individual users.
* **Dynamic Scaling:** Internal data structures utilize elastic arrays that resize automatically as the volume of orders increases.

### 💾 Cross-Platform Persistence Layer
* **Sharded File System:** Utilizes a custom flat-file database architecture where data is organized into sharded directories (`users/` and `orders/`) for high-speed access.
* **OS Agnostic:** Engineered with `File.separator` logic to ensure consistent file pathing across Windows and Linux systems.
* **Optimized I/O:** Employs `BufferedReader` and `PrintWriter` pipelines to handle high-efficiency data streams between RAM and Disk.

## 🛠 Technical Architecture

* **Interface-Driven Design:** Follows the "Interface-Implementation" pattern to decouple business logic from core entities.
* **State Synchronization:** Ensures that the in-memory application state and the physical local database are synchronized during the application boot sequence.
* **Clean Data Parsing:** Uses pipe-delimited (`|`) formatting and Lambda-based file filtering to maintain data integrity and prevent system file interference.

## 📂 Project Structure

* `/src`: Source code containing entity models and service implementations.
* `/users`: Sharded database for individual user account records.
* `/orders`: Sharded database for customer transaction logs.

## 🔧 Prerequisites & Usage

1. **Environment:** Java 8 or higher.
2. **Setup:** The system automatically initializes the necessary directory structure on the first run.
3. **Execution:** Run the `Main` class to enter the interactive console interface.

---
*Developed as a Computer Engineering project focused on local data persistence and backend service architecture.*
