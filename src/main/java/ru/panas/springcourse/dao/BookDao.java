package ru.panas.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.panas.springcourse.models.Book;
import ru.panas.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional(readOnly = true)
    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Book show(final int book_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, book_id);
    }

    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        Session session = sessionFactory.getCurrentSession();

        MutationQuery mutationQuery = session.createMutationQuery("UPDATE Book SET title=:title, author=:author, year_production=:year_production WHERE book_id=:id");
        mutationQuery.setParameter("title", updateBook.getTitle());
        mutationQuery.setParameter("author", updateBook.getAuthor());
        mutationQuery.setParameter("year_production", updateBook.getYear_production());
        mutationQuery.setParameter("id", id);

        mutationQuery.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, id);
        session.remove(book);
    }

    @Transactional(readOnly = true)
    public Optional<Person> getBookOwner(int id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, id);

        return Optional.ofNullable(book.getOwner());
    }

    @Transactional
    public void release(int id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, id);
        book.setOwner(null);

    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, id);
        book.setOwner(selectedPerson);

    }
}
