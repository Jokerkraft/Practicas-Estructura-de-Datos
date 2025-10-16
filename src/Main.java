public class Main {
    public static void main(String[] args) {

        // Crear cliente, productos y venta
        Cliente cliente = new Cliente(20, "Carlos Ramírez", "Calle Tecnología 101");
        Producto p1 = new Producto(21, "Mouse Gamer", 350.0, 15);
        Producto p2 = new Producto(22, "Teclado Mecánico", 1200.0, 5);
        Producto p3 = new Producto(23, "Auriculares", 800.0, 0); // sin stock
        Venta venta = new Venta(1621, cliente, "2024-06-15");

        // Mostrar estado de stock inicial
        System.out.println("--- Stock inicial ---");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // Agregar productos a la venta
        System.out.println("\n--- Agregando productos a la venta ---");
        System.out.println("Agregando 2 mouse gamers: " + venta.agregarProducto(p1, 2));
        System.out.println("Agregando 1 teclado mecánico: " + venta.agregarProducto(p2, 1));
        System.out.println("Agregando 1 auricular: " + venta.agregarProducto(p3, 1)); // debería fallar por stock

        // Estado final de la venta
        System.out.println("\n--- Estado actual de la venta ---");
        System.out.println("Venta actual:");
        System.out.println(venta);

        // Mostrar detalles de la venta
        System.out.println("\n--- Detalles de la venta ---");
        System.out.println("Detalles:");
        for (DetalleVenta detalles : venta.getDetalles()) {
            System.out.println(detalles);
        }

        // Mostrar totales
        System.out.println("\n--- Totales ---");
        System.out.println("Total (recalculado): " + venta.calcularTotal());
        System.out.println("Total (almacenado): " + venta.getTotal());

        // Estado final de stock
        System.out.println("\n--- Stock final ---");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
    }
}
