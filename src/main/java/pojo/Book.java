package pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Version;

@Entity
public class Book {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int id;

  @JoinColumn(name = "AUTHOR_ID")
  public Author author;

  @Column(name = "AUTHOR_ID", updatable = false, insertable = false)
  public int authorId;

  @Version
  public int version;
  
  public String title;
  
  public int counter;
}
