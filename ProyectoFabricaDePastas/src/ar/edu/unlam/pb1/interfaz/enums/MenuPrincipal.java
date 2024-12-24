package ar.edu.unlam.pb1.interfaz.enums;

public enum MenuPrincipal {
	AGREGAR_PASTA_A_PEDIDO("Agregar pasta a pedido"),
	MOSTRAR_TOTAL_DEL_PEDIDO("Mostrar total del pedido"),
	MOSTRAR_PASTA_DE_UN_TIPO_DE_PASTA_CON_MENOR_CANTIDAD_EN_El_PEDIDO(
			"Mostrar pasta de un tipo de pasta con menor cantidad en el pedido"),
	MOSTRAR_PEDIDO_ORDENADO_POR_PRECIO_DE_PASTA_DESCENDENTE("Mostrar pedido ordenado por precio de pasta descendente"),
	SALIR("Salir");

	private String descripcion;

	MenuPrincipal(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
