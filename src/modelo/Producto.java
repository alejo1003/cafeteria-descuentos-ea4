package modelo;

public abstract class Producto {
    private final String nombre;
    private final double precioBase;

    public Producto(String nombre, double precioBase) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio base no puede ser negativo.");
        }
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    // Polimorfismo: cada tipo de producto puede calcular su precio efectivo
    public abstract double calcularPrecioEfectivo();

    public abstract String getCategoria();
}