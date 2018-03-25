import db.EmfProvider;
import org.junit.Assert;
import org.junit.Test;
import pojo.Book;
import pojo.Movie;
import pojo.MovieReview;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Discrim {
  @Test
  public void create()
  {
    Movie m = new Movie();
    m.reviews.add(new MovieReview(m));

    EntityManagerFactory emf = EmfProvider.getEmf();
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(m);
    em.flush();
    em.getTransaction().commit();
    em.close();

    em = emf.createEntityManager();
    Movie m2 = em.find(Movie.class, m.id);
    Assert.assertNotNull(m2);
    Assert.assertTrue(m2.reviews.size() > 0);
    System.out.println("descriptions: " + m2.descriptions.size());
    Assert.assertTrue(m2.descriptions.size() == 0);
    em.close();

    Logger.getLogger("asdf").fine("hello");
  }
}
