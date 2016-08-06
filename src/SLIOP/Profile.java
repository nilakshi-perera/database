package SLIOP;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

public class Profile extends javax.swing.JFrame {
             
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;

    public Profile() {
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
        
        update_staff_personal_table();
        update_lecturer_personal_table();
        Fill_course_list();
    }

       //IMPORT MYSQL TABLE DATA INTO NETBEANS TABLE
    private void update_staff_personal_table(){
      try{
            
                pst=conn.prepareStatement("SELECT idprofile as 'ID', name as 'Name',position as 'Position',"
                        + "status as 'Status', gender as 'Gender',dob as 'Date of Birth',nic as 'NIC', marital_status as 'Marital Status',"
                        + "mobile_number as 'Mobile',home_number as 'Home',email as 'Email', address as 'Address',qualifications as 'Qualifications' FROM profile");
                        
                
                rs=pst.executeQuery();
                staff_personal_table.setModel(DbUtils.resultSetToTableModel(rs));
            
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
      
      
      for (int column = 0; column < staff_personal_table.getColumnCount(); column++)
                {
                    TableColumn tableColumn = staff_personal_table.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();

                    for (int row = 0; row < staff_personal_table.getRowCount(); row++)
                    {
                        TableCellRenderer cellRenderer = staff_personal_table.getCellRenderer(row, column);
                        Component c = staff_personal_table.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + staff_personal_table.getIntercellSpacing().width;
                        
                        Object value = tableColumn.getHeaderValue();
                        TableCellRenderer renderer = tableColumn.getHeaderRenderer();

                        if (renderer == null)
                        {
                                renderer = staff_personal_table.getTableHeader().getDefaultRenderer();
                        }

                        Component d = renderer.getTableCellRendererComponent(staff_personal_table, value, false, false, -1, column);
                        int wid=d.getPreferredSize().width+ staff_personal_table.getIntercellSpacing().width;
                        
                          //CENTER THE JTABLE HEADER TEXT 
                        TableCellRenderer header = staff_personal_table.getTableHeader().getDefaultRenderer();
                        JLabel headerLabel = (JLabel) header;
                        headerLabel.setHorizontalAlignment(JLabel.CENTER);

                        //SET COLUMN TEXT BOLD
                        staff_personal_table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 11));
                        
                        preferredWidth = Math.max(preferredWidth,Math.max(wid, width));
   
                    }
 
                        tableColumn.setPreferredWidth( preferredWidth );
                }
        
    }//END OF UPDATE_TABLE METHOD
    
    //IMPORT MYSQL TABLE DATA INTO NETBEANS TABLE
    private void update_lecturer_personal_table(){
      try{
            
                pst=conn.prepareStatement("select lecturer_name as 'Lec. name',course.course_code as 'Course code' ,"
                        + "course.course_name as 'Course name',category_type as 'Course type'  from course "
                        + "INNER JOIN lecturer ON course.course_code=lecturer.course_code");
                        
                
                rs=pst.executeQuery();
                lec_course_table.setModel(DbUtils.resultSetToTableModel(rs));
            
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
      
      
      for (int column = 0; column < lec_course_table.getColumnCount(); column++)
                {
                    TableColumn tableColumn = lec_course_table.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();

                    for (int row = 0; row < lec_course_table.getRowCount(); row++)
                    {
                        TableCellRenderer cellRenderer = lec_course_table.getCellRenderer(row, column);
                        Component c = lec_course_table.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + lec_course_table.getIntercellSpacing().width;
                        
                        Object value = tableColumn.getHeaderValue();
                        TableCellRenderer renderer = tableColumn.getHeaderRenderer();

                        if (renderer == null)
                        {
                                renderer = lec_course_table.getTableHeader().getDefaultRenderer();
                        }

                        Component d = renderer.getTableCellRendererComponent(lec_course_table, value, false, false, -1, column);
                        int wid=d.getPreferredSize().width+ lec_course_table.getIntercellSpacing().width;
                        
                          //CENTER THE JTABLE HEADER TEXT 
                        TableCellRenderer header = lec_course_table.getTableHeader().getDefaultRenderer();
                        JLabel headerLabel = (JLabel) header;
                        headerLabel.setHorizontalAlignment(JLabel.CENTER);

                        //SET COLUMN TEXT BOLD
                        lec_course_table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 11));
                        
                        preferredWidth = Math.max(preferredWidth,Math.max(wid, width));
   
                    }
 
                        tableColumn.setPreferredWidth( preferredWidth );
                }
        
    }//END OF UPDATE_TABLE METHOD
    
    private void Fill_course_list(){
        try{
        
        String sql="select (course_code) from course";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            while(rs.next()){
                String cname=rs.getString("course_list");
                course_list.addItem(cname);
              
                }
        }
        catch(Exception e){
          //  JOptionPane.showMessageDialog(null, e);
          e.printStackTrace();
        }
    }
 
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        home = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        staff_personal_table = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        image_path = new javax.swing.JTextField();
        qual_2 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mobile_num = new javax.swing.JTextField();
        upload_image = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        university = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        martial_stat = new javax.swing.JComboBox<>();
        name = new javax.swing.JTextField();
        clear_staff = new javax.swing.JButton();
        multimedia = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        save_staff = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        dob = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        qual_3 = new javax.swing.JTextField();
        nic = new javax.swing.JTextField();
        graphics = new javax.swing.JCheckBox();
        printing = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        home_num = new javax.swing.JTextField();
        position = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        update_staff = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        title = new javax.swing.JComboBox<>();
        gender = new javax.swing.JComboBox<>();
        image_box = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        qual_1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lec_course_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        eve6 = new javax.swing.JCheckBox();
        eve3 = new javax.swing.JCheckBox();
        eve2 = new javax.swing.JCheckBox();
        day3 = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        course_list = new javax.swing.JComboBox<>();
        mor5 = new javax.swing.JCheckBox();
        category_type = new javax.swing.JComboBox<>();
        mor6 = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        eve1 = new javax.swing.JCheckBox();
        eve4 = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        mor3 = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        day = new javax.swing.JCheckBox();
        day4 = new javax.swing.JCheckBox();
        day6 = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        day1 = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        eve5 = new javax.swing.JCheckBox();
        day5 = new javax.swing.JCheckBox();
        mor1 = new javax.swing.JCheckBox();
        eve = new javax.swing.JCheckBox();
        mor = new javax.swing.JCheckBox();
        day2 = new javax.swing.JCheckBox();
        mor4 = new javax.swing.JCheckBox();
        jLabel29 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        mor2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Staff Management");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/help.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 60, 60));

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/shutdown_icon.png"))); // NOI18N
        logout.setContentAreaFilled(false);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 60, 60));

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/home-dash.png"))); // NOI18N
        home.setContentAreaFilled(false);
        home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        getContentPane().add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jLabel1.setBackground(new java.awt.Color(44, 62, 80));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 64));

        jTabbedPane1.setBackground(new java.awt.Color(255, 204, 204));

        jPanel4.setBackground(new java.awt.Color(222, 222, 245));

        staff_personal_table.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        staff_personal_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        staff_personal_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        staff_personal_table.setIntercellSpacing(new java.awt.Dimension(5, 1));
        staff_personal_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_personal_tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(staff_personal_table);

        jPanel5.setBackground(new java.awt.Color(222, 222, 245));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        image_path.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        qual_2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel28.setText("Email :");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Title :");

        upload_image.setText("Select Image");
        upload_image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upload_imageActionPerformed(evt);
            }
        });

        university.setBackground(new java.awt.Color(222, 222, 245));
        university.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        university.setText("University Degrees/Postgraduate/Undergraduate");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Status :");

        martial_stat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        martial_stat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Married", "Single" }));

        clear_staff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/clear icon.png"))); // NOI18N
        clear_staff.setText("  Clear");
        clear_staff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_staffActionPerformed(evt);
            }
        });

        multimedia.setBackground(new java.awt.Color(222, 222, 245));
        multimedia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        multimedia.setText("Multimedia");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Martial status :");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Gender :");

        save_staff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/save icon.png"))); // NOI18N
        save_staff.setText("  Save");
        save_staff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_staffActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel27.setText("Address :");

        dob.setDateFormatString("yyyy-MM-dd");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setText("Qualifications :");

        status.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Permanant staff", "Visiting staff" }));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("NIC :");

        qual_3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        graphics.setBackground(new java.awt.Color(222, 222, 245));
        graphics.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        graphics.setText("Graphics");
        graphics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphicsActionPerformed(evt);
            }
        });

        printing.setBackground(new java.awt.Color(222, 222, 245));
        printing.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        printing.setText("Printing");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Home number :");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Position :");

        position.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        position.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chairman", "CEO", "Director of Studies", "Board Member", "Academic Staff", "Non-Academic Staff" }));

        address.setColumns(20);
        address.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        address.setLineWrap(true);
        address.setRows(3);
        jScrollPane3.setViewportView(address);

        update_staff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/update icon.png"))); // NOI18N
        update_staff.setText("  Update");
        update_staff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_staffActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel26.setText("Mobile number :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Provide Details :");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Full name :");

        title.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        title.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mr.", "Mrs.", "Miss." }));

        gender.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        image_box.setBackground(new java.awt.Color(153, 153, 153));
        image_box.setOpaque(true);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("DOB");

        qual_1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(image_box, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(image_path, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(position, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nic, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel26)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(martial_stat, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(home_num, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mobile_num, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(graphics)
                            .addComponent(multimedia)
                            .addComponent(printing)
                            .addComponent(university)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(qual_1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(qual_2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(qual_3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(upload_image)
                        .addGap(287, 287, 287)
                        .addComponent(save_staff, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(update_staff, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(clear_staff, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(martial_stat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel27)))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(home_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mobile_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel22))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(graphics)
                                .addGap(2, 2, 2)
                                .addComponent(multimedia)
                                .addGap(0, 0, 0)
                                .addComponent(printing)
                                .addGap(0, 0, 0)
                                .addComponent(university)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel5)
                                .addGap(1, 1, 1)
                                .addComponent(qual_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(qual_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(qual_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9))
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(save_staff)
                            .addComponent(update_staff)
                            .addComponent(clear_staff)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(image_box, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(image_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(upload_image)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1017, Short.MAX_VALUE))
                            .addComponent(jScrollPane4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add staff details", jPanel4);

        jPanel1.setBackground(new java.awt.Color(222, 222, 245));

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/save icon.png"))); // NOI18N
        save.setText("  Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/update icon.png"))); // NOI18N
        jButton6.setText("  Update");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/clear icon.png"))); // NOI18N
        jButton7.setText("  Reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        lec_course_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(lec_course_table);

        jPanel2.setBackground(new java.awt.Color(222, 222, 245));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Academic Staff Scheduling ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        eve6.setBackground(new java.awt.Color(222, 222, 245));
        eve6.setText("Evening");

        eve3.setBackground(new java.awt.Color(222, 222, 245));
        eve3.setText("Evening");

        eve2.setBackground(new java.awt.Color(222, 222, 245));
        eve2.setText("Evening");

        day3.setBackground(new java.awt.Color(222, 222, 245));
        day3.setText("Daytime");

        jLabel24.setText("Saturday");

        mor5.setBackground(new java.awt.Color(222, 222, 245));
        mor5.setText("Morning");

        category_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theory", "Practical" }));

        mor6.setBackground(new java.awt.Color(222, 222, 245));
        mor6.setText("Morning");

        jLabel15.setText("Monday");

        eve1.setBackground(new java.awt.Color(222, 222, 245));
        eve1.setText("Evening");

        eve4.setBackground(new java.awt.Color(222, 222, 245));
        eve4.setText("Evening");

        jLabel19.setText("Thursday");

        jLabel25.setText("Sunday");

        mor3.setBackground(new java.awt.Color(222, 222, 245));
        mor3.setText("Morning");

        jLabel14.setText("Time table :");

        day.setBackground(new java.awt.Color(222, 222, 245));
        day.setText("Daytime");

        day4.setBackground(new java.awt.Color(222, 222, 245));
        day4.setText("Daytime");

        day6.setBackground(new java.awt.Color(222, 222, 245));
        day6.setText("Daytime");

        jLabel18.setText("Wednesday");

        day1.setBackground(new java.awt.Color(222, 222, 245));
        day1.setText("Daytime");

        jLabel30.setText("Type : ");

        eve5.setBackground(new java.awt.Color(222, 222, 245));
        eve5.setText("Evening");

        day5.setBackground(new java.awt.Color(222, 222, 245));
        day5.setText("Daytime");

        mor1.setBackground(new java.awt.Color(222, 222, 245));
        mor1.setText("Morning");

        eve.setBackground(new java.awt.Color(222, 222, 245));
        eve.setText("Evening");

        mor.setBackground(new java.awt.Color(222, 222, 245));
        mor.setText("Morning");

        day2.setBackground(new java.awt.Color(222, 222, 245));
        day2.setText("Daytime");

        mor4.setBackground(new java.awt.Color(222, 222, 245));
        mor4.setText("Morning");

        jLabel29.setText("Course :");

        jLabel16.setText("Tuesday");

        jLabel23.setText("Friday");

        mor2.setBackground(new java.awt.Color(222, 222, 245));
        mor2.setText("Morning");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel15))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(mor6)
                                .addGap(18, 18, 18)
                                .addComponent(day6)
                                .addGap(18, 18, 18)
                                .addComponent(eve6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(mor5)
                                .addGap(18, 18, 18)
                                .addComponent(day5)
                                .addGap(18, 18, 18)
                                .addComponent(eve5))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(mor4)
                                .addGap(18, 18, 18)
                                .addComponent(day4)
                                .addGap(18, 18, 18)
                                .addComponent(eve4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(mor3)
                                .addGap(18, 18, 18)
                                .addComponent(day3)
                                .addGap(18, 18, 18)
                                .addComponent(eve3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(mor2)
                                .addGap(18, 18, 18)
                                .addComponent(day2)
                                .addGap(18, 18, 18)
                                .addComponent(eve2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(mor1)
                                .addGap(18, 18, 18)
                                .addComponent(day1)
                                .addGap(18, 18, 18)
                                .addComponent(eve1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(mor)
                                .addGap(18, 18, 18)
                                .addComponent(day)
                                .addGap(18, 18, 18)
                                .addComponent(eve))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addGap(18, 18, 18)
                            .addComponent(category_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(course_list, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(course_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(category_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(mor)
                    .addComponent(day)
                    .addComponent(eve)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(mor1)
                    .addComponent(day1)
                    .addComponent(eve1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(mor2)
                    .addComponent(day2)
                    .addComponent(eve2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(mor3)
                    .addComponent(day3)
                    .addComponent(eve3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(mor4)
                    .addComponent(day4)
                    .addComponent(eve4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(mor5)
                    .addComponent(day5)
                    .addComponent(eve5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(mor6)
                    .addComponent(day6)
                    .addComponent(eve6)))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/printer icon.png"))); // NOI18N
        jButton1.setText("  Print");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(113, 113, 113))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Academic Staff Scheduling ", jPanel1);

        jTabbedPane1.addTab("Staff Profile Entry", jTabbedPane2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1270, 610));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 700));

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

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        dashboard_admin ad=new dashboard_admin();
        ad.setVisible(true);
        dispose();
    }//GEN-LAST:event_homeActionPerformed

    private void graphicsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphicsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_graphicsActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
      try{
        String sql="INSERT INTO lecturer(lecturer_name,course_code,category_type,monday,tuesday,wednesday,thursday,friday,saturday,sunday)values(?,?,?,?,?,?,?,?,?,?) ";

        pst=conn.prepareStatement(sql);
        
        if(position.getSelectedItem()=="Academic Staff"){
        String input_1=name.getText();
        pst.setString(1, input_1);
        }
        
        String input_2=course_list.getSelectedItem().toString();
        pst.setString(2, input_2);
        
        String input_3=category_type.getSelectedItem().toString();
        pst.setString(3, input_3);
        
         pst.execute();        

            JOptionPane.showMessageDialog(null, "Successfully Inserted");
        
       }
       
      catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
       
      update_staff_personal_table(); 
    }//GEN-LAST:event_saveActionPerformed

    private void save_staffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_staffActionPerformed
       try{
           String sql="insert into profile(title,position,name,status,gender,dob,nic,marital_status,address,home_number,"
                   + "mobile_number,email,qualifications,profile_image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
       
           pst=conn.prepareStatement(sql);
           
           String result1=title.getSelectedItem().toString();
           pst.setString(1, result1);

           String result2=position.getSelectedItem().toString();
           pst.setString(2, result2);
          
           pst.setString(3,name.getText());
          
           String result3=status.getSelectedItem().toString();
           pst.setString(4, result3);
           
           String result4=gender.getSelectedItem().toString();
           pst.setString(5, result4);
           
           pst.setString(6, ((JTextField)dob.getDateEditor().getUiComponent()).getText());
           
           pst.setString(7, nic.getText());
           
           String result5=martial_stat.getSelectedItem().toString();
           pst.setString(8, result5);
           
           pst.setString(11,mobile_num.getText());
           pst.setString(10,home_num.getText());
           pst.setString(12,email.getText());
           pst.setString(9, address.getText());
           
           String qualification="";
            if(graphics.isSelected()){
                 qualification+=graphics.getText()+",";
            }
            
            if(multimedia.isSelected()){
                if(graphics.isSelected()){
                qualification+=", ";
                }
                else{
                    qualification+="";
                }
                qualification+=multimedia.getText();
            }
            
            if(printing.isSelected()){
                if(graphics.isSelected()||multimedia.isSelected()){
                qualification+=", ";
                }
                else{
                    qualification+="";
                }
                qualification+=printing.getText();
            }
            
            if(university.isSelected()){
                if(graphics.isSelected()||multimedia.isSelected()||printing.isSelected()){
                qualification+=", ";
                }
                else{
                    qualification+="";
                }
                qualification+=qual_1.getText();
                qualification+=qual_2.getText();
                qualification+=qual_3.getText();
            }
           
            pst.setString(13, qualification);
            
            pst.setBytes(14,staff_member_image);
            
           pst.execute();
           JOptionPane.showMessageDialog(null, "Successfully Inserted.");
       }
       
       catch(Exception e){
          // JOptionPane.showMessageDialog(null, e.getMessage());
           e.printStackTrace();
       }
          update_lecturer_personal_table();
    }//GEN-LAST:event_save_staffActionPerformed

    
    private void upload_imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upload_imageActionPerformed
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        filename=f.getAbsolutePath();
        image_path.setText(filename);
        
        try{
            File image=new File(filename);
            FileInputStream fis=new FileInputStream(image);
            
            ByteArrayOutputStream bos= new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            
            for (int readNum; (readNum=fis.read(buf))!=-1;){
                bos.write(buf,0,readNum);
            }
            staff_member_image=bos.toByteArray();
        }
        
        catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_upload_imageActionPerformed

    private void staff_personal_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_personal_tableMouseClicked
        try{
              int row=staff_personal_table.getSelectedRow();
              
               String Table_click=(staff_personal_table.getModel().getValueAt(row, 0).toString());
                String sql="select * from profile where idprofile='"+Table_click+"' ";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
           
                if(rs.next()){
   
                String result1=rs.getString("title");
                title.setSelectedItem(result1);
                
                String result2=rs.getString("name");
                name.setText(result2);
                
                String result3=rs.getString("position");
                position.setSelectedItem(result3);
                
                String result4=rs.getString("status");
                status.setSelectedItem(result4);

                String result5=rs.getString("gender");
                gender.setSelectedItem(result5);
                
                String result6=rs.getString("dob"); 
                ((JTextField)dob.getDateEditor().getUiComponent()).setText(result6);
                
                String result7=rs.getString("nic");
                nic.setText(result7);
   
                String result8=rs.getString("marital_status");
                martial_stat.setSelectedItem(result8);
                
                String result9=rs.getString("address");
                address.setText(result9);
                
                String result10=rs.getString("home_number");
                home_num.setText(result10);
                
                String result11=rs.getString("mobile_number");
                mobile_num.setText(result11);
                
                String result12=rs.getString("email");
                email.setText(result12);

                byte[] imagedata=rs.getBytes("profile_image");  
                InputStream in = new ByteArrayInputStream(imagedata);                 
                BufferedImage bImageFromConvert = ImageIO.read(in);                  
                format=new ImageIcon(Scaled_upload_Image(bImageFromConvert,image_box.getWidth(),image_box.getHeight()));                  
                image_box.setIcon(format);
                 
                
                String val3=rs.getString("qualifications");
                if(val3.contains("Graphics")||val3.contains("Graphics,")){
                    graphics.setSelected(true);
                }
                else{
                    graphics.setSelected(false);
                }

                if(val3.contains("Multimedia")||val3.contains("Multimedia, ")){
                    multimedia.setSelected(true);
                }
                else{
                    multimedia.setSelected(false);
                }

                if(val3.contains("Printing")||val3.contains("Printing, ")){
                    printing.setSelected(true);
                }
                else{
                    printing.setSelected(false);
                }

                if(val3.contains("University Degrees/Postgraduate/Undergraduate")||val3.contains("University Degrees/Postgraduate/Undergraduate, ")){
                    university.setSelected(true);
                    
                    qual_1.setText(val3);
                    qual_2.setText(val3);
                    qual_3.setText(val3);
                }
                else{
                    university.setSelected(false);
                }
            }
        
        }
        catch(Exception e){
           // JOptionPane.showMessageDialog(null, e);
           e.printStackTrace();
        }
    }//GEN-LAST:event_staff_personal_tableMouseClicked

    private void clear_staffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_staffActionPerformed
        title.setSelectedIndex(0);
        position.setSelectedIndex(0);
        name.setText("");
        status.setSelectedIndex(0);
        gender.setSelectedIndex(0);
        dob.setCalendar(null);
        nic.setText("");
        martial_stat.setSelectedIndex(0);
        address.setText("");
        home_num.setText("");
        mobile_num.setText("");
        email.setText("");
        image_path.setText("");
        image_box.setIcon(null);
        graphics.setSelected(false);
        multimedia.setSelected(false);
        printing.setSelected(false);
        university.setSelected(false);
        qual_1.setText("");
        qual_2.setText("");
        qual_3.setText("");
    }//GEN-LAST:event_clear_staffActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void update_staffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_staffActionPerformed
    try{ 
            String add1=title.getSelectedItem().toString();
            String add2=position.getSelectedItem().toString();
            String add3=name.getText();
            String add4=status.getSelectedItem().toString();
            String add5=gender.getSelectedItem().toString();
            String add6=((JTextField)dob.getDateEditor().getUiComponent()).getText();
            String add7=nic.getText();
            String add8=martial_stat.getSelectedItem().toString();
            String add9=address.getText();
            String add10=home_num.getText();
            String add11=mobile_num.getText();
            String add12=email.getText();
           

            String sql="UPDATE profile SET title=? , position=? ,name=? " +
                    ",status=? ,gender=?, dob=? ,nic=? "+
                    " ,marital_status=? ,address=? , home_number=? , mobile_number=? "+
                    " ,email= ?,profile_image=? where name='"+add3+" '";

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
            pst.setBytes(13,staff_member_image);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully updated.");
           
        }
        catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
       update_staff_personal_table();
    }//GEN-LAST:event_update_staffActionPerformed

    
     private Image Scaled_upload_Image(Image img, int w, int h){
            BufferedImage resizedImage=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
            Graphics2D g2=resizedImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img,0,0,w,h,null);
            g2.dispose();
            return resizedImage;
    }
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
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea address;
    private javax.swing.JComboBox<String> category_type;
    private javax.swing.JButton clear_staff;
    private javax.swing.JComboBox<String> course_list;
    private javax.swing.JCheckBox day;
    private javax.swing.JCheckBox day1;
    private javax.swing.JCheckBox day2;
    private javax.swing.JCheckBox day3;
    private javax.swing.JCheckBox day4;
    private javax.swing.JCheckBox day5;
    private javax.swing.JCheckBox day6;
    private com.toedter.calendar.JDateChooser dob;
    private javax.swing.JTextField email;
    private javax.swing.JCheckBox eve;
    private javax.swing.JCheckBox eve1;
    private javax.swing.JCheckBox eve2;
    private javax.swing.JCheckBox eve3;
    private javax.swing.JCheckBox eve4;
    private javax.swing.JCheckBox eve5;
    private javax.swing.JCheckBox eve6;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JCheckBox graphics;
    private javax.swing.JButton home;
    private javax.swing.JTextField home_num;
    private javax.swing.JLabel image_box;
    private javax.swing.JTextField image_path;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable lec_course_table;
    private javax.swing.JButton logout;
    private javax.swing.JComboBox<String> martial_stat;
    private javax.swing.JTextField mobile_num;
    private javax.swing.JCheckBox mor;
    private javax.swing.JCheckBox mor1;
    private javax.swing.JCheckBox mor2;
    private javax.swing.JCheckBox mor3;
    private javax.swing.JCheckBox mor4;
    private javax.swing.JCheckBox mor5;
    private javax.swing.JCheckBox mor6;
    private javax.swing.JCheckBox multimedia;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nic;
    private javax.swing.JComboBox<String> position;
    private javax.swing.JCheckBox printing;
    private javax.swing.JTextField qual_1;
    private javax.swing.JTextField qual_2;
    private javax.swing.JTextField qual_3;
    private javax.swing.JButton save;
    private javax.swing.JButton save_staff;
    private javax.swing.JTable staff_personal_table;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JComboBox<String> title;
    private javax.swing.JCheckBox university;
    private javax.swing.JButton update_staff;
    private javax.swing.JButton upload_image;
    // End of variables declaration//GEN-END:variables
    private ImageIcon format=null;
    String filename=null;
    int s=0;
    byte[] staff_member_image=null;
}
