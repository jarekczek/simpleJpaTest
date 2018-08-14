import db.EmfProvider;
import org.junit.Assert;
import org.junit.Test;
import pojo.Club;
import pojo.SchoolClass;
import pojo.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CascadeMMTests
{
  Logger logger;

  @Test
  public void test()
  {
    EntityManagerFactory emf = EmfProvider.getEmf();

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.createQuery("delete from Student").executeUpdate();
    em.getTransaction().commit();
    em.close();

    em = emf.createEntityManager();
    Student s1 = new Student();
    Student s2 = new Student();
    Club c1 = new Club();
    Club c2 = new Club();
    c1.students.addAll(Arrays.asList(s1, s2));
    c2.students.addAll(Arrays.asList(s1, s2));
    em.getTransaction().begin();
    em.persist(c1);
    em.persist(c2);
    em.flush();
    em.getTransaction().commit();
    em.close();
    int idc1 = c1.id;

    em = emf.createEntityManager();
    List<Student> students = (List<Student>) em.createQuery("select s from Student s").getResultList();
    Assert.assertEquals(2, students.size());
    Club c3 = em.find(Club.class, idc1);
    Assert.assertEquals(2, c3.students.size());
  }
}