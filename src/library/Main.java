package library;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library("books.csv");
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add book  2.List books  3.Search  4.Issue  5.Return  0.Exit");
            System.out.print("Choice: ");
            if (!in.hasNextLine()) return;
            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Title: "); String t = in.nextLine();
                        System.out.print("Author: "); String a = in.nextLine();
                        lib.addBook(t, a);
                        System.out.println("Book added.");
                    }
                    case "2" -> lib.allBooks().forEach(System.out::println);
                    case "3" -> {
                        System.out.print("Keyword: ");
                        List<Book> r = lib.searchByTitle(in.nextLine());
                        if (r.isEmpty()) System.out.println("No match."); else r.forEach(System.out::println);
                    }
                    case "4" -> {
                        System.out.print("Book id: "); int id = Integer.parseInt(in.nextLine().trim());
                        System.out.print("Member name: ");
                        lib.issueBook(id, in.nextLine().trim());
                        System.out.println("Book issued.");
                    }
                    case "5" -> {
                        System.out.print("Book id: "); int id = Integer.parseInt(in.nextLine().trim());
                        System.out.print("Member name: ");
                        lib.returnBook(id, in.nextLine().trim());
                        System.out.println("Book returned.");
                    }
                    case "0" -> { System.out.println("Goodbye!"); return; }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (LibraryException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
