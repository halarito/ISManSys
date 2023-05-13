/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Modulo.Conexao;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;


public class Professores extends javax.swing.JInternalFrame {

    /**
     * Creates new form Professores
     */
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public Professores() {
        initComponents();
        
        conexao = Conexao.conector();
        
        preencher();
    }

    void preencher() {
        String sql = "Select d.id, d.nome, p.descricao, d.contacto from Professor d inner join perfil p on p.id = d.perfil";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

            TableProfessores.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    void inserir(){
        
        String inserir = "insert into Professor (nome, bi, naturalidade, contacto, email, perfil, username, password) values(?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            pst = conexao.prepareStatement(inserir);
            
            pst.setString(1, CampoNome.getText());
            pst.setString(2, CampoBI.getText());
            pst.setString(3, CampoNaturalidade.getText());
            pst.setString(4, CampoContacto.getText());
            pst.setString(5, CampoEmail.getText());
            pst.setString(6, String.valueOf(ComboPerfil.getSelectedItem()));
            pst.setString(7, CampoUsername.getText());
            pst.setString(8, CampoPassword.getText());
            
            if (CampoNome.getText().isEmpty() ||  CampoBI.getText().isEmpty() || CampoContacto.getText().isEmpty() || CampoUsername.getText().isEmpty() || CampoPassword.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(rootPane, "Preencha todos  os campos por favor");
            }
            else{
                int registrarN = JOptionPane.showConfirmDialog(null, "Registrar novo Professor?", "Confirme", JOptionPane.YES_NO_OPTION);
                
                if(registrarN == JOptionPane.YES_OPTION){
                    int registrado = pst.executeUpdate();
                    
                    if(registrado > 0){
                        JOptionPane.showMessageDialog(null, "Professor adicionado com sucesso!");
                        
                        CampoNome.setText(null);
                        CampoBI.setText(null);
                        CampoPassword.setText(null);
                        CampoUsername.setText(null);
                        CampoNaturalidade.setText(null);
                        CampoContacto.setText(null);
                        CampoEmail.setText(null);
                    }
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
    }
    int id;
    
    
    void excluir(){
        String excluir = "delete from Professor where id = ?";
        
        if(TextId.getText().isEmpty()){
                    JOptionPane.showMessageDialog(rootPane, "Insira o ID do Professor no respectivo campo");
                    
        }
        else{
            id = Integer.parseInt(TextId.getText().toString());
        
            try {
            pst = conexao.prepareStatement(excluir);
            
            pst.setString(1, String.valueOf(id));
            
            int per = JOptionPane.showConfirmDialog(rootPane, "Excluir o Professor? ", "Confirme", JOptionPane.YES_NO_OPTION);
        
            if(per == JOptionPane.YES_OPTION){
                
                if(TextId.getText().isEmpty()){
                    JOptionPane.showMessageDialog(rootPane, "Insira o ID do Professor no respectivo campo");
                }
                else{
                    int actua = pst.executeUpdate();
                
                    if(actua > 0){
                    JOptionPane.showMessageDialog(rootPane, "Professor excluido com sucesso!");
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Exclusão nao sucedida!");
                    
                }
                
            }
        } 
        /*catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Insira o ID do Professor no respectivo campo");*/
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
                    
        }
        }    
    }
    
    void completar(){
        
            int tab = TableProfessores.getSelectedRow();
            
            CampoNome.setText(TableProfessores.getModel().getValueAt(tab, 1).toString());
            TextId.setText(TableProfessores.getModel().getValueAt(tab, 0).toString());
            CampoContacto.setText(TableProfessores.getModel().getValueAt(tab, 3).toString());
            
            int id = Integer.parseInt(TableProfessores.getModel().getValueAt(tab, 0).toString());
            
            String tudo = "select bi, naturalidade, email, username, password from Professor where id = ?";
            
            try {
                pst = conexao.prepareStatement(tudo);
                
                pst.setString(1, String.valueOf(id));
                
                rs = pst.executeQuery();
                
                if(rs.next()){
                    CampoBI.setText(rs.getString("bi"));
                    CampoNaturalidade.setText(rs.getString("naturalidade"));
                    CampoEmail.setText(rs.getString("email"));
                    CampoUsername.setText(rs.getString("username"));
                    CampoPassword.setText(rs.getString("password"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
    }
    
    void editar(){
        
        String act = "update professor set nome = ?, bi = ?, naturalidade = ?, contacto = ?, email = ?, perfil = ?, username = ?, password = ? where id = ?";
        
        if(TableProfessores.getSelectedRow() != -1){
            int tab = TableProfessores.getSelectedRow();
            
            String id = TableProfessores.getModel().getValueAt(tab, 0).toString();
        
        try {
            pst = conexao.prepareStatement(act);
            
            pst.setString(1, CampoNome.getText());
            pst.setString(2, CampoBI.getText());
            pst.setString(3, CampoNaturalidade.getText());
            pst.setString(4, CampoContacto.getText());
            pst.setString(5, CampoEmail.getText());
            pst.setString(6, String.valueOf(ComboPerfil.getSelectedItem()));
            pst.setString(7, CampoUsername.getText());
            pst.setString(8, CampoPassword.getText());
            pst.setString(9, id);
            
            int per = JOptionPane.showConfirmDialog(rootPane, "Acualizar dados do Professor? ", "Confirme", JOptionPane.YES_NO_OPTION);
        
            if(per == JOptionPane.YES_OPTION){
                int actua = pst.executeUpdate();
                
                if(actua > 0){
                    JOptionPane.showMessageDialog(rootPane, "DAdos do Professor actualizados com sucesso!");
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Actualização nao sucedida!");
                    
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        }
        else if (TableProfessores.getSelectedRow() != -1 && TextId.getText().isEmpty() ){
            JOptionPane.showMessageDialog(rootPane, "Selecione na tabela o Professor que pretende actualizar");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        CampoNome = new javax.swing.JTextField();
        CampoNaturalidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TextId = new javax.swing.JTextField();
        ComboPerfil = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        CampoBI = new javax.swing.JFormattedTextField();
        CampoContacto = new javax.swing.JFormattedTextField();
        CampoEmail = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        CampoUsername = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        CampoPassword = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableProfessores = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("PROFESSORES");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INFORMAÇAO PESSOAL");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nome:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("BI:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Naturalidade:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Contacto:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("E-Mail:");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/user_edit.png"))); // NOI18N
        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/user_delete.png"))); // NOI18N
        jButton2.setText("Excluir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/user_add.png"))); // NOI18N
        jButton3.setText("Registrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        CampoNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CampoNome.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        CampoNaturalidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CampoNaturalidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("ID: ");

        TextId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TextId.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ComboPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Perfil:");

        try {
            CampoBI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#############")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        CampoBI.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        try {
            CampoContacto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(+258) ## ### ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        CampoContacto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampoEmail)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CampoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(CampoNome)
                            .addComponent(CampoNaturalidade)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CampoBI, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextId)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(TextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoBI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoNaturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(CampoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Username: ");

        CampoUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CampoUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Password: ");

        CampoPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CampoPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        TableProfessores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TableProfessores.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TableProfessores);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PROFESSORES INSCRITOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CampoUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(CampoPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(CampoUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(CampoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        inserir();
        preencher();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        editar();
        preencher();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        excluir();
        preencher();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField CampoBI;
    private javax.swing.JFormattedTextField CampoContacto;
    private javax.swing.JFormattedTextField CampoEmail;
    private javax.swing.JTextField CampoNaturalidade;
    private javax.swing.JTextField CampoNome;
    private javax.swing.JTextField CampoPassword;
    private javax.swing.JTextField CampoUsername;
    private javax.swing.JComboBox ComboPerfil;
    private javax.swing.JTable TableProfessores;
    private javax.swing.JTextField TextId;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
