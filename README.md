# Academic Portal Development Project


## Project Overview - JSSI Education

JSSI Education is an academic management system designed to optimize the administration of users, subjects, schedules, grades, attendance, and other related aspects in an educational institution. The primary goal is to centralize and automate processes, enhancing the overall effectiveness of academic management. This system is being developed for JSSI Education and aims to provide key functionalities for different user roles, including administrators, teachers, students, and staff.



# Table of Contents

- [Academic Portal Development Project](#academic-portal-development-project)
  - [Project Overview - JSSI Education](#project-overview---jssi-education)
- [Table of Contents](#table-of-contents)
  - [Project Overview](#project-overview)
  - [Main Features Include:](#main-features-include)
  - [Visuals](#visuals)
  - [Technologies used:](#technologies-used)
  - [Technologies' versions](#technologies-versions)
  - [Key Requirements:](#key-requirements)
  - [Installation](#installation)
    - [Prerequisites](#prerequisites)
    - [Steps to Install](#steps-to-install)
      - [1. **Create a Folder on Your Computer**](#1-create-a-folder-on-your-computer)
      - [2. **Navigate to the Folder and Open Git Bash**](#2-navigate-to-the-folder-and-open-git-bash)
      - [3. **Clone the Repository**](#3-clone-the-repository)
      - [4. **Connection DB MySQL**](#4-connection-db-mysql)
        - [Step 1: Open MySQL and Select the Root User Connection](#step-1-open-mysql-and-select-the-root-user-connection)
        - [Step 2: Create the Database Schema](#step-2-create-the-database-schema)
        - [Step 3: Create a New User and Grant Privileges](#step-3-create-a-new-user-and-grant-privileges)
        - [Step 4: Load an SQL Script File](#step-4-load-an-sql-script-file)
      - [6. Install Lombok](#6-install-lombok)
      - [7. Run the Application](#7-run-the-application)
      - [8. Access the Application](#8-access-the-application)
      - [9. Users to access the application](#9-users-to-access-the-application)
  - [Useful Resources](#useful-resources)
  - [Authors and Acknowledgments](#authors-and-acknowledgments)




## Project Overview

## Main Features Include:
- ### **User management** 
   - Creation, modification, and deletion of users.
- ### **Role management**.
  - Assigning and managing permissions for different user roles such as Administrator, Teacher, Student, and Manager.
- ### **Subject and schedule management**.
  - Efficiently managing subjects and schedules.
- ### **Grade and tracking**. 
  - Tracking students' grades and attendance.

## Visuals

![Giphy](https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExa2M3dGVkaHJmZmN1dGt1bzhld290MXlpbmt1dmplaDJvMWg5bHE1NiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/BlroduCjidyGDjQid5/giphy.gif)

![Giphy](https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExdXl5ZWwwcHYzd2Nya3Vzcjh3ZXR5aWkzZ2ljd3h5Y3F0cnZmaDEydSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/rSsDalw2z1QFLxM0LG/giphy.gif)


## Technologies used:
- ### **Backend**:
  - Java (Spring Framework)
- ### **Frontend**: 
  - HTML, CSS, JavaScript
- ### **Database**.
  - [MySQL WorkBench](https://dev.mysql.com/downloads/workbench/), [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)
- ### **IDE**:  
  - [IntelliJ IDEA Community Edition](https://www.jetbrains.com/es-es/idea/download/?section=windows)
- ### **Code Editor**: 
  - [Visual Studio Code](https://code.visualstudio.com/download)

## Technologies' versions

- ### **Java JDK 21**: 
  - The programming language and environment for building the application.
- ### **Spring Boot 3.3.3**: 
  - The framework used for creating the backend services.
- ### **Maven**: 
  - The build automation tool used for managing dependencies and building the project.
- ### **HTML5**: 
  - The markup language for structuring the web pages.
- ### **CSS3**: 
  - The stylesheet language for designing and styling the web pages.
- ### **JavaScript**: 
  - The programming language used for client-side scripting.


## Key Requirements:
- ### **Security**.
  - The system ensures encrypted passwords and access control to protect sensitive data.
- ### **Usability**.
  - It offers an intuitive and easy-to-navigate interface.


## Installation

To set up the project locally, follow these steps:

### Prerequisites

Before you begin, ensure you have the following installed on your machine:

- **Java JDK 21**
- **Maven**
- **IntelliJ IDEA** (or any preferred IDE)
- **MySQL Workbench and MySQL Server**

### Steps to Install
 

#### 1. **Create a Folder on Your Computer**

   - On your desktop or any preferred location, create a new folder for the project. For example, you can name it `JSSI-Education`.

#### 2. **Navigate to the Folder and Open Git Bash**

   - Go to the folder you just created.
   - Inside the folder, right-click and select **Git Bash Here** to open the terminal in that location.


#### 3. **Clone the Repository**

   In the terminal opened before, run the following command to clone the repository:

   ```bash
   git clone https://github.com/11JuanK11/JSSI-Education.git
   ```

#### 4. **Connection DB MySQL**

##### Step 1: Open MySQL and Select the Root User Connection

1. ###### **Open the MySQL application**:
   - If you are using MySQL Workbench, launch it from your applications or start menu.  
   - If you don't have MySQL Workbench, follow this guide before starting.
      - [MySQL guide](https://www.youtube.com/watch?v=EmQZt6o6-78)

2. ###### **Select the root user connection**:
   - Once MySQL Workbench is open, you’ll see a list of saved connections. Click on the connection for the `root` user (usually labeled as `root@localhost` or similar).
   - If you haven't created a root connection, click the `+` icon to create a new connection, then set the username to `root` and the hostname to `localhost`.

3. ###### **Enter the root password** when prompted.

---

##### Step 2: Create the Database Schema

1. ###### **Run the following command** to create the schema named `jssi_education` with UTF-8 character encoding:
   ```sql
   CREATE SCHEMA IF NOT EXISTS jssi_education DEFAULT CHARACTER SET utf8;
   ```

   - This will create the schema if it doesn’t already exist.

---

##### Step 3: Create a New User and Grant Privileges

1. ###### **Create the user** `administrator` with the password `12345@` using the following command:
   ```sql
   CREATE USER 'administrator'@'localhost' IDENTIFIED BY '12345@';
   ```

2. ###### **Grant all privileges** to the user on the schema `jssi_education`:
   ```sql
   GRANT ALL PRIVILEGES ON jssi_education.* TO 'administrator'@'localhost';
   ```

3. ###### **Apply the changes** by flushing privileges:
   ```sql
   FLUSH PRIVILEGES;
   ```

---

##### Step 4: Load an SQL Script File

1. ###### **Run the application**
    - Start the application to ensure it creates the necessary tables in the database. To run your application, navigate to the  ``JssiEducationApplication``. Once there, click on the green arrow to start the application.

2. ###### **Go to the "File" menu** in the top-left corner of the MySQL Workbench interface.

3. ###### **Click on "Open SQL Script"**.

4. ###### **Navigate to your project directory**:
   - Look for the folder named `db` inside your project.

5. ###### **Select the file** named `script.sql`.

6. ###### **Run the script**:
   - After selecting the file, it will open in the query editor. Click on the "Run" or "Execute" button (usually represented by a lightning bolt icon) to execute the SQL script.

--- 

#### 6. Install Lombok
Step-by-step Guide to Install the Lombok Plugin in IntelliJ IDEA

2. **Access Settings**  
   - Click on `File` from the menu bar.
   - Select `Settings` from the dropdown menu.

3. **Navigate to the Plugins Section**  
   - In the left-hand sidebar of the settings window, scroll down and find the `Plugins` option.
   - Click on `Plugins`.

4. **Search for the Lombok Plugin**  
   - In the `Plugins` window, you'll see a search bar at the top.
   - Type **Lombok** in the search bar.

5. **Select the Lombok Plugin**  
   - From the search results, select the **Lombok** plugin.

6. **Install the Plugin**  
   - Click the **Install** button that appears next to the plugin name.

8. **Verify Installation**  
   - Once IntelliJ IDEA has restarted, the Lombok plugin will be installed and ready to use.

---

#### 7. Run the Application
- If you still have the application running from the previous steps, restart it to ensure the latest changes take effect. **Remember** to run your application, navigate to the  ``JssiEducationApplication`` class in your project. Once there, click on the green arrow next to the class name to start the application.


#### 8. Access the Application
Open your web browser and navigate to: **http://localhost:8080** 


#### 9. Users to access the application

Below are the users that can be used to log into the application:

1. **Administrator**:  
   - **Username**: `johndoe`  
   - **Password**: `password123`

2. **Teacher**:  
   - **Username**: `davidmartinez`  
   - **Password**: `password111`

3. **User**:  
   - **Username**: `alicejohnson`  
   - **Password**: `password789`


## Useful Resources

A collection of helpful tools and platforms that we have used throughout the development of the project:

- **[Canva](https://www.canva.com/)**: A design tool used for creating presentations, logos, and other graphic assets used in the project.
- **[PlantUML](https://plantuml.com/)**: A tool for generating UML diagrams using simple text descriptions. Used for class diagrams, component diagrams, and other system designs.
- **[Stack Overflow](https://stackoverflow.com/)**: A community-driven platform where developers ask questions and share solutions. It has been instrumental for solving various technical challenges during development.
- **[GitHub](https://github.com/)**: The platform where the project's version control is hosted. It allows collaboration among team members through features like pull requests, branches, and issues.
- **[MDN Web Docs](https://developer.mozilla.org/en-US/)**: A valuable resource for frontend development, providing documentation and examples for HTML, CSS, and JavaScript.
- **[Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)**: Official Spring Boot documentation, used as a reference for configuring and understanding Spring Boot features.
- **[Maven Central Repository](https://mvnrepository.com/)**: A repository for Maven dependencies, used to search for and integrate libraries into the project.
- **[MySQL Documentation](https://dev.mysql.com/doc/)**: Official documentation for the MySQL database, used for managing and configuring the database system.
- **[Postman](https://www.postman.com/)**: A collaboration platform for API development. It helps test, monitor, and document APIs during backend development.
- **[Visual Paradigm Online](https://online.visual-paradigm.com/)**: A UML and ERD diagram tool for visualizing the system's architecture and database design.
- **[JetBrains Academy](https://www.jetbrains.com/academy/)**: A learning platform by JetBrains that offers comprehensive tutorials and projects for mastering development skills, including Java and Spring Boot.
- **[W3Schools](https://www.w3schools.com/)**: A web development learning platform with examples and documentation on web technologies like HTML, CSS, JavaScript, and more.



## Authors and Acknowledgments

This project was developed by:

- [**Juan Camilo Alzate Bedoya**](https://github.com/11JuanK11) - Developer
- [**Sara Castañeda Echeverri**](https://github.com/Saraccee25) - Developer
- [**Sara Carolina Sánchez Arroyave**](https://github.com/Caro-26S) - Developer
- [**Isabela Montoya Alarcón**](https://github.com/IsaMontoya17) - Developer

We appreciate all the contributors who made this project possible. Their effort and dedication have been fundamental to its development.
