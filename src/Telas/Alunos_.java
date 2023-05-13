package Telas;

import Modulo.Conexao;
import javax.swing.*;
import java.sql.*;

public class Alunos_ extends JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form Alunos_
     */
    public Alunos_() {
        initComponents();

        conexao = Conexao.conector();
    }

    static int ano;
    static int sem;
    static int nivel;

    void inserir() {

        if (A2022.isSelected()) {
            ano = 1;
        } else if (A2023.isSelected()) {
            ano = 2;
        } else if (A2024.isSelected()) {
            ano = 3;
        }

        if (Sem_1.isSelected()) {
            sem = 1;
        } else if (Sem_2.isSelected()) {
            sem = 2;
        }

        if (nivel_1.isSelected()) {
            nivel = 1;
        } else if (nivel_2.isSelected()) {
            nivel = 2;
        } else if (nivel_3.isSelected()) {
            nivel = 4;
        } else if (nivel_4.isSelected()) {
            nivel = 5;
        }

        String inserir = "insert into aluno (nome, bi, naturalidade, contacto, email, ano_id, semestre_id, nivel_id) values(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            pst = conexao.prepareStatement(inserir);

            pst.setString(1, CampoNome.getText());
            pst.setString(2, CampoBI.getText());
            pst.setString(3, CampoNaturalidade.getText());
            pst.setString(4, CampoContacto.getText());
            pst.setString(5, CampoEmail.getText());
            pst.setString(6, String.valueOf(ano));
            pst.setString(7, String.valueOf(sem));
            pst.setString(8, String.valueOf(nivel));

            if (CampoNome.getText().isEmpty() || CampoBI.getText().isEmpty() || CampoNaturalidade.getText().isEmpty() || CampoContacto.getText().isEmpty() || CampoEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Preencha todos  os campos por favor");
            } else {
                int registrarN = JOptionPane.showConfirmDialog(null, "Registrar novo Aluno?", "Confirme", JOptionPane.YES_NO_OPTION);

                if (registrarN == JOptionPane.YES_OPTION) {
                    int registrado = pst.executeUpdate();

                    if (registrado > 0) {
                        JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso!");

                        CampoNome.setText(null);
                        CampoBI.setText(null);
                        
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

    void actualizar() {
        
        if (A2022.isSelected()) {
            ano = 1;
        } else if (A2023.isSelected()) {
            ano = 2;
        } else if (A2024.isSelected()) {
            ano = 3;
        }

        if (Sem_1.isSelected()) {
            sem = 1;
        } else if (Sem_2.isSelected()) {
            sem = 2;
        }

        if (nivel_1.isSelected()) {
            nivel = 1;
        } else if (nivel_2.isSelected()) {
            nivel = 2;
        } else if (nivel_3.isSelected()) {
            nivel = 4;
        } else if (nivel_4.isSelected()) {
            nivel = 5;
        }
        
        String act = "update aluno set nome = ?, bi = ?, naturalidade = ?, contacto = ?, email = ?, ano_id = ?, semestre_id = ?, nivel_id = ? where id = ?";
        
        try {
            pst = conexao.prepareStatement(act);
            
            pst.setString(1, CampoNome.getText());
            pst.setString(2, CampoBI.getText());
            pst.setString(3, CampoNaturalidade.getText());
            pst.setString(4, CampoContacto.getText());
            pst.setString(5, CampoEmail.getText());
            pst.setString(6, String.valueOf(ano));
            pst.setString(7, String.valueOf(sem));
            pst.setString(8, String.valueOf(nivel));
            
            int per = JOptionPane.showConfirmDialog(rootPane, "Acualizar dados do Aluno? ", "Confirme", JOptionPane.YES_NO_OPTION);
        
            if(per == JOptionPane.YES_OPTION){
                int actua = pst.executeUpdate();
                
                if(actua > 0){
                    JOptionPane.showMessageDialog(rootPane, "Dados do Aluno actualizados com sucesso!");
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Actualização nao sucedida!");
                    
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
        
    }

    void apagar() {

        String cmd = "delete from aluno where id = ?";

        int id = Integer.parseInt(CampoID.getText().toString());

        try {
            pst = conexao.prepareStatement(cmd);

            pst.setString(1, String.valueOf(id));

            int per = JOptionPane.showConfirmDialog(rootPane, "Excluir o Aluno? ", "Confirme", JOptionPane.YES_NO_OPTION);

            if (per == JOptionPane.YES_OPTION) {

                if (CampoID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Insira o ID do Professor no respectivo campo");
                } else {
                    int del = pst.executeUpdate();

                    if (del > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Aluno excluido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Exclusão nao sucedida!");

                    }

                }
            }
        } catch (Exception e) {

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
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
        CampoBI = new javax.swing.JTextField();
        CampoNaturalidade = new javax.swing.JTextField();
        CampoEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        CampoID = new javax.swing.JTextField();
        CampoContacto = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        A2022 = new javax.swing.JRadioButton();
        A2023 = new javax.swing.JRadioButton();
        A2024 = new javax.swing.JRadioButton();
        Sem_1 = new javax.swing.JRadioButton();
        Sem_2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        nivel_2 = new javax.swing.JRadioButton();
        nivel_1 = new javax.swing.JRadioButton();
        nivel_3 = new javax.swing.JRadioButton();
        nivel_4 = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setTitle("ALUNOS");

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

        CampoBI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CampoBI.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        CampoNaturalidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CampoNaturalidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        CampoEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CampoEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("ID:");

        CampoID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CampoID.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        try {
            CampoContacto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        CampoContacto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampoEmail)
                            .addComponent(CampoNaturalidade)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CampoBI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CampoID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CampoNome)
                            .addComponent(CampoContacto)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(CampoBI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(CampoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoNaturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INFORMAÇAO ACADEMICA");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Ano Lectivo");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Semestre");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Nível");

        buttonGroup1.add(A2022);
        A2022.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        A2022.setText("2022");

        buttonGroup1.add(A2023);
        A2023.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        A2023.setText("2023");

        buttonGroup1.add(A2024);
        A2024.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        A2024.setText("2024");

        buttonGroup2.add(Sem_1);
        Sem_1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Sem_1.setText("1º Semestre");
        Sem_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sem_1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(Sem_2);
        Sem_2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Sem_2.setText("2º Semestre");

        buttonGroup3.add(nivel_2);
        nivel_2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nivel_2.setText("2º Nível");

        buttonGroup3.add(nivel_1);
        nivel_1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nivel_1.setText("1º Nível");

        buttonGroup3.add(nivel_3);
        nivel_3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nivel_3.setText("3º Nivel");

        buttonGroup3.add(nivel_4);
        nivel_4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nivel_4.setText("4º Nível");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nivel_1)
                    .addComponent(nivel_2)
                    .addComponent(nivel_3)
                    .addComponent(nivel_4))
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(nivel_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nivel_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nivel_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nivel_4))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(A2024)
                                    .addComponent(A2023)
                                    .addComponent(A2022)))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Sem_1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sem_2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(A2022)
                                .addGap(18, 18, 18)
                                .addComponent(A2023)
                                .addGap(18, 18, 18)
                                .addComponent(A2024))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(Sem_1)
                        .addGap(18, 18, 18)
                        .addComponent(Sem_2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        inserir();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        if (CampoID.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Insira o ID do aluno");
        } else {
            apagar();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Sem_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sem_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sem_1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton A2022;
    private javax.swing.JRadioButton A2023;
    private javax.swing.JRadioButton A2024;
    private javax.swing.JTextField CampoBI;
    private javax.swing.JFormattedTextField CampoContacto;
    private javax.swing.JTextField CampoEmail;
    private javax.swing.JTextField CampoID;
    private javax.swing.JTextField CampoNaturalidade;
    private javax.swing.JTextField CampoNome;
    private javax.swing.JRadioButton Sem_1;
    private javax.swing.JRadioButton Sem_2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton nivel_1;
    private javax.swing.JRadioButton nivel_2;
    private javax.swing.JRadioButton nivel_3;
    private javax.swing.JRadioButton nivel_4;
    // End of variables declaration//GEN-END:variables
}
