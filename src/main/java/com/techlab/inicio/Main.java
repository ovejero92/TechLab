package com.techlab.inicio;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static boolean SU = false;
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();


    public static void main(String[] args) {
     inicializarProductos();
     menu();
    }
    private static void inicializarProductos(){
        productos.add(new Producto(1,"Café",2000));
        productos.add(new Producto(2,"Tostadas",600));
        productos.add(new Producto(3,"Jugo",400));
    }

    private static void menu() {
        int opcion;
        do{
            System.out.println();
            System.out.println("=".repeat(35));
            System.out.println("--- MENÚ PRINCIPAL - TECHLAB ---");
            System.out.println("=".repeat(35));
            System.out.println("1. Ver productos");
            System.out.println("2. Crear pedido");
            System.out.println("3. Ver pedidos");
            System.out.println("4. Super Usuario");
            if(SU){System.out.println("5. Agregar nuevo producto(SU)");}
            if(SU){System.out.println("6. Cerrar sesión Super Usuario");}
            System.out.println("0. salir");

            opcion = Utilidades.leerEntero(sc,"Ingrese opcion: ");
            switch (opcion) {
                case 1 -> mostrarProductos();
                case 2 -> crearPedido();
                case 3 -> mostrarPedidos();
                case 4 -> superUsuario();
                case 5 -> {if(SU){agregarProducto();}}
                case 6 -> {SU=false;}
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarProductos() {
        System.out.println();
        System.out.println("=".repeat(35));
        System.out.println("--- PRODUCTOS - TECHLAB ---");
        System.out.println("=".repeat(35));
        for(Producto p: productos) {
            System.out.println(p);
        }
    }

    private static void crearPedido() {
        sc.nextLine();
        System.out.println("Nombre del cliente: ");
        String nombre = sc.nextLine();
        System.out.println("Email del cliente: ");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(nombre,email);
        Pedido pedido = new Pedido(cliente);

        boolean seguir = true;
        while (seguir) {
            mostrarProductos();
            int id = Utilidades.leerEntero(sc, "Ingrese ID del producto a agregar (0 para terminar):");
            if (id == 0) break;

            Producto seleccionado = buscarProductoPorId(id);
            if (seleccionado != null) {
                pedido.agregarProducto(seleccionado);
                System.out.println("Producto agregado.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        }
        try {
            if(pedido.calcularTotal() == 0) {
                throw new PedidoException("No se puede crear un pedido vacio.");
            } else {
                pedidos.add(pedido);
                System.out.println("Pedido creado con exito.");
            }
        } catch (PedidoException e) {
            System.out.println("Error al crear pedido: " + e.getMessage());
        }
    }

    private static Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private static void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos aún.");
            return;
        }
        System.out.println();
        System.out.println("=".repeat(35));
        System.out.println("--- Pedidos - TECHLAB ---");
        System.out.println("=".repeat(35));
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }

    private static void superUsuario() {
        sc.nextLine(); // al estar utilizando el metodo anterior se usa este codigo para de una sierta forma limiar el Scaner
        System.out.print("Ingrese su usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contra = sc.nextLine();

        if(usuario.equals("gustavo") && contra.equals("gu123")) {
            SU = true;
            System.out.println("Modo Super Usuario activado ✅");
        } else {
            System.out.println("Credenciales incorrectas ❌");
        }
    }
    private static void agregarProducto() {
        sc.nextLine();
        System.out.print("Nombre del nuevo producto: ");
        String nombre = sc.nextLine();
        int precio = Utilidades.leerEntero(sc, "Precio del producto: ");
        int nuevoId = productos.size() + 1;
        productos.add(new Producto(nuevoId, nombre, precio));
        System.out.println("Producto agregado exitosamente. ✅");
    }
}
