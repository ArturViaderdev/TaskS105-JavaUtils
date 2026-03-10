# Task S105 - Java Utils

## Description
This project was created for academic purposes as part of the IT Academy Java & Spring specialization.
The goal is to test the Java Utils.

The project is structured in three levels of increasing complexity.

## 🛠 Technologies
- Java

##    Project Structure
````bash
├── pom.xml
├── README.md
└── src
    └── main
        └── java
            ├── level1
            │   ├── ConsoleUI.java
            │   ├── DirectoryExploration.java
            │   ├── Example.java
            │   ├── Main.java
            │   ├── NotDirectoryException.java
            │   └── Program.java
            ├── level2
            │   ├── DirectoryExploration.java
            │   ├── Main.java
            │   ├── NotDirectoryException.java
            │   └── Program.java
            └── level3
                ├── CryptFiles.java
                ├── DirectoryExploration.java
                ├── Main.java
                ├── NotDirectoryException.java
                └── Program.java

````

## 🚀 Instal.lation and Execution
1. Clone the repository:
````bash
git clone https://github.com/ArturViaderdev/TaskS105-JavaUtils
cd TaskS105-JavaUtils
````
You can do cd into the project folder you want to execute:
Examples:
````
cd src/main/java/level1
cd src/main/java/level2
cd src/main/java/level3
````

And run the program:
````
java Main.java
````

If you want you can compile the class files.
````
cd src/main/java/level3
javac -d . -encoding UTF-8 *.java
````

And execute the class file with
```
java level3.Main
```

The level 3 need to be executed by console because it has arguments.

# Task Objectives

In this task, we will work with various Java utilities that are fundamental for software development in real-world environments. You will learn how to manage files and directories, read and write data, configure your code flexibly, and even protect information using basic encryption techniques.

We will work step by step, from simple functionalities to more advanced operations, structured in three levels. Additionally, all activities must be executed from the command line — this will help you better understand how your code runs outside the IDE and prepare you for professional environments where mastering the terminal is key.

During this task, you will:
- Practice using basic Java libraries (`java.io`, `java.util`, `java.nio.file`, etc.).
- Learn how to navigate directories, create files, read and write text, and work with configuration files.
- Understand the process of object serialization and deserialization.
- Discover how to protect data with AES encryption.
- Ensure your code is portable by using relative paths and `File.separator`.
- Get familiar with the manual compilation and execution process of your programs.

Remember to follow the good practices established in **Sprint 0**, and that all work must be written in **English**, following a clear, professional, and submission-ready structure.

---

## Important

All exercises from the three levels must be executable from the **command line**, not only from the IDE.

In your **README**, explain the necessary commands to:
- Compile the Java source files (`.java`) into bytecode (`.class`).
- Execute the compiled files.

Also, make sure to follow these best practices:
- Use **relative paths**, not absolute ones.
- Use `File.separator` to ensure portability and compatibility across different operating systems.

The goal is for your project to be **executable from any environment**.

---

## Level 1

In this first level, we will work with some basic Java utilities for managing files and directories, as well as for reading and writing information to the file system.

You will get familiar with common operations in the development world: navigating directories, filtering and sorting content, saving data to files, and serializing objects.

This set of exercises will allow you to practice:
- Using the `File` class to access and manage files.
- Handling recursive structures like directory trees.
- Managing input/output (I/O) streams.
- Ensuring object persistence through serialization.

By the end of this level, you will have created a small set of tools for working with the file system that you can reuse or extend in future projects.

### Important

Remember: always execute your programs from the **command line**, and document the commands in the `README` of your repository.

### Exercise 1
Create a class that lists alphabetically the contents of a directory received as a parameter.

***Answer***

I developed a class that reads the files and folders inside a directory and displays the result in the console, sorted alphabetically.

### Exercise 2
Enhance the previous class to list a **directory tree** recursively, showing all levels. Each entry should be printed in alphabetical order and indicate whether it is a **Directory (D)** or a **File (F)**, as well as its **last modification date**.

***Answer***

I built a method that reads a folder recursively, displays the results sorted alphabetically, shows the last modified date, and detects which items are files and which are folders.

### Exercise 3
Modify the previous exercise so that, instead of displaying the results on screen, it saves them to a **TXT file**.

***Answer***

I made the program write the results of recursively exploring a directory to a file.

### Exercise 4
Add functionality to read any **TXT file** and display its contents on the console.

***Answer***

I developed a method that displays the contents of a file in the console.

### Exercise 5
The program must now **serialize a Java object to a `.ser` file** and then **deserialize** it.

***Answer***

I serialized and deserialized an object into a binary file.

---

## Level 2

In this level, you will learn how to parameterize your applications’ behavior — a fundamental step to make them more flexible, reusable, and adaptable to different environments.

You will focus on moving configuration data out of the code and into an external file, such as a `.properties` file, which is widely used in Java projects. You will also have the option to explore more advanced libraries like **Apache Commons Configuration**.

Starting from a solved exercise from the previous level, you will modify your program to read the following configuration from a file:
- Which directory to read.
- What the name and location of the resulting TXT file should be.

This practice will help you better understand how to separate configuration from program logic — a key skill in professional environments and scalable application development.

### Exercise 1
Execute **Exercise 3** from the previous level, parameterizing all methods through a configuration file.  
You can use a **Java Properties** file or the **Apache Commons Configuration** library if you prefer.

From the previous exercise, parameterize the following:
- The directory to read.
- The name and directory of the resulting TXT file.

***Answer***

I parameterized the directory exploration using a properties file that specifies the output text file and the directory to explore.

---

## Level 3

In this final level, you will delve into a key concept in computer security: **data encryption**.

You will create a utility that can encrypt and decrypt files, applying one of the most widely used real-world algorithms: **AES (Advanced Encryption Standard)**, in **ECB** or **CBC** mode, with **PKCS5Padding**.

You can use the standard Java libraries (`javax.crypto`) or explore more powerful alternatives like `org.apache.commons.crypto`.

The goal is to understand how to **protect sensitive information** through symmetric cryptography and apply it to real-world cases, such as the files generated in previous exercises. This task prepares you for professional environments where **security and privacy** are essential.

### Exercise 1
Create a utility that **encrypts and decrypts** the files generated in the previous levels.  
Use the **AES algorithm** in **ECB** or **CBC** mode with **PKCS5Padding**.  
You may use either `javax.crypto` or `org.apache.commons.crypto`.

***Answer***

I developed a program that encodes and decodes all the files in a directory using AES encryption.

**Warning!**

Use a test directory. All files will be encrypted, and the originals will be deleted.

The program can process large files by using a buffer.
I store the IV at the beginning of the file to prevent revealing patterns that could help decode it without the key.

The program must be executed from the terminal using command-line parameters.

Navigate to src/main/java/level3 with the cd command.

You can run the program with:

```text
java Main.java
```
You can type:

```text
java Main.java help
```
to view the instructions.

    The first parameter is encode or decode.

    The second parameter is the directory.

    If you use decode, the third parameter is the key.

    When you encode the directory files, the key will be shown in the console. 

    You must save this key to be able to decode the files later.

**Examples of use:**

Encode a directory:

```text
java Main.java encode /home/fedorahp/Documentos/prova/
```
Decode a directory:

```text
java Main.java decode /home/fedorahp/Documentos/prova/ i/L9KGSIo71ofJiE5ZJUE86OeN5Fn+NlUPHVjr3y3ZE=
```

