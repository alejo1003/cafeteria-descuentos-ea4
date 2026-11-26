package modelo;

public class Comida extends Producto {
    private final boolean esCombo;

    public Comida(String nombre, double precioBase, boolean esCombo) {
        super(nombre, precioBase);
        this.esCombo = esCombo;
    }

    public boolean isEsCombo() {
        return esCombo;
    }

    @Override
    public double calcularPrecioEfectivo() {
        // Ejemplo de polimorfismo: leve ajuste si es combo
        return esCombo ? getPrecioBase() * 0.95 : getPrecioBase();
    }

    @Override
    public String getCategoria() {
        return "Comida";
    }
}