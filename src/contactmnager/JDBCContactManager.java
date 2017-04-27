/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmnager;
import java.sql.*;
import javax.swing.*;
import org.apache.derby.jdbc.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 00220682
 */
public class JDBCContactManager extends javax.swing.JFrame {
    private Connection con;
    private ResultSet rs;
    private Statement stmt;
    private String dbURI = "jdbc:derby:contact;create=true";

    /**
     * Creates new form JDBCContacManager
     */
    public JDBCContactManager() {
        initComponents();
        setDBSystemDir();
        createTable();
        getResultSet();
        displayResults();
        pnlInsert.setVisible(false);
        pnlResults.setVisible(false);
         txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
         pnlResults.setVisible(true);
           String search = txtSearch.getText();
           if (search.contains(" "))
           {
             String[] words=search.split("\\s");
           //System.out.println(words.length);
           int c = words.length;
           String search1 = words[0];
           StringBuffer output = new StringBuffer(110);
           //output.append("select * from contact where (fname like '%"+search1+"%' or lname like '%"+search1+"%' or street like '%"+search1+"%' or city like '%"+search1+"%' or state like '%"+search1+"%' or zip like '%"+search1+"%' or email like '%"+search1+"%' or phone like '%"+search1+"%' or notes like '%"+search1+"%')");
           output.append("select * from contact where (UPPER(fname) like UPPER('%"+search1+"%') or UPPER(lname) like UPPER('%"+search1+"%') or UPPER(street) like UPPER('%"+search1+"%') or UPPER(city) like UPPER('%"+search1+"%') or UPPER(state) like UPPER('%"+search1+"%') or zip like '%"+search1+"%' or UPPER(email) like UPPER('%"+search1+"%') or phone like '%"+search1+"%' or UPPER(notes) like UPPER('%"+search1+"%'))");

//System.out.println(output);
           
           //System.out.println(search1);
               try {
                   String search2 = words[1];
                   //System.out.println(search2);
                      try{ 
                        
                        //output.append("String1");
                          for(int i=1; i<c; i++){
                              
                           //output.append(" and (fname like '%"+words[i].toString()+"%' or lname like '%"+words[i].toString()+"%' or street like '%"+words[i].toString()+"%' or city like '%"+words[i].toString()+"%' or state like '%"+words[i].toString()+"%' or zip like '%"+words[i].toString()+"%' or email like '%"+words[i].toString()+"%' or phone like '%"+words[i].toString()+"%' or notes like '%"+words[i].toString()+"%'))");
                           output.append(" and (UPPER(fname) like UPPER('%"+words[i].toString()+"%') or UPPER(lname) like UPPER('%"+words[i].toString()+"%') or UPPER(street) like UPPER('%"+words[i].toString()+"%') or UPPER(city) like UPPER('%"+words[i].toString()+"%') or UPPER(state) like UPPER('%"+words[i].toString()+"%') or zip like '%"+words[i].toString()+"%' or UPPER(email) like UPPER('%"+words[i].toString()+"%') or phone like '%"+words[i].toString()+"%' or UPPER(notes) like UPPER('%"+words[i].toString()+"%'))");

                            //fname like '%"+search1+"%' or lname like '%"+search1+"%' or street like '%"+search1+"%' or city like '%"+search1+"%' or state like '%"+search1+"%' or zip like '%"+search1+"%' or email like '%"+search1+"%' or phone like '%"+search1+"%' or notes like '%"+search1+"%')
                           //words[i].toString()
                           //System.out.println(output);
                           
         }
                          
           stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //rs = stmt.executeQuery("select * from contact where fname like '%"+fname+"%' or lname like '%"+lname+"%'");
            //rs = stmt.executeQuery("select * from contact where (fname like '%"+search1+"%' or lname like '%"+search1+"%')  and ( fname like '%"+search2+"%' or lname like '%"+search2+"%')");
            rs = stmt.executeQuery(output.toString());

            //ResultSet is scrollable and updateable
            rs.first();
            displaySearchResults();
            rs.previous();
            tblResults.setModel(new ResultsTableModel(rs));
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(this,e.getMessage());
        } 
                     
               } catch (Exception e) {
               }
               }else{
                     try{ 
                         
           stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //stmt.executeQuery("select * from contact where fname like '%"+search+"%' or lname like '%"+search+"%' or street like '%"+search+"%' or city like '%"+search+"%' or state like '%"+search+"%' or zip like '%"+search+"%' or email like '%"+search+"%' or phone like '%"+search+"%' or notes like '%"+search+"%'");
            rs = stmt.executeQuery("select * from contact where UPPER(fname) like UPPER('%"+search+"%') or UPPER(lname) like UPPER('%"+search+"%') or UPPER(street) like UPPER('%"+search+"%') or UPPER(city) like UPPER('%"+search+"%') or UPPER(state) like UPPER('%"+search+"%') or zip like '%"+search+"%' or UPPER(email) like UPPER('%"+search+"%') or phone like '%"+search+"%' or UPPER(notes) like UPPER('%"+search+"%') ");
           rs.first();
           displaySearchResults();
           rs.previous();
           tblResults.setModel(new ResultsTableModel(rs));
       }catch(SQLException e){
            //JOptionPane.showMessageDialog(this,e.getMessage());
        }      catch (Exception ex) {  
                   Logger.getLogger(JDBCContactManager.class.getName()).log(Level.SEVERE, null, ex);
               }  
           };
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                pnlResults.setVisible(true);
            String search = txtSearch.getText();
           if (search.contains(" "))
           {
             String[] words=search.split("\\s");
           //System.out.println(words.length);
           int c = words.length;
           String search1 = words[0];
           StringBuffer output = new StringBuffer(110);
           //output.append("select * from contact where (fname like '%"+search1+"%' or lname like '%"+search1+"%' or street like '%"+search1+"%' or city like '%"+search1+"%' or state like '%"+search1+"%' or zip like '%"+search1+"%' or email like '%"+search1+"%' or phone like '%"+search1+"%' or notes like '%"+search1+"%')");
           output.append("select * from contact where (UPPER(fname) like UPPER('%"+search1+"%') or UPPER(lname) like UPPER('%"+search1+"%') or UPPER(street) like UPPER('%"+search1+"%') or UPPER(city) like UPPER('%"+search1+"%') or UPPER(state) like UPPER('%"+search1+"%') or zip like '%"+search1+"%' or UPPER(email) like UPPER('%"+search1+"%') or phone like '%"+search1+"%' or UPPER(notes) like UPPER('%"+search1+"%'))");

//System.out.println(output);
           
           //System.out.println(search1);
               try {
                   String search2 = words[1];
                   //System.out.println(search2);
                      try{ 
                        
                        //output.append("String1");
                          for(int i=1; i<c; i++){
                              
                           //output.append(" and (fname like '%"+words[i].toString()+"%' or lname like '%"+words[i].toString()+"%' or street like '%"+words[i].toString()+"%' or city like '%"+words[i].toString()+"%' or state like '%"+words[i].toString()+"%' or zip like '%"+words[i].toString()+"%' or email like '%"+words[i].toString()+"%' or phone like '%"+words[i].toString()+"%' or notes like '%"+words[i].toString()+"%'))");
                           output.append(" and (UPPER(fname) like UPPER('%"+words[i].toString()+"%') or UPPER(lname) like UPPER('%"+words[i].toString()+"%') or UPPER(street) like UPPER('%"+words[i].toString()+"%') or UPPER(city) like UPPER('%"+words[i].toString()+"%') or UPPER(state) like UPPER('%"+words[i].toString()+"%') or zip like '%"+words[i].toString()+"%' or UPPER(email) like UPPER('%"+words[i].toString()+"%') or phone like '%"+words[i].toString()+"%' or UPPER(notes) like UPPER('%"+words[i].toString()+"%'))");

                            //fname like '%"+search1+"%' or lname like '%"+search1+"%' or street like '%"+search1+"%' or city like '%"+search1+"%' or state like '%"+search1+"%' or zip like '%"+search1+"%' or email like '%"+search1+"%' or phone like '%"+search1+"%' or notes like '%"+search1+"%')
                           //words[i].toString()
                           //System.out.println(output);
                           
         }
                          
           stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //rs = stmt.executeQuery("select * from contact where fname like '%"+fname+"%' or lname like '%"+lname+"%'");
            //rs = stmt.executeQuery("select * from contact where (fname like '%"+search1+"%' or lname like '%"+search1+"%')  and ( fname like '%"+search2+"%' or lname like '%"+search2+"%')");
            rs = stmt.executeQuery(output.toString());

            //ResultSet is scrollable and updateable
            rs.first();
            displaySearchResults();
            rs.previous();
            tblResults.setModel(new ResultsTableModel(rs));
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(this,e.getMessage());
        } 
                     
               } catch (Exception e) {
               }
               }else{
                     try{ 
                         
           stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //rs = stmt.executeQuery("select * from contact where fname like '%juan%'");
            //rs1 = rs.executeQuery("select * from contact where fname like '%lozano%' or lname like '%lozano%'");
            //rs = stmt.executeQuery("select * from contact where fname like '%"+search+"%' or lname like '%"+search+"%' or street like '%"+search+"%' or city like '%"+search+"%' or state like '%"+search+"%' or zip like '%"+search+"%' or email like '%"+search+"%' or phone like '%"+search+"%' or notes like '%"+search+"%'");
            rs = stmt.executeQuery("select * from contact where UPPER(fname) like UPPER('%"+search+"%') or UPPER(lname) like UPPER('%"+search+"%') or UPPER(street) like UPPER('%"+search+"%') or UPPER(city) like UPPER('%"+search+"%') or UPPER(state) like UPPER('%"+search+"%') or zip like '%"+search+"%' or UPPER(email) like UPPER('%"+search+"%') or phone like '%"+search+"%' or UPPER(notes) like UPPER('%"+search+"%') ");

            //ResultSet is scrollable and updateable
           rs.first();
           displaySearchResults();
           rs.previous();
           tblResults.setModel(new ResultsTableModel(rs));
       }catch(SQLException e){
            //JOptionPane.showMessageDialog(this,e.getMessage());
        }       catch (Exception ex) {  
                    Logger.getLogger(JDBCContactManager.class.getName()).log(Level.SEVERE, null, ex);
                }  
           };
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                pnlResults.setVisible(true);
               String search = txtSearch.getText();
           if (search.contains(" "))
           {
             String[] words=search.split("\\s");
           //System.out.println(words.length);
           int c = words.length;
           String search1 = words[0];
           StringBuffer output = new StringBuffer(110);
           //output.append("select * from contact where (fname like '%"+search1+"%' or lname like '%"+search1+"%' or street like '%"+search1+"%' or city like '%"+search1+"%' or state like '%"+search1+"%' or zip like '%"+search1+"%' or email like '%"+search1+"%' or phone like '%"+search1+"%' or notes like '%"+search1+"%')");
           output.append("select * from contact where (UPPER(fname) like UPPER('%"+search1+"%') or UPPER(lname) like UPPER('%"+search1+"%') or UPPER(street) like UPPER('%"+search1+"%') or UPPER(city) like UPPER('%"+search1+"%') or UPPER(state) like UPPER('%"+search1+"%') or zip like '%"+search1+"%' or UPPER(email) like UPPER('%"+search1+"%') or phone like '%"+search1+"%' or UPPER(notes) like UPPER('%"+search1+"%'))");

//System.out.println(output);
           
           //System.out.println(search1);
               try {
                   String search2 = words[1];
                   //System.out.println(search2);
                      try{ 
                        
                        //output.append("String1");
                          for(int i=1; i<c; i++){
                              
                           //output.append(" and (fname like '%"+words[i].toString()+"%' or lname like '%"+words[i].toString()+"%' or street like '%"+words[i].toString()+"%' or city like '%"+words[i].toString()+"%' or state like '%"+words[i].toString()+"%' or zip like '%"+words[i].toString()+"%' or email like '%"+words[i].toString()+"%' or phone like '%"+words[i].toString()+"%' or notes like '%"+words[i].toString()+"%'))");
                           output.append(" and (UPPER(fname) like UPPER('%"+words[i].toString()+"%') or UPPER(lname) like UPPER('%"+words[i].toString()+"%') or UPPER(street) like UPPER('%"+words[i].toString()+"%') or UPPER(city) like UPPER('%"+words[i].toString()+"%') or UPPER(state) like UPPER('%"+words[i].toString()+"%') or zip like '%"+words[i].toString()+"%' or UPPER(email) like UPPER('%"+words[i].toString()+"%') or phone like '%"+words[i].toString()+"%' or UPPER(notes) like UPPER('%"+words[i].toString()+"%'))");

                            //fname like '%"+search1+"%' or lname like '%"+search1+"%' or street like '%"+search1+"%' or city like '%"+search1+"%' or state like '%"+search1+"%' or zip like '%"+search1+"%' or email like '%"+search1+"%' or phone like '%"+search1+"%' or notes like '%"+search1+"%')
                           //words[i].toString()
                           //System.out.println(output);
                           
         }
                          
           stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //rs = stmt.executeQuery("select * from contact where fname like '%"+fname+"%' or lname like '%"+lname+"%'");
            //rs = stmt.executeQuery("select * from contact where (fname like '%"+search1+"%' or lname like '%"+search1+"%')  and ( fname like '%"+search2+"%' or lname like '%"+search2+"%')");
            rs = stmt.executeQuery(output.toString());

            //ResultSet is scrollable and updateable
            rs.first();
            displaySearchResults();
            rs.previous();
            tblResults.setModel(new ResultsTableModel(rs));
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(this,e.getMessage());
        } 
                     
               } catch (Exception e) {
               }
               }else{
                     try{ 
                         
           stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //rs = stmt.executeQuery("select * from contact where fname like '%juan%'");
            //rs1 = rs.executeQuery("select * from contact where fname like '%lozano%' or lname like '%lozano%'");
            //rs = stmt.executeQuery("select * from contact where fname like '%"+search+"%' or lname like '%"+search+"%' or street like '%"+search+"%' or city like '%"+search+"%' or state like '%"+search+"%' or zip like '%"+search+"%' or email like '%"+search+"%' or phone like '%"+search+"%' or notes like '%"+search+"%'");
            rs = stmt.executeQuery("select * from contact where UPPER(fname) like UPPER('%"+search+"%') or UPPER(lname) like UPPER('%"+search+"%') or UPPER(street) like UPPER('%"+search+"%') or UPPER(city) like UPPER('%"+search+"%') or UPPER(state) like UPPER('%"+search+"%') or zip like '%"+search+"%' or UPPER(email) like UPPER('%"+search+"%') or phone like '%"+search+"%' or UPPER(notes) like UPPER('%"+search+"%') ");

            //ResultSet is scrollable and updateable
           rs.first();
           displaySearchResults();
           rs.previous();
           tblResults.setModel(new ResultsTableModel(rs));
       }catch(SQLException e){
            //JOptionPane.showMessageDialog(this,e.getMessage());
        }          catch (Exception ex) {  
                       Logger.getLogger(JDBCContactManager.class.getName()).log(Level.SEVERE, null, ex);
                   }  
           };
            }
        });
    }
    
    private void setDBSystemDir(){
        //Decide on the database location
        String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = userHomeDir + "/contact";
        //set the db system property
        System.setProperty("derby.system.home", systemDir);
    }
    
    private void createTable(){
        try{
            DriverManager.registerDriver(new EmbeddedDriver());
            con = DriverManager.getConnection(dbURI);
            String sql = "create table contact (fname varchar(30), lname varchar(30), street varchar(30), city varchar(30),state varchar(30),zip varchar(30), email varchar(30), phone varchar(30), notes varchar(300))";
            stmt = con.createStatement();
            stmt.execute(sql);
        }catch(SQLException e){
            if(e.getErrorCode()!=30000){
                //Error code 30000: table already exists - not an error!
                JOptionPane.showMessageDialog(this,e.getMessage());
            }
        }
    }
    
    private void getResultSet(){
        try{
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("select * from contact");
            //ResultSet is scrollable and updateable
            rs.first();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }
    
    private void displayResults(){
        try{
            txtFirstName.setText(rs.getString("fname"));
            txtLastName.setText(rs.getString("lname"));
            txtStreet.setText(rs.getString("street"));
            txtCity.setText(rs.getString("city"));
            txtState.setSelectedItem(rs.getString("state"));
            txtZip.setText(rs.getString("zip"));
            txtEmail.setText(rs.getString("email"));
            txtPhone.setText(rs.getString("phone"));
            txtNotes.setText(rs.getString("notes"));
            
        }catch(SQLException e){
            if(e.getErrorCode()==20000){
                //Invalid Cursor State - no records in result set
                JOptionPane.showMessageDialog(this, "Click New to get started");
                //In case the only record was deleted

            txtFirstName.setText("");
            txtLastName.setText("");
            txtStreet.setText("");
            txtCity.setText("");
            txtState.setSelectedItem(" ");
            txtZip.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtNotes.setText("");
            }else{
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }    
    }
    
  private void displaySearchResults(){
        try{
            txtFirstName.setText(rs.getString("fname"));
            txtLastName.setText(rs.getString("lname"));
            txtStreet.setText(rs.getString("street"));
            txtCity.setText(rs.getString("city"));
            txtState.setSelectedItem(rs.getString("state"));
            txtZip.setText(rs.getString("zip"));
            txtEmail.setText(rs.getString("email"));
            txtPhone.setText(rs.getString("phone"));
            txtNotes.setText(rs.getString("notes"));
            
        }catch(SQLException e){
            if(e.getErrorCode()==20000){
                //Invalid Cursor State - no records in result set
                JOptionPane.showMessageDialog(this, "No Result Found");
                //In case the only record was deleted

            txtFirstName.setText("");
            txtLastName.setText("");
            txtStreet.setText("");
            txtCity.setText("");
            txtState.setSelectedItem("");
            txtZip.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtNotes.setText("");
            }else{
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }    
    }  
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlData = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtStreet = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtZip = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        pnlNav = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtState = new javax.swing.JComboBox<>();
        pnlButtons = new javax.swing.JPanel();
        pnlControls = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        pnlInsert = new javax.swing.JPanel();
        btnInsert = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        pnlResults = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResults = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JDBC Contact Manager");

        jLabel1.setText("First Name");

        jLabel2.setText("Email");

        jLabel3.setText("Phone");

        jLabel4.setText("Last Name");

        jLabel6.setText("City");

        jLabel7.setText("State");

        jLabel8.setText("Street");

        jLabel9.setText("ZIP");

        jLabel10.setText("Notes");

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jScrollPane1.setViewportView(txtNotes);

        btnFirst.setText("<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlNav.add(btnFirst);

        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnlNav.add(btnPrev);

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlNav.add(btnNext);

        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlNav.add(btnLast);

        jLabel11.setText("Search");
        jLabel11.setToolTipText("");

        txtState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY" }));
        txtState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDataLayout = new javax.swing.GroupLayout(pnlData);
        pnlData.setLayout(pnlDataLayout);
        pnlDataLayout.setHorizontalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCity, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtStreet, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDataLayout.createSequentialGroup()
                                .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtZip))))
                    .addGroup(pnlDataLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(pnlNav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDataLayout.setVerticalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(txtZip, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(38, 38, 38)
                .addComponent(pnlNav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlData, java.awt.BorderLayout.NORTH);

        pnlButtons.setLayout(new java.awt.GridLayout(2, 0));

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        pnlControls.add(btnNew);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        pnlControls.add(btnDelete);

        btnView.setText("View All");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });
        pnlControls.add(btnView);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        pnlControls.add(btnUpdate);

        pnlButtons.add(pnlControls);

        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });
        pnlInsert.add(btnInsert);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        pnlInsert.add(btnCancel);

        pnlButtons.add(pnlInsert);

        getContentPane().add(pnlButtons, java.awt.BorderLayout.CENTER);

        tblResults.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblResults);

        javax.swing.GroupLayout pnlResultsLayout = new javax.swing.GroupLayout(pnlResults);
        pnlResults.setLayout(pnlResultsLayout);
        pnlResultsLayout.setHorizontalGroup(
            pnlResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );
        pnlResultsLayout.setVerticalGroup(
            pnlResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlResultsLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlResults, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(479, 703));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        pnlButtons.setVisible(true);
        pnlInsert.setVisible(true);
        pnlResults.setVisible(false);
        pack();
        try{
            rs.moveToInsertRow();
            displayResults();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        try {
            if(CheckFields()){
                rs.updateString("fname",txtFirstName.getText());
                rs.updateString("lname",txtLastName.getText());
                rs.updateString("street",txtStreet.getText());
                rs.updateString("city",txtCity.getText());
                rs.updateString("state",(String)txtState.getSelectedItem());
                rs.updateString("zip",txtZip.getText());
                rs.updateString("email",txtEmail.getText());
                rs.updateString("phone", txtPhone.getText());
                rs.updateString("notes", txtNotes.getText());
                rs.insertRow();
                //Refresh the ResultSet
                rs.close();
                rs = stmt.executeQuery("select * from contact");
                rs.last();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }finally{
            pnlInsert.setVisible(false);
            pnlButtons.setVisible(true);
            pack();
            displayResults();
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try{
            rs.moveToCurrentRow();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }finally{
            pnlInsert.setVisible(false);
            pnlButtons.setVisible(true);
            pack();
            displayResults();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try{
            if(CheckFields()){
                rs.updateString("fname", txtFirstName.getText());
                rs.updateString("lname",txtLastName.getText());
                rs.updateString("street", txtStreet.getText());
                rs.updateString("city", txtCity.getText());
                rs.updateString("state",(String)txtState.getSelectedItem());
                rs.updateString("zip", txtZip.getText());
                rs.updateString("email", txtEmail.getText());
                rs.updateString("phone",txtPhone.getText());
                rs.updateString("notes", txtNotes.getText());
                rs.updateRow();
                int currentRow = rs.getRow();
                rs.close();
                rs = stmt.executeQuery("select * from contact");
                rs.absolute(currentRow);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try{
            int dialogResult = JOptionPane.showConfirmDialog(this, "Delete Record?","Warning",JOptionPane.YES_NO_OPTION);
            if(dialogResult==0){
                int currentRow = rs.getRow() - 1;
                if(currentRow==0)
                    currentRow = 1;
                rs.deleteRow();
                rs.close();
                rs = stmt.executeQuery("select * from contact");
                rs.absolute(currentRow);
                displayResults();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
         try{
            rs.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }finally{
            rs = null;
            stmt = null;
            con = null;
        }
    }//GEN-LAST:event_formWindowClosed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        pnlResults.setVisible(true);
        pnlButtons.setVisible(true);
        pnlInsert.setVisible(false);
        pack();
        try{
            DriverManager.registerDriver(new EmbeddedDriver());
            Connection con = DriverManager.getConnection(dbURI);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select * from contact");
            tblResults.setAutoCreateRowSorter(true);
            tblResults.setModel(new ResultsTableModel(rs));
            rs.first();
            displayResults();
        }catch(Exception e){
           System.out.println(e.getMessage());  
        }
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        try{
            rs.last();
            displayResults();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        try{
            if(rs.next()){
                displayResults();
            }else{
                rs.last();
                displayResults();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        try{
            if(rs.previous()){
                displayResults();
            }else{
                rs.first();
                displayResults();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        try{
            rs.first();
            displayResults();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnFirstActionPerformed

    private void txtStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStateActionPerformed

                    
    
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDBCContactManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDBCContactManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDBCContactManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDBCContactManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JDBCContactManager().setVisible(true);
            }
        });
    }

    private boolean CheckFields(){
        //returns true if passed checks\
        System.out.println(txtPhone.getText().length());
        RegexMatch phonenumber = new RegexMatch("\\d{3}-\\d{3}-\\d{4}");
        RegexMatch zip = new RegexMatch("\\d{5}");
        RegexMatch email = new RegexMatch(".*@.*\\..*");
        if (txtPhone.getText().length() > 0){
            if (phonenumber.CheckExp(txtPhone.getText()) == false){
                JOptionPane.showMessageDialog(this, "Phone number is improperly formatted. It needs to be in xxx-xxx-xxxx format");
                return false;
            }
        }
        if(txtZip.getText().length() > 0){
            if(zip.CheckExp(txtZip.getText()) == false){
                JOptionPane.showMessageDialog(this, "Zip code is invalid, zip code needs to be five digits");
                return false;
            }
        }
        if(txtEmail.getText().length() > 0){
            if(email.CheckExp(txtEmail.getText()) == false){
                JOptionPane.showMessageDialog(this, "Email invalid, needs to be in email@email.com format");
                return false;
            }
        }
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlControls;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlInsert;
    private javax.swing.JPanel pnlNav;
    private javax.swing.JPanel pnlResults;
    private javax.swing.JTable tblResults;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JComboBox<String> txtState;
    private javax.swing.JTextField txtStreet;
    private javax.swing.JTextField txtZip;
    // End of variables declaration//GEN-END:variables
}
