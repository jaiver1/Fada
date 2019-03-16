/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada.views;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import fada.controllers.Controller;
import fada.views.include.Map;
import fada.views.include.Config;

/**
 *
 * @author PC
 */
public class Main extends javax.swing.JFrame {

    private final JDesktopPane desktop;
    private final JInternalFrame config;
    private final JInternalFrame map;

    /**
     * Creates new form Main
     */
    public Main() {
        setExtendedState(Frame.MAXIMIZED_BOTH);
        initComponents();
        desktop = new Escritorio(this);
        setLayout(new BorderLayout());
        desktop.setSize(getSize());
        add(desktop, BorderLayout.CENTER);
        map = new Map();
        config = new Config();
        setIconImage(Controller.app_icon.getImage());
    }

    private void Abrir(JInternalFrame ir) throws Exception {

        if (Controller.config == null){
          JOptionPane.showMessageDialog(this, "Seleccione un archivo con extension (.txt) para cargar los datos.", "Alerta", JOptionPane.WARNING_MESSAGE);
        } else {
             if (ir.getParent() == null && !ir.isIcon()) {
                desktop.add(ir);
                ir.setVisible(true);
                ir.setMaximum(true);
                ir.moveToFront();
            } else {
                ir.setVisible(true);
                ir.setIcon(false);
                ir.setSelected(true);
                ir.moveToFront();
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

        user_bar = new javax.swing.JMenuBar();
        user_menu = new javax.swing.JMenu();
        import_item = new javax.swing.JMenuItem();
        config_item = new javax.swing.JMenuItem();
        map_item = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FADA");

        user_menu.setForeground(new java.awt.Color(204, 0, 51));
        user_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fada/views/resources/Menu.png"))); // NOI18N
        user_menu.setText("Menu");
        user_menu.setToolTipText("Menu de usuario");
        user_menu.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        user_menu.setName(""); // NOI18N

        import_item.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        import_item.setForeground(new java.awt.Color(204, 0, 51));
        import_item.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fada/views/resources/Import.png"))); // NOI18N
        import_item.setText("Subir Archivo");
        import_item.setToolTipText("Subir archivo con extension (.txt) para obtener la información.");
        import_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                import_itemActionPerformed(evt);
            }
        });
        user_menu.add(import_item);

        config_item.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        config_item.setForeground(new java.awt.Color(204, 0, 51));
        config_item.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fada/views/resources/Config.png"))); // NOI18N
        config_item.setText("Configuración");
        config_item.setToolTipText("Configurar la información del archivo.");
        config_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                config_itemActionPerformed(evt);
            }
        });
        user_menu.add(config_item);

        map_item.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        map_item.setForeground(new java.awt.Color(204, 0, 51));
        map_item.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fada/views/resources/Map.png"))); // NOI18N
        map_item.setText("Mapa");
        map_item.setToolTipText("Ver mapa y ruta optima.");
        map_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                map_itemActionPerformed(evt);
            }
        });
        user_menu.add(map_item);

        user_bar.add(user_menu);

        setJMenuBar(user_bar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void map_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_map_itemActionPerformed
        // TODO add your handling code here:
        try {
            Abrir(map);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_map_itemActionPerformed

    private void config_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_config_itemActionPerformed
        try {
            Abrir(config);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_config_itemActionPerformed

    private void import_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_import_itemActionPerformed
        try {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Texto (*.txt)", "txt");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filtro);
            int seleccion = fc.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                Controller.extraerDatos(fc.getSelectedFile());
                JOptionPane.showMessageDialog(this, "Los datos han sido importados", "Exito", -1, Controller.check_icon);
            }
        } catch (Exception error) {
            Controller.config = null;
            JOptionPane.showMessageDialog(this, error.getMessage(), "Error", 0);
        }
    }//GEN-LAST:event_import_itemActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    private class Escritorio extends JDesktopPane {

        private final ImageIcon icon;
        private final Image image;
        private final JFrame container;

        public Escritorio(JFrame container) {
            this.container = container;
            icon = new ImageIcon(getClass().getResource("resources/Background.jpg"));
            image = icon.getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, container.getWidth(), container.getHeight(), this);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem config_item;
    private javax.swing.JMenuItem import_item;
    private javax.swing.JMenuItem map_item;
    private javax.swing.JMenuBar user_bar;
    private javax.swing.JMenu user_menu;
    // End of variables declaration//GEN-END:variables
}
