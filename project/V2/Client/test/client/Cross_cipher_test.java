/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.serverCipher;
/**
 *
 * @author kings
 */
public class Cross_cipher_test {
    
    public Cross_cipher_test() {
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
     * Test of encrypt method, of class Encrypted.
     */
    @Test
    public void test() {
        System.out.println("client encrypt - server decrypt");
        String message = "hello";
        String expResult = "hello";
        String result = clientCipher.encrypt(message);
        result = server.serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    
    @Test
    public void test1() {
        /*
            testing lowercase letters
        */
        System.out.println("client encrypt - server decrypt");
        String message = "hello";
        String expResult = "hello";
        String result = clientCipher.encrypt(message);
        result = server.serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void test2() {
        /*
            testing numbers
        */
        System.out.println("client encrypt - server decrypt");
        String message = "1234567";
        String expResult = "1234567";
        String result = clientCipher.encrypt(message);
        result = server.serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void test3() {
        /*
            testing non-alphabhetical and non-numerical characters
        */
        System.out.println("eclient encrypt - server decrypt");
        String message = ",.,/;''[] =-_+";
        String expResult = ",.,/;''[] =-_+";
        String result = clientCipher.encrypt(message);
        result = server.serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void test4() {
        /*
            testing a combination of lowercase, umbers and special characters
        */
        System.out.println("client encrypt - server decrypt");
        String message = "hello! I am 12 years old";
        String expResult = "hello! I am 12 years old";
        String result = clientCipher.encrypt(message);
        result = server.serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void test5() {
        /*
            testing uppercase
        */
        System.out.println("client encrypt - server decrypt");
        String message = "HELLO";
        String expResult = "HELLO";
        String result = clientCipher.encrypt(message);
        result = server.serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void test6() {
        /*
            testing a combination of uppercase, lowercase, numbers and special characters
        */
        System.out.println("client encrypt - server decrypt");
        String message = "HELLO! how are YOU>.?";
        String expResult = "HELLO! how are YOU>.?";
        String result = clientCipher.encrypt(message);
        result = server.serverCipher.decrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    
    @Test
    public void test7() {
        System.out.println("client encrypt - server decrypt");
        String message = "hello";
        String expResult = "hello";
        String result = clientCipher.decrypt(message);
        result = server.serverCipher.encrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    
    @Test
    public void test8() {
        /*
            testing lowercase letters
        */
        System.out.println("client decrypt- server encrypt");
        String message = "hello";
        String expResult = "hello";
        String result = clientCipher.decrypt(message);
        result = server.serverCipher.encrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void test9() {
        /*
            testing numbers
        */
        System.out.println("client decrypt- server encrypt");
        String message = "1234567";
        String expResult = "1234567";
        String result = clientCipher.decrypt(message);
        result = server.serverCipher.encrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void test10() {
        /*
            testing non-alphabhetical and non-numerical characters
        */
        System.out.println("client decrypt- server encrypt");
        String message = ",.,/;''[] =-_+";
        String expResult = ",.,/;''[] =-_+";
        String result = clientCipher.decrypt(message);
        result = server.serverCipher.encrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void test11() {
        /*
            testing a combination of lowercase, umbers and special characters
        */
        System.out.println("client decrypt- server encrypt");
        String message = "hello! I am 12 years old";
        String expResult = "hello! I am 12 years old";
        String result = clientCipher.decrypt(message);
        result = server.serverCipher.encrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void test12() {
        /*
            testing uppercase
        */
        System.out.println("client decrypt- server encrypt");
        String message = "HELLO";
        String expResult = "HELLO";
        String result = clientCipher.decrypt(message);
        result = server.serverCipher.encrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void test13() {
        /*
            testing a combination of uppercase, lowercase, numbers and special characters
        */
        System.out.println("client decrypt- server encrypt");
        String message = "HELLO! how are YOU>.?";
        String expResult = "HELLO! how are YOU>.?";
        String result = clientCipher.decrypt(message);
        result = server.serverCipher.encrypt(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
