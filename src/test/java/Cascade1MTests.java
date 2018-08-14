import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import db.EmfProvider;
import pojo.*;

public class Cascade1MTests
{
  Logger logger;

  @Test
  public void test()
  {
    EntityManagerFactory emf = EmfProvider.getEmf();

    EntityManager em = emf.createEntityManager();
    Student s1 = new Student();
    Student s2 = new Student();
    SchoolClass c = new SchoolClass();
    c.students.addAll(Arrays.asList(s1, s2));
    s1.schoolClass = c;
    s2.schoolClass = c;
    em.getTransaction().begin();
    em.persist(c);
    em.flush();
    em.getTransaction().commit();
    em.close();
    int idClass = c.id;

    em = emf.createEntityManager();
    List<Student> students = (List<Student>) em.createQuery("select s from Student s").getResultList();
    Assert.assertEquals(2, students.size());
    SchoolClass c2 = em.find(SchoolClass.class, idClass);
    Assert.assertEquals(2, c2.students.size());
  }
}