/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.util;

import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rodrigo
 */
public class ValidatorsTest {

    public ValidatorsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of validateEmail method, of class Validators.
     */
    @Test
    public void testValidateEmail() {
        assertTrue(Validators.validateEmail("rodrigoill@hotmail.com"));
        assertFalse(Validators.validateEmail("rodrigoill@hotmailcom"));
        assertFalse(Validators.validateEmail("rodrigoill@hotmail."));
        assertFalse(Validators.validateEmail("rodrigoill.hotmail.com"));
    }

    /**
     * Test of validateCpf method, of class Validators.
     */
    @Test
    public void testValidateCpf() {
        assertTrue(Validators.validateCpf("00917471393"));
        assertFalse(Validators.validateCpf("00917471391"));
        assertFalse(Validators.validateEmail("12312233312"));
        assertFalse(Validators.validateEmail("12333222321"));
    }

    /**
     * Test of validateZipCode method, of class Validators.
     */
    @Test
    public void testValidateZipCode() {
        assertTrue(Validators.validateZipCode("60135-420",""));
        assertFalse(Validators.validateZipCode("60132221",""));
        assertFalse(Validators.validateZipCode("6a111122",""));
    }

    /**
     * Test of validateMaxCharacters method, of class Validators.
     */
    @Test
    public void testValidateMaxCharacters() {
        assertTrue(Validators.validateMaxCharacters("asda123123", 10));
        assertTrue(Validators.validateMaxCharacters("asda123", 10));
        assertFalse(Validators.validateMaxCharacters("asda123123assd2", 10));
        assertFalse(Validators.validateMaxCharacters("asda123123123123as", 10));
    }

    /**
     * Test of validateMinCharacters method, of class Validators.
     */
    @Test
    public void testValidateMinCharacters() {
        assertTrue(Validators.validateMinCharacters("asda123123", 10));
        assertTrue(Validators.validateMinCharacters("asda123123213123", 10));
        assertFalse(Validators.validateMinCharacters("asda1231", 10));
    }

    /**
     * Test of isNumeric method, of class Validators.
     */
    @Test
    public void testIsNumeric() {
        assertTrue(Validators.isNumeric("2312"));
    }

    /**
     * Test of validateDate method, of class Validators.
     */
    @Test
    public void testValidateDate_String() {
        assertTrue(Validators.validateDate("25-07-2008"));
        assertTrue(Validators.validateDate("25-7-2008"));
        assertTrue(Validators.validateDate("15-12-2008"));
        assertTrue(Validators.validateDate("25-07-08"));
    }

    /**
     * Test of validateDate method, of class Validators.
     */
    @Test
    public void testValidateDate_String_String() {
        assertTrue(Validators.validateDate("322","DD"));
        assertTrue(Validators.validateDate("12","H"));
    }

    /**
     * Test of isLowerOrEqual method, of class Validators.
     */
    @Test
    public void testIsLowerOrEqual() {
        Date date1 = new Date();
        Date date2 = new Date();
        assertTrue(Validators.isLowerOrEqual(date1, date2));
    }

}