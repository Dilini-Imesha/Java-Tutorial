
package javaproject;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class StudentsForm extends javax.swing.JFrame {

   
    public StudentsForm() {
        initComponents();
       
        fillTable();
    }

    public Connection MySqlConnection(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project",
                    "root", "root");
          
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mysql Connection Failed....");
            return null;
        }    
    }
    String photopath="";
    public ImageIcon resetImageSize(String photopath,byte[] photo){
        ImageIcon myPhoto=null;
        if (photopath!=null) {
            myPhoto=new ImageIcon(photopath);
        }else{
               myPhoto=new ImageIcon(photo);
        }
        Image img=myPhoto.getImage();
        Image img1=img.getScaledInstance(label_photo.getWidth(),label_photo.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon ph=new ImageIcon(img1);
        return ph;        
    }

    public ArrayList<StudentBean> retrieveData(){
        ArrayList<StudentBean> al=null;
                al=new ArrayList<StudentBean>();
       
        try {
            Connection conn=MySqlConnection();
            String qry="select * from students";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(qry);
            StudentBean student;
            while (rs.next()) {
                student =new StudentBean(rs.getInt(1),rs.getString("name"),
                        Float.parseFloat(rs.getString(3)),rs.getString(4),
                        rs.getBytes("photo"));                
                al.add(student);
              }
           
        } catch (Exception e) {
            System.out.println(e);
        }
       return al; 
    }
    
    public void fillTable(){
        ArrayList<StudentBean> al=retrieveData();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        model.setRowCount(0); 
        Object[] row=new Object[4];
        for (int i = 0; i < al.size(); i++) {
            row[0]=al.get(i).getId();
            row[1]=al.get(i).getName();
            row[2]=al.get(i).getFees();
            row[3]=al.get(i).getDob();
            model.addRow(row);
        }
      
    }
   
    public void showItemToFields(int index){
        jTextField_id.setText(Integer.toString(retrieveData().get(index).getId()));
        jTextField_name.setText(retrieveData().get(index).getName());
        jTextField_fees.setText(Float.toString(retrieveData().get(index).getFees()));
        
        try {
            java.util.Date dob=null;
 dob=new SimpleDateFormat("dd-MM-yyyy").parse((String)retrieveData().get(index).getDob());
            jDateChooser1_dob.setDate(dob);          
         } catch (Exception e) {
             System.out.println("Error at showItemToFields method "+e);
        }
        label_photo.setIcon(resetImageSize(null, retrieveData().get(index).getPhoto()));         
    }
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField_id = new javax.swing.JTextField();
        jTextField_name = new javax.swing.JTextField();
        jTextField_fees = new javax.swing.JTextField();
        jDateChooser1_dob = new com.toedter.calendar.JDateChooser();
        label_photo = new javax.swing.JLabel();
        jButton_new = new javax.swing.JButton();
        jButton_save = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField_search = new javax.swing.JTextField();
        jButton_photo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36));
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("CSMSS, Chh. Shahu College of Enginnering");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); 
        jLabel2.setText("Kanchanwadi, Aurangabad, Maharashtra, India.");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cscoecollegelogo.jpg"))); 

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jLabel4.setText("Student Information");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jLabel5.setText("Id:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jLabel6.setText("Student Name:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel7.setText("Fees:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jLabel8.setText("Date of Birth:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jLabel9.setText("Photo:");

        jTextField_id.setForeground(new java.awt.Color(204, 0, 51));

        jTextField_name.setForeground(new java.awt.Color(204, 0, 51));

        jTextField_fees.setForeground(new java.awt.Color(204, 0, 51));

        jDateChooser1_dob.setForeground(new java.awt.Color(0, 0, 102));
        jDateChooser1_dob.setDateFormatString("dd-MM-yyyy");

        label_photo.setBackground(new java.awt.Color(255, 255, 255));
        label_photo.setOpaque(true);

        jButton_new.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_new.setForeground(new java.awt.Color(0, 0, 153));
        jButton_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check.png"))); // NOI18N
        jButton_new.setText("New ");

        jButton_save.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_save.setForeground(new java.awt.Color(0, 0, 153));
        jButton_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_add.png"))); // NOI18N
        jButton_save.setText("Insert/Save");
        jButton_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saveActionPerformed(evt);
            }
        });

        jButton_delete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_delete.setForeground(new java.awt.Color(0, 0, 153));
        jButton_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/erase.png"))); // NOI18N
        jButton_delete.setText("Delete");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jButton_update.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_update.setForeground(new java.awt.Color(0, 0, 153));
        jButton_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sync.png"))); // NOI18N
        jButton_update.setText("Update");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Search Student By Name:");

        jTextField_search.setForeground(new java.awt.Color(204, 0, 51));
        jTextField_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchKeyReleased(evt);
            }
        });

        jButton_photo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_photo.setForeground(new java.awt.Color(0, 0, 153));
        jButton_photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/folder_red.png"))); // NOI18N
        jButton_photo.setText("Select Photo");
        jButton_photo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_photoActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Student Name", "Fees", "Date of Birth"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(138, 138, 138)
                                .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(29, 29, 29))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_fees)
                                    .addComponent(jTextField_name)
                                    .addComponent(jDateChooser1_dob, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addComponent(label_photo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton_new, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_save, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_update, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(56, 56, 56)
                        .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_new, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jButton_save, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextField_fees, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jDateChooser1_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }


    private void jButton_photoActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser chooser=new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter fnef=new FileNameExtensionFilter("*.images", "jpg","png");
        chooser.addChoosableFileFilter(fnef);
        int ans=chooser.showSaveDialog(null);
        if (ans==JFileChooser.APPROVE_OPTION) {
            File selectedPhoto=chooser.getSelectedFile();
            String path=selectedPhoto.getAbsolutePath();
            label_photo.setIcon(resetImageSize(path, null));
            this.photopath=path;
        }
    }

    private void jButton_saveActionPerformed(java.awt.event.ActionEvent evt) {
        
        if ((jTextField_id.getText()!=null || jTextField_name!=null
        || jTextField_fees!=null || jDateChooser1_dob!=null)&& photopath!=null) {
            try {
                Connection conn=MySqlConnection();
             
                PreparedStatement ps=conn.prepareStatement("insert into students"
                        + "(id,name,fees,dob,photo) values (?,?,?,?,?)");
                ps.setInt(1, Integer.parseInt(jTextField_id.getText()));
                ps.setString(2,jTextField_name.getText());
                ps.setFloat(3, Float.parseFloat(jTextField_fees.getText()));
                
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                String dob1=sdf.format(jDateChooser1_dob.getDate());
                ps.setString(4,dob1);
                
                InputStream is=new FileInputStream(new File(photopath));
                ps.setBlob(5, is);
                int res=ps.executeUpdate();
                 fillTable();
                if (res>=1) {
                    JOptionPane.showMessageDialog(null, res+" Number of Student"
                            + " inserted into database .....");
                }else
                    JOptionPane.showMessageDialog(null, "Student Insertion Failed ....");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }           
            jTextField_id.setText("");
            jTextField_name.setText("");
            jTextField_fees.setText("");
            
        }else{
             JOptionPane.showMessageDialog(null, "All fields are compulsory ....");
        }
        
    }

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {
        
          if (jTextField_id!=null || jTextField_name!=null
                    || jTextField_fees!=null || jDateChooser1_dob!=null) {
              String qry=null;
              PreparedStatement ps=null;
              Connection conn=MySqlConnection();
              
              if (photopath==null) {
                    try {        
                        qry="update students set name=?, fees=?,dob=? where id=?";
                        ps=conn.prepareStatement(qry);
                        
                        ps.setString(1,jTextField_name.getText());
                        ps.setFloat(2, Float.parseFloat(jTextField_fees.getText()));

                        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                        String dob1=sdf.format(jDateChooser1_dob.getDate());
                        ps.setString(3,dob1);
                         
                        ps.setInt(4, Integer.parseInt(jTextField_id.getText()));
                       
                        
                        
                        int res=ps.executeUpdate();
                         fillTable();
                        if (res>=1) {
                            JOptionPane.showMessageDialog(null, res+" Number of Student"
                                    + " inserted into database .....");
                        }else
                            JOptionPane.showMessageDialog(null, "Student Insertion Failed ....");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }           
                    jTextField_id.setText("");
                    jTextField_name.setText("");
                    jTextField_fees.setText("");

                    }else{
                        try {    
                            InputStream is=new FileInputStream(new File(photopath));
                            
                        qry="update students set name=?, fees=?,dob=?, photo=? where id=?";
                        ps=conn.prepareStatement(qry);
                        
                        ps.setString(1,jTextField_name.getText());
                        ps.setFloat(2, Float.parseFloat(jTextField_fees.getText()));

                        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                        String dob1=sdf.format(jDateChooser1_dob.getDate());
                        ps.setString(3,dob1);
                        
                        ps.setBlob(4, is);
                        
                        ps.setInt(5, Integer.parseInt(jTextField_id.getText()));
                                                
                        int res=ps.executeUpdate();
                         fillTable();
                        if (res>=1) {
                            JOptionPane.showMessageDialog(null, res+" Student"
                                    + " Updated into database .....");
                        }else
                            JOptionPane.showMessageDialog(null, "Student Updation Failed ....");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }           
                    jTextField_id.setText("");
                    jTextField_name.setText("");
                    jTextField_fees.setText("");
     
                    }
          }else
              JOptionPane.showMessageDialog(null, "All fields are mandatory.......");
    }

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTextField_id.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "Please Enter the student id");
        }else{             
                try{
                String qry="delete from students where id=?";
                Connection conn=MySqlConnection();
                PreparedStatement ps=conn.prepareStatement(qry);
                ps.setInt(1, Integer.parseInt(jTextField_id.getText().toString()));
                int res=ps.executeUpdate();
                 fillTable();
                if (res>=1) {
                    JOptionPane.showMessageDialog(null, "Student Deleted Successfully ....");    
                }else
                    JOptionPane.showMessageDialog(null, "Student Deletion failed ....");    
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int ind=jTable1.getSelectedRow();
        showItemToFields(ind);
    }
    private void jTextField_searchKeyReleased(java.awt.event.KeyEvent evt) {
               
        ArrayList<StudentBean> al=null;
                al=new ArrayList<StudentBean>();
       String val=jTextField_search.getText().toString();
        try {
            Connection conn=MySqlConnection();
           
            String qry="select * from students where name like '%"+val+"%'";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(qry);
            StudentBean student;
            while (rs.next()) {
          student =new StudentBean(rs.getInt(1),rs.getString("name")
                  ,Float.parseFloat(rs.getString(3)),rs.getString(4),rs.getBytes("photo"));                
                al.add(student);
              }
            
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row=new Object[4];
        for (int i = 0; i < al.size(); i++) {
            row[0]=al.get(i).getId();
            row[1]=al.get(i).getName();
            row[2]=al.get(i).getFees();
            row[3]=al.get(i).getDob();
            model.addRow(row);
        }           
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentsForm().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_new;
    private javax.swing.JButton jButton_photo;
    private javax.swing.JButton jButton_save;
    private javax.swing.JButton jButton_update;
    private com.toedter.calendar.JDateChooser jDateChooser1_dob;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_fees;
    private javax.swing.JTextField jTextField_id;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_search;
    private javax.swing.JLabel label_photo;
   
}
