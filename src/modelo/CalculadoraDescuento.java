package modelo;

@FunctionalInterface
public interface CalculadoraDescuento {
    double calcularMontoFinal(double montoTotal);

    // Expresión lambda por defecto alineada con el criterio del enunciado (> $10 -> 15% dto, sino 0%)
    CalculadoraDescuento DESCUENTO_CAFETERIA_LAMBDA = total -> {
        if (total < 0) {
            throw new IllegalArgumentException("El monto total no puede ser negativo.");
        }
        return total > 10.0 ? total * 0.85 : total;
    };
}