package pojo;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "101")
public class MovieReview extends MovieInfo {
  public MovieReview()
  {
  }

  public MovieReview(Movie movie) {
    this.movie = movie;
  }
}
