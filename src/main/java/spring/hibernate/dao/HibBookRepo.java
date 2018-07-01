package spring.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.hibernate.entities.HibBook;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class HibBookRepo implements HibBookDAO {

    @Autowired
    private SessionFactory sf;

    @Override
    public List<HibBook> getBooks() {

        Session session = sf.openSession();
        Transaction trx = session.beginTransaction();

        CriteriaQuery<HibBook> query = session.getCriteriaBuilder().createQuery(HibBook.class);
        query.from(HibBook.class);

        List<HibBook> list = session.createQuery(query).getResultList();

        trx.commit();

        return list;

    }

    @Override
    public HibBook getBookByTitle(String title) {

        Session session = sf.openSession();
        Transaction trx = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<HibBook> query = cb.createQuery(HibBook.class);

        Root<HibBook> root = query.from(HibBook.class);

        query = query.select(root).where(cb.equal(root.get("title"), title));

        HibBook book = session.createQuery(query).getSingleResult();

        trx.commit();

        return book;


    }

    @Override
    public HibBook getBookByAuthor(String author) {

        Session session = sf.openSession();
        Transaction trx = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<HibBook> query = cb.createQuery(HibBook.class);

        Root<HibBook> root = query.from(HibBook.class);

        query = query.select(root).where(cb.equal(root.get("author"), author));

        HibBook book = session.createQuery(query).getSingleResult();

        trx.commit();

        return book;
    }

    @Override
    public void addBook(HibBook book) {

        Session session = sf.openSession();

        Transaction trx = session.beginTransaction();

        session.saveOrUpdate(book);

        trx.commit();

    }

    @Override
    public void deleteBook(HibBook book) {

        Session session = sf.openSession();

        Transaction trx = session.beginTransaction();

        session.delete(book);

        trx.commit();

    }
}
