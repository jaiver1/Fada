/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada;

/**
 *
 * @author PC
 */
import fada.views.Main;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class FADA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
            Main ir = new Main();
            ir.setDefaultCloseOperation(3);
            ir.setLocationRelativeTo(null);
            ir.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

}
