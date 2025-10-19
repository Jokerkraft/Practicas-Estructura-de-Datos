package PracticaEsttructuraConArreglos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Venta {
    private static int contadorVentas = 0;
    private int numeroVenta;
    private ArrayList<ItemVenta> items;
    private double total;
    private LocalDateTime fechaHora;

    public Venta() {
        this.numeroVenta = ++contadorVentas;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.fechaHora = LocalDateTime.now();
    }

    public void agregarItem(ItemVenta item) {
        items.add(item);
        total += item.getSubtotal();
        item.getProducto().reducirStock(item.getCantidad());
    }

    public int getNumeroVenta() {
        return numeroVenta;
    }

    public ArrayList<ItemVenta> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void mostrarTicket() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd//MM/yyyy HH:mm:ss");
        System.out.println("-------------------------------");
        System.out.println("         TICKET DE VENTA       ");
        System.out.println("Fecha y Hora: " + fechaHora.format(formato));
        System.out.printf("Venta #: %d%n", numeroVenta);
        System.out.println("-------------------------------");
        for (ItemVenta item : items) {
            System.out.println("Producto: " + item.getProducto().getNombre() +
                    " | Cantidad: " + item.getCantidad() +
                    " | Subtotal: $" + String.format("%.2f", item.getSubtotal()));

        }
        System.out.println("-------------------------------");
        System.out.println("Total a Pagar: $" + String.format("%10.2f", total));
        System.out.println("!Gracias por su compra, vuelva pronto!");
        System.out.println("-------------------------------");

    }
}
