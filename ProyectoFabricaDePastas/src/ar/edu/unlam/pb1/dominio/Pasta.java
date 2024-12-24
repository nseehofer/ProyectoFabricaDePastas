package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.TipoDePasta;

public class Pasta {
	
	
	private String codigo;
	private TipoDePasta tipoDePasta;
	private boolean esRellena;
	private double precio;
	private int cantidad;

	public Pasta(String codigo, TipoDePasta tipoDePasta, boolean esRellena, double precio, int cantidadEnStock) {
		this.codigo = codigo;
		this.cantidad = cantidadEnStock;
		this.tipoDePasta = tipoDePasta;
		this.precio = precio;
		this.esRellena = esRellena;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public TipoDePasta getTipoDePasta() {
		return tipoDePasta;
	}

	public void setTipoDePasta(TipoDePasta tipoDePasta) {
		this.tipoDePasta = tipoDePasta;
	}
	
	

	
	public boolean isEsRellena() {
		return esRellena;
	}

	public void setEsRellena(boolean esRellena) {
		this.esRellena = esRellena;
	}

	public String toString() {
		return "Pasta [codigo=" + codigo + ", tipoDePasta=" + tipoDePasta + ", esRellena=" + esRellena + ", precio="
				+ precio + ", cantidad=" + cantidad + "]";
	}
	
	

}
