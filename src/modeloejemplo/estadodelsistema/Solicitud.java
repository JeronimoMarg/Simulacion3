package modeloejemplo.estadodelsistema;

import modeloejemplo.componentespropios.GeneradorNumeroAleatorio;

/* Solicitud que puede ser procesada por el servidor. */

public class Solicitud {
	
	private boolean tipo; // false = bebidas | true = panaderia
	private int cantidadArticulos;

	public Solicitud() {
		super();

		//Para asignar el tipo de producto
		double random = GeneradorNumeroAleatorio.generarNumeroAleatorio();
		if (random <= 0.7){
			tipo = false;
		}else{
			tipo = true;
		}

		//Para asignar la cantidad de productos
		double random2 = GeneradorNumeroAleatorio.generarNumeroAleatorio();
		if(tipo = true){
			if (random2 <= 0.27){
				cantidadArticulos = 1;}
			else if (random2 <= 0.52 && random2 > 0.27){
				cantidadArticulos =2;}
			else if (random2 > 0.52 && random2 <= 0.87){
				cantidadArticulos = 3;
			}else if (random2 > 0.87){
				cantidadArticulos = 4;}
		}
		else{
			if(random2 <= 0.57){
				cantidadArticulos = 1;}
			else if(random2 <= 0.9 && random2 > 0.57){
				cantidadArticulos = 2;}
			else if(random2 > 0.9){
				cantidadArticulos = 3;}
		}

	}

	public boolean getTipo(){
		return tipo;
	}

	public int getCantidad(){
		return cantidadArticulos;
	}

	public String getTipoString(){
		if(tipo){
			return "panaderia";
		}
		return "bebidas saludables";
	}
}
