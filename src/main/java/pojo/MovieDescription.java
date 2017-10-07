package pojo;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "102")
public class MovieDescription extends MovieInfo
{
  public MovieDescription()
  {
  }

  public MovieDescription(Movie movie) {
    this.movie = movie;
  }
}
