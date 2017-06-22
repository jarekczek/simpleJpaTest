package db;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfProvider {
  public static EntityManagerFactory getEmf()
  {
    Properties props = new Properties();
    props.setProperty("javax.persistence.jdbc.url",
      "jdbc:h2:mem:app2;TRACE_LEVEL_SYSTEM_OUT=1");
    props.setProperty("javax.persistence.schema-generation.database.action",
      "drop-and-create");
    return Persistence.createEntityManagerFactory(null, props);
    
  }
}
