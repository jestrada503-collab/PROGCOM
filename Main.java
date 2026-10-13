import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean estado = true;
        int opcion;
        double pagar = 0;
        String rta = "";
        String metodo = "";

        String[] tipo = {"Rosa", "Tulipan", "Girasol", "Lirio"};
        int[] cantidad = {50, 30, 20, 15};
        double[] precio = {2000, 3000, 5000, 4000};

        String[] compFlor = new String[100];
        int[] compCantidad = new int[100];
        double[] compSubtotal = new double[100];
        int compras = 0;

        System.out.println("Bienvenido a mi floristeria.");

        while (estado) {

            System.out.println("*** MENU ***");
            System.out.println("Que deseas hacer?");
            System.out.println("1. Visualizar nuestras flores");
            System.out.println("2. Comprar");
            System.out.println("3. Pagar");
            System.out.println("4. Factura de compra");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("\nEstas son nuestras flores:\n");

                    for (int f = 0; f < tipo.length; f++) {
                        System.out.println(tipo[f] + ": "
                                + cantidad[f] + " disponibles, valor por unidad: $"
                                + precio[f]);
                    }
                    System.out.println();
                    break;

                case 2:
                    System.out.println("\nQue flores desea comprar?");
                    System.out.print("Ingrese el nombre de la flor: ");

                    String florac = sc.nextLine().trim();
                    int n = -1;

                    for (int i = 0; i < tipo.length; i++) {
                        if (tipo[i].equalsIgnoreCase(florac)) {
                            n = i;
                            break;
                        }
                    }

                    if (n != -1) {

                        if (cantidad[n] > 0) {

                            System.out.print("Cuantas unidades desea comprar? ");
                            int cantComprar = sc.nextInt();
                            sc.nextLine();

                            if (cantComprar <= 0) {
                                System.out.println("La cantidad debe ser mayor a 0.");

                            } else if (cantComprar <= cantidad[n]) {

                                double costoTotal = cantComprar * precio[n];

                                cantidad[n] = cantidad[n] - cantComprar;

                                compFlor[compras] = tipo[n];
                                compCantidad[compras] = cantComprar;
                                compSubtotal[compras] = costoTotal;
                                compras++;

                                pagar = pagar + costoTotal;

                                System.out.println("Se agregaron "
                                        + cantComprar + " " + tipo[n]
                                        + "(s) al carrito.\n");

                            } else {
                                System.out.println("No hay suficiente stock. "
                                        + "Solo tenemos " + cantidad[n]
                                        + " unidades.");
                            }

                        } else {
                            System.out.println("No quedan unidades de esa flor.");
                        }

                    } else {
                        System.out.println("Esa flor no existe en nuestro catalogo.");
                    }
                    break;

                case 3:
                    if (pagar > 0) {

                        System.out.print("Metodo de pago "
                                + "(Efectivo, Tarjeta Debito, "
                                + "Tarjeta Credito, PSE): ");
                        metodo = sc.nextLine();

                        double total = pagar + (pagar * 0.19);

                        System.out.println("Subtotal: $" + pagar);
                        System.out.println("Total con IVA del 19%: $" + total);

                        System.out.print("Desea pagar? (Si/No): ");
                        rta = sc.nextLine().trim();

                        if (rta.equalsIgnoreCase("Si")
                                || rta.equalsIgnoreCase("Sí")) {
                            rta = "Si";
                            System.out.println("Gracias por su compra.");

                        } else if (rta.equalsIgnoreCase("No")) {
                            rta = "No";
                            System.out.println("Debes realizar el pago.");

                        } else {
                            rta = "";
                            System.out.println("Ingresa una respuesta valida.");
                        }

                    } else {
                        System.out.println("Primero debes comprar flores.");
                    }
                    break;

                case 4:
                    if (rta.equals("Si")) {

                        System.out.println("\n*** FACTURA DE COMPRA ***");
                        System.out.println("Productos comprados:");

                        for (int i = 0; i < compras; i++) {
                            System.out.println("Flor: " + compFlor[i]);
                            System.out.println("Cantidad: " + compCantidad[i]);
                            System.out.println("Subtotal: $" + compSubtotal[i]);
                            System.out.println("----------------------");
                        }

                        System.out.println("Subtotal de la compra: $" + pagar);
                        System.out.println("IVA del 19%: $" + (pagar * 0.19));
                        System.out.println("Total pagado: $" + (pagar * 1.19));
                        System.out.println("Metodo de pago: " + metodo);

                    } else {
                        System.out.println("Primero debes pagar.");
                    }
                    break;

                case 5:
                    System.out.println("Hasta pronto.");
                    estado = false;
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

            System.out.println();
        }

        sc.close();
    }
}