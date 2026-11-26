package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import modelo.CalculadoraDescuento;
import persistencia.ConexionSQLite;

public class VentanaDescuentos extends JFrame {

    private final JTextField txtMontoTotal;
    private final JButton btnCalcularDescuento;
    private final JTextArea txtAreaResultado;

    public VentanaDescuentos() {
        setTitle("Cafetería - Calculadora de Descuentos (EA4)");
        setSize(520, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel panelEntrada = new JPanel(new GridLayout(2, 2, 8, 8));
        JLabel lblMonto = new JLabel("Monto total del pedido ($):");
        txtMontoTotal = new JTextField();
        btnCalcularDescuento = new JButton("Calcular Descuento");
        JLabel lblAyuda = new JLabel("Regla activa: > $10 aplica 15% de descuento");
        lblAyuda.setFont(lblAyuda.getFont().deriveFont(Font.ITALIC, 12f));

        panelEntrada.add(lblMonto);
        panelEntrada.add(txtMontoTotal);
        panelEntrada.add(lblAyuda);
        panelEntrada.add(btnCalcularDescuento);

        txtAreaResultado = new JTextArea();
        txtAreaResultado.setEditable(false);
        txtAreaResultado.setLineWrap(true);
        txtAreaResultado.setWrapStyleWord(true);
        JScrollPane scrollResultado = new JScrollPane(txtAreaResultado);
        scrollResultado.setBorder(BorderFactory.createTitledBorder("Estado de conexión y resultados"));

        panelPrincipal.add(panelEntrada, BorderLayout.NORTH);
        panelPrincipal.add(scrollResultado, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener((ActionEvent e) -> {
            txtMontoTotal.setText("");
            txtAreaResultado.setText(ConexionSQLite.probarConexionInicial() + "\n");
            txtMontoTotal.requestFocusInWindow();
        });
        panelInferior.add(btnLimpiar);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);

        configurarEventos();
        inicializarEstadoBD();
    }

    private void inicializarEstadoBD() {
        String estadoConexion = ConexionSQLite.probarConexionInicial();
        txtAreaResultado.setText(estadoConexion + "\n----------------------------------------\n");
    }

    private void configurarEventos() {
        btnCalcularDescuento.addActionListener((ActionEvent e) -> calcularDescuento());
        txtMontoTotal.addActionListener((ActionEvent e) -> calcularDescuento());
    }

    private void calcularDescuento() {
        String textoIngresado = txtMontoTotal.getText().trim().replace(',', '.');

        if (textoIngresado.isEmpty()) {
            txtAreaResultado.append("Error: por favor ingrese un monto total válido.\n");
            return;
        }

        try {
            double montoOriginal = Double.parseDouble(textoIngresado);
            double montoFinal = CalculadoraDescuento.DESCUENTO_CAFETERIA_LAMBDA.calcularMontoFinal(montoOriginal);
            double ahorro = montoOriginal - montoFinal;

            String reporte = String.format(
                    Locale.US,
                    "Monto ingresado: $%.2f | Descuento aplicado: $%.2f | Monto final: $%.2f%n",
                    montoOriginal,
                    ahorro,
                    montoFinal
            );
            txtAreaResultado.append(reporte);
        } catch (NumberFormatException ex) {
            txtAreaResultado.append("Error de formato: '" + txtMontoTotal.getText().trim() + "' no es un número válido.\n");
        } catch (IllegalArgumentException ex) {
            txtAreaResultado.append("Error de validación: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaDescuentos ventana = new VentanaDescuentos();
            ventana.setVisible(true);
        });
    }
}