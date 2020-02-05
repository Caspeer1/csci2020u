package gradleDemo;
import org.apache.commons.validator.routines.*;
public class CSVDemo {
   static final String cardNum = "4716841076742166";
   public static void main(String[] args) {
       CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.AMEX + CreditCardValidator.VISA);
       boolean valid = validator.isValid(cardNum);
       System.out.println("Is the credit card number valid? " + valid);
   }
}