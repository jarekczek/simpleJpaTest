package pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int id;

  @Version
  public int version;

  @ManyToOne
  public SchoolClass schoolClass;

  @ManyToMany(mappedBy = "students")
  //@JoinTable(name = "CLUB_STUD"
    //joinColumns = @JoinColumn(name = "CSTUDENT_ID"),
    //inverseJoinColumns = @JoinColumn(name = "CCLUB_ID")
  //)
  public Set<Club> clubs;

  public Student()
  {
    this.clubs = new HashSet<>();
  }
}
