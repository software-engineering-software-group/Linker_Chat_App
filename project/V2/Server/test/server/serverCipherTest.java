/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kings
 */
public class serverCipherTest {
    
    public serverCipherTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of decrypt method, of class serverCipher.
     */
    @Test
    public void test7() {
        System.out.println("server decrypt -  encrypt");
        String message = "hello";
        String expResult = "hello";
        String result = serverCipher.encrypt(message);
        result = serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    
    @Test
    public void test8() {
        /*
            testing lowercase letters
        */
        System.out.println("server decrypt -  encrypt");
        String message = "hello";
        String expResult = "hello";
        String result = serverCipher.encrypt(message);
        result = serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void test9() {
        /*
            testing numbers
        */
        System.out.println("server decrypt -  encrypt");
        String message = "1234567";
        String expResult = "1234567";
        String result = serverCipher.encrypt(message);
        result = serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void test10() {
        /*
            testing non-alphabhetical and non-numerical characters
        */
        System.out.println("server decrypt -  encrypt");
        String message = ",.,/;''[] =-_+";
        String expResult = ",.,/;''[] =-_+";
        String result = serverCipher.encrypt(message);
        result = serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void test11() {
        /*
            testing a combination of lowercase, umbers and special characters
        */
        System.out.println("server decrypt -  encrypt");
        String message = "hello! I am 12 years old";
        String expResult = "hello! I am 12 years old";
        String result = serverCipher.encrypt(message);
        result = serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void test12() {
        /*
            testing uppercase
        */
        System.out.println("server decrypt -  encrypt");
        String message = "HELLO";
        String expResult = "HELLO";
        String result = serverCipher.encrypt(message);
        result = serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void test13() {
        /*
            testing a combination of uppercase, lowercase, numbers and special characters
        */
        System.out.println("server decrypt -  encrypt");
        String message = "HELLO! how are YOU>.?";
        String expResult = "HELLO! how are YOU>.?";
        String result = serverCipher.encrypt(message);
        result = serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
