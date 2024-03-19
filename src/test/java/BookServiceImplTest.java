import com.books.dto.BookDto;
import com.books.entity.Book;
import com.books.exception.BookCreateException;
import com.books.mapper.BookMapper;
import com.books.repository.BookRepository;
import com.books.service.impl.BookServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookServiceImpl bookService;

    private final Book book = Book.builder().author("Test author").title("Test title").user_id(1).build();
    private final BookDto bookDto = BookDto.builder().author("Test author").title("Test title").user_id(1).build();

    @Test
    @DisplayName("Create book")
    public void createBook() {
        when(bookService.create(bookDto)).thenReturn(book);
        assertEquals(book, bookService.create(bookDto));
    }

    @Test
    @DisplayName("BookCreateException author is empty")
    public void createBook_ShouldBeException_author_empty() {
        BookDto bookDto = BookDto.builder().author("").title("Test title").user_id(1).build();
        assertThrows(BookCreateException.class, () -> bookService.create(bookDto));
    }

    @Test
    @DisplayName("BookCreateException author is null")
    public void createBook_ShouldBeException_author_null() {
        BookDto bookDto = BookDto.builder().author(null).title("Test title").user_id(1).build();
        assertThrows(BookCreateException.class, () -> bookService.create(bookDto));
    }

    @Test
    @DisplayName("BookCreateException title is empty")
    public void createBook_ShouldBeException_title_empty() {
        BookDto bookDto = BookDto.builder().author("Test author").title("").user_id(1).build();
        assertThrows(BookCreateException.class, () -> bookService.create(bookDto));
    }

    @Test
    @DisplayName("BookCreateException title is null")
    public void createBook_ShouldBeException_title_null() {
        BookDto bookDto = BookDto.builder().author("Test author").title(null).user_id(1).build();
        assertThrows(BookCreateException.class, () -> bookService.create(bookDto));
    }

    @Test
    @DisplayName("Get book by id")
    public void getBookById() {
        when(bookRepository.getById(1)).thenReturn(book);
        Book responseBook = bookService.getById(1);
        assertEquals(book, responseBook);
    }

    @Test
    @DisplayName("Get all books")
    public void getAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book);

        when(bookRepository.getAll()).thenReturn(books);
        List<Book> responseBooks = bookService.getAll();

        assertEquals(books, responseBooks);
        assertEquals(books.size(), responseBooks.size());
    }

    @Test
    @DisplayName("Update book")
    public void updateBook() {
        when(bookRepository.update(book)).thenReturn(book);
        bookService.update(BookMapper.toBookDto(book));

        verify(bookRepository, times(1)).update(book);
    }

    @Test
    @DisplayName("Delete book")
    public void deleteBook() {
        bookService.delete(1);
        verify(bookRepository, times(1)).delete(1);
    }
}
