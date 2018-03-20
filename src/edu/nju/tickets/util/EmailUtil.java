package edu.nju.tickets.util;

/**
 * Created by foxwel on 2018/3/7.
 */
public class EmailUtil {

    public static String getEmailCheckNumber(String emailAddress) {
        int result = 1;
        for (int i = 0; i < emailAddress.length(); ++i) {
            char currentChar = emailAddress.charAt(i);
            int currentNumber = 1;
            if ((currentChar >= 'a') && (currentChar <= 'z')) {
                currentNumber = currentChar - 'a' + 10;
            } else if ((currentChar >= 'A') && (currentChar <= 'Z')) {
                currentNumber = currentChar - 'A' + 40;
            } else if ((currentChar >= '0') && (currentChar <= '9')) {
                currentNumber = currentChar - '0' + 70;
            } else {
                currentNumber = 1;
            }
            result = (result * currentNumber) % 1000000;
        }
        String emailCheckNumber = String.valueOf(result);
        while (emailCheckNumber.length() < 6) {
            emailCheckNumber = "0" + emailCheckNumber;
        }
        return emailCheckNumber;
    }

    public static void main(String[] args) {
        System.out.println(getEmailCheckNumber("cttony1997@126.com"));
    }
}
