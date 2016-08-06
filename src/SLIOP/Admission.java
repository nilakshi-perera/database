package SLIOP;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import static com.itextpdf.text.Image.MIDDLE;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import net.proteanit.sql.DbUtils;


public class Admission extends javax.swing.JFrame {
             
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;

    public Admission() {
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

        //SET THE TABLE UPDATE AT THE BEGINNING OF THE INTERFACE
        update_table();     
        //FillBatch_NoComboBox();
        update_education_table();
        
    }

      
    private void update_table(){
        
        //IMPORT MYSQL TABLE DATA INTO NETBEANS TABLE
            try{
                pst=conn.prepareStatement("select idstudent as 'ID', student_code as 'Student code',batch_number as 'Batch',surname as 'Surname',initials as 'Initials',full_name as 'Full name',gender as 'Gender',nic as 'NIC',dob as 'Date of Birth',contact_type as 'Contact Type',"
                        + "email as 'Email',home_number as 'Home Number',"
                    + "mobile_number as 'Mobile',address as 'Address',pg_name as 'Parent/Guardian Name',pg_contact_number as 'Parent/Guardian number' from student");
                
                rs=pst.executeQuery();
                student_table.setModel(DbUtils.resultSetToTableModel(rs));
            
                }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                }    
            
        //SET THE TABLE COULMN AND ROW WIDTH    
            for (int column = 0; column < student_table.getColumnCount(); column++)
                {
                    TableColumn tableColumn = student_table.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();

                    for (int row = 0; row < student_table.getRowCount(); row++)
                    {
                        TableCellRenderer cellRenderer = student_table.getCellRenderer(row, column);
                        Component c = student_table.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + student_table.getIntercellSpacing().width;
                        
                        Object value = tableColumn.getHeaderValue();
                        TableCellRenderer renderer = tableColumn.getHeaderRenderer();

                        if (renderer == null)
                        {
                                renderer = student_table.getTableHeader().getDefaultRenderer();
                        }

                        Component d = renderer.getTableCellRendererComponent(student_table, value, false, false, -1, column);
                        int wid=d.getPreferredSize().width+ student_table.getIntercellSpacing().width;
                        
                         //CENTER THE JTABLE HEADER TEXT 
                        TableCellRenderer header = student_table.getTableHeader().getDefaultRenderer();
                        JLabel headerLabel = (JLabel) header;
                        headerLabel.setHorizontalAlignment(JLabel.CENTER);

                        //SET COLUMN TEXT BOLD
                        student_table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 11));
                        
                        preferredWidth = Math.max(preferredWidth,Math.max(wid, width));
   
                    }
 
                        tableColumn.setPreferredWidth( preferredWidth );
                }
    }//END OF UPDATE_TABLE METHODta 
   
    private void update_education_table(){
        //IMPORT MYSQL TABLE DATA INTO NETBEANS TABLE
            try{
                pst=conn.prepareStatement("select student.student_code as 'Student_code',full_name as 'Student Name',ol_education.ol_maths as 'Maths (O/L)', ol_education.ol_english as 'English (O/L)',"
                        + "al_education.al_english as 'English (A/L)'from student INNER JOIN ol_education ON student.student_code=ol_education.student_code JOIN al_education ON student.student_code=al_education.student_code");
                
                rs=pst.executeQuery();
                education_table.setModel(DbUtils.resultSetToTableModel(rs));
            
                }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                }    
            
        //SET THE TABLE COULMN AND ROW WIDTH    
            for (int column = 0; column < education_table.getColumnCount(); column++)
                {
                    TableColumn tableColumn = education_table.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();

                    for (int row = 0; row < education_table.getRowCount(); row++)
                    {
                        TableCellRenderer cellRenderer = education_table.getCellRenderer(row, column);
                        Component c = education_table.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + education_table.getIntercellSpacing().width;
                        
                        Object value = tableColumn.getHeaderValue();
                        TableCellRenderer renderer = tableColumn.getHeaderRenderer();

                        if (renderer == null)
                        {
                            renderer = education_table.getTableHeader().getDefaultRenderer();
                        }

                        Component d = renderer.getTableCellRendererComponent(education_table, value, false, false, -1, column);
                        int wid=d.getPreferredSize().width+ education_table.getIntercellSpacing().width;
                        
                         //CENTER THE JTABLE HEADER TEXT 
                        TableCellRenderer header = education_table.getTableHeader().getDefaultRenderer();
                        JLabel headerLabel = (JLabel) header;
                        headerLabel.setHorizontalAlignment(JLabel.CENTER);

                         //SET COLUMN TEXT BOLD
                        education_table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 11));
                        
                        preferredWidth = Math.max(preferredWidth,Math.max(wid, width));
                    }
                        tableColumn.setPreferredWidth( preferredWidth );
                }
    }
    
   
 /*  private void FillBatch_NoComboBox(){
         try{
            String sql="select * from batch";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            while(rs.next()){
                String batch=rs.getString("batch_number");
                batch_no.addItem(batch);
            }     
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    } */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        home = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        student_table = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        gender = new javax.swing.JComboBox<>();
        batch_no = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        pgname = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        stu_save = new javax.swing.JButton();
        pgnumber = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        dob = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        studentid = new javax.swing.JTextField();
        fullname = new javax.swing.JTextField();
        nic = new javax.swing.JTextField();
        image = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        select_image = new javax.swing.JButton();
        img_path = new javax.swing.JTextField();
        surname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        initials = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        hm = new javax.swing.JTextField();
        mn = new javax.swing.JTextField();
        contacttype = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        education_table = new javax.swing.JTable();
        jLabel68 = new javax.swing.JLabel();
        qualification_1 = new javax.swing.JTextField();
        qualification_2 = new javax.swing.JTextField();
        edu_save = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        buddhism = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        commerce = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        ol_index = new javax.swing.JTextField();
        history = new javax.swing.JComboBox<>();
        english = new javax.swing.JComboBox<>();
        science = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        health = new javax.swing.JComboBox<>();
        civil = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        ol_year = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        sinhala = new javax.swing.JComboBox<>();
        music = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        geography = new javax.swing.JComboBox<>();
        it = new javax.swing.JComboBox<>();
        soical_studies = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        dancing = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        english_lit = new javax.swing.JComboBox<>();
        art = new javax.swing.JComboBox<>();
        maths = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        religious = new javax.swing.JComboBox<>();
        al_english = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        economics = new javax.swing.JComboBox<>();
        aesthetic = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        social_science = new javax.swing.JComboBox<>();
        stream = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        al_index = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        accounting = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        chem = new javax.swing.JComboBox<>();
        business = new javax.swing.JComboBox<>();
        language = new javax.swing.JComboBox<>();
        agri = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        al_year = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        com_maths = new javax.swing.JComboBox<>();
        biology = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jComboBox28 = new javax.swing.JComboBox<>();
        jLabel62 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        view_application = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jComboBox29 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Registration");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/help.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 60, 60));

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/home-dash.png"))); // NOI18N
        home.setContentAreaFilled(false);
        home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        getContentPane().add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/shutdown_icon.png"))); // NOI18N
        logout.setContentAreaFilled(false);
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 60, 60));

        jLabel1.setBackground(new java.awt.Color(44, 62, 80));
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 80));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 64));

        jPanel2.setBackground(new java.awt.Color(222, 222, 245));

        student_table.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        student_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16"
            }
        ));
        student_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        student_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        student_table.setIntercellSpacing(new java.awt.Dimension(6, 3));
        student_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_tableMouseClicked(evt);
            }
        });
        student_table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                student_tableKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(student_table);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Search by Student code :");

        jPanel6.setBackground(new java.awt.Color(222, 222, 245));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Address");

        jButton12.setText("Check availibility");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        address.setColumns(20);
        address.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        address.setRows(2);
        jScrollPane4.setViewportView(address);

        stu_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/save icon.png"))); // NOI18N
        stu_save.setText("Save");
        stu_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stu_saveActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Student ID");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Home Number");

        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/update icon.png"))); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel56.setText("Surname");

        dob.setDateFormatString("yyyy-MM-dd");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Initials");

        studentid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentidMouseClicked(evt);
            }
        });

        image.setBackground(new java.awt.Color(204, 204, 204));
        image.setOpaque(true);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Gender");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Date of Birth");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Contact Type");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Parent/Guardian Contact number");

        jLabel57.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel57.setText("Full name");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("NIC");

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/delete icon.png"))); // NOI18N
        delete.setText("  Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        select_image.setText("Select Image");
        select_image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_imageActionPerformed(evt);
            }
        });

        surname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                surnameKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Mobile Number");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Email");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Parent/Guardian Name");

        contacttype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Email", "Phone", "SMS" }));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Batch No");

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/clear icon.png"))); // NOI18N
        reset.setText("  Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(select_image, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(222, 222, 222))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel57)
                                                    .addComponent(jLabel56))
                                                .addGap(9, 9, 9))
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(surname, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(nic, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(gender, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(batch_no, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(studentid, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(initials, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)))
                                .addGap(134, 134, 134)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pgname, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                    .addComponent(pgnumber)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(214, 214, 214))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(140, 140, 140)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel13))))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mn, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hm, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(contacttype, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(img_path, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(reset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(update, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(stu_save, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(studentid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jButton12)
                                    .addComponent(jLabel12)
                                    .addComponent(contacttype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(batch_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(hm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel56)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(initials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel57)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(nic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(dob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(pgname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel14)
                                            .addComponent(pgnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(img_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(stu_save, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(select_image)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Step 1 - Personal Details", jPanel2);

        jPanel3.setBackground(new java.awt.Color(222, 222, 245));

        education_table.setModel(new javax.swing.table.DefaultTableModel(
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
        education_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                education_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(education_table);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setText("Other :");

        edu_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/save icon.png"))); // NOI18N
        edu_save.setText("  Save");
        edu_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edu_saveActionPerformed(evt);
            }
        });

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/clear icon.png"))); // NOI18N
        refresh.setText("  Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(222, 222, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ordinary Level", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        buddhism.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        jLabel19.setText("Year");

        commerce.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        jLabel28.setText("History");

        history.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        english.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        science.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        jLabel33.setText("Civil Education");

        health.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        civil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        jLabel23.setText("Maths");

        jLabel20.setText("Index No");

        jLabel36.setText("Health & Physical Education");

        jLabel31.setText("IT");

        sinhala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        music.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        jLabel29.setText("English literature");

        geography.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        it.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        soical_studies.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        jLabel30.setText("Science");

        jLabel24.setText("English");

        dancing.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        jLabel21.setText("Sinhala");

        jLabel34.setText("Geography");

        jLabel32.setText("Dancing");

        jLabel25.setText("Social Studies");

        jLabel22.setText("Buddhism");

        jLabel27.setText("Art");

        jLabel26.setText("Music");

        jLabel35.setText("Business & Accounting Studies");

        english_lit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        art.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        maths.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "S", "F", "ab", "--" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(34, 34, 34)
                        .addComponent(ol_year, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel25)
                                                .addGap(40, 40, 40))
                                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(43, 43, 43)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(maths, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buddhism, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sinhala, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(science, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(english, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(soical_studies, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel28)
                            .addComponent(jLabel34))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(health, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(english_lit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(civil, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(history, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(geography, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(commerce, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ol_index, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel32))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(music, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(it, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(art, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dancing, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ol_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(ol_index, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sinhala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(geography, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(maths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel28))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buddhism, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel33)
                                    .addComponent(civil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32)
                                    .addComponent(dancing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(history, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(it, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(english, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel35)
                            .addComponent(commerce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(art, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(music, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(science, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel36)
                    .addComponent(health, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soical_studies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel29)
                    .addComponent(english_lit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(222, 222, 245));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Advanced Level", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setText("Commerce");

        jLabel42.setText("Biology");

        jLabel46.setText("Business Studies");

        jLabel52.setText("Religious and Civilizational Subjects");

        jLabel39.setText("Year");

        jLabel53.setText("Aesthetic Subjects");

        religious.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        al_english.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("Art");

        economics.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        aesthetic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        jLabel41.setText("Stream");

        jLabel54.setText("Language Subjects");

        social_science.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        stream.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maths", "Bio", "Commerce", "Art", "Technology" }));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setText("English");

        jLabel47.setText("Accounting");

        jLabel51.setText("Social Science Subjects");

        jLabel40.setText("Index No");

        jLabel44.setText("Chemistry");

        accounting.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        jLabel45.setText("Physics/Agriculture");

        chem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        business.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        language.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        agri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        jLabel48.setText("Economics");

        jLabel43.setText("Combined Maths");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setText("Science");

        com_maths.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        biology.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "A", "B", "C", "S", "F", "ab" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel43)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(com_maths, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel44)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chem, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel45)
                                    .addGap(15, 15, 15)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(biology, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(agri, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel42))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(social_science, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel54))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(aesthetic, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(religious, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(language, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel39))
                                .addGap(217, 217, 217)
                                .addComponent(al_index, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(al_year, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel40))))
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel46)
                    .addComponent(jLabel48)
                    .addComponent(jLabel47)
                    .addComponent(jLabel55)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(economics, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(business, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accounting, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(al_english, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stream, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(al_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(al_index, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(stream, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(business, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(social_science, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(biology, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(economics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(religious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel45)
                    .addComponent(agri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(accounting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aesthetic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel43)
                    .addComponent(com_maths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(al_english, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(language, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel44)
                    .addComponent(chem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/search icon.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/update icon.png"))); // NOI18N
        jButton4.setText("  Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(edu_save, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(qualification_2)
                                .addComponent(qualification_1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qualification_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qualification_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(refresh)
                            .addComponent(edu_save))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(jTextField2))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane3.addTab("Step 2 - Educational Qualifications", jPanel3);

        jPanel4.setBackground(new java.awt.Color(222, 222, 245));

        jLabel60.setText("Payment done");

        jComboBox28.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Full", "Half", "Quarter" }));

        jLabel62.setText("Amount");

        jLabel64.setText("Payment type");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/save icon.png"))); // NOI18N
        jButton7.setText("Save");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/update icon.png"))); // NOI18N
        jButton9.setText("Update");

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/clear icon.png"))); // NOI18N
        jButton10.setText("Clear");

        jCheckBox1.setBackground(new java.awt.Color(222, 222, 245));
        jCheckBox1.setText("Free student");

        jCheckBox2.setBackground(new java.awt.Color(222, 222, 245));
        jCheckBox2.setText("Other");

        view_application.setText("View Application");
        view_application.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_applicationActionPerformed(evt);
            }
        });

        jButton11.setText("View Slip");

        jLabel37.setText("Course ID");

        jComboBox29.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SLIOP/Images/search icon.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setContentAreaFilled(false);

        jLabel15.setText("Receipt No :");

        jLabel18.setText("Student ID :");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTable1);

        jLabel38.setText("Pending amount");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel37))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel64)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel38))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox2))
                                    .addComponent(jComboBox28, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField20, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(jTextField6))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(view_application))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton9)
                            .addComponent(jButton10))
                        .addGap(30, 30, 30)
                        .addComponent(view_application, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jTabbedPane3.addTab("Step 3 - Payment Details", jPanel4);

        jTabbedPane2.addTab("Student Details Entry", jTabbedPane3);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1280, 610));

        jLabel2.setBackground(new java.awt.Color(0, 153, 204));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        dashboard_admin ad=new dashboard_admin();
        ad.setVisible(true);
        
        dispose();
    }//GEN-LAST:event_homeActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        int reply=JOptionPane.showConfirmDialog(null, "Do you want to log out?","Confirm Logout",YES_NO_OPTION);
        
        if(reply==JOptionPane.YES_OPTION){
            login_page login=new login_page();
            login.setVisible(true);
            
            dispose();
        }
        
        if(reply==JOptionPane.NO_OPTION){
            this.setVisible(true);
        }
    }//GEN-LAST:event_logoutActionPerformed

    private void select_imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_imageActionPerformed
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        filename=f.getAbsolutePath();
        img_path.setText(filename);
        
        try{
            File image=new File(filename);
            FileInputStream fis=new FileInputStream(image);
            
            ByteArrayOutputStream bos= new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            
            for (int readNum; (readNum=fis.read(buf))!=-1;){
                bos.write(buf,0,readNum);
            }
            person_image=bos.toByteArray();
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_select_imageActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        studentid.setText("");
       // batch_no.setSelectedIndex(0);
        surname.setText("");
        initials.setText("");
        fullname.setText("");
        gender.setSelectedIndex(0);
        nic.setText("");
        dob.setCalendar(null);
        contacttype.setSelectedIndex(0);
        email.setText("");
        hm.setText("");
        mn.setText("");
        address.setText("");
        pgname.setText("");
        pgnumber.setText("");
        image.setIcon(null);
        img_path.setText("");
    }//GEN-LAST:event_resetActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        
    }//GEN-LAST:event_deleteActionPerformed

    private void stu_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stu_saveActionPerformed
      
            try{
            String sql=" insert into student(student_code,batch_number,surname,initials,full_name,gender,nic,dob,contact_type,email,home_number,"
                    + "mobile_number,address,pg_name,pg_contact_number,image)value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
    
            pst=conn.prepareStatement(sql);
            
            pst.setString(1, studentid.getText());

            String value2=batch_no.getSelectedItem().toString();
            pst.setString(2, value2);
              
            pst.setString(3, surname.getText());
            pst.setString(4, initials.getText());
            pst.setString(5, fullname.getText());
            
            String value3=gender.getSelectedItem().toString();
            pst.setString(6, value3);
            
            pst.setString(7, nic.getText());
            pst.setString(8, ((JTextField)dob.getDateEditor().getUiComponent()).getText());
            
            String ct=contacttype.getSelectedItem().toString();
            pst.setString(9, ct);
         
            pst.setString(10, email.getText());
            pst.setString(11, hm.getText());
            pst.setString(12, mn.getText());
            pst.setString(13, address.getText());
            pst.setString(14, pgname.getText());
            pst.setString(15, pgnumber.getText()); 
            pst.setBytes(16,person_image);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Successfully Inserted");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }

        update_table();
    }//GEN-LAST:event_stu_saveActionPerformed

    private void view_applicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_applicationActionPerformed
        String a1=studentid.getText();
       // batch_no.setSelectedIndex(0);
        String a2=surname.getText();
        String a3=fullname.getText();
        String a4=gender.getSelectedItem().toString();
        String a5=nic.getText();
        String a6=((JTextField)dob.getDateEditor().getUiComponent()).getText();
        String a7=email.getText();
        String a8=hm.getText();
        String a9=mn.getText();
        String a10=address.getText();
        String a11=pgname.getText();
        String a12=pgnumber.getText();
 
        try{
                Document document=new Document();
                PdfWriter.getInstance(document, new FileOutputStream("Report.pdf"));
                document.open();
                
                //PLACE THE LOGO AT THE CENTER OF THE PDF FILE
                com.itextpdf.text.Image img =com.itextpdf.text.Image.getInstance("logo.png");
                img.setAlignment(MIDDLE);
                document.add(img);
                
                Paragraph para1=new Paragraph("Sri Lanka Institute of Printing",FontFactory.getFont(FontFactory.TIMES_ROMAN, 20));
                para1.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para1);
               
                Paragraph para2=new Paragraph("Ministry of skills Development & Vocational Training",FontFactory.getFont(FontFactory.TIMES_ROMAN, 16));
                para2.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para2);
      
                Paragraph para3=new Paragraph("----------------------------------------------------------------------------");
                para3.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para3);
                
               // add a couple of blank lines
                document.add( Chunk.NEWLINE );
                
                PdfPTable table=new PdfPTable(2);
                table.setWidths(new int[]{50,100});
                table.setWidthPercentage(100);
                
                PdfPCell cell = table.getDefaultCell();
                cell.setPadding(5);
                
                PdfPCell header=new PdfPCell(new Paragraph("Student Details"));
                header.setColspan(2);
                header.setPadding(5);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(header);
                
                
                table.addCell("Student ID");
                table.addCell(a1);
                table.addCell("Student's full name");
                table.addCell(a3+" "+a2);
                table.addCell("Date of Birth");
                table.addCell(a6);
                table.addCell("Gender");
                table.addCell(a4);
                table.addCell("NIC");
                table.addCell(a5);
                table.addCell("Address");
                table.addCell(a10);
                table.addCell("Contact Number");
                table.addCell("Home :"+a8+"\n"+"Mobile :"+a9);
                table.addCell("Email");
                table.addCell(a7);
                table.addCell("Parent/Guardian name");
                table.addCell(a11);
                table.addCell("Parent/Guardian contact number");
                table.addCell(a12);
               
                document.add(table);
                
                document.add(Chunk.NEWLINE);   //Something like in HTML :-)
                
                Calendar cal=new GregorianCalendar();
                int month=cal.get(Calendar.MONTH);
                int year=cal.get(Calendar.YEAR);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                
                document.add(new Paragraph("Document Generated On - " + day+"/"+(month+1)+"/"+year));
                
                document.close();
                
                //OPEN THE FILE
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("C:\\Users\\Kalpana\\Documents\\NetBeansProjects\\SLIOP\\Report.pdf");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
       
                    }
                }
        }                 
        catch(Exception e){                                          
            JOptionPane.showMessageDialog(null, e);                 
        }
    }//GEN-LAST:event_view_applicationActionPerformed

    private void studentidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentidMouseClicked
       
        String sid = "^SLIOP-(S|WS)-(\\d)+$";
        Pattern pattern = Pattern.compile(sid);
        Matcher matcher = pattern.matcher(String.valueOf(studentid.getText()));

        if (!matcher.matches()) {
           JOptionPane.showMessageDialog(null, "Invalid student ID");
        } 
    }//GEN-LAST:event_studentidMouseClicked

    private void surnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_surnameKeyTyped
         char sur_n=evt.getKeyChar(); //EACH TIME USE TYPES SOMETHING,ITS SAVED INTO VARIABLE
        
        if(Character.isDigit(sur_n)){ //IF IT IS A CHARACTER ACCEPT IT AND IF NOT MAKE A BEEP SOUND
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_surnameKeyTyped

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
       
        check_availability ca=new check_availability(new javax.swing.JFrame(), true);
        ca.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void student_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_tableMouseClicked
        try{
              int row=student_table.getSelectedRow();
              
               String Table_click=(student_table.getModel().getValueAt(row, 0).toString());
                String sql="select * from student where idstudent='"+Table_click+"' ";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
           
                if(rs.next()){
   
                String result1=rs.getString("student_code");
                studentid.setText(result1); 
                    
          
                String result3=rs.getString("batch_number");
                batch_no.setSelectedItem(result3);
                
                String result4=rs.getString("surname");
                surname.setText(result4);
                
                String result5=rs.getString("initials");
                initials.setText(result5);
                
                String result6=rs.getString("full_name");
                fullname.setText(result6);
                
                String result7=rs.getString("gender");
                gender.setSelectedItem(result7);
                
                String result8=rs.getString("nic");
                nic.setText(result8);
                
                String result9=rs.getString("dob"); 
                ((JTextField)dob.getDateEditor().getUiComponent()).setText(result9);
                
                String result10=rs.getString("contact_type");
                contacttype.setSelectedItem(result10);
                
                String result11=rs.getString("email");
                email.setText(result11);
                
                String result12=rs.getString("home_number");
                hm.setText(result12);
                
                String result13=rs.getString("mobile_number");
                mn.setText(result13);
                
                String result14=rs.getString("address");
                address.setText(result14);
                
                String result15=rs.getString("pg_name");
                pgname.setText(result15);
                
                String result16=rs.getString("pg_contact_number");
                pgnumber.setText(result16);
                
                byte[] imagedata=rs.getBytes("image");  
                InputStream in = new ByteArrayInputStream(imagedata);                 
                BufferedImage bImageFromConvert = ImageIO.read(in);                  
                format=new ImageIcon(ScaledImage(bImageFromConvert,image.getWidth(),image.getHeight()));                  
                image.setIcon(format);
                
                
                }
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_student_tableMouseClicked

    //USE UP AND DOWN ARROW KEYS TO MOVE UP AND DOWN IN THE JTABLE
    private void student_tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_tableKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            try{
              int row=student_table.getSelectedRow();
              
               String Table_click=(student_table.getModel().getValueAt(row, 0).toString());
                String sql="select * from student where idstudent='"+Table_click+"' ";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
           
                if(rs.next()){
   
                String result1=rs.getString("student_code");
                studentid.setText(result1);

                String result3=rs.getString("batch_number");
                batch_no.setSelectedItem(result3);
                
                String result4=rs.getString("surname");
                surname.setText(result4);
                
                String result5=rs.getString("initials");
                initials.setText(result5);
                
                String result6=rs.getString("full_name");
                fullname.setText(result6);
                
                String result7=rs.getString("gender");
                gender.setSelectedItem(result7);
                
                String result8=rs.getString("nic");
                nic.setText(result8);
                
                String result9=rs.getString("dob"); 
                ((JTextField)dob.getDateEditor().getUiComponent()).setText(result9);
                
                String result10=rs.getString("contact_type");
                contacttype.setSelectedItem(result10);
                
                String result11=rs.getString("email");
                email.setText(result11);
                
                String result12=rs.getString("home_number");
                hm.setText(result12);
                
                String result13=rs.getString("mobile_number");
                mn.setText(result13);
                
                String result14=rs.getString("address");
                address.setText(result14);
                
                String result15=rs.getString("pg_name");
                pgname.setText(result15);
                
                String result16=rs.getString("pg_contact_number");
                pgnumber.setText(result16);
                
                byte[] imagedata=rs.getBytes("image");  
                InputStream in = new ByteArrayInputStream(imagedata);                 
                BufferedImage bImageFromConvert = ImageIO.read(in);                  
                format=new ImageIcon(ScaledImage(bImageFromConvert,image.getWidth(),image.getHeight()));                  
                image.setIcon(format);
                }
            }
            catch(SQLException | IOException e){
                JOptionPane.showMessageDialog(null, e);
            }
    
        }
    }//GEN-LAST:event_student_tableKeyPressed

    private void edu_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edu_saveActionPerformed
        try{
                    try{
                    String sql=" insert into ol_education(student_code,ol_year,ol_index,ol_sinhala,ol_maths,ol_buddhism,ol_english,"
                            + "ol_science,ol_socials,ol_geography,ol_history,ol_civil,ol_commerce,ol_health,ol_englit,ol_art,ol_it,"
                            + "ol_dancing,ol_music)value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

                    pst=conn.prepareStatement(sql);


                    pst.setString(1,studentid.getText());

                    pst.setString(2,ol_year.getText());
                    pst.setString(3, ol_index.getText());

                    String value1=sinhala.getSelectedItem().toString();
                    pst.setString(4, value1);

                    String value2=maths.getSelectedItem().toString();
                    pst.setString(5, value2);

                    String value3=buddhism.getSelectedItem().toString();
                    pst.setString(6, value3);

                    String value4=english.getSelectedItem().toString();
                    pst.setString(7, value4);

                    String value5=science.getSelectedItem().toString();
                    pst.setString(8, value5);

                    String value6=soical_studies.getSelectedItem().toString();
                    pst.setString(9, value6);

                    String value7=geography.getSelectedItem().toString();
                    pst.setString(10, value7);

                    String value8=history.getSelectedItem().toString();
                    pst.setString(11, value8);

                    String value9=civil.getSelectedItem().toString();
                    pst.setString(12, value9);

                    String value10=commerce.getSelectedItem().toString();
                    pst.setString(13, value10);

                    String value11=health.getSelectedItem().toString();
                    pst.setString(14, value11);

                    String value12=english_lit.getSelectedItem().toString();
                    pst.setString(15, value12);

                    String value13=art.getSelectedItem().toString();
                    pst.setString(16, value13);

                    String value14=it.getSelectedItem().toString();
                    pst.setString(17, value14);

                    String value15=dancing.getSelectedItem().toString();
                    pst.setString(18, value15);

                    String value16=music.getSelectedItem().toString();
                    pst.setString(19, value16);

                    pst.execute();
                    }
                    
                    catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                    }
                    
                    
                    try{
                    String sql1=" insert into al_education(student_code,al_year,al_index,al_stream,al_bio,al_physics_agri,al_maths,"
                            + "al_chemistry,al_social_sci,al_religous_civil,al_aesthetic,al_language,"
                            + "al_business,al_economic,al_accounting,al_english )value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

                    pst=conn.prepareStatement(sql1);
                    
                    
                    pst.setString(1,studentid.getText());

                    pst.setString(2,al_year.getText());
                    pst.setString(3, al_index.getText());

                    String record4=stream.getSelectedItem().toString();
                    pst.setString(4, record4);

                    String record5=biology.getSelectedItem().toString();
                    pst.setString(5, record5);

                    String record6=agri.getSelectedItem().toString();
                    pst.setString(6, record6);

                    String record7=com_maths.getSelectedItem().toString();
                    pst.setString(7, record7);

                    String record8=chem.getSelectedItem().toString();
                    pst.setString(8, record8);

                    String record9=social_science.getSelectedItem().toString();
                    pst.setString(9, record9);

                    String record10=religious.getSelectedItem().toString();
                    pst.setString(10, record10);

                    String record11=aesthetic.getSelectedItem().toString();
                    pst.setString(11, record11);

                    String record12=language.getSelectedItem().toString();
                    pst.setString(12, record12);

                    String record13=business.getSelectedItem().toString();
                    pst.setString(13, record13);

                    String record14=economics.getSelectedItem().toString();
                    pst.setString(14, record14);

                    String record15=accounting.getSelectedItem().toString();
                    pst.setString(15, record15);

                    String record16=al_english.getSelectedItem().toString();
                    pst.setString(16, record16);

                    pst.execute();
                    }
                    
                    catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                    }
                    
            JOptionPane.showMessageDialog(null, "Successfully Inserted");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        update_education_table();
    }//GEN-LAST:event_edu_saveActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        ol_year.setText("");
        ol_index.setText("");
        sinhala.setSelectedIndex(0);
        maths.setSelectedIndex(0);
        buddhism.setSelectedIndex(0);
        english.setSelectedIndex(0);
        science.setSelectedIndex(0);
        soical_studies.setSelectedIndex(0);
        geography.setSelectedIndex(0);
        history.setSelectedIndex(0);
        civil.setSelectedIndex(0);
        commerce.setSelectedIndex(0);
        health.setSelectedIndex(0);
        english_lit.setSelectedIndex(0);
        it.setSelectedIndex(0);
        dancing.setSelectedIndex(0);
        music.setSelectedIndex(0);
        al_year.setText("");
        al_index.setText("");
        biology.setSelectedIndex(0);
        agri.setSelectedIndex(0);
        com_maths.setSelectedIndex(0);
        chem.setSelectedIndex(0);
        social_science.setSelectedIndex(0);
        religious.setSelectedIndex(0);
        aesthetic.setSelectedIndex(0);
        language.setSelectedIndex(0);
        business.setSelectedIndex(0);
        economics.setSelectedIndex(0);
        accounting.setSelectedIndex(0);
        al_english.setSelectedIndex(0);
        qualification_1.setText("");
        qualification_2.setText("");
    }//GEN-LAST:event_refreshActionPerformed

    private void education_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_education_tableMouseClicked
        try{
              int row=education_table.getSelectedRow();
              
                String Table_click=(education_table.getModel().getValueAt(row, 0).toString());
                String sql1="select * from ol_education where student_code='"+Table_click+"' ";
                pst=conn.prepareStatement(sql1);
                rs=pst.executeQuery();
           
                if(rs.next()){

                String input1=rs.getString("ol_year");
                ol_year.setText(input1);
  
                String input2=rs.getString("ol_index");
                ol_index.setText(input2);
                
                String input3=rs.getString("ol_sinhala");
                sinhala.setSelectedItem(input3);
      
                String input4=rs.getString("ol_maths");
                maths.setSelectedItem(input4);
                
                String input5=rs.getString("ol_buddhism");
                buddhism.setSelectedItem(input5);
                
                String input6=rs.getString("ol_english");
                english.setSelectedItem(input6);
                
                String input7=rs.getString("ol_science");
                science.setSelectedItem(input7);
                
                String input8=rs.getString("ol_socials");
                soical_studies.setSelectedItem(input8);
                
                String input9=rs.getString("ol_geography");
                geography.setSelectedItem(input9);
                
                String input10=rs.getString("ol_history");
                history.setSelectedItem(input10);
                
                String input11=rs.getString("ol_civil");
                civil.setSelectedItem(input11);
                
                String input12=rs.getString("ol_commerce");
                commerce.setSelectedItem(input12);
                    
                String input13=rs.getString("ol_health");
                health.setSelectedItem(input13);
                
                String input14=rs.getString("ol_englit");
                english_lit.setSelectedItem(input14);
                
                String input15=rs.getString("ol_it");
                it.setSelectedItem(input15);
                
                String input16=rs.getString("ol_dancing");
                dancing.setSelectedItem(input16);
                
                String input17=rs.getString("ol_music");
                music.setSelectedItem(input17);
                }
                
                String sql2="select * from al_education where student_code='"+Table_click+"' ";
                pst=conn.prepareStatement(sql2);
                rs=pst.executeQuery();
           
                if(rs.next()){

                String input18=rs.getString("al_year");
                al_year.setText(input18);
  
                String input19=rs.getString("al_index");
                al_index.setText(input19);
                
                String input20=rs.getString("al_stream");
                stream.setSelectedItem(input20);
      
                String input21=rs.getString("al_bio");
                biology.setSelectedItem(input21);
                
                String input22=rs.getString("al_physics_agri");
                agri.setSelectedItem(input22);
                
                String input23=rs.getString("al_maths");
                com_maths.setSelectedItem(input23);
                
                String input24=rs.getString("al_chemistry");
                chem.setSelectedItem(input24);
                
                String input25=rs.getString("al_social_sci");
                social_science.setSelectedItem(input25);
                
                String input26=rs.getString("al_religous_civil");
                religious.setSelectedItem(input26);
                
                String input27=rs.getString("al_aesthetic");
                aesthetic.setSelectedItem(input27);
                
                String input28=rs.getString("al_language");
                language.setSelectedItem(input28);
                
                String input29=rs.getString("al_business");
                business.setSelectedItem(input29);
                    
                String input30=rs.getString("al_economic");
                economics.setSelectedItem(input30);
                
                String input31=rs.getString("al_accounting");
                accounting.setSelectedItem(input31);
                
                String input32=rs.getString("al_english");
                al_english.setSelectedItem(input32);
                }
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_education_tableMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
         
        String res1=ol_year.getText();
        String res2=ol_index.getText();
        String res3=sinhala.getSelectedItem().toString();
        String res4=maths.getSelectedItem().toString();
        String res5=buddhism.getSelectedItem().toString();
        String res6=english.getSelectedItem().toString();
        String res7=science.getSelectedItem().toString();
        String res8=soical_studies.getSelectedItem().toString();
        String res9=geography.getSelectedItem().toString();
        String res10=history.getSelectedItem().toString();
        String res11=civil.getSelectedItem().toString();
        String res12=commerce.getSelectedItem().toString();
        String res13=health.getSelectedItem().toString();
        String res14=english_lit.getSelectedItem().toString();
        String res15=it.getSelectedItem().toString();
        String res16=dancing.getSelectedItem().toString();
        String res17=music.getSelectedItem().toString();

        String sql="update ol_education set ol_year='"+res1+"' ,ol_index='"+res2+", ol_sinhala="+res3+"',ol_maths='"+res4+"',ol_buddhism='"+res5+"',ol_english'"+res6+",ol_science="+res7+"',ol_socials='"+res8+"',ol_geography='"+res9+"',ol_history='"+res10+"',ol_civil='"+res11+"',ol_commerce='"+res12+"',ol_health='"+res13+"',ol_englit='"+res14+ "',ol_it='"+res15+"',ol_dancing='"+res16+"',ol_music='"+res17+"' ";
  
          pst=conn.prepareStatement(sql);
          pst.execute();
          JOptionPane.showMessageDialog(null, "Successfully updated");
           
        }
        catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private Image ScaledImage(Image img, int w, int h){
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
            java.util.logging.Logger.getLogger(Admission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admission().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> accounting;
    private javax.swing.JTextArea address;
    private javax.swing.JComboBox<String> aesthetic;
    private javax.swing.JComboBox<String> agri;
    private javax.swing.JComboBox<String> al_english;
    private javax.swing.JTextField al_index;
    private javax.swing.JTextField al_year;
    private javax.swing.JComboBox<String> art;
    private javax.swing.JComboBox<String> batch_no;
    private javax.swing.JComboBox<String> biology;
    private javax.swing.JComboBox<String> buddhism;
    private javax.swing.JComboBox<String> business;
    private javax.swing.JComboBox<String> chem;
    private javax.swing.JComboBox<String> civil;
    private javax.swing.JComboBox<String> com_maths;
    private javax.swing.JComboBox<String> commerce;
    private javax.swing.JComboBox<String> contacttype;
    private javax.swing.JComboBox<String> dancing;
    private javax.swing.JButton delete;
    private com.toedter.calendar.JDateChooser dob;
    private javax.swing.JComboBox<String> economics;
    private javax.swing.JButton edu_save;
    private javax.swing.JTable education_table;
    private javax.swing.JTextField email;
    private javax.swing.JComboBox<String> english;
    private javax.swing.JComboBox<String> english_lit;
    private javax.swing.JTextField fullname;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JComboBox<String> geography;
    private javax.swing.JComboBox<String> health;
    private javax.swing.JComboBox<String> history;
    private javax.swing.JTextField hm;
    private javax.swing.JButton home;
    private javax.swing.JLabel image;
    private javax.swing.JTextField img_path;
    private javax.swing.JTextField initials;
    private javax.swing.JComboBox<String> it;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox28;
    private javax.swing.JComboBox<String> jComboBox29;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JComboBox<String> language;
    private javax.swing.JButton logout;
    private javax.swing.JComboBox<String> maths;
    private javax.swing.JTextField mn;
    private javax.swing.JComboBox<String> music;
    private javax.swing.JTextField nic;
    private javax.swing.JTextField ol_index;
    private javax.swing.JTextField ol_year;
    private javax.swing.JTextField pgname;
    private javax.swing.JTextField pgnumber;
    private javax.swing.JTextField qualification_1;
    private javax.swing.JTextField qualification_2;
    private javax.swing.JButton refresh;
    private javax.swing.JComboBox<String> religious;
    private javax.swing.JButton reset;
    private javax.swing.JComboBox<String> science;
    private javax.swing.JButton select_image;
    private javax.swing.JComboBox<String> sinhala;
    private javax.swing.JComboBox<String> social_science;
    private javax.swing.JComboBox<String> soical_studies;
    private javax.swing.JComboBox<String> stream;
    private javax.swing.JButton stu_save;
    private javax.swing.JTable student_table;
    private javax.swing.JTextField studentid;
    private javax.swing.JTextField surname;
    private javax.swing.JButton update;
    private javax.swing.JButton view_application;
    // End of variables declaration//GEN-END:variables

    private ImageIcon format=null;
    String filename=null;
    int s=0;
    byte[] person_image=null;
}
