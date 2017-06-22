package db;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfProvider {
  static EntityManagerFactory emf;
  
  public static EntityManagerFactory getEmf()
  {
    if (emf == null) {
      Properties props = new Properties();
      props.setProperty("javax.persistence.jdbc.url",
          "jdbc:h2:mem:app2;TRACE_LEVEL_SYSTEM_OUT=1");
      props.setProperty("javax.persistence.schema-generation.database.action",
          "drop-and-create");
      emf = Persistence.createEntityManagerFactory(null, props);
    }
    return emf;
  }
}
