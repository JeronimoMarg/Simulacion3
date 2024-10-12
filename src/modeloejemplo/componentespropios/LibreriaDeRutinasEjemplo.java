package modeloejemplo.componentespropios;

import des.LibreriaDeRutinas;

/* Subprogramas usados para generar observaciones aleatorias desde las distribuciones de 
 * probabilidad asociadas al modelo. */

public class LibreriaDeRutinasEjemplo extends LibreriaDeRutinas {

	public double tiempoEntreArribosSolicitudes() {
		//Siguen la distribución exponencial ya utilizada (media de 4 minutos).
		double random = GeneradorNumeroAleatorio.generarNumeroAleatorio();
		double valor = -4 * Math.log(1 - random);
		System.out.println("Numero generado para TIEMPO ENTRE ARRIBO: " + random + " es igual a: " + valor);
		return valor;
	}

	public double tiempoDeProcesamientoBebidasSaludables(int cantidadArticulos) {
		//Exponencial con media de 2,4 minutos.
		double random = GeneradorNumeroAleatorio.generarNumeroAleatorio();
		double valor = -2.4 * Math.log(1 - random);
		System.out.println("Numero generado para TIEMPO PROCESAMIENTO BEBIDA: " + random + " es igual a: " + valor);
		switch (cantidadArticulos){
			case 2:
				valor *= 1.10;
				break;
			case 3:
				valor *= 1.13;
				break;
		}
		return valor;
	}

	public double tiempoDeProcesamientoPanaderia(int cantidadArticulos){
		//Exponencial con media de 3,5 minutos.
		double random = GeneradorNumeroAleatorio.generarNumeroAleatorio();
		double valor = -3.5 * Math.log(1 - random);
		System.out.println("Numero generado para TIEMPO PROCESAMIENTO PAN: " + random + " es igual a: " + valor);
		switch(cantidadArticulos){
			case 2: 
				valor *= 1.12;
				break;	
			case 3:
				valor *= 1.15;
				break; 
			case 4: 
				valor *= 1.2;
				break;
		}

		return valor;
	}

}
