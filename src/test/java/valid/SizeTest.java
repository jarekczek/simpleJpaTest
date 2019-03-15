package valid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Size;
import java.util.Set;

public class SizeTest {

  private Validator validator;

  @Before
  public void init() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  public void sizedMayBeNull() {
    NullableString c = new NullableString();
    c.value = null;
    Set<ConstraintViolation<NullableString>> results = validator.validate(c);
    results.forEach(System.out::println);
    Assert.assertEquals(0, results.size());
  }

  @Test
  public void sizeMustBeWithinBounds() {
    NullableString c = new NullableString();
    c.value = "123";
    Set<ConstraintViolation<NullableString>> results = validator.validate(c);
    results.forEach(System.out::println);
    Assert.assertEquals(1, results.size());
  }

  public static class NullableString {
    @Size(min = 4, max = 10)
    public String value;
  }

}
