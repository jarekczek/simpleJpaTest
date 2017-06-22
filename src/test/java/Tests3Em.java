import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Properties;
import java.util.logging.Logger;
import org.junit.Test;

import db.EmfProvider;
import pojo.*;

public class Tests3Em
{
  Logger logger;

  public Tests3Em()
  {
    logger = Logger.getLogger("bs");
  }

  class Modifier implements Runnable
  {
    private int bookId;
    public Modifier(int bookId)
    {
      this.bookId = bookId;
    }
    
    @Override
    public void run() {
      boolean bStop = false;
      while (!bStop) {
        EntityManager em = EmfProvider.getEmf().createEntityManager();
        em.getTransaction().begin();
        Book b = em.find(Book.class, bookId);
        System.out.println("will modify a book counter " + b.counter);
        b.counter++;
        em.flush();
        em.getTransaction().commit();
        em.close();
        try {
          Thread.sleep(0);
        } catch (InterruptedException ie) {
          System.out.println("interrupt received");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException ie2) {
            System.out.println("interrupt received again");
          }
          bStop = true;
          System.out.println("stop now");
        }
      }
    }
    
  }
  
  @Test
  public void test3em() throws InterruptedException
  {
    logger.info("This is 3 em test...");
    EntityManagerFactory emf = EmfProvider.getEmf();
    EntityManager em1 = emf.createEntityManager();
    EntityManager em2 = emf.createEntityManager();
    EntityManager em3 = emf.createEntityManager();

    Book b = new Book();
    em1.getTransaction().begin();
    em1.persist(b);
    em1.flush();
    em1.getTransaction().commit();
    //em1.close();
    int bookId = b.id;
    
    Thread th = new Thread(new Modifier(b.id));
    th.start();
    
    Thread.sleep(100);
    
    Book b2 = em2.find(Book.class, bookId);
    System.out.println("b: " + b.counter);
    System.out.println("b2: " + b2.counter);
    
    em1.refresh(b);
    System.out.println("b after refresh: " + b.counter);
    
    th.interrupt();
    th.join();
  }
}
