package library;

public class Book {
    private final int id;
    private final String title;
    private final String author;
    private boolean available = true;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String toCsv() { return id + "," + title + "," + author + "," + available; }

    public static Book fromCsv(String line) {
        String[] p = line.split(",");
        Book b = new Book(Integer.parseInt(p[0]), p[1], p[2]);
        b.setAvailable(Boolean.parseBoolean(p[3]));
        return b;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s by %s - %s", id, title, author, available ? "Available" : "Issued");
    }
}
