package modelo;

public class DescuentoUmbralRegla implements ReglaDescuento {
    private final double umbralMinimo;
    private final double porcentajeDescuento;

    public DescuentoUmbralRegla(double umbralMinimo, double porcentajeDescuento) {
        this.umbralMinimo = umbralMinimo;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double aplicarDescuento(double montoTotal) {
        if (montoTotal > umbralMinimo) {
            return montoTotal * (1.0 - porcentajeDescuento);
        }
        return montoTotal;
    }

    @Override
    public String getDescripcion() {
        int pct = (int) Math.round(porcentajeDescuento * 100);
        return pct + "% de descuento si el total supera $" + umbralMinimo;
    }
}
