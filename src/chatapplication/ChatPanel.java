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
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Notebook
 */
public class ChatPanel extends javax.swing.JPanel {

    /**
     * Creates new form ChatPanel
     */
    //socket chat
    private Socket socket = null;
    private BufferedReader bf = null;
    private DataOutputStream os = null;
    private OutputThread t = null;
    private String sender;
    private String receiver;
    //socket sendfile
    private Socket socketFile;

    public ChatPanel(Socket s, String sender, String receiver) {
        initComponents();
        socket = s;
        this.sender = sender;
        this.receiver = receiver;
        try {
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new DataOutputStream(socket.getOutputStream());
            t = new OutputThread(s, txtMessages, sender, receiver);
            t.start();
            txtMessages.setCaretPosition(txtMessages.getDocument().getLength());
        } catch (Exception e) {
        }
    }

    public void scrollToLastLine() {
        DefaultCaret caret = (DefaultCaret) txtMessages.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public JTextArea getTxtMessages() {
        return this.txtMessages;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        txtMessages = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnSend = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(550, 225));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        txtMessages.setColumns(20);
        txtMessages.setRows(5);
        scrollPane.setViewportView(txtMessages);

        add(scrollPane);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Message", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 255))); // NOI18N
        jPanel1.setMinimumSize(new java.awt.Dimension(126, 1));
        jPanel1.setPreferredSize(new java.awt.Dimension(344, 10));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        txtMessage.setMinimumSize(new java.awt.Dimension(4, 1));
        txtMessage.setPreferredSize(new java.awt.Dimension(164, 1));
        jScrollPane2.setViewportView(txtMessage);

        jPanel1.add(jScrollPane2);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnSend.setText("Send");
        btnSend.setMinimumSize(new java.awt.Dimension(57, 1));
        btnSend.setPreferredSize(new java.awt.Dimension(57, 1));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });
        jPanel2.add(btnSend);

        jPanel1.add(jPanel2);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        if (txtMessages.getText().trim().length() == 0) {
            return;
        }
        try {
//            os.writeBytes(txtMessage.getText());
            char x = (char) 6;
            String subString = txtMessage.getText().replace('\n', x);
            os.writeBytes(subString);
            os.write(13);
            os.write(10);
            os.flush();
            this.txtMessages.append("\n" + sender + ": " + txtMessage.getText());
            txtMessage.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextArea txtMessages;
    // End of variables declaration//GEN-END:variables
}
