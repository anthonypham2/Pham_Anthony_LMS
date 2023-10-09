package jframe;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.PreparedStatement;

/**
 *
 * @author Anthony Pham
 */
public class bookManager extends javax.swing.JFrame {
    
    String bookTitle, bookAuthor, bookid;
    DefaultTableModel table;
    
    public bookManager(){
        initComponents();
        displayBooks();
    }
    
    //displays all books on the table
    public void displayBooks(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from books");
            
            while(rs.next()){
                String bookName = rs.getString("bookTitle");
                String bookWriter = rs.getString("bookAuthor");
                String bookID = rs.getString("bookid");
                
                Object[] obj = {bookName, bookWriter, bookID};
                table =(DefaultTableModel) bookTable.getModel();
                table.addRow(obj);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //add another book to database when "ADD" is clicked with all fields entered
    public boolean addBook(){
        boolean addSuccessful = false;
        bookTitle = txt_bookTitle.getText();
        bookAuthor = txt_bookAuthor.getText();
        bookid = txt_bookid.getText();
        
        try{
            Connection con = DBC.getConnection();
            String sql = "insert into books values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookTitle);
            pst.setString(2,bookAuthor);
            pst.setString(3, bookid);
            
            int row = pst.executeUpdate();
            if(row > 0){
                addSuccessful = true;
            }
            else{
                addSuccessful = false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return addSuccessful;
    }
    
    //deletes selected book either by searching title or selecting from table
    public boolean deleteBook(){
        boolean bookDeleted = false;
        bookTitle = txt_bookTitle.getText();
        
        try{
            Connection con = DBC.getConnection();
            String sql = "delete from books where bookTitle = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookTitle);
            int row = pst.executeUpdate();
            
            if(row > 0){
                bookDeleted = true;
            }
            else{
                bookDeleted = false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return bookDeleted;
    }
    public boolean deleteBookByID(){
        boolean bookDeleted = false;
        bookid = txt_bookid.getText();
        
        try{
            Connection con = DBC.getConnection();
            String sql = "delete from books where bookid = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookid);
            int row = pst.executeUpdate();
            
            if(row > 0){
                bookDeleted = true;
            }
            else{
                bookDeleted = false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return bookDeleted;
    }
    
    //refresh table when a book is added
    public void refreshTable(){
        DefaultTableModel table = (DefaultTableModel) bookTable.getModel();
        table.setRowCount(0);
    }
    public boolean updateTable(){
        boolean addSuccessful = false;
        bookTitle = txt_bookTitle.getText();
        bookAuthor = txt_bookAuthor.getText();
        bookid = txt_bookid.getText(); 
        
        try{
            Connection con = DBC.getConnection();
            String sql = "update books set bookTitle = ?, bookAuthor = ?, bookid = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookTitle);
            pst.setString(2, bookAuthor);
            pst.setString(3, bookid);
            
            int row = pst.executeUpdate();
            
            if(row > 0){
                addSuccessful = true;
            }
            else{
                addSuccessful = false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return addSuccessful;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_bookAuthor = new app.bolivia.swing.JCTextField();
        txt_bookTitle = new app.bolivia.swing.JCTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookTable = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(191, 49, 26));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookid.setPlaceholder("Enter book ID here");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 170, 30));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Book Barcode");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Author");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        txt_bookAuthor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookAuthor.setPlaceholder("Enter book author here");
        txt_bookAuthor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookAuthorFocusLost(evt);
            }
        });
        txt_bookAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookAuthorActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 170, 30));

        txt_bookTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookTitle.setPlaceholder("Enter book title here");
        txt_bookTitle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookTitleFocusLost(evt);
            }
        });
        txt_bookTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookTitleActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 170, 40));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Title");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Book Manager");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 30));

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("ADD BOOK");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 80, 30));

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("REMOVE");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 60, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 220, 540));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Author", "Book Barcode"
            }
        ));
        bookTable.setColorBackgoundHead(new java.awt.Color(191, 49, 26));
        bookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookTable);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 590, 190));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EXIT");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 60, 30));

        jPanel3.setBackground(new java.awt.Color(191, 49, 26));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("All Books");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jLabel4)
                .addContainerGap(259, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 590, 60));

        jLabel5.setBackground(new java.awt.Color(191, 49, 26));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/VC-Primary-Web-1024x156.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, 1050, 100));

        jPanel7.setBackground(new java.awt.Color(191, 49, 26));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 1030, 640));

        jPanel4.setBackground(new java.awt.Color(191, 49, 26));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel3.setText("Home");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 100));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost

    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void txt_bookAuthorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookAuthorFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookAuthorFocusLost

    private void txt_bookAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookAuthorActionPerformed

    private void txt_bookTitleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookTitleFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookTitleFocusLost

    private void txt_bookTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookTitleActionPerformed

    //User will be able to select specific row on table, and this method populates information on the left hand side
    private void bookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookTableMouseClicked
        int rowNum = bookTable.getSelectedRow();
        TableModel table = bookTable.getModel();
        
        txt_bookTitle.setText(table.getValueAt(rowNum, 0).toString());
        txt_bookAuthor.setText(table.getValueAt(rowNum, 1).toString());
        txt_bookid.setText(table.getValueAt(rowNum, 2).toString());
    }//GEN-LAST:event_bookTableMouseClicked
    
    //redirects to library home screen
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        LibraryHome home = new LibraryHome();
        home.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_jLabel3MouseClicked
    
    //Attempts to add book informatino to database when clicking "Add" button, if successful, book will be added, then table will refresh with updated database
    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if(addBook() == true){
            JOptionPane.showMessageDialog(this, "Book sucessfully added");
            refreshTable();
            displayBooks();
        }
        else{
            JOptionPane.showMessageDialog(this, "Error:Book not added");
        }
    }//GEN-LAST:event_jLabel6MouseClicked
    
    //Select a book from the table to delete
    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        if(deleteBook() == true){
            JOptionPane.showMessageDialog(this, "Book deleted by title.");
            refreshTable();
            displayBooks();
        }
        else if(deleteBookByID() == true){
            JOptionPane.showMessageDialog(this, "Book deleted by barcode ID.");
            refreshTable();
            displayBooks();      
        }
        else{
            JOptionPane.showMessageDialog(this, "Error:Book not deleted");
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(bookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bookManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.RSTableMetro bookTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private app.bolivia.swing.JCTextField txt_bookAuthor;
    private app.bolivia.swing.JCTextField txt_bookTitle;
    private app.bolivia.swing.JCTextField txt_bookid;
    // End of variables declaration//GEN-END:variables
}
