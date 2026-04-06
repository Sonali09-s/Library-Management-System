package LibrarayManagemnt.entity.dao;

import LibrarayManagemnt.enums.BookStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Books")
public class Books {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long bookId;

	    @Column(name = "book_name")
	    private String bookName;
	    
	    @Column(name = "book_author")
	    private String bookAuthor;
	    
	    @Column(name = "book_category")
	    private String bookCategory;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "book_Status")
	    private BookStatus bookStatus = BookStatus.AVAILABLE;

		public Long getBookId() {
			return bookId;
		}

		public void setBookId(Long bookId) {
			this.bookId = bookId;
		}

		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		public String getBookAuthor() {
			return bookAuthor;
		}

		public void setBookAuthor(String bookAuthor) {
			this.bookAuthor = bookAuthor;
		}

		public String getBookCategory() {
			return bookCategory;
		}

		public void setBookCategory(String bookCategory) {
			this.bookCategory = bookCategory;
		}

		public BookStatus getBookStatus() {
			return bookStatus;
		}

		public void setBookStatus(BookStatus bookStatus) {
			this.bookStatus = bookStatus;
		}
	    
	    
}
