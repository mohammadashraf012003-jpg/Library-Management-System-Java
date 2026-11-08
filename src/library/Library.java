package library;

import java.io.*;
import java.util.*;

public class Library {
    private final Map<Integer, Book> books = new LinkedHashMap<>();
    private final Map<String, List<Integer>> issuedByMember = new HashMap<>();
    private final File dataFile;
    private static final int MAX_BOOKS_PER_MEMBER = 3;

    public Library(String path) {
        this.dataFile = new File(path);
        load();
    }

    public void addBook(String title, String author) {
        int id = books.isEmpty() ? 1 : Collections.max(books.keySet()) + 1;
        books.put(id, new Book(id, title, author));
        save();
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values())
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) result.add(b);
        return result;
    }

    public Collection<Book> allBooks() { return books.values(); }

    public void issueBook(int id, String member) throws LibraryException {
        Book b = find(id);
        if (!b.isAvailable()) throw new LibraryException("Book already issued: " + b.getTitle());
        List<Integer> list = issuedByMember.computeIfAbsent(member, k -> new ArrayList<>());
        if (list.size() >= MAX_BOOKS_PER_MEMBER)
            throw new LibraryException(member + " already has " + MAX_BOOKS_PER_MEMBER + " books");
        b.setAvailable(false);
        list.add(id);
        save();
    }

    public void returnBook(int id, String member) throws LibraryException {
        Book b = find(id);
        List<Integer> list = issuedByMember.get(member);
        if (list == null || !list.remove(Integer.valueOf(id)))
            throw new LibraryException(member + " has not issued book " + id);
        b.setAvailable(true);
        save();
    }

    private Book find(int id) throws LibraryException {
        Book b = books.get(id);
        if (b == null) throw new LibraryException("No book with id " + id);
        return b;
    }

    private void save() {
        try (PrintWriter w = new PrintWriter(new FileWriter(dataFile))) {
            for (Book b : books.values()) w.println(b.toCsv());
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    private void load() {
        if (!dataFile.exists()) return;
        try (BufferedReader r = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = r.readLine()) != null) {
                if (line.isBlank()) continue;
                Book b = Book.fromCsv(line);
                books.put(b.getId(), b);
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("Could not read data file: " + e.getMessage());
        }
    }
}
