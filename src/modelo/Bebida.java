package modelo;

public class Bebida extends Producto {
    private final boolean esCaliente;

    public Bebida(String nombre, double precioBase, boolean esCaliente) {
        super(nombre, precioBase);
        this.esCaliente = esCaliente;
    }

    public boolean isEsCaliente() {
        return esCaliente;
    }

    @Override
    public double calcularPrecioEfectivo() {
        // Ejemplo de polimorfismo: recargo leve si es bebida caliente especial
        return esCaliente ? getPrecioBase() * 1.05 : getPrecioBase();
    }

    @Override
    public String getCategoria() {
        return "Bebida";
    }
}
