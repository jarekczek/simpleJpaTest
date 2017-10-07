package pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int id;

  @Version
  public int version;

  // Below we map to base class of MovieReview, impossible in Hibernate
  // https://stackoverflow.com/a/46619634/772981
  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
  public Set<MovieReview> reviews;
  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
  public Set<MovieDescription> descriptions;

  public Movie()
  {
    this.reviews = new HashSet<>();
    this.descriptions = new HashSet<>();
  }
}
