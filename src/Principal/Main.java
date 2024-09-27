package Principal;

import Vista.FrmBombero;
import Controlador.Ctrl_Bombero;
import Modelo.Bombero;

public class Main {
    public static void main(String[] args) {
        // Configura el look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crea el formulario y el controlador
        FrmBombero vista = new FrmBombero();
        Bombero modelo = new Bombero();
        Ctrl_Bombero controlador = new Ctrl_Bombero(vista, modelo);

        vista.setVisible(true);
    }
}
