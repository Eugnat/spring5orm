package spring.hibernate;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.hibernate.dao.HibBookRepo;
import spring.hibernate.entities.HibBook;

import java.util.List;

public class SpringMain {

    public static void main(String[] args) {

        try
        {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans_classpath.xml");

            System.out.println("Context created successfully!");

            HibBookRepo bookDAO = context.getBean(HibBookRepo.class);

//            HibBook book = new HibBook();
//            book.setTitle("title two");
//            book.setAuthor("author two");
//
//            bookDAO.addBook(book);

            List<HibBook> list = bookDAO.getBooks();

            for (HibBook book : list)
                System.out.println(book);

            context.close();
        }
        catch(BeansException e)
        {
            System.out.println("ApplicationContext error: " + e.getLocalizedMessage());
        }

    }
}
