# Library Management System (Core Java)

A console application to manage books: add, list, search, issue and return, with data saved in `books.csv`.

**Concepts practised:** classes & objects, encapsulation, custom exceptions, Collections (`ArrayList`, `HashMap`, `LinkedHashMap`), file I/O (`BufferedReader`, `PrintWriter`), try-with-resources, switch expressions, packages.

## Run
```
javac -d out src/library/*.java
java -cp out library.Main
```

## Ideas to extend (do these yourself and commit each one)
- Add a `Member` class and fine calculation for late returns
- Replace the CSV file with MySQL using JDBC
- Add JUnit tests for `Library`
- Convert to a Maven project / Spring Boot REST API
