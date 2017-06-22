package pojo;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Book {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int id;
  
  @Version
  public int version;
  
  public String title;
  
  public int counter;
}
