package PracticaEsttructuraConArreglos;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class SistemaVentas {
    private ArrayList<Producto> catalogo;
    private ArrayList<Venta> historialVenta;
    private Scanner entrada;

    public SistemaVentas() {
        catalogo = new ArrayList<>();
        historialVenta = new ArrayList<>();
        entrada = new Scanner(System.in);
        inicializarCatalogo();
    }

    private void inicializarCatalogo() {
        catalogo.add(new Producto("P001", "Laptop", 15999.99, 10));
        catalogo.add(new Producto("P002", "Mouse Gamer", 1299.99, 25));
        catalogo.add(new Producto("P003", "Teclado Mecánico", 2499.99, 15));
        catalogo.add(new Producto("P004", "Monitor", 8999.99, 8));
        catalogo.add(new Producto("P005", "Webcam", 899.99, 30));
        catalogo.add(new Producto("P006", "Audífonos", 3499.99, 20));
        catalogo.add(new Producto("P007", "Disco Duro Externo", 1599.99, 18));
        catalogo.add(new Producto("P008", "Memoria USB", 299.99, 50));
    }

    public void mostarCatalogo() {
        System.out.println("-----------------------------");
        System.out.println("----- Catálogo de Productos -----");
        for (Producto producto : catalogo) {
            System.out.println(producto);
        }
        System.out.println("---------------------------------");

    }

    private Producto buscarProducto(String codigo) {
        for (Producto p : catalogo) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void realizarVenta() {
        Venta ventaActual = new Venta();
        boolean continuarVenta = true;

        System.out.println("----- Iniciando Nueva Venta -----");

        mostarCatalogo();

        while (continuarVenta) {
            System.out.println("Ingrese el código del producto para agregar o escriba 'salir' para finalizar: ");
            String codigo = entrada.nextLine().trim();

            if (codigo.equalsIgnoreCase("salir")) {
                break;
            }

            Producto producto = buscarProducto(codigo);

            if (producto == null) {
                System.out.println("Producto no encontrado. Intente nuevamente.");
                continue;
            }

            System.out.println("Ingrese la cantidad deseada: ");
            int cantidad;

            try {
                cantidad = Integer.parseInt(entrada.nextLine().trim());

                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser mayor a cero. Intente nuevamente.");
                    continue;
                }

                if (!producto.hayStock(cantidad)) {
                    System.out.println("Insuficiente Stock. Stock disponible: " + producto.getStock());
                    continue;
                }

                ItemVenta item = new ItemVenta(producto, cantidad);
                ventaActual.agregarItem(item);

                System.out.printf("Producto agregado: %s x%d = $%.2f%n",
                        producto.getNombre(), cantidad, item.getSubtotal());

            } catch (NumberFormatException e) {
                System.out.println("Cantidad inválida. Debe ingresar un número.");
                continue;
            }
        }

        if (ventaActual.getItems().isEmpty()) {
            System.out.println("No se agregaron productos a la venta. Venta cancelada.");
        } else {
            historialVenta.add(ventaActual);
            ventaActual.mostrarTicket();
        }

    }

    public void mostrarHistorial() {
        if (historialVenta.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        System.out.println("-----------------------------");
        System.out.println("-------Historial de Ventas-------");
        System.out.println("-----------------------------");

        double totalGeneral = 0.0;
        for (Venta v : historialVenta) {
            System.out.println("Venta #" + v.getNumeroVenta() + " - Total: $" + String.format("%.2f", v.getTotal()));
            totalGeneral += v.getTotal();
        }

        System.out.println("-----------------------------");
        System.out.println("Total General de Ventas: $" + String.format("%.2f", totalGeneral));
        System.out.println("-----------------------------");
    }

    public void menuPrincipal() {
        int opcion = 0;

        do {
            System.out.println("===== Bienvenido a nuestro Sistema de Ventas =====");
            System.out.println("1. Realizar Nueva Venta");
            System.out.println("2. Mostrar Catálogo de Productos");
            System.out.println("3. Mostrar Historial de Ventas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(entrada.nextLine().trim());

                switch (opcion) {
                    case 1:
                        realizarVenta();
                        break;
                    case 2:
                        mostarCatalogo();
                        break;
                    case 3:
                        mostrarHistorial();
                        break;
                    case 4:
                        System.out.println("Gracias por usar nuestro sistema. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número valido.");
                opcion = 0;
            }
        } while (opcion != 4);
    }
}

