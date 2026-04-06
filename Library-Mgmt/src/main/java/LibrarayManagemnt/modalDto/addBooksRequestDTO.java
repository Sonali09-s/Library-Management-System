package LibrarayManagemnt.modalDto;

import org.springframework.web.multipart.MultipartFile;

import LibrarayManagemnt.enums.BookStatus;

public class addBooksRequestDTO {
	
	private Long id;

    private String bookName;

    private String bookAuthor;

    private String bookCategory;

    private BookStatus bookStatus;

    // Constructors
    public addBooksRequestDTO() {}

    public addBooksRequestDTO(Long id, String bookName, String bookAuthor, String bookCategory, BookStatus bookStatus) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookStatus = bookStatus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String name) {
        this.bookName = name;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String author) {
        this.bookAuthor = author;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String category) {
        this.bookCategory = category;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus status) {
        this.bookStatus = status;
    }

}