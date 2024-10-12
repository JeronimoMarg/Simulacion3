package modeloejemplo.estadodelsistema;

import modeloejemplo.componentespropios.GeneradorNumeroAleatorio;

/* Solicitud que puede ser procesada por el servidor. */

public class Solicitud {
	
	private TipoSolicitud tipo;
	private int cantidadArticulos;
	private double tiempoDeArribo;

	public Solicitud() {
		super();

		//Para asignar el tipo de producto
		double random = GeneradorNumeroAleatorio.generarNumeroAleatorio();
		System.out.println("~~~~~~~Numero generado para asignar TIPO: " + random);
		
		if (random > 0.7){
			this.tipo = TipoSolicitud.PANADERIA;
		}else{
			this.tipo = TipoSolicitud.BEBIDA;
		}

		//Para asignar la cantidad de productos
		double random2 = GeneradorNumeroAleatorio.generarNumeroAleatorio();
		System.out.println("~~~~~~~Numero generado para asignar CANTIDAD: " + random2);
		if(this.tipo.equals(TipoSolicitud.PANADERIA)){
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

	public void setTiempoDeArribo(double tiempo){
		tiempoDeArribo = tiempo;
	}

	public TipoSolicitud getTipo(){
		return tipo;
	}

	public int getCantidad(){
		return cantidadArticulos;
	}

	public String getTipoString(){
		return tipo.toString();
	}

	public double getTiempoDeArribo() {
		return tiempoDeArribo;
	}
}
