/**
 * A demonstration of students checking out books from a library
 * @author Muaaz Wahid
 * @version 2024-12-12
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Optional;

// Book class representing someone adding a book(s) to the library
class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int quantity;
    // to check if all books have been returned
    private int originalQuantity;

    public Book(int id, String title, String author, String genre, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        originalQuantity = quantity;
    }
    
    // getter methods
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getQuantity() { return quantity; }

    // helper methods to manage quantity of book
    public void increaseQuantity() { quantity++; }
    public void decreaseQuantity() { quantity--; }
    public boolean isAtOriginalQuantity() { return originalQuantity == quantity; }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author +
        ", Genre: " + genre + ", Quantity: " + quantity;
    }
}

// straightforward student class holding all the important attributes of a student
class Student {
    private int id;
    private String name;
    private String email;
    private String department;

    public Student(int id, String name, String email, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public int getId() { return id; }
    public int getEmail() { return email; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name +
        ", Email: " + email + ", Department: " + department;
    }
}

public class LibraryManagementSystem {
    // variables to keep track of books and students in library
    private static List<Book> listOfBooks = new ArrayList<>();
    private static List<Student> listOfStudents = new ArrayList<>();
    // variable to add unique id to each book order
    private static int bookId = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // boolean to know when to end program
        boolean exit = false;
        // main loop
        while (!exit) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Add Student");
            System.out.println("5. View All Students");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // get rid of newline

            // using case -> for a cleaner switch statement
            // this works in Java 14+
            switch (choice) {
                case 1 -> addBook(scanner);
                case 2 -> displayAllBooks();
                case 3 -> searchBook(scanner);
                case 4 -> addStudent(scanner);
                case 5 -> viewAllStudents();
                case 6 -> borrowBook(scanner);
                case 7 -> returnBook(scanner);
                case 8 -> {
                    System.out.println("Goodbye!");
                    exit = true;
                }
                default -> System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        
        // check for duplicate book being added, if so adjust quantity
        for (Book book:listOfBooks) {
            if ( book.getTitle().toLowerCase().equals(title.toLowerCase()) &&
                book.getAuthor().toLowerCase().equals(author.toLowerCase()) &&
                book.getGenre().toLowerCase().equals(genre.toLowerCase()) ) {
                for (int i=0; i < quantity; i++) {
                    book.increaseQuantity();
                }
                System.out.println("Book id " + book.getId() +
                " quantity updated to: " + book.getQuantity());
                return; // don't execute rest of method
            }
        }

        // increment bookId for unique id since this is not a duplicate book
        bookId++;
        listOfBooks.add(new Book(bookId, title, author, genre, quantity));
        System.out.println("Book id " + bookId + " added successfully.");
    }

    private static void displayAllBooks() {
        if (listOfBooks.isEmpty()) {
            System.out.println("No books available.");
        } else {
            // print each book in list
            listOfBooks.forEach(System.out::println);
        }
    }

    private static void searchBook(Scanner scanner) {
        System.out.println("Search by title or author:");
        String query = scanner.nextLine().toLowerCase();
        boolean noMatchFound = true;
        
        // loop through all the books, if the query string is in the title or author
        // print that book
        for (Book book:listOfBooks) {
            if (book.getTitle().toLowerCase().contains(query) ||
                book.getAuthor().toLowerCase().contains(query) ) {
                System.out.println(book);
                noMatchFound = false;
            }
        }
        // no book found
        if (noMatchFound) { System.out.println("No books found matching the query."); }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        // check for matching student id or email
        for (Student student:listOfStudents) {
            if (id == student.getId() ) {
                System.out.println("Can't have matching student id.");
                return;
            } else if (email == student.getEmail()) {
                System.out.println("Can't have matching student email.");
                return;
            }
        }

        // distinct id and email, so we can add normally
        listOfStudents.add(new Student(id, name, email, department));
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        if (listOfStudents.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            // print each student in list
            listOfStudents.forEach(System.out::println);
        }
    }

    private static void borrowBook(Scanner scanner) {
        boolean validStudentId = false;
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        // check if provided id is valid
        for (Student student:listOfStudents) {
            if (studentId == student.getId()) { validStudentId = true; }
        }
        if (!validStudentId) {
            System.out.println("Invalid Student ID.");
            return;
        }

        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        // loop through books, look for id match
        for (Book book:listOfBooks) {
            if (bookId == book.getId()) {
                if (book.getQuantity() <= 0) {
                    System.out.println("Book is out of stock.");
                } else {
                    book.decreaseQuantity();
                    System.out.println("Book borrowed successfully.");
                }
                return;
            }
        }
        // after looping through books, there was no id match
        System.out.println("Book not found.");
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        
        // loop through books check for id match
        for (Book book:listOfBooks) {
            if (bookId == book.getId()) {
                // you cannot return a book that wasn't borrowed from the library
                if (book.isAtOriginalQuantity()) {
                    System.out.println("No more books to return.");
                } else {
                    book.increaseQuantity();
                    System.out.println("Book returned successfully.");
                }
            }
        }
    }
}