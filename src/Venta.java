public class Venta {
    private int id;
    private Cliente cliente;
    private String fecha;
    private DetalleVenta[] detalles;
    private int contador; // Número de detalles agregados
    private double total;

    // Constructor con tamaño máximo de detalles
    public Venta(int id, Cliente cliente, String fecha, int maxDetalles) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.detalles = new DetalleVenta[maxDetalles]; // Inicializa el arreglo
        this.contador = 0;
        this.total = 0;
    }

    // Constructor sin especificar detalles, se asume tamaño máximo 10
    public Venta(int id, Cliente cliente, String fecha) {
        this(id, cliente, fecha, 10);
    }

    // Agrega un producto a la venta si hay stock suficiente
    public boolean agregarProducto(Producto producto, int cantidad) {
        if (contador >= detalles.length) {
            System.out.println("No se pueden agregar más productos a la venta.");
            return false;
        }

        if (!producto.reducirStock(cantidad)) {
            System.out.println("No hay stock suficiente para " + producto.getNombre());
            return false;
        }

        DetalleVenta detalle = new DetalleVenta(producto, cantidad);
        detalles[contador++] = detalle;
        total += detalle.getSubtotal();
        return true;
    }

    // Recalcula el total sumando todos los subtotales
    public double calcularTotal() {
        double suma = 0.0;
        for (int i = 0; i < contador; i++) {
            suma += detalles[i].getSubtotal();
        }
        return suma;
    }

    // Devuelve solo los detalles válidos
    public DetalleVenta[] getDetalles() {
        DetalleVenta[] resultado = new DetalleVenta[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = detalles[i];
        }
        return resultado;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta{id=").append(id)
                .append(", cliente=").append(cliente.getNombre())
                .append(", fecha='").append(fecha)
                .append("', total=").append(total)
                .append(", detalles=[\n");
        for (int i = 0; i < contador; i++) {
            sb.append("  ").append(detalles[i]).append("\n");
        }
        sb.append("]}");
        return sb.toString();
    }
}
