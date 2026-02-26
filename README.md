# Task S105 - Java Utils

## Level 1

### Exercise 1

I developed a class that reads the files and folders inside a directory and show the result by console ordered alphabetically.

### Exercise 2

I build a method that reads a folder recursively, shows the results ordered alphabetically, shows the last modified date and detects what is a file and what is a folder.

### Exercise 3

I made that the results of exploring a directory recursively are written to a file.

### Exercise 4

I developed a method that shows the contents of a file by console.

### Exercise 5

I serialized and deserialized an object in a binary file.

## Level 2

### Exercise 1

I parametrized the directory exploration with a properties file that indicates the output txt and the directory to explore.

## Level 3

I developed a program that encodes and decodes all the files of a directory with aes.

Warning! 

Put a test directory. All the files will be encrypted.

The program can encode large files. It uses a buffer.
I used iv at the start of the file for avoid revealing patterns to decode without the key.

The program has to be used from the terminal with the use of console parameters.

You have to go to src/main/java/Level3 with cd command.
You can execute the program with java Main.java.
You can put java Main.java help for the instructions.

The first parameter is encode or decode.
The second parameter is the directory.
If you put decode the third parameter is the key.
When you encode directory files the key will appear on console. You have to save this key to be able to decode the files.

Examples of use:

Encode a directory
```console
java Main.java encode /home/fedorahp/Documentos/prova/
```

Decode a directory
````
java Main.java decode /home/fedorahp/Documentos/prova/ i/L9KGSIo71ofJiE5ZJUE86OeN5Fn+NlUPHVjr3y3ZE=
```` 
If you want you can compile the class files.
````
javac -d . -encoding UTF-8 *.java
````

