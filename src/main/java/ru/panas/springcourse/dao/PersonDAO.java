package ru.panas.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.SelectionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.panas.springcourse.models.Book;
import ru.panas.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(final int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);

    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();

        MutationQuery mutationQuery = session.createMutationQuery("UPDATE Person SET name=:name, age=:age WHERE person_id=:id");
        mutationQuery.setParameter("name", updatedPerson.getName());
        mutationQuery.setParameter("age", updatedPerson.getAge());
        mutationQuery.setParameter("id", id);

        mutationQuery.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);
        session.remove(person);
    }

    // Для валидации уникальности ФИО

    @Transactional(readOnly = true)
    public Optional<Person> getPersonByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        SelectionQuery<Person> selectionQuery = session.createSelectionQuery("SELECT p FROM Person p WHERE name=:name", Person.class);
        selectionQuery.setParameter("name", name );

        return selectionQuery.uniqueResultOptional();
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByPersonId(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);
        System.out.println(person.getBooks() + " List of books"); // Без этого не подгружает из бд

        return person.getBooks();

    }

}
