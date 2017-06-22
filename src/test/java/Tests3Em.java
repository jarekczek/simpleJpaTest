import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Properties;
import java.util.logging.Logger;
import org.junit.Test;

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
    Properties props = new Properties();
    props.setProperty("javax.persistence.jdbc.url",
      "jdbc:h2:mem:app2;TRACE_LEVEL_SYSTEM_OUT=1");
    props.setProperty("javax.persistence.schema-generation.database.action",
      "drop-and-create");
    EntityManagerFactory emf =
      Persistence.createEntityManagerFactory(null, props);
    EntityManager em = emf.createEntityManager();
    Book b = new Book();
    em.getTransaction().begin();
    em.persist(b);
    em.flush();
    em.getTransaction().commit();
    em.close();
  }
}