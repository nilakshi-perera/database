package SLIOP;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dashboard_admin extends javax.swing.JFrame {

    public dashboard_admin() {
        initComponents();
        
        //SET THE ICON IN TITLE BAR
        this.setIconImage(new ImageIcon(getClass().getResource("Images/logo.png")).getImage());

        //CALL THE DATE AND TIME
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
                    String months[] = {
                        "Jan", "Feb", "Mar", "Apr",
                        "May", "Jun", "Jul", "Aug",
                        "Sep", "Oct", "Nov", "Dec"};
                    
                        date_field.setText(months[calendar.get(Calendar.MONTH)]+" " + calendar.get(Calendar.DATE) + ", "+ calendar.get(Calendar.YEAR));

                        //SET THE TIME
                        int second=calendar.get(Calendar.SECOND);
                        int minute=calendar.get(Calendar.MINUTE);
                        int hour=calendar.get(Calendar.HOUR);
                        int am_pm=calendar.get(Calendar.AM_PM);
                        time_field.setText(hour+":"+(minute)+":"+second+" "+((am_pm==Calendar.AM)?"am":"pm"));
                            
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

        jRadioButton1 = new javax.swing.JRadioButton();
        contact = new javax.swing.JButton();
        exam = new javax.swing.JButton();
        profile = new javax.swing.JButton();
        course = new javax.swing.JButton();
        schedule = new javax.swing.JButton();
        attendance = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        alert = new javax.swing.JButton();
        admission = new javax.swing.JButton();
        settings = new javax.swing.JButton();
        date_field = new javax.swing.JLabel();
        time_field = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        library = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");
        setMinimumSize(new java.awt.Dimension(1300, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/contact.png"))); // NOI18N
        contact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });
        getContentPane().add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 170, 160));

        exam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/view.png"))); // NOI18N
        exam.setToolTipText("");
        exam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examActionPerformed(evt);
            }
        });
        getContentPane().add(exam, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, 300, 160));

        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/profile.png"))); // NOI18N
        profile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileActionPerformed(evt);
            }
        });
        getContentPane().add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 500, 300, 150));

        course.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/course.png"))); // NOI18N
        course.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseActionPerformed(evt);
            }
        });
        getContentPane().add(course, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 300, 160));

        schedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/schedule.png"))); // NOI18N
        schedule.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        schedule.setFocusable(false);
        schedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleActionPerformed(evt);
            }
        });
        getContentPane().add(schedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 350, 150));
        schedule.getAccessibleContext().setAccessibleDescription("");

        attendance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/attendance.png"))); // NOI18N
        attendance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        attendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendanceActionPerformed(evt);
            }
        });
        getContentPane().add(attendance, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 170, 160));

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/shutdown_icon.png"))); // NOI18N
        logout.setContentAreaFilled(false);
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 10, 40, 40));

        alert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/alert.png"))); // NOI18N
        alert.setToolTipText("");
        alert.setContentAreaFilled(false);
        alert.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        alert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alertActionPerformed(evt);
            }
        });
        getContentPane().add(alert, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 40, 40));

        admission.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/admission.png"))); // NOI18N
        admission.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        admission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissionActionPerformed(evt);
            }
        });
        getContentPane().add(admission, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 300, 160));

        settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/settings.png"))); // NOI18N
        settings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });
        getContentPane().add(settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 330, 300, 160));

        date_field.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        date_field.setForeground(new java.awt.Color(255, 255, 255));
        date_field.setText("date");
        getContentPane().add(date_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 20));

        time_field.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        time_field.setForeground(new java.awt.Color(255, 255, 255));
        time_field.setText("time");
        getContentPane().add(time_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 150, 20));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Welcome to Sri Lanka Institute of Printing");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 670, 60));

        library.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/library.png"))); // NOI18N
        library.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libraryActionPerformed(evt);
            }
        });
        getContentPane().add(library, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 170, 160));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/internet.png"))); // NOI18N
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 500, 300, 150));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/dashboard_background.png"))); // NOI18N
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
         int reply=JOptionPane.showConfirmDialog(null,"Do you want to log out?","Confirm Logout",YES_NO_OPTION);
        
        if(reply==JOptionPane.YES_OPTION){
            login_page login=new login_page();
            login.setVisible(true);

            dispose();
        }
        
        if(reply==JOptionPane.NO_OPTION){
            this.setVisible(true);
        }
    }//GEN-LAST:event_logoutActionPerformed

    private void contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactActionPerformed
        Contact alert=new Contact();
        alert.setVisible(true);
        
        dispose();
    }//GEN-LAST:event_contactActionPerformed

    private void scheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleActionPerformed
        Scheduling schedule=new Scheduling();
        schedule.setVisible(true);
        
        dispose();
    }//GEN-LAST:event_scheduleActionPerformed

    private void attendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendanceActionPerformed

        Attendance attend=new Attendance();
        attend.setVisible(true);

        dispose();
    }//GEN-LAST:event_attendanceActionPerformed

    private void courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseActionPerformed
        Course course=new Course();
        course.setVisible(true);
        dispose();
    }//GEN-LAST:event_courseActionPerformed

    private void profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileActionPerformed
        Profile pro=new Profile();
        pro.setVisible(true);

        dispose();
    }//GEN-LAST:event_profileActionPerformed

    private void admissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissionActionPerformed
        Admission ad=new Admission();
        ad.setVisible(true);

        dispose();
    }//GEN-LAST:event_admissionActionPerformed

    private void settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsActionPerformed
        Settings setting=new Settings();
        setting.setVisible(true);
        
        dispose();
    }//GEN-LAST:event_settingsActionPerformed

    private void examActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examActionPerformed
        view v=new view();
        v.setVisible(true);
        
        dispose();
    }//GEN-LAST:event_examActionPerformed

    private void alertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alertActionPerformed

    private void libraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libraryActionPerformed
       Library lib=new Library();
       lib.setVisible(true);
       
       dispose();
    }//GEN-LAST:event_libraryActionPerformed

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
            java.util.logging.Logger.getLogger(dashboard_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard_admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admission;
    private javax.swing.JButton alert;
    private javax.swing.JButton attendance;
    private javax.swing.JButton contact;
    private javax.swing.JButton course;
    private javax.swing.JLabel date_field;
    private javax.swing.JButton exam;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JButton library;
    private javax.swing.JButton logout;
    private javax.swing.JButton profile;
    private javax.swing.JButton schedule;
    private javax.swing.JButton settings;
    private javax.swing.JLabel time_field;
    // End of variables declaration//GEN-END:variables
}
