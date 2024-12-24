package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.Pasta;
import ar.edu.unlam.pb1.dominio.FabricaDePastas;
import ar.edu.unlam.pb1.dominio.enums.TipoDePasta;
import ar.edu.unlam.pb1.interfaz.enums.MenuPrincipal;

public class AdministrarFabricaDePasta {
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		String nombrePedido = ingresarString("\nIngrese el nombre del pedido:");
		int opcion = 0;

		FabricaDePastas fabricaDePastas = new FabricaDePastas(nombrePedido);
		MenuPrincipal opcionMenu = null;

		do {
			mostrarPorPantalla(
					"\n\n*****************************************\n\tPEDIDO " + fabricaDePastas.getNombrePedido());

			opcionMenu = ingresarOpcionDeMenuPrincipalValida();

			switch (opcionMenu) {
			case MOSTRAR_PASTA_DE_UN_TIPO_DE_PASTA_CON_MENOR_CANTIDAD_EN_El_PEDIDO:
				mostrarPastaDeUnTipoDePastaConMenorCantidadEnElPedido(fabricaDePastas);
				break;
			case AGREGAR_PASTA_A_PEDIDO:
				agregarPastaAPedido(fabricaDePastas);
				break;
			case MOSTRAR_PEDIDO_ORDENADO_POR_PRECIO_DE_PASTA_DESCENDENTE:
				mostrarPedidoOrdenadoPorPrecioDeOPastaDescendente(fabricaDePastas);
				break;
			case MOSTRAR_TOTAL_DEL_PEDIDO:
				mostrarTotalDelPedido(fabricaDePastas);
				break;
			case SALIR:
				mostrarPorPantalla("Hasta pronto!");
				break;
			}
		} while (opcionMenu != MenuPrincipal.SALIR);

	}

	private static void mostrarTotalDelPedido(FabricaDePastas fabricaDePastas) {
		// TODO: Se debe obtener el total del pedido desde la fabrica y luego mostrarlo.
		mostrarPorPantalla("\nTotal del pedido = " + fabricaDePastas.obtenerTotalDelPedido());
	}

	private static void mostrarPedidoOrdenadoPorPrecioDeOPastaDescendente(FabricaDePastas fabricaDePastas) {
		// TODO: Se debe obtener el pedido de la fabrica y mostrar las pastas en el
		// mismo.
		mostrarArrayDePastas(fabricaDePastas.obtenerPedidoPorPrecioDePastaDescendente());
	}

	private static void mostrarPastaDeUnTipoDePastaConMenorCantidadEnElPedido(FabricaDePastas fabricaDePastas) {
		// TODO: Se debe mostrar los tipos de pasta disponibles, solicitar la opcion
		// deseada como texto y luego obtener desde la fabrica la pasta del TipoDePasta
		// ingresado con menor cantidad en el pedido. Mostrar la pasta
		// obtenida.

		TipoDePasta opcion = null;
		String opcionA = "";
		mostrarTiposDePasta();
		int indice = 0;
		boolean seEncontro = false;
		opcionA = ingresarString("\nIngrese el tipo de pasta: ");
		// opcionA = ingresarString("\nIngrese el tipo de pasta: ");

		while (indice < TipoDePasta.values().length && !seEncontro) {
			if (TipoDePasta.values()[indice] != null
					&& TipoDePasta.values()[indice].toString().equalsIgnoreCase(opcionA)) {
				opcion = TipoDePasta.values()[indice];
				seEncontro = true;
			}
			indice++;
		}

		if (fabricaDePastas.obtenerPastaDeUnTipoDePastaConMenorCantidadEnElPedido(opcion) == null) {
			mostrarPorPantalla("\nNo existen pastas de este tipo dentro del pedido.");
		} else {
			mostrarPorPantalla(
					fabricaDePastas.obtenerPastaDeUnTipoDePastaConMenorCantidadEnElPedido(opcion).toString());
		}

	}

	private static void agregarPastaAPedido(FabricaDePastas fabricaDePastas) {
		// TODO: Debe mostrar las pastas disponibles en la fabrica, solicitar el ingreso
		// del codigo de la pasta deseada, la cantidad que se quiere agregar al pedido
		// (debe ser mayor a cero) y luego agregarla al pedido de la fabrica. Mostrar un
		// mensaje de exito si fue posible agregar al pedido o de error en caso
		// contrario.

		mostrarArrayDePastas(fabricaDePastas.getPastas());

		String codigo = ingresarString("\nIngrese el codigo de la pasta deseada: ");
		int cantidad = 0;
		do {
			cantidad = ingresarEntero("\nIngrese la cantidad de pastas deseada:");
		} while (cantidad <= 0);

		if (fabricaDePastas.agregarPastaAlPedido(codigo, cantidad) == true) {
			mostrarPorPantalla("\nPastas agregadas con exito!");
		} else {
			mostrarPorPantalla("\nAlgo salio mal :/");
		}

	}

	private static MenuPrincipal ingresarOpcionDeMenuPrincipalValida() {
		// TODO: Se debe mostrar el menu principal y solicitar el ingreso de la opcion
		// deseada. Se debe validar que la opcion ingresada sea valida para el menu
		// mostrado y luego devolverla.
		// REVISAR
		MenuPrincipal menuPrincipal = null;
		int numeroIngresado = 0;
		do {
			mostrarMenuPrincipal();
			mostrarPorPantalla("\nIngrese la opcion deseada: ");
			numeroIngresado = teclado.nextInt();
		} while (numeroIngresado < 0 || numeroIngresado > MenuPrincipal.values().length);

		menuPrincipal = MenuPrincipal.values()[numeroIngresado - 1];

		return menuPrincipal;
	}

	private static TipoDePasta ingresarOpcionDeMenuPrincipalTipoDePastaValida() {
		// TODO: Se debe mostrar el menu principal y solicitar el ingreso de la opcion
		// deseada. Se debe validar que la opcion ingresada sea valida para el menu
		// mostrado y luego devolverla.
		// REVISAR
		TipoDePasta menuPrincipal = null;
		int numeroIngresado = 0;
		do {
			mostrarPorPantalla("\nIngrese la opcion deseada: ");

			numeroIngresado = teclado.nextInt();
		} while (!(TipoDePasta.FIDEOS.ordinal() < numeroIngresado)
				|| !(TipoDePasta.CANELONES.ordinal() > numeroIngresado));

		menuPrincipal = TipoDePasta.values()[numeroIngresado - 1];

		return menuPrincipal;
	}

	private static void mostrarMenuPrincipal() {
		String menu = "\n*****Menu Pedido Pastas On line*****\n";
		for (int i = 0; i < MenuPrincipal.values().length; i++) {
			menu += (i + 1) + "- " + MenuPrincipal.values()[i].getDescripcion() + "\n";
		}
		mostrarPorPantalla(menu);
	}

	private static void mostrarTiposDePasta() {
		String tiposDePasta = "\n*****Tipos de pasta*****\n";
		for (int i = 0; i < TipoDePasta.values().length; i++) {
			tiposDePasta += TipoDePasta.values()[i] + "\n";
		}

		mostrarPorPantalla(tiposDePasta);
	}

	private static int ingresarEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next();
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

	private static void mostrarArrayDePastas(Pasta[] pastas) {
		for (int i = 0; i < pastas.length; i++) {
			if (pastas[i] != null) {
				mostrarPorPantalla(pastas[i].toString());
			}
		}
	}
}
