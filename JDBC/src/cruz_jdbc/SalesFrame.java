/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cruz_jdbc;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cipher
 */
public final class SalesFrame extends javax.swing.JFrame {
    private Connection conn;
    private Statement stmt;
    private DefaultTableModel model;
    private String col[];
    private Object row[][];
    /**
     * Creates new form Sales
     */
    public SalesFrame() {
        initComponents();
        initConnection();
        initialState();
        setTable();
    }
    
    public void initTable(){
        this.col = new String[]{"PURCHASEID", "CUSTOMER FNAME", "CUSTOMER LNAME", "CUSTOMER ADDRESS", "PRODUCT NAME", "PRODUCT QUANTITY", "PRODUCT PRICE", "TOTAL AMOUNT PAYABLE", "STATUS"};
        model = new DefaultTableModel(row,col);
        recordedDataTable.setModel(model);
    }
    
    private void initConnection(){
      final String url = "jdbc:mysql://localhost:3307/sales";
      final String usn = "a";
      final String pwd = "a";
      
        try {
            conn = DriverManager.getConnection(url,usn,pwd);
            stmt = conn.createStatement();
            System.out.println("ACCESS GRANTED");
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "NOT CONNECTED", "ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void initialState(){
        newBtn.setEnabled(true);
        saveBtn.setEnabled(false);
        purchaseId.setEditable(false);
        fnameTF.setEditable(false);
        lnameTF.setEditable(false);
        addressTF.setEditable(false);
        productNameTF.setEditable(false);
        quantityTF.setEditable(false);
        priceTF.setEditable(false);
    }
    
    public void setTable(){
        initTable();
        String sql = "Select * from purchase";
        try(ResultSet res = stmt.executeQuery(sql)){
            while(res.next()){
                //Add data row to model 
                model.addRow(new Object[] {res.getInt("purchaseId"),res.getString("customer_fname"),res.getString("customer_lname"),res.getString("customer_address"),res.getString("productName"),res.getInt("productQuantity"), res.getDouble("productPrice"),res.getDouble("totalAmountPayable"),res.getString("status")});
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void makeInput(){
        newBtn.setEnabled(false);
        saveBtn.setEnabled(true);
        purchaseId.setEditable(true);
        fnameTF.setEditable(true);
        lnameTF.setEditable(true);
        addressTF.setEditable(true);
        productNameTF.setEditable(true);
        quantityTF.setEditable(true);
        priceTF.setEditable(true);
    }
    
    public void setClear(){
        purchaseId.setText("");
        fnameTF.setText("");
        lnameTF.setText("");
        addressTF.setText("");
        productNameTF.setText("");
        quantityTF.setText("");
        priceTF.setText("");
    }
    
    public  void makeEdit(){
        purchaseId.setEditable(false);
        fnameTF.setEditable(true);
        lnameTF.setEditable(true);
        addressTF.setEditable(true);
        productNameTF.setEditable(true);
        quantityTF.setEditable(true);
        priceTF.setEditable(true);
    }
    
    public void displayDataToTextField(){
        //GET THE ROW NUMBER OF THE SELECTED ROW;
        int getRowNumber = recordedDataTable.getSelectedRow();
        //DISPLAY TO TEXTFIELD
        purchaseId.setText(model.getValueAt(getRowNumber, 0).toString());
        fnameTF.setText(model.getValueAt(getRowNumber, 1).toString());
        lnameTF.setText(model.getValueAt(getRowNumber, 2).toString());
        addressTF.setText(model.getValueAt(getRowNumber, 3).toString());
        productNameTF.setText(model.getValueAt(getRowNumber, 4).toString());
        quantityTF.setText(model.getValueAt(getRowNumber, 5).toString());
        priceTF.setText(model.getValueAt(getRowNumber, 6).toString());
    }
    
    public void saveData(){
        try{
            //COMPUTE FOR TOTALAMOUNT
            double totalAmount = Double.parseDouble(priceTF.getText()) * Integer.parseInt(quantityTF.getText());
            //INSERT INTO QUERY
            String sql = "INSERT INTO purchase VALUES('"+Integer.valueOf(purchaseId.getText())+"','"+fnameTF.getText()+"','"+lnameTF.getText()+"','"+addressTF.getText()+"','"+productNameTF.getText()+"','"+Integer.valueOf(quantityTF.getText())+"','"+Double.valueOf(priceTF.getText())+"','"+totalAmount+"','SOLD')";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "SAVED SUCCESSFULLY!");
        }catch(SQLException | NumberFormatException | NullPointerException ex){
            JOptionPane.showMessageDialog(null, "DATA NOT SAVE","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateData(){
           try{
               double totalAmount = Double.parseDouble(priceTF.getText()) * Integer.parseInt(quantityTF.getText());
               //PERFORM THE UPDATE QUERY
               String sql = "UPDATE purchase SET customer_fname='"+fnameTF.getText()+"',customer_lname='"+lnameTF.getText()+"',customer_address='"+addressTF.getText()+"',productName = '"+productNameTF.getText()+"',productQuantity='"+Integer.valueOf(quantityTF.getText())+"',productPrice = '"+Double.valueOf(priceTF.getText())+"',totalAmountPayable='"+totalAmount+"',status='SOLD'WHERE purchaseId='"+Integer.valueOf(purchaseId.getText())+"'";
               stmt.executeUpdate(sql);
               JOptionPane.showMessageDialog(null,"UPDATED SUCCESSFUL!");
           }catch(SQLException e){
               System.out.println(e.getMessage());
           }catch(NumberFormatException | NullPointerException ex){
               JOptionPane.showMessageDialog(null,"Select a row from the table first","INFO",JOptionPane.INFORMATION_MESSAGE);
            }
    }
    
    public void deleteData(){
        try{
            String sql = "DELETE from purchase WHERE purchaseId='"+Integer.valueOf(purchaseId.getText())+"'";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "DELETED SUCCESSFULLY!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(NumberFormatException | NullPointerException ex){
               JOptionPane.showMessageDialog(null,"Select a row from the table first","INFO",JOptionPane.INFORMATION_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recordedDataTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        purchaseId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fnameTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lnameTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        addressTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        productNameTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        quantityTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        priceTF = new javax.swing.JTextField();
        newBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        recordedDataTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        recordedDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        recordedDataTable.getTableHeader().setResizingAllowed(false);
        recordedDataTable.getTableHeader().setReorderingAllowed(false);
        recordedDataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recordedDataTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(recordedDataTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("SALES SYSTEM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("PURCHASE ID:");

        purchaseId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("CUSTOMER FIRSTNAME:");

        fnameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CUSTOMER LASTNAME:");

        lnameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("CUSTOMER ADDRESS:");

        addressTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("PRODUCT NAME:");

        productNameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("PRODUCT QUANTITY:");

        quantityTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("PRODUCT PRICE:");

        priceTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        newBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newBtn.setText("NEW");
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });

        deleteBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        saveBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saveBtn.setText("SAVE");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        updateBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(purchaseId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(newBtn)
                        .addGap(18, 18, 18)
                        .addComponent(saveBtn)
                        .addGap(18, 18, 18)
                        .addComponent(updateBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(quantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(lnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(addressTF, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(productNameTF))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(purchaseId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3))
                    .addComponent(fnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4))
                    .addComponent(lnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1172, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void recordedDataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recordedDataTableMouseClicked
        makeEdit();
        displayDataToTextField();
    }//GEN-LAST:event_recordedDataTableMouseClicked

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
         makeInput();
    }//GEN-LAST:event_newBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        deleteData();
        setClear();
        initialState();
        setTable();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        saveData();
        setTable();
        initialState();
        setClear();
    }//GEN-LAST:event_saveBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        updateData();
        setTable();
        setClear();
        initialState();
    }//GEN-LAST:event_updateBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTF;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField fnameTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lnameTF;
    private javax.swing.JButton newBtn;
    private javax.swing.JTextField priceTF;
    private javax.swing.JTextField productNameTF;
    private javax.swing.JTextField purchaseId;
    private javax.swing.JTextField quantityTF;
    private javax.swing.JTable recordedDataTable;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
