import javax.swing.JOptionPane;

public class Exemplo {
    public static void main(String[] args) {
        int opcao = JOptionPane.showConfirmDialog(null, "Você deseja continuar?");
        
        if (opcao == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Você escolheu SIM!");
        } else {
            JOptionPane.showMessageDialog(null, "Você escolheu NÃO!");
        }
    }
}
