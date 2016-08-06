 package SLIOP;

import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

public class Course extends javax.swing.JFrame {
   

    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;

    
    public Course() {
        initComponents();
            
        //SET THE ICON IN TITLE BAR
        this.setIconImage(new ImageIcon(getClass().getResource("Images/logo.png")).getImage());
            
        try{
            //GET THE CONNECTION TO THE DATABASE
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sliop_db","root","kalpana"); 
        }
         catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }   
        generate_database();
        update_course_table();
    }

    private void generate_database(){
        try{
        //GENERATE DATABASE DATA INTO THE COURSE TABLE
            String sql="SELECT * FROM course";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            course_table.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 //TABLE PROPERTY HANDLING
 private void update_course_table(){

             //SET THE TABLE COLUMN NAMES
             //SET THE TABLE COULMN AND ROW WIDTH
            String head_name[] = {"ID", "Course code", "Catergory", "Course type", "Course name", "Medium", "Duration","Start Date","Fee","Groups","Maximum Participants","Total Students","Attachement","Requirements"};
            
            for (int column = 0; column < course_table.getColumnCount(); column++)
            {
                TableColumn column1 = course_table.getTableHeader().getColumnModel().getColumn(column);
                column1.setHeaderValue(head_name[column]);
                TableColumn tableColumn = course_table.getColumnModel().getColumn(column);
                int preferredWidth = tableColumn.getMinWidth();
                
                for (int row = 0; row < course_table.getRowCount(); row++)
                {
                    TableCellRenderer cellRenderer = course_table.getCellRenderer(row, column);
                    Component c = course_table.prepareRenderer(cellRenderer, row, column);
                    int width = c.getPreferredSize().width + course_table.getIntercellSpacing().width;
                    
                    Object value = tableColumn.getHeaderValue();
                    TableCellRenderer renderer = tableColumn.getHeaderRenderer();
                    
                    if (renderer == null)
                    {
                        renderer = course_table.getTableHeader().getDefaultRenderer();
                    }
                    
                    Component d = renderer.getTableCellRendererComponent(course_table, value, false, false, -1, column);
                    int wid=d.getPreferredSize().width+ course_table.getIntercellSpacing().width;
                    
                    //CENTER THE JTABLE HEADER TEXT
                    TableCellRenderer header = course_table.getTableHeader().getDefaultRenderer();
                    JLabel headerLabel = (JLabel) header;
                    headerLabel.setHorizontalAlignment(JLabel.CENTER);
                    
                    //SET COLUMN TEXT BOLD
                    course_table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 11));
                    
                    preferredWidth = Math.max(preferredWidth,Math.max(wid, width));
                }
                tableColumn.setPreferredWidth( preferredWidth );
                
            }
            //  course_table.setSelectionBackground(new Color(149,165,166)); 
  }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        logout = new javax.swing.JButton();
        dash_home = new javax.swing.JButton();
        help = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        course_table = new javax.swing.JTable();
        add = new javax.swing.JButton();
        clear_fields = new javax.swing.JButton();
        update_fields = new javax.swing.JButton();
        course_cat = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        req_1 = new javax.swing.JTextField();
        req_2 = new javax.swing.JTextField();
        other = new javax.swing.JCheckBox();
        gp = new javax.swing.JCheckBox();
        cp = new javax.swing.JCheckBox();
        ek = new javax.swing.JCheckBox();
        al = new javax.swing.JCheckBox();
        ol = new javax.swing.JCheckBox();
        total_students = new javax.swing.JTextField();
        max_students = new javax.swing.JTextField();
        no_groups = new javax.swing.JTextField();
        fee = new javax.swing.JTextField();
        sd = new com.toedter.calendar.JDateChooser();
        duration = new javax.swing.JTextField();
        medium = new javax.swing.JComboBox<>();
        course_name = new javax.swing.JTextField();
        course_type = new javax.swing.JComboBox<>();
        code_course = new javax.swing.JTextField();
        course_catergory = new javax.swing.JComboBox<>();
        batch_setting = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        duration_time = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        file_attach = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        view_list = new javax.swing.JComboBox<>();
        view_courses = new javax.swing.JButton();
        attach = new javax.swing.JButton();
        open_file = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Course Information");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/shutdown_icon.png"))); // NOI18N
        logout.setToolTipText("Login");
        logout.setContentAreaFilled(false);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1241, 0, 60, 60));

        dash_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/home-dash.png"))); // NOI18N
        dash_home.setToolTipText("Dashboard");
        dash_home.setContentAreaFilled(false);
        dash_home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dash_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dash_homeActionPerformed(evt);
            }
        });
        getContentPane().add(dash_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/help.png"))); // NOI18N
        help.setContentAreaFilled(false);
        getContentPane().add(help, new org.netbeans.lib.awtextra.AbsoluteConstraints(1175, 0, 60, 60));

        course_table.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        course_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        course_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        course_table.setIntercellSpacing(new java.awt.Dimension(5, 1));
        course_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                course_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(course_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1281, 330));

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/save icon.png"))); // NOI18N
        add.setText("   Save");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 653, 115, 36));
        add.getAccessibleContext().setAccessibleDescription("");

        clear_fields.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/clear icon.png"))); // NOI18N
        clear_fields.setText("  Clear");
        clear_fields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_fieldsActionPerformed(evt);
            }
        });
        getContentPane().add(clear_fields, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 653, 115, 36));

        update_fields.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/update icon.png"))); // NOI18N
        update_fields.setText("Update");
        update_fields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_fieldsActionPerformed(evt);
            }
        });
        getContentPane().add(update_fields, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 653, 115, 36));

        course_cat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        course_cat.setText("Course Catergory");
        getContentPane().add(course_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 475, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Course Code");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 502, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Course Type");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 529, 86, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Course Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 558, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Medium");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 585, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Duration");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 612, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Start Date");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 475, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Fee (Rs.)");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 502, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Number of Groups");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 529, -1, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Max: Participants");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 558, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Total No of Students");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 585, 118, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Requirments :");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(867, 473, -1, -1));

        req_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(req_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(985, 637, 254, -1));

        req_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(req_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(985, 609, 254, -1));

        other.setBackground(new java.awt.Color(222, 222, 245));
        other.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        other.setText("Other (Provide details on other Requirments) ");
        getContentPane().add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 585, -1, -1));

        gp.setBackground(new java.awt.Color(222, 222, 245));
        gp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gp.setText("Should complete Graphic/Printing Certificate Courses");
        getContentPane().add(gp, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 562, -1, -1));

        cp.setBackground(new java.awt.Color(222, 222, 245));
        cp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cp.setText("Basic Computer Practical Practice");
        getContentPane().add(cp, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 539, -1, -1));

        ek.setBackground(new java.awt.Color(222, 222, 245));
        ek.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ek.setText("English Knowledge");
        getContentPane().add(ek, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 516, -1, -1));

        al.setBackground(new java.awt.Color(222, 222, 245));
        al.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        al.setText("Advanced Level");
        getContentPane().add(al, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 492, -1, -1));

        ol.setBackground(new java.awt.Color(222, 222, 245));
        ol.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ol.setText("Ordinary Level");
        getContentPane().add(ol, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 469, -1, -1));

        total_students.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                total_studentsMousePressed(evt);
            }
        });
        getContentPane().add(total_students, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 583, 94, -1));
        getContentPane().add(max_students, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 556, 94, -1));

        no_groups.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(no_groups, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 526, 94, -1));

        fee.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(fee, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 499, 94, -1));

        sd.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(sd, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 472, 159, -1));

        duration.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 609, 77, -1));

        medium.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        medium.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Sinhala", "Sinhala & English" }));
        getContentPane().add(medium, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 582, 159, -1));

        course_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(course_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 555, 219, -1));

        course_type.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        course_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Certificate", "Diploma", "Advanced Diploma", "MBA" }));
        getContentPane().add(course_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 526, 159, -1));

        code_course.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(code_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 499, 159, -1));

        course_catergory.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        course_catergory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Printing", "Multimedia", "Computer" }));
        getContentPane().add(course_catergory, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 472, 158, -1));

        batch_setting.setText("Batch Settings");
        batch_setting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batch_settingActionPerformed(evt);
            }
        });
        getContentPane().add(batch_setting, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 526, 112, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("in a group");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 558, -1, -1));

        duration_time.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        duration_time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Weeks", "Months", "Years" }));
        getContentPane().add(duration_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 609, 72, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Attachement File");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 612, -1, -1));
        getContentPane().add(file_attach, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 610, 140, -1));

        jButton1.setText("View in PDF");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 653, 115, 36));

        jLabel4.setBackground(new java.awt.Color(44, 62, 80));
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 64));

        view_list.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Certificate", "Diploma", "Advanced Diploma", "Printing", "Multimedia", "Computer" }));
        getContentPane().add(view_list, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 89, 160, -1));

        view_courses.setText("View");
        view_courses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_coursesActionPerformed(evt);
            }
        });
        getContentPane().add(view_courses, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 88, -1, -1));

        attach.setText("Attach");
        attach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attachActionPerformed(evt);
            }
        });
        getContentPane().add(attach, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 609, 92, -1));

        open_file.setText("Open");
        open_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open_fileActionPerformed(evt);
            }
        });
        getContentPane().add(open_file, new org.netbeans.lib.awtextra.AbsoluteConstraints(766, 609, 91, -1));

        jLabel8.setBackground(new java.awt.Color(222, 222, 245));
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

//LOGOUT FROM THE JFRAME
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

//GO TO THE DASHBOARD ADMIN
    private void dash_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dash_homeActionPerformed
        dashboard_admin admin=new dashboard_admin();
        admin.setVisible(true);
        
        dispose();    
    }//GEN-LAST:event_dash_homeActionPerformed

    private void batch_settingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batch_settingActionPerformed
        Batch_settings batch_set=new Batch_settings(new javax.swing.JFrame(), true);
        batch_set.setVisible(true);
    }//GEN-LAST:event_batch_settingActionPerformed

//FILL THE FIELDS WHEN TABLE CLICK
    private void course_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_tableMouseClicked
        try{
            int row=course_table.getSelectedRow();
            String Table_click=(course_table.getModel().getValueAt(row, 0).toString());
            String sql="select * from course where idcourse='"+Table_click+"' ";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();

            if(rs.next()){

                String val1=rs.getString("duration");
                String ino= val1.substring(0,2);
                duration.setText(ino);

                String val2=rs.getString("start_date");
                ((JTextField)sd.getDateEditor().getUiComponent()).setText(val2);

                String val3=rs.getString("stu_requirement");
                if(val3.contains("Ordinary Level")||val3.contains("Ordinary Level,")){
                    ol.setSelected(true);
                }
                else{
                    ol.setSelected(false);
                }

                if(val3.contains("Advanced Level")||val3.contains("Advanced Level, ")){
                    al.setSelected(true);
                }
                else{
                    al.setSelected(false);
                }

                if(val3.contains("English Knowledge")||val3.contains("English Knowledge, ")){
                    ek.setSelected(true);
                }
                else{
                    ek.setSelected(false);
                }

                if(val3.contains("Basic Computer Practical Practice")||val3.contains("Basic Computer Practical Practice, ")){
                    cp.setSelected(true);
                }
                else{
                    cp.setSelected(false);
                }

                if(val3.contains("Should complete Graphic/Printing Certificate Courses")||val3.contains("Should complete Graphic/Printing Certificate Courses, ")){
                    gp.setSelected(true);
                }
                else{
                    gp.setSelected(false);
                }

            }
            
            
            code_course.setText(course_table.getModel().getValueAt(row, 1).toString());
            course_catergory.setSelectedItem(course_table.getModel().getValueAt(row, 2).toString());
            course_type.setSelectedItem(course_table.getModel().getValueAt(row, 3).toString());
            course_name.setText(course_table.getModel().getValueAt(row, 4).toString());
            medium.setSelectedItem(course_table.getModel().getValueAt(row, 5).toString());

            // duration_time.setSelectedItem(course_table.getModel().getValueAt(row, 6).toString());

            fee.setText(course_table.getModel().getValueAt(row, 8).toString());
            no_groups.setText(course_table.getModel().getValueAt(row, 9).toString());
            max_students.setText(course_table.getModel().getValueAt(row, 10).toString());
            total_students.setText(course_table.getModel().getValueAt(row, 11).toString());
            file_attach.setText(course_table.getModel().getValueAt(row, 12).toString());
               
        }

        catch (Exception e) {
             JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_course_tableMouseClicked

//UPDATE RECORDS IN COURSE SETTING TAB
    private void update_fieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_fieldsActionPerformed

        try{ 
            String add1=course_catergory.getSelectedItem().toString();
            String add2=code_course.getText();
            String add3=course_type.getSelectedItem().toString();
            String add4=course_name.getText();
            String add5=medium.getSelectedItem().toString();
            String add6=duration.getText()+" "+duration_time.getSelectedItem().toString();
            String add7=((JTextField)sd.getDateEditor().getUiComponent()).getText();
            String add8=fee.getText();
            String add9=no_groups.getText();
            String add10=max_students.getText();
            String add11=total_students.getText();
            String add12=file_attach.getText();

            String sql="UPDATE course SET category=? , course_code=? ,course_type=? " +
                    ",course_name=? ,medium=?, duration=? ,start_date=? "+
                    " ,fee=? ,no_groups=? , max_participants=? , total_students=? "+
                    " ,attach_file= ? where  course_code='"+add2+" '";

            pst=conn.prepareStatement(sql);
            
            pst.setString(1, add1);
            pst.setString(2, add2);
            pst.setString(3, add3);
            pst.setString(4, add4);
            pst.setString(5, add5);
            pst.setString(6, add6);
            pst.setString(7, add7);
            pst.setString(8, add8);
            pst.setString(9, add9);
            pst.setString(10, add10);
            pst.setString(11, add11);
            pst.setString(12, add12);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully updated.");
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       
        generate_database();
        update_course_table();
    }//GEN-LAST:event_update_fieldsActionPerformed

//SAVE RECORDS IN COURSE SETTING TAB
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        try{
            String sql="insert into course(category,course_code,course_type,course_name,medium,duration,start_date,fee,no_groups,max_participants,total_students,attach_file,stu_requirement) value(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            pst=conn.prepareStatement(sql);

            String ss=course_catergory.getSelectedItem().toString();
            pst.setString(1, ss);

            pst.setString(2, code_course.getText());

            String value1=course_type.getSelectedItem().toString();
            pst.setString(3, value1);

            pst.setString(4, course_name.getText());

            String value2=medium.getSelectedItem().toString();
            pst.setString(5, value2);

            pst.setString(6, duration.getText()+" "+duration_time.getSelectedItem().toString());

            pst.setString(7, ((JTextField)sd.getDateEditor().getUiComponent()).getText());

            pst.setString(8, fee.getText());

            pst.setString(9, no_groups.getText());

            pst.setString(10,max_students.getText());
            
            pst.setString(11, total_students.getText());
            
            pst.setString(12, file_attach.getText());

            String srequirement="";
            if(ol.isSelected()){
                srequirement+=ol.getText();
            }

            if(al.isSelected()){
                if(ol.isSelected()){
                    srequirement+=", ";
                }
                else{
                    srequirement+="";
                }
                srequirement+=al.getText();
            }

            if(ek.isSelected()){
                if(ol.isSelected()||al.isSelected()){
                    srequirement+=", ";
                }
                else{
                    srequirement+="";
                }
                srequirement+=ek.getText();
            }
            if(cp.isSelected()){
                if(ol.isSelected()||al.isSelected()||ek.isSelected()){
                    srequirement+=", ";
                }
                else{
                    srequirement+="";
                }
                srequirement+=cp.getText();
            }
            if(gp.isSelected()){
                if(ol.isSelected()||al.isSelected()||ek.isSelected()||cp.isSelected()){
                    srequirement+=", ";
                }
                else{
                    srequirement+="";
                }
                srequirement+=gp.getText();
            }
            if(other.isSelected()){
                if(ol.isSelected()||al.isSelected()||ek.isSelected()||cp.isSelected()||gp.isSelected()){
                    srequirement+=", ";
                }
                else{
                    srequirement+="";
                }
                srequirement+=req_1.getText();
                srequirement+=req_2.getText();
            }
            pst.setString(13,srequirement);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully Inserted");
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
        }
        generate_database();
        update_course_table();
    }//GEN-LAST:event_addActionPerformed

    private void view_coursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_coursesActionPerformed
            String item=(String)view_list.getSelectedItem();
             try{                  
                if("All".equals(item)){
               
                    String sql1="SELECT * FROM course";
                    pst=conn.prepareStatement(sql1);
               
                } 
                if("Certificate".equals(item)){
               
                    String sql1="SELECT * FROM course WHERE course_type='Certificate'";
                    pst=conn.prepareStatement(sql1);
                  
                } 
                if("Diploma".equals(item)){
               
                    String sql1="SELECT * FROM course WHERE course_type='Diploma'";
                    pst=conn.prepareStatement(sql1);
               
                } 
                if("Advanced Diploma".equals(item)){
               
                    String sql1="SELECT * FROM course WHERE course_type='Advanced Diploma'";
                    pst=conn.prepareStatement(sql1);
             
                } 
                if("Printing".equals(item)){
               
                    String sql1="SELECT * FROM course WHERE category='Printing'";
                    pst=conn.prepareStatement(sql1);
               
                } if("Multimedia".equals(item)){
               
                    String sql1="SELECT * FROM course WHERE category='Multimedia'";
                    pst=conn.prepareStatement(sql1);
                   
                } if("Computer".equals(item)){
               
                    String sql1="SELECT * FROM course WHERE category='Computer'";
                    pst=conn.prepareStatement(sql1);
                  
                }    
                rs=pst.executeQuery();
                course_table.setModel(DbUtils.resultSetToTableModel(rs));
             }   
            catch (SQLException ex) {
                    Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);               
            }
             
        update_course_table();

    }//GEN-LAST:event_view_coursesActionPerformed

//CLEAR FIELDS IN COURSE SETTING TAB
    private void clear_fieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_fieldsActionPerformed
        course_catergory.setSelectedIndex(0); //RESET JCOMBOBOX VALUE TO FIRST VALUE AGAIN
        code_course.setText("");
        course_type.setSelectedIndex(0);
        course_name.setText("");
        medium.setSelectedIndex(0);
        duration.setText("");
        duration_time.setSelectedIndex(0);
        sd.setCalendar(null); //RESET JCALENDAR VALUE
        fee.setText("");
        no_groups.setText("");
        max_students.setText("");
        total_students.setText("");
        file_attach.setText("");
        ol.setSelected(false); //RESET CHECKBOX VALUE INTO FIRST VALUE AGAIN
        al.setSelected(false);
        ek.setSelected(false);
        cp.setSelected(false);
        gp.setSelected(false);
        other.setSelected(false);
        req_1.setText("");
        req_2.setText("");
        view_list.setSelectedIndex(0);
    }//GEN-LAST:event_clear_fieldsActionPerformed

    private void attachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attachActionPerformed
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String file=f.getAbsolutePath();
        file_attach.setText(file);
        
    }//GEN-LAST:event_attachActionPerformed

    private void open_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_open_fileActionPerformed
        String f=file_attach.getText();
        try{ 
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+f);
        } 
        
        catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_open_fileActionPerformed

    private void total_studentsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_total_studentsMousePressed
            int x=Integer.parseInt(no_groups.getText());
            int y=Integer.parseInt(max_students.getText());
            int z=x*y;
            total_students.setText(Integer.toString(z));
    }//GEN-LAST:event_total_studentsMousePressed


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
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Course().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JCheckBox al;
    private javax.swing.JButton attach;
    private javax.swing.JButton batch_setting;
    private javax.swing.JButton clear_fields;
    private javax.swing.JTextField code_course;
    private javax.swing.JLabel course_cat;
    private javax.swing.JComboBox<String> course_catergory;
    private javax.swing.JTextField course_name;
    private javax.swing.JTable course_table;
    private javax.swing.JComboBox<String> course_type;
    private javax.swing.JCheckBox cp;
    private javax.swing.JButton dash_home;
    private javax.swing.JTextField duration;
    private javax.swing.JComboBox<String> duration_time;
    private javax.swing.JCheckBox ek;
    private javax.swing.JTextField fee;
    private javax.swing.JTextField file_attach;
    private javax.swing.JCheckBox gp;
    private javax.swing.JButton help;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout;
    private javax.swing.JTextField max_students;
    private javax.swing.JComboBox<String> medium;
    private javax.swing.JTextField no_groups;
    private javax.swing.JCheckBox ol;
    private javax.swing.JButton open_file;
    private javax.swing.JCheckBox other;
    private javax.swing.JTextField req_1;
    private javax.swing.JTextField req_2;
    private com.toedter.calendar.JDateChooser sd;
    private javax.swing.JTextField total_students;
    private javax.swing.JButton update_fields;
    private javax.swing.JButton view_courses;
    private javax.swing.JComboBox<String> view_list;
    // End of variables declaration//GEN-END:variables
   
}
