import db.EmfProvider;
import org.junit.Assert;
import org.junit.Test;
import pojo.Author;
import pojo.Book;
import pojo.Movie;
import pojo.MovieReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ReferenceByIdTest {

  @Test
  public void normalAuthor() {
    Book b = new Book();
    b.title = "The Obscene Bird of Night"; //"Plugawy ptak nocy";
    Author a = new Author();
    a.name = "Jose Donoso";

    EntityManagerFactory emf = EmfProvider.getEmf();
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(b);
    em.flush();
    em.getTransaction().commit();

    int bookId = b.id;
    System.out.println("book id: " + b.id);

    em.clear();
    Book b2 = em.find(Book.class, b.id);
    System.out.println("book title: " + b2.title);
    Assert.assertEquals(b.title, b2.title);
    em.close();
  }

  @Test
  public void fixingAuthor() {
    Book b = new Book();
    b.title = "Les Misérables";
    Author wrongAuthor = new Author();
    wrongAuthor.name = "Edgar Allan Poe";
    b.author = wrongAuthor;
    Author correctAuthor = new Author();
    correctAuthor.name = "Victor Hugo";

    EntityManagerFactory emf = EmfProvider.getEmf();
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(b);
    em.persist(wrongAuthor);
    em.persist(correctAuthor);
    em.flush();
    em.getTransaction().commit();

    int bookId = b.id;
    System.out.println("book id: " + b.id);
    System.out.println("wrong author id: " + wrongAuthor.id);
    System.out.println("correct author id: " + correctAuthor.id);

    em.clear();
    Book b2 = em.find(Book.class, b.id);
    System.out.println("book title: " + b2.title);
    System.out.println("author id: " + b2.authorId);
    Assert.assertEquals(wrongAuthor.id, b2.authorId);

    // Fix author id.
    em.clear();
    Book b3 = em.find(Book.class, b.id);
    b3.author = new Author();
    b3.author.id = correctAuthor.id;
    em.getTransaction().begin();
    em.merge(b3);
    em.flush();
    em.getTransaction().commit();

    em.clear();
    Book b4 = em.find(Book.class, b.id);
    Assert.assertEquals(correctAuthor.id, b4.authorId);
    System.out.println("correct author: " + b4.author.name);

    em.close();


  }

}
