package SLIOP;

import javax.swing.ImageIcon;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import static javax.mail.Transport.send;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;


public class Email_Form extends javax.swing.JFrame {

    public Email_Form() {
        initComponents();
        
        //SET THE ICON IN TITLE BAR
        this.setIconImage(new ImageIcon(getClass().getResource("Images/logo.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        to_field = new javax.swing.JTextField();
        CC_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BCC_field = new javax.swing.JTextField();
        from_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        password_field = new javax.swing.JPasswordField();
        subject_field = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        message_field = new javax.swing.JTextArea();
        file_name = new javax.swing.JTextField();
        attachment_field = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        send_btn = new javax.swing.JButton();
        clear_btn = new javax.swing.JButton();
        browse_btn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Email");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(to_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 120, 330, -1));
        getContentPane().add(CC_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 154, 330, -1));

        jLabel2.setText("To :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 118, -1, 25));

        jLabel10.setText("CC :");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 157, -1, -1));

        jLabel6.setText("BCC :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 188, -1, -1));
        getContentPane().add(BCC_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 185, 470, -1));
        getContentPane().add(from_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 216, 470, -1));

        jLabel3.setText("From :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 219, -1, -1));

        jLabel5.setText("Password :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 250, -1, -1));
        getContentPane().add(password_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 247, 470, -1));
        getContentPane().add(subject_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 278, 470, -1));

        jLabel7.setText("Subject :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 281, -1, -1));

        jLabel8.setText("Message :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 309, -1, -1));

        message_field.setColumns(20);
        message_field.setRows(5);
        jScrollPane1.setViewportView(message_field);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 309, 470, 112));
        getContentPane().add(file_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 432, 337, -1));
        getContentPane().add(attachment_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 464, 337, -1));

        jLabel4.setText("File name:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 435, -1, -1));

        jLabel9.setText("Attachment :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 467, -1, -1));

        send_btn.setText("Send");
        send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_btnActionPerformed(evt);
            }
        });
        getContentPane().add(send_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 90, -1));

        clear_btn.setText("Clear");
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });
        getContentPane().add(clear_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 510, 90, -1));

        browse_btn.setText("Attach");
        browse_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browse_btnActionPerformed(evt);
            }
        });
        getContentPane().add(browse_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 463, 105, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/back.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 4, 50, 50));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Compose Email");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, -1));

        jLabel1.setBackground(new java.awt.Color(65, 131, 215));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 60));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

//SEND EMAIL
    private void send_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send_btnActionPerformed
        String to_add=to_field.getText();
        String from_add=from_field.getText();
        String Subject=subject_field.getText();
        String Text_content=message_field.getText();
        String bcc_add=BCC_field.getText();
        String cc_add=CC_field.getText();

        Properties props=new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", true); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", true);

        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from_add,new String(password_field.getPassword()));
                }
            }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from_add));
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to_add));
            message.setRecipients(javax.mail.Message.RecipientType.BCC, InternetAddress.parse(bcc_add));
            message.setRecipients(javax.mail.Message.RecipientType.CC, InternetAddress.parse(cc_add));

            //SET THE MESSAGE SUBJECT & CONTENT
            message.setSubject(Subject);
            message.setText(Text_content);

            //SET THE MESSAGE SUBJECT & CONTENT WITH ATTACHMENT IF IT HAS AN ATTACHEMENT
            if(attachment_path!=null)
            {
                MimeBodyPart messageBodyPart=new MimeBodyPart();
                messageBodyPart.setText(Text_content);
                Multipart multipart=new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                //ATTACH THE FILE
                messageBodyPart=new MimeBodyPart();
                javax.activation.DataSource source=new FileDataSource(attachment_path);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(file_name.getText());
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
            }

            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Message Sent.");
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_send_btnActionPerformed

    //CLEAR THE ALL FIELDS
    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        to_field.setText(null);
        CC_field.setText(null);
        BCC_field.setText(null);
        from_field.setText(null);
        password_field.setText(null);
        subject_field.setText(null);
        message_field.setText(null);
        file_name.setText(null);
        attachment_field.setText(null);
    }//GEN-LAST:event_clear_btnActionPerformed

    //ATTACHE FILE
    private void browse_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browse_btnActionPerformed
        JFileChooser attach_file=new JFileChooser();
        attach_file.showOpenDialog(null);
        File f=attach_file.getSelectedFile();
        attachment_path=f.getAbsolutePath();
        attachment_field.setText(attachment_path);
    }//GEN-LAST:event_browse_btnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Email_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Email_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Email_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Email_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Email_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BCC_field;
    private javax.swing.JTextField CC_field;
    private javax.swing.JTextField attachment_field;
    private javax.swing.JButton browse_btn;
    private javax.swing.JButton clear_btn;
    private javax.swing.JTextField file_name;
    private javax.swing.JTextField from_field;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea message_field;
    private javax.swing.JPasswordField password_field;
    private javax.swing.JButton send_btn;
    private javax.swing.JTextField subject_field;
    private javax.swing.JTextField to_field;
    // End of variables declaration//GEN-END:variables
 
//DECALRE A GLOBAL VARIALBE TO ATTACHEMNT PATH TO GET 
        String attachment_path;

}
