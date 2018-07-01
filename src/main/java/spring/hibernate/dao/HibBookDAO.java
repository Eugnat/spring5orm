package spring.hibernate.dao;

import spring.hibernate.entities.HibBook;

import java.util.List;

public interface HibBookDAO {

    List<HibBook> getBooks();

    HibBook getBookByTitle(String title);

    HibBook getBookByAuthor(String author);

    void addBook(HibBook book);

    void deleteBook(HibBook book);
}
