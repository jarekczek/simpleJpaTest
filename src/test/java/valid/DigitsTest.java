package valid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

public class DigitsTest {

  private Validator validator;

  @Before
  public void init() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  public void fullNumberIsOk() {
    Assert.assertTrue(bigDecimalValidatesOk("1234.45"));
  }

  @Test
  public void tooManyFractionDigitsFail() {
    Assert.assertFalse(bigDecimalValidatesOk("1234.456"));
  }

  @Test
  public void tooManyIntegerDigitsFail() {
    Assert.assertFalse(bigDecimalValidatesOk("12345.45"));
  }

  @Test
  public void bigIntegerFails() {
    Assert.assertFalse(bigDecimalValidatesOk("12345"));
  }

  private boolean bigDecimalValidatesOk(String strValue) {
    Digits42 rec = new Digits42();
    rec.value = new BigDecimal(strValue);
    Set<ConstraintViolation<Digits42>> results = validator.validate(rec);
    results.forEach(System.out::println);
    return results.size() == 0;
  }

  public static class Digits42 {
    @Digits(integer = 4, fraction = 2)
    public BigDecimal value;
  }

}
