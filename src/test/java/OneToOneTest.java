import db.EmfProvider;
import org.junit.Assert;
import org.junit.Test;
import pojo.Part1;
import pojo.Part2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class OneToOneTest {
  @Test
  public void oneToOneTest() throws InterruptedException {
    EntityManagerFactory emf = EmfProvider.getEmf();
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Part1 p1 = new Part1();
    Part2 p2 = new Part2();
    p1.setPart2(p2);
    Part1 p1a = new Part1();
    Part2 p2a = new Part2();
    p1a.setPart2(p2a);
    em.persist(p1);
    em.persist(p2);
    em.persist(p1a);
    em.persist(p2a);
    em.flush();
    em.getTransaction().commit();

    em.clear();

    //Part1 pn = em.find(Part1.class, p1.getId());
    List lst = em.createQuery("select p from Part1 p").getResultList();
    //List lst = em.createQuery("select p from Part1 p join fetch p.part2 p2").getResultList();
    Part1 pn = (Part1)(lst.get(0));

    Thread.sleep(300);
    System.out.println("found: " + pn);
    Thread.sleep(300);
    System.out.println("p2: " + pn.getPart2());
    //Thread.sleep(300);
    //System.out.println("loading p2: " + em.find(Part2.class, 1));
    //Thread.sleep(300);
    //System.out.println("p2: " + pn.getPart2());

    em.close();
  }
}
