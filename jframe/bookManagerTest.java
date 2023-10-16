/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class bookManagerTest {
    
    public bookManagerTest() {
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

    @Test
    public void testAddBook() {
        System.out.println("addBook");
        bookManager instance = new bookManager();
        boolean expResult = false;
        boolean result = instance.addBook();
        assertEquals(expResult, result);
        
        String bookTitle = "bookTest";
        String bookAuthor = "bookAuthorTest";
        String bookid = "BookTest123";
        
        try{
            Connection con = DBC.getConnection();
            String sql = "insert into books values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookTitle);
            pst.setString(2,bookAuthor);
            pst.setString(3, bookid);
            
            pst.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteBook method, of class bookManager.
     */
    @Test
    public void testDeleteBook() {
        System.out.println("deleteBook");
        bookManager instance = new bookManager();
        boolean expResult = false;
        boolean result = instance.deleteBook();
        assertEquals(expResult, result);
        String bookTitle = "book1";
        
        try{
            Connection con = DBC.getConnection();
            String sql = "delete from books where bookTitle = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookTitle);
            pst.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteBookByID method, of class bookManager.
     */
    @Test
    public void testDeleteBookByID() {
        System.out.println("deleteBookByID");
        bookManager instance = new bookManager();
        boolean expResult = false;
        boolean result = instance.deleteBookByID();
        assertEquals(expResult, result);
        String bookid = "123456";
        
        try{
            Connection con = DBC.getConnection();
            String sql = "delete from books where bookid = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookid);
            pst.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        fail("The test case is a prototype.");
    }
    /**
     * Test of main method, of class bookManager.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        bookManager.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
