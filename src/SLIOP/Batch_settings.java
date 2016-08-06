package SLIOP;

import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

public class Batch_settings extends javax.swing.JDialog {
    
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;

    public Batch_settings(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        FillComboItems();
        update_batch_table();
        update_time_table();
    }

    private void update_time_table(){
        //SET THE TABLE COULMN AND ROW WIDTH    
            for (int column = 0; column < time_table.getColumnCount(); column++)
                {
                    TableColumn tableColumn = time_table.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();

                    for (int row = 0; row < time_table.getRowCount(); row++)
                    {
                        TableCellRenderer cellRenderer = time_table.getCellRenderer(row, column);
                        Component c = time_table.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + time_table.getIntercellSpacing().width;
                        
                        Object value = tableColumn.getHeaderValue();
                        TableCellRenderer renderer = tableColumn.getHeaderRenderer();

                        if (renderer == null)
                        {
                                renderer = time_table.getTableHeader().getDefaultRenderer();
                        }

                        Component d = renderer.getTableCellRendererComponent(time_table, value, false, false, -1, column);
                        int wid=d.getPreferredSize().width+ time_table.getIntercellSpacing().width;
                        
                       //CENTER THE JTABLE HEADER TEXT
                        TableCellRenderer header = time_table.getTableHeader().getDefaultRenderer();
                        JLabel headerLabel = (JLabel) header;
                        headerLabel.setHorizontalAlignment(JLabel.CENTER);
                        
                        preferredWidth = Math.max(preferredWidth,Math.max(wid, width));
   
                    }
 
                        tableColumn.setPreferredWidth( preferredWidth );
                }
    
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        course_code = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        year = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        batch_table = new javax.swing.JTable();
        save = new javax.swing.JButton();
        update = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        group_no = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        time_chooser = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        time_table = new javax.swing.JTable();
        time_course = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Batch Settings");

        jLabel19.setText("Course Code");

        jLabel17.setText("Batch code");

        batch_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        batch_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        batch_table.setIntercellSpacing(new java.awt.Dimension(5, 1));
        jScrollPane1.setViewportView(batch_table);

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/save icon.png"))); // NOI18N
        save.setText(" Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/update icon.png"))); // NOI18N
        update.setText(" Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/clear icon.png"))); // NOI18N
        reset.setText(" Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        jLabel1.setText("Year");

        jLabel2.setText("Group No");

        time_chooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Weekday", "Weekend" }));

        jLabel3.setText("Batch Time Select");

        jLabel6.setText("Scheduling ");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/back.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(0, 102, 204));
        jLabel4.setOpaque(true);

        time_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"08.30 am - 01.30 pm", null, null, null, null, null, null, null},
                {"09.00 am - 12.00 pm", null, null, null, null, null, null, null},
                {"09.00 am - 03.00 pm", null, null, null, null, null, null, null},
                {"01.00 pm - 04.00 pm", null, null, null, null, null, null, null},
                {"01.30 pm - 06.30 pm", null, null, null, null, null, null, null},
                {"05.00 pm - 08.00 pm", null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(time_table);

        jLabel7.setText("Time");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel6))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(course_code, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(time_course, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jLabel19)
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7)
                                        .addGap(25, 25, 25)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(group_no, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(time_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel7)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel17))
                    .addComponent(course_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(time_course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(group_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(time_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(update)
                        .addComponent(reset))
                    .addComponent(save))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

 private void update_batch_table(){
 
     //IMPORT MYSQL TABLE DATA INTO NETBEANS TABLE
            try{
                pst=conn.prepareStatement("select course.course_name as 'Course Name',course.course_code as 'Course code', "
                        + "batch.batch_number as 'Batch Code', batch.time as 'Time',batch.monday as 'Monday',"
                        + "batch.tuesday as 'Tuesday',batch.wednesday as 'Wednesday',batch.thursday as 'Thursday',"
                        + "batch.friday as 'Friday',batch.Saturday as 'Saturday',"
                        + "batch.Sunday as 'Sunday'from batch INNER JOIN course ON course.course_code=batch.course_code"); 
                
                rs=pst.executeQuery();
                batch_table.setModel(DbUtils.resultSetToTableModel(rs));
            
                }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                }    
            
        //SET THE TABLE COULMN AND ROW WIDTH    
            for (int column = 0; column < batch_table.getColumnCount(); column++)
                {
                    TableColumn tableColumn = batch_table.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();

                    for (int row = 0; row < batch_table.getRowCount(); row++)
                    {
                        TableCellRenderer cellRenderer = batch_table.getCellRenderer(row, column);
                        Component c = batch_table.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + batch_table.getIntercellSpacing().width;
                        
                        Object value = tableColumn.getHeaderValue();
                        TableCellRenderer renderer = tableColumn.getHeaderRenderer();

                        if (renderer == null)
                        {
                                renderer = batch_table.getTableHeader().getDefaultRenderer();
                        }

                        Component d = renderer.getTableCellRendererComponent(batch_table, value, false, false, -1, column);
                        int wid=d.getPreferredSize().width+ batch_table.getIntercellSpacing().width;
                        
                        //CENTER THE JTABLE HEADER TEXT
                        TableCellRenderer header = batch_table.getTableHeader().getDefaultRenderer();
                        JLabel headerLabel = (JLabel) header;
                        headerLabel.setHorizontalAlignment(JLabel.CENTER);
                    
                        //SET COLUMN TEXT BOLD
                        batch_table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 11));
                        
                        preferredWidth = Math.max(preferredWidth,Math.max(wid, width));
   
                    }
 
                        tableColumn.setPreferredWidth( preferredWidth );
                }
 
 }
   
    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        course_code.setSelectedIndex(0);
        year.setText("");
        group_no.setText("");
        time_chooser.setSelectedIndex(0);
    }//GEN-LAST:event_resetActionPerformed

    //GENERATE COURSE CODE INTO JCOMBOBOX FROM THE DATABASE
    private void FillComboItems(){
        try{
            String sql="select * from course";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            while(rs.next()){
                String course_no=rs.getString("course_code");
                course_code.addItem(course_no);
            }     
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
       try{
            for(int i = 0 ; i < time_table.getModel().getRowCount() ; i++)
            {
                for(int j = 1 ; j < time_table.getModel().getColumnCount() ; j++)
                {
                    String sql="insert into batch (course_code,batch_number,time,"+ time_table.getColumnName(j).toLowerCase()+")values(?,?,?,?)";

                    pst=conn.prepareStatement(sql);

                    String value1=course_code.getSelectedItem().toString();
                    pst.setString(1,value1);

                    String value2=course_code.getSelectedItem().toString();
                    pst.setString(2, value2+"-"+year.getText()+"-"+group_no.getText());

                    String value3=time_chooser.getSelectedItem().toString();
                    pst.setString(3, value3);

                    String c=time_table.getModel().getValueAt(i, 0).toString();
                    Boolean val1 = (Boolean)time_table.getModel().getValueAt(i, j);
                    System.out.println(val1 + "\t");
                    if(Boolean.TRUE.equals(val1)) {
                        pst.setString(4, c );
                        pst.executeUpdate();
                        pst.close();
                        JOptionPane.showMessageDialog(null, "Successfully Inserted");
                    }
                    System.out.println();
                }
            }
       }
       catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
                }
        
        update_batch_table();
    }//GEN-LAST:event_saveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Settings set=new Settings();
        set.setVisible(true);
        
        this.dispose();
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
            java.util.logging.Logger.getLogger(Batch_settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Batch_settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Batch_settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Batch_settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Batch_settings dialog = new Batch_settings(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable batch_table;
    private javax.swing.JComboBox<String> course_code;
    private javax.swing.JTextField group_no;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton reset;
    private javax.swing.JButton save;
    private javax.swing.JComboBox<String> time_chooser;
    private javax.swing.JTextField time_course;
    private javax.swing.JTable time_table;
    private javax.swing.JButton update;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables
}
