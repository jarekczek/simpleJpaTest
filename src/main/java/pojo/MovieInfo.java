package pojo;

import javax.persistence.*;

@Entity
@Table(name = "MOVIE_INFO")
@DiscriminatorColumn(name = "typ")
public class MovieInfo
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int id;

  @Version
  public int version;

  @ManyToOne
  public Movie movie;
}
