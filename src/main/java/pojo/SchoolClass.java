package pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SchoolClass {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int id;

  @Version
  public int version;

  @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL)
  public Set<Student> students;

  public SchoolClass()
  {
    students = new HashSet<>();
  }
}
