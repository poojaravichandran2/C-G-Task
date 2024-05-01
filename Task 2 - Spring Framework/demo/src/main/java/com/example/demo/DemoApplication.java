// Book.java
public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int publicationYear;
    
    // Getters and setters
}

// BookController.java
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

// BookService.java
@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    public Book createBook(Book book) {
        // Implement logic to save book to database or repository
        // Return the created book
    }

    public List<Book> getAllBooks() {
        // Implement logic to retrieve all books from database or repository
        // Return list of books
    }

    public Book getBookById(Long id) {
        // Implement logic to retrieve book by id from database or repository
        // Return the book or null if not found
    }

    public Book updateBook(Long id, Book book) {
        // Implement logic to update book in database or repository
        // Return the updated book or null if not found
    }

    public boolean deleteBook(Long id) {
        // Implement logic to delete book from database or repository
        // Return true if deleted successfully, false otherwise
    }
}
