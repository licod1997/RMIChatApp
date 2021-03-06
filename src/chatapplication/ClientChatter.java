/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Notebook
 */
public class ClientChatter extends javax.swing.JFrame {

    /**
     * Creates new form ClientChatter
     */
    private Socket mngSocket = null;
    private String mngIP = "";
    private int mngPort = 0;
    private String staffName = "";
    private BufferedReader bf = null;
    private DataOutputStream os = null;
    private OutputThread t = null;
    private Socket socketFile = null;

    public ClientChatter() {
        initComponents();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
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
        txtStaff = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtServerIP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtServerPort = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Staff and Server Info.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 51, 255))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Staff:");
        jPanel1.add(jLabel1);

        txtStaff.setText("Hoa");
        jPanel1.add(txtStaff);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Mng IP:");
        jPanel1.add(jLabel2);

        txtServerIP.setText("127.0.0.3");
        txtServerIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServerIPActionPerformed(evt);
            }
        });
        jPanel1.add(txtServerIP);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Port:");
        jPanel1.add(jLabel3);

        txtServerPort.setText("12340");
        jPanel1.add(txtServerPort);

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });
        jPanel1.add(btnConnect);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtServerIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServerIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServerIPActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        // TODO add your handling code here:
        mngIP = this.txtServerIP.getText();
        mngPort = Integer.parseInt(this.txtServerPort.getText());
        staffName = this.txtStaff.getText();
        try {
            mngSocket = new Socket(mngIP, mngPort);
            socketFile = new Socket(mngIP, mngPort + 1);
            if (mngSocket != null) {
                ChatPanel p = new ChatPanel(mngSocket, staffName, "Manager");
                this.getContentPane().add(p);
                SendFile s = new SendFile(socketFile, staffName, "Manager");
                this.getContentPane().add(s);
                p.getTxtMessages().append("Manager is running\n");
                p.updateUI();
                bf = new BufferedReader(new InputStreamReader(mngSocket.getInputStream()));
                os = new DataOutputStream(mngSocket.getOutputStream());
                os.writeBytes("Staff:" + staffName);
                os.write(13);
                os.write(10);
                os.flush();
                pack();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Manager is not running.");
            System.exit(0);
        }
        btnConnect.setEnabled(false);
    }//GEN-LAST:event_btnConnectActionPerformed
    
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
            java.util.logging.Logger.getLogger(ClientChatter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientChatter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientChatter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientChatter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientChatter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtServerIP;
    private javax.swing.JTextField txtServerPort;
    private javax.swing.JTextField txtStaff;
    // End of variables declaration//GEN-END:variables
}
