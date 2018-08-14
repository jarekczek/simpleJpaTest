package pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Club {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int id;

  @Version
  public int version;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "CLUB_STUD",
    joinColumns = @JoinColumn(name = "CLUB_ID"),
    inverseJoinColumns = @JoinColumn(name = "STUDENT_ID")
  )
  public Set<Student> students;

  public Club()
  {
    students = new HashSet<>();
  }
}
