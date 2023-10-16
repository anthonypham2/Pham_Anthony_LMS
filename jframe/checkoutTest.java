/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Anthony Pham
 */
public class checkoutTest {
    
    public checkoutTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    /**
     * Test of checkoutBook method, of class checkout.
     */
    @Test
    public void testCheckoutBook() {
        System.out.println("checkoutBook");
        checkout instance = new checkout();
        boolean expResult = false;
        boolean result = instance.checkoutBook();
        assertEquals(expResult, result);
        
        String studentID = "V1234";
        String bookTitle = "bookTest";
        Date issueDate = new Date();
        
        int noOfDays = 14;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issueDate);            
        calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
        Date dueDate = calendar.getTime();
        
        Long l1 = issueDate.getTime();
        Long l2 = dueDate.getTime();
        
        java.sql.Date sissueDate = new java.sql.Date(l1);
        java.sql.Date sdueDate = new java.sql.Date(l2);
        
        try{
            Connection con = DBC.getConnection();
            String sql = "insert into checkout values (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, studentID);
            pst.setString(2, bookTitle);
            pst.setDate(3, sissueDate);
            pst.setDate(4, sdueDate);
            pst.setString(5, "Yes");
            pst.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnBook method, of class checkout.
     */
    @Test
    public void testReturnBook() {
        System.out.println("returnBook");
        checkout instance = new checkout();
        boolean expResult = false;
        boolean result = instance.returnBook();
        assertEquals(expResult, result);
        
        String studentID = "V1234";
        String bookTitle = "bookTest";
        
        try{
            Connection con = DBC.getConnection();
            String sql = "update checkout set bookCheckedOut = ? where studentID = ? and bookTitle = ? and bookCheckedOut = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "Returned: No fees");        
            pst.setString(2, studentID); 
            pst.setString(3, bookTitle); 
            pst.setString(4, "Yes"); 
            pst.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class checkout.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        checkout.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
