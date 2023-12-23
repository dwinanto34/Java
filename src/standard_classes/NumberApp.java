package standard_classes;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberApp {
    public static void main(String[] args) {
        // 1. Integer
        Integer number  = Integer.parseInt("123");
        Math.max(1, 2);
        Math.min(1, 2);

        // 2. Big Integer
        BigInteger bi = new BigInteger("1").multiply(new BigInteger("2"));

        // 3. Big Decimal
        BigDecimal bd = new BigDecimal("1").multiply(new BigDecimal("2"));
    }
}
