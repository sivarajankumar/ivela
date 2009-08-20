/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 *
 * @author rodrigo
 */
public class Validators {

    /**
     * verifies whether an email is valid or not
     * @param email
     * @return true, if is valid
     *         false, otherwise
     */
    public static final boolean validateEmail(String email) {
        // set the email pattern string
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

        // match the given string with the pattern
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * verifies whether a CPF is valid or not
     * @param cpf
     * @return true, if is valid
     *         false, otherwise
     */
    public static final boolean validateCpf(String cpf) {
        // verifies the cpf lenght
        if (cpf.length() != 11) {
            return false;
        }

        return calculateDigit(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
    }

    /**
     * calculates the verifier digit of the CPF
     * @param num
     * @return
     */
    private static String calculateDigit(String num) {
        Integer firstDigit, secondDigit;
        int sum = 0, weight = 10;
        for (int i = 0; i < num.length(); i++) {
            sum += Integer.parseInt(num.substring(i, i + 1)) * weight--;
        }
        if (sum % 11 == 0 | sum % 11 == 1) {
            firstDigit = new Integer(0);
        } else {
            firstDigit = new Integer(11 - (sum % 11));
        }
        sum = 0;
        weight = 11;
        for (int i = 0; i < num.length(); i++) {
            sum += Integer.parseInt(num.substring(i, i + 1)) * weight--;
        }
        sum += firstDigit.intValue() * 2;
        if (sum % 11 == 0 | sum % 11 == 1) {
            secondDigit = new Integer(0);
        } else {
            secondDigit = new Integer(11 - (sum % 11));
        }
        return firstDigit.toString() + secondDigit.toString();
    }

    /**
     * verifies whether a zip code is valid or not
     * @param email
     * @param idiom
     * @return true, if is valid
     *         false, otherwise
     */
    public static final boolean validateZipCode(String zip, String idiom) {
        // set the email pattern string
        Pattern p = Pattern.compile("[0-9]{5}-[0-9]{3}");

        // match the given string with the pattern
        Matcher m = p.matcher(zip);

        return m.matches();
    }

    /**
     * verifies whether a word is lower or equal than max
     * @param word
     * @param max
     * @return true, if is valid
     *         false, otherwise
     */
    public static final boolean validateMaxCharacters(String word, Integer max) {
        return (word.length() <= max);
    }

    /**
     * verifies whether a word is greater or equal than min
     * @param word
     * @param max
     * @return true, if is valid
     *         false, otherwise
     */
    public static final boolean validateMinCharacters(String word, Integer min) {
        return (word.length() >= min);
    }

    /**
     * verifies whether an object is numeric
     * @param object
     * @return true, if is numeric
     *         false, otherwise
     */
    public static final boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    /**
     * verifies if an integer is greater than zero
     * @param number
     * @return true, if is greater than zero
     *         false, otherwise
     */
    public static final boolean isPositive(Integer number) {
        return (number > 0);
    }
    
    /**
     * verifies if a long is greater than zero
     * @param number
     * @return true, if is greater than zero
     *         false, otherwise
     */
    public static final boolean isPositive(Long number) {
        return (number > 0);
    }

    /**
     * verifies whether a date is valid or not
     * @param date
     * @return true, if is numeric
     *         false, otherwise
     */
    public static final boolean validateDate(String date) {
        return validateDate(date, "dd-MM-yyyy");
    }

    /**
     * verifies whether a date is valid or not
     * @param date
     * @param format
     * @return true, if is numeric
     *         false, otherwise
     */
    public static final boolean validateDate(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    
    /**
     * verifies whether date1 is lower or equal than date2
     * @param date1
     * @param date2
     * @return true, if is lower or equal than
     *         false, otherwise
     */
    public static final boolean isLowerOrEqual(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        
        int result = calendar1.compareTo(calendar2);   
            
        return (result <= 0);
    }
    
    /**
     * verifies whether a string is not empty
     * @param string
     * @return true, if is not empty
     *         false, otherwise
     */
    public static final boolean hasText(String string) {
        return StringUtils.hasText(string);
    }
}