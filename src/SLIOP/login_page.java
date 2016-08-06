package SLIOP;

import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class login_page extends javax.swing.JFrame {
        
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    
    public login_page() {
        initComponents();
        
        //SET THE ICON IN TITLE BAR
        this.setIconImage(new ImageIcon(getClass().getResource("Images/logo.png")).getImage());
        
        try{
        //GET THE CONNECTION TO THE DATABASE 
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sliop_db","root","kalpana");
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        CurrentDate();
    }

    //SET THE DATE AND TIME
    public void CurrentDate(){
              
        //TO SET DYNAMIC TIME SETTER 
        Thread clock=new Thread(){
            public void run(){
                for(;;){
                    
                    Calendar calendar=new GregorianCalendar();
        
                        //SET THE DATE
                        int month=calendar.get(Calendar.MONTH);
                        int year=calendar.get(Calendar.YEAR);
                        int day=calendar.get(Calendar.DAY_OF_MONTH);
                        login_date.setText(day+"/"+(month+1)+"/"+year);

                        //SET THE TIME
                        int second=calendar.get(Calendar.SECOND);
                        int minute=calendar.get(Calendar.MINUTE);
                        int hour=calendar.get(Calendar.HOUR);
                        int am_pm=calendar.get(Calendar.AM_PM);
                        login_time.setText(hour+":"+(minute)+":"+second+" "+((am_pm==Calendar.AM)?"am":"pm"));
                            
                        //TO SET DYNAMIC TIME SETTER 
                        try {
                              sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(dashboard_admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                }
            }
        };
        clock.start();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jComboBox2 = new javax.swing.JComboBox<>();
        login_as = new javax.swing.JComboBox<>();
        txt_password = new javax.swing.JPasswordField();
        user_role = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        forget_pw = new javax.swing.JButton();
        cmd_login = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        login_date = new javax.swing.JLabel();
        login_time = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sri Lanka Institute of Printing");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        login_as.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        login_as.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Management", "Academic Staff", "Assistant Staff" }));
        login_as.setAutoscrolls(true);
        getContentPane().add(login_as, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 210, 30));

        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 210, 30));

        user_role.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        user_role.setText("User Role :");
        user_role.setAutoscrolls(true);
        getContentPane().add(user_role, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 228, -1, -1));
        user_role.getAccessibleContext().setAccessibleDescription("");

        password.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        password.setText("Password :");
        password.setAutoscrolls(true);
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 264, -1, 20));

        forget_pw.setText("Forget Password");
        forget_pw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forget_pw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forget_pwActionPerformed(evt);
            }
        });
        getContentPane().add(forget_pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 353, 250, -1));

        cmd_login.setText("Login");
        cmd_login.setAutoscrolls(true);
        cmd_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmd_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_loginActionPerformed(evt);
            }
        });
        getContentPane().add(cmd_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 120, -1));

        clear.setText("Reset");
        clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        getContentPane().add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 120, -1));

        login_date.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        login_date.setForeground(new java.awt.Color(255, 255, 255));
        login_date.setText("date");
        getContentPane().add(login_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 90, -1));

        login_time.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        login_time.setForeground(new java.awt.Color(255, 255, 255));
        login_time.setText("Time");
        getContentPane().add(login_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, 90, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/login_main.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //VALIDATING THE USERNAME AND PASSWORD
    private void cmd_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_loginActionPerformed
        
           try{
            //  PREPARE STATEMENT
            pst=conn.prepareStatement("select * from user_login where user_role=? and password=?");
 
            //GET VALUES FROM JCOMBOBOX
            String name=login_as.getSelectedItem().toString();
            pst.setString(1,name);
            
            //GET VALUES FROM PASSWORD FIELD
            pst.setString(2,txt_password.getText());
            
            rs=pst.executeQuery();
            
                        
            /*
             String sql="insert into login_time (login_name,date,time) values(?,?,?) ";

                         pst=conn.prepareStatement(sql);
                            
                         String role=login_as.getSelectedItem().toString();
                         pst.setString(1,role);
                         
                         java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                         pst.setTimestamp(2, date);
                         
                         java.sql.Timestamp time = new java.sql.Timestamp(new java.util.Date().getTime());
                         pst.setTimestamp(3,time);

                         pst.execute();
            */
                         
                if(rs.next()){ 
                    JOptionPane.showMessageDialog(null,"Login Successfully.");
                     
                    //  WHEN DASHBOARD APPERARS LOGIN PAGE WILL DISPOSE
                    dispose();
                        
                        //IF USERNAME EQUALS ADMIN , GO TO THE DASHBOARD_PAGE
                        if(name.equals("Management")){
                        //IF USERNAME AND PASSWORD CORRECT GO TO THE DASHBOARD PAGE
                        dashboard_admin q=new dashboard_admin();  
                        q.setVisible(true);
                        }
                        
                        //IF USERNAME EQUALS ADMIN , GO TO THE DASHBOARD_PAGE
                        if(name.equals("Admin")){
                        //IF USERNAME AND PASSWORD CORRECT GO TO THE DASHBOARD PAGE
                        dashboard_admin page=new dashboard_admin(); 
                        page.setVisible(true);
                        }

                        //IF USERNAME EQUALS STAFF , GO TO THE DASHBOARD_STAFF
                        if(name.equals("Academic Staff")){
                        //IF USERNAME AND PASSWORD CORRECT GO TO THE DASHBOARD PAGE
                        dashboard_academicstaff aca=new dashboard_academicstaff();
                        aca.setVisible(true);
                        
                        //SET THE USER TYPE IN DASHBOARD
                        //dashboard_academicstaff.page_loger2.setText(login_page.login_as.getSelectedItem().toString());
                        //s.setVisible(true); 
                        }
                        
                        //IF USERNAME EQUALS MANAGEMENT, GO TO THE DABOARD_MANAGEMENT
                        if(name.equals("Assistant Staff")){
                        dashboard_assistantstaff op=new dashboard_assistantstaff();
                        op.setVisible(true);
                        
                        //SET THE USER TYPE IN DASHBOARD
                        //dashboard_assistantstaff.page_loger3.setText(login_page.login_as.getSelectedItem().toString());
                        //m.setVisible(true);
                        }

                } //END OF IF

                else{
                    JOptionPane.showMessageDialog(null,"Incorrect username or password");
                 }
            } //END OF TRY
        
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);    
            }//END OF CATCH 
    }//GEN-LAST:event_cmd_loginActionPerformed

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){//WHEN FILL THE USERNAME AND PASSWORD PRESS ENTER KEY

           try{
            //  PREPARE STATEMENT
            pst=conn.prepareStatement("select * from user_login where user_role=? and password=?");
 
            //GET VALUES FROM JCOMBOBOX
            String name=login_as.getSelectedItem().toString();
            pst.setString(1,name);
            
            //GET VALUES FROM PASSWORD FIELD
            pst.setString(2,txt_password.getText());
            
            rs=pst.executeQuery();
          
                if(rs.next()){ 
                    JOptionPane.showMessageDialog(null,"Login Successfully.");
                    
                    //  WHEN DASHBOARD APPERARS LOGIN PAGE WILL DISPOSE
                    dispose();
                    
                        //IF USERNAME EQUALS MANAGEMENT , GO TO THE DASHBOARD_PAGE
                        if(name.equals("Management")){
                        //IF USERNAME AND PASSWORD CORRECT GO TO THE DASHBOARD PAGE
                        dashboard_admin q=new dashboard_admin();
                        q.setVisible(true);
                        }
                        
                        //IF USERNAME EQUALS ADMIN , GO TO THE DASHBOARD_PAGE
                        if(name.equals("Admin")){
                        //IF USERNAME AND PASSWORD CORRECT GO TO THE DASHBOARD PAGE
                        dashboard_admin page=new dashboard_admin();
                        page.setVisible(true);
                        }

                        //IF USERNAME EQUALS ACADEMIC STAFF , GO TO THE DASHBOARD_STAFF
                        if(name.equals("Academic Staff")){
                        //IF USERNAME AND PASSWORD CORRECT GO TO THE DASHBOARD PAGE
                        dashboard_academicstaff s=new dashboard_academicstaff();
                        s.setVisible(true);
               
                        }
                        
                        //IF USERNAME EQUALS ASSISTANT STAFF , GO TO THE DASHBOARD_STAFF
                        if(name.equals("Assistant Staff")){
                        //IF USERNAME AND PASSWORD CORRECT GO TO THE DASHBOARD PAGE
                        dashboard_assistantstaff k=new dashboard_assistantstaff();
                        k.setVisible(true); 
                        }
                } //END OF IF

                else{
                    JOptionPane.showMessageDialog(null,"Incorrect username or password");
                 }
            } //END OF TRY
        
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);    
            }//END OF CATCH
                                        
        }//MAIN IF CLOSE

    }//GEN-LAST:event_txt_passwordKeyPressed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        txt_password.setText("");
    }//GEN-LAST:event_clearActionPerformed

    private void forget_pwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forget_pwActionPerformed
        Forget_password dialog = new Forget_password(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_forget_pwActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login_page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    private javax.swing.JButton cmd_login;
    private javax.swing.JButton forget_pw;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JComboBox<String> login_as;
    private javax.swing.JLabel login_date;
    private javax.swing.JLabel login_time;
    private javax.swing.JLabel password;
    public static javax.swing.JPasswordField txt_password;
    private javax.swing.JLabel user_role;
    // End of variables declaration//GEN-END:variables

}
