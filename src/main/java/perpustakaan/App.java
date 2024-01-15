package perpustakaan;
import com.formdev.flatlaf.FlatDarculaLaf;

public class App {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Inisialisasi frame AdminPerpus
                FlatDarculaLaf.setup();
                perpustakaan.BukuCrudAdmin adminPerpusFrame = new perpustakaan.BukuCrudAdmin();
                adminPerpusFrame.setVisible(true);
            }
        });
    }
}
