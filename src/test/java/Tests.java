import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Properties;
import java.util.logging.Logger;
import org.junit.Test;

import db.EmfProvider;
import pojo.*;

public class Tests
{
  Logger logger;

  public Tests()
  {
    logger = Logger.getLogger("bs");
  }

  @Test
  public void test()
  {
    logger.info("This is just a simple test...");
    EntityManagerFactory emf = EmfProvider.getEmf();
    EntityManager em = emf.createEntityManager();
    Book b = new Book();
    em.getTransaction().begin();
    em.persist(b);
    em.flush();
    em.getTransaction().commit();
    em.close();
  }
}