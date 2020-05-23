/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CtlPrestamo;
import Model.Prestamo;
import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Vista extends javax.swing.JFrame {
    ArrayList<Prestamo> empleado;
    CtlPrestamo db = new CtlPrestamo();
    
    public Vista() {
        initComponents();
        setTitle("REGISTRO");
        setLocationRelativeTo(null);
    }
    
    public void LimpiarFormulario() {
        DefaultTableModel tb = (DefaultTableModel) tblTabla.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }
    
    public void ListarDatos() {
        empleado = db.ListEmpleados();
        DefaultTableModel tb = (DefaultTableModel) tblTabla.getModel();
        for (Prestamo cl : empleado) {
            LocalDateTime locaDate = LocalDateTime.now();
                int hours  = locaDate.getHour();
                int minutes = locaDate.getMinute();
            
            String cadena = String.valueOf(hours);
            String cadena2 = String.valueOf(minutes);
            if(minutes<10){
                cadena2 = "0"+String.valueOf(minutes);
            }
            if((hours<10)&&(hours>0)){
                cadena = "0"+String.valueOf(hours);
            }
            
            String fecha=cadena+":"+cadena2;
            int lengt = cl.getFecha().length();
            LocalTime entrada;
            if (lengt==4){
                entrada = LocalTime.parse("0"+cl.getFecha());
            }else{
                entrada = LocalTime.parse(cl.getFecha());
            } 
            
            LocalTime actual = LocalTime.parse(fecha);
            int minute = (int) ChronoUnit.MINUTES.between(entrada, actual);
            int tiempo=60*4;
            if ("Bicicleta".equals(cl.getPrestamo())){
                tiempo=60*2;
            }
            if ("Casco".equals(cl.getPrestamo())){
                tiempo=60*8;
            }
          
            if((minute >= tiempo)||((minute<0))&& ("NO TIENE".equals(cl.getMora()))) {
                String newmora="TIENE";
                Prestamo c2=new Prestamo(cl.getCodigo()+" ",cl.getPrestamo(),cl.getFecha(),cl.getFechaTotal(),newmora);
                
                tb.addRow(new Object[]{cl.getCodigo(),cl.getPrestamo(), cl.getFecha(),cl.getFechaTotal(),newmora});
                borrarEmpleados(cl.getCodigo());
                db.insertarEmpleados(c2);
                
            }else{
                tb.addRow(new Object[]{cl.getCodigo(),cl.getPrestamo(), cl.getFecha(),cl.getFechaTotal(), cl.getMora()});
            }
        }
    }
    
    public void borrarEmpleados(String value) {
        try {
            Connection cnx = Model.Conexion.getConnection();
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM CEIISS WHERE CODIGO=" + "\'" + value + "\'");
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Delete");
        }
    }
    public void ListarDatos2() {
        empleado = db.ListEmpleados2(TipoBuscador.getSelectedIndex(),String.valueOf(txtBuscar.getText()));
        DefaultTableModel tb = (DefaultTableModel) tblTabla.getModel();
        for (Prestamo cl : empleado) {
            tb.addRow(new Object[]{cl.getCodigo(),cl.getPrestamo(), cl.getFecha(),cl.getFechaTotal(), cl.getMora()});
        }
    }
    
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TipoPrestamo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnLista = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        TipoBuscador = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Codigo:");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Articulo:");

        TipoPrestamo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Calculadora", "Bicicleta", "Mandil", "Pelota Futbol", "Pelota Basquet", "Pelota Voley", "Net Voley", "Ajedrez", "Guitarra", "Escuadra", "Pistolete", "Escalimetro", "Compas", "Casco" }));
        TipoPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoPrestamoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("PRESTAMOS CEIIS");

        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnLista.setText("LISTAR");
        btnLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tblTabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Prestamo", "Hora", "Fecha", "Mora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTabla);

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        TipoBuscador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CODIGO", "PRESTAMO" }));
        TipoBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoBuscadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(TipoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TipoBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(TipoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TipoBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void TipoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoPrestamoActionPerformed

    private void TipoBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoBuscadorActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        LimpiarFormulario();        
        ListarDatos();
    }//GEN-LAST:event_btnListaActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        System.out.println(txtCodigo.getText());
    if (!"".equals(txtCodigo.getText())&& txtCodigo.getText().length()==9){
            String mora="NO TIENE";
            String prestamo="Ninguno";
            
            LocalDateTime locaDate = LocalDateTime.now();
                int hours  = locaDate.getHour();
                int minutes = locaDate.getMinute();
                int dia = locaDate.getDayOfMonth();
                int mes = locaDate.getMonthValue();
                int ano = locaDate.getYear();
            
            String cadena = String.valueOf(hours);
            String cadena2 = String.valueOf(minutes);
            String dia1= String.valueOf(dia);
            String mes1= String.valueOf(mes);
            String ano1= String.valueOf(ano);
            
            if(minutes<10){
                cadena2 = "0"+String.valueOf(minutes);
            } 
            if(mes<10){
                mes1 = "0"+String.valueOf(mes);
            } 
            if(TipoPrestamo.getSelectedIndex()==0){
                prestamo="Calculadora";
            }
            if(TipoPrestamo.getSelectedIndex()==2){
                prestamo="Mandil";
            }
            if(TipoPrestamo.getSelectedIndex()==1){
                prestamo="Bicicleta";
            }
            if(TipoPrestamo.getSelectedIndex()==3){
                prestamo="Pelota Futbol";
            }
            if(TipoPrestamo.getSelectedIndex()==4){
                prestamo="Pelota Basquet";
            }
            if(TipoPrestamo.getSelectedIndex()==5){
                prestamo="Pelota Voley";
            }
            if(TipoPrestamo.getSelectedIndex()==6){
                prestamo="Net Voley";
            }
            if(TipoPrestamo.getSelectedIndex()==7){
                prestamo="Ajedrez";
            }
            if(TipoPrestamo.getSelectedIndex()==8){
                prestamo="Guitarra";
            }
            if(TipoPrestamo.getSelectedIndex()==9){
                prestamo="Escuadra";
            }
            if(TipoPrestamo.getSelectedIndex()==10){
                prestamo="Pistolete";
            }
            if(TipoPrestamo.getSelectedIndex()==11){
                prestamo="Escalimetro";
            }
            if(TipoPrestamo.getSelectedIndex()==12){
                prestamo="Compas";
            }
            if(TipoPrestamo.getSelectedIndex()==13){
                prestamo="Casco";
            }
            
            String fecha=cadena+":"+cadena2;
            String fechat= dia1+"/"+mes1+"/"+ano1;
            int a=1;
            
            Prestamo cl=new Prestamo(String.valueOf(txtCodigo.getText()),prestamo,fecha,fechat,mora);
            JOptionPane.showMessageDialog(this, "Datos Ingresados correctos", "", JOptionPane.INFORMATION_MESSAGE);
            db.insertarEmpleados(cl);
            txtCodigo.setText(null);
            

        } else {
            JOptionPane.showMessageDialog(this, "Error al ingresar codigo", "", JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DefaultTableModel model= (DefaultTableModel) tblTabla.getModel();
        int fila=tblTabla.getSelectedRow();
        int colum=0;
        if (fila>=0){
            String value = (String)tblTabla.getValueAt(fila, colum);
            model.removeRow(fila);
            borrarEmpleados(value);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        Connection cn;
        char tecla= evt.getKeyChar();
        if (tecla==KeyEvent.VK_ENTER){
            LimpiarFormulario();
            ListarDatos2();
            txtBuscar.setText(null);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLowerCase(c)){
            String cad=(""+c).toUpperCase();
            c=cad.charAt(0);
            evt.setKeyChar(c);
            
        } else {
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

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
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
                
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> TipoBuscador;
    private javax.swing.JComboBox<String> TipoPrestamo;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLista;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTabla;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
