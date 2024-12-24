package ar.edu.unlam.pb1.dominio;

import java.util.Arrays;
import java.util.Iterator;

import ar.edu.unlam.pb1.dominio.enums.TipoDePasta;

public class FabricaDePastas {

	private final int CANTIDAD_MAXIMA_DE_PASTAS_EN_UN_PEDIDO = 300;
	private String nombrePedido;
	private Pasta[] pastas;
	private Pasta[] pedido;

	public FabricaDePastas(String nombrePedido) {
		this.nombrePedido = nombrePedido;
		this.pastas = new Pasta[TipoDePasta.values().length];
		this.pedido = new Pasta[CANTIDAD_MAXIMA_DE_PASTAS_EN_UN_PEDIDO];
		this.inicializarFabricaDePastas();
	}

	public boolean agregarPastaAlPedido(String codigoPasta, int cantidad) {

		Pasta pasta = obtenerPastaPorCodigo(codigoPasta);
		int indice = 0;
		boolean seAgrego = false;
		Pasta pedido = new Pasta(codigoPasta, pasta.getTipoDePasta(), pasta.isEsRellena(), pasta.getPrecio(), cantidad);

		if (pasta != null && pasta.getCantidad() >= cantidad) {
			while (indice < this.pedido.length && !seAgrego) {
				if (this.pedido[indice] == null) {
					this.pedido[indice] = pedido;
					descontarCantidadDePastaDisponible(codigoPasta, cantidad);
					seAgrego = true;
					this.pedido[indice] = pedido;
				}
				indice++;
			}

		}

		return seAgrego;
	}

	private Pasta obtenerPastaPorCodigo(String codigoPasta) {
		boolean seEncontro = false;
		int indice = 0;
		Pasta pastaEncontrada = null;
		while (indice < this.pastas.length && !seEncontro) {

			if (this.pastas[indice] != null && this.pastas[indice].getCodigo().equals(codigoPasta)) {
				pastaEncontrada = this.pastas[indice];
				seEncontro = true;
			}

			indice++;
		}
		return pastaEncontrada;

	}

	private void descontarCantidadDePastaDisponible(String codigoPasta, int cantidadADescontar) {

		boolean seEncontro = false;
		int indice = 0, cantidadActualizada = 0;
		while (indice < this.pastas.length && !seEncontro) {

			if (this.pastas[indice] != null && this.pastas[indice].getCodigo().equals(codigoPasta)) {
				cantidadActualizada = this.pastas[indice].getCantidad() - cantidadADescontar;
				this.pastas[indice].setCantidad(cantidadActualizada);
				seEncontro = true;
			}
			indice++;
		}

	}

	public double obtenerTotalDelPedido() {

		double montoTotalDelPedido = 0;
		for (int indice = 0; indice < this.pedido.length; indice++) {
			if (this.pedido[indice] != null) {
				montoTotalDelPedido += this.pedido[indice].getPrecio() * this.pedido[indice].getCantidad();
			}
		}

		return montoTotalDelPedido;
	}

	public Pasta obtenerPastaDeUnTipoDePastaConMenorCantidadEnElPedido(TipoDePasta tipoDePasta) {

		Pasta pastaDelTipoConMenorCantidad = null;

		for (int i = 0; i < this.pedido.length; i++) {
			if (this.pedido[i] != null && this.pedido[i].getTipoDePasta().equals(tipoDePasta)) {
				if (pastaDelTipoConMenorCantidad == null
						|| this.pedido[i].getCantidad() < pastaDelTipoConMenorCantidad.getCantidad()) {
					pastaDelTipoConMenorCantidad = this.pedido[i];
				}
			}
		}

		return pastaDelTipoConMenorCantidad;

	}

	public Pasta[] obtenerPedidoPorPrecioDePastaDescendente() {

		Pasta auxiliar = null;

		for (int indice = 0; indice < this.pedido.length; indice++) {
			for (int indiceBis = 0; indiceBis < this.pedido.length - 1; indiceBis++) {
				if (this.pedido[indiceBis] != null && this.pedido[indiceBis + 1] != null) {
					if (this.pedido[indiceBis].getPrecio() < this.pedido[indiceBis + 1].getPrecio()) {
						auxiliar = this.pedido[indiceBis + 1];
						this.pedido[indiceBis + 1] = this.pedido[indiceBis];
						this.pedido[indiceBis] = auxiliar;
					}
				}
			}
		}

		return this.pedido;
	}

	private Pasta agregarPasta(String codigo, TipoDePasta tipoDePasta, boolean esRellena, double precio,
			int cantidadEnStock) {
		return new Pasta(codigo, tipoDePasta, esRellena, precio, cantidadEnStock);
	}

	private void inicializarFabricaDePastas() {
		int codigo = 100;
		for (int i = 0; i < TipoDePasta.values().length; i++) {
			this.pastas[i] = this.agregarPasta("" + codigo, TipoDePasta.values()[i], 0 != i, ((i * 7) + 115.5),
					(i * 7 + 15));

			codigo += 100;
		}

	}

	public String getNombrePedido() {
		return nombrePedido;
	}

	public void setNombrePedido(String nombrePedido) {
		this.nombrePedido = nombrePedido;
	}

	public Pasta[] getPastas() {
		return pastas;
	}

	public void setPastas(Pasta[] pastas) {
		this.pastas = pastas;
	}

	public Pasta[] getPedido() {
		return pedido;
	}

	public void setPedido(Pasta[] pedido) {
		this.pedido = pedido;
	}

	public String toString() {
		return "FabricaDePastas [nombrePedido=" + nombrePedido + ", pastas=" + Arrays.toString(pastas) + ", pedido="
				+ Arrays.toString(pedido) + "]";
	}

}
