package modeloejemplo.componentespropios;

import java.util.List;

import des.ContadoresEstadisticos;
import des.GeneradorDeReportes;

/* Subprograma que calcula las estimaciones de las medidas de performance 
 * (a partir de los Contadores Estadísticos). */

public class GeneradorDeReportesEjemplo extends GeneradorDeReportes {

	public void run(ContadoresEstadisticos contadores) {
		
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		
		System.out.println("------------------------------------------------------");
		System.out.println("***GENERADOR DE REPORTES *** ");
		System.out.println("------------------------------------------------------");
		
		System.out.println("Beneficios totales en $: " + contadoresEjemplo.getBeneficiosObtenidos());		

		System.out.println("Longitud promedio de clientes en cola: " + contadoresEjemplo.getLongitudPromedioClientesEnCola());

		System.out.println("Tiempo promedio de los clientes en el kiosko: " + contadoresEjemplo.getTiempoPromedioClientes());
		
		for (int i=0; i<contadoresEjemplo.getCantidadServidores(); i++){
			//Para 10 arribos la cantidad de horas es: 62.428 aprox.
			//Si no, usar 8*60.0 minutos.
			List<Double> lista = contadoresEjemplo.getTasaDeAtencion(62.428);
			System.out.println("Tasa de atencion (clientes por hora) de la empleada " + i + ": " + lista.get(i));
		}
		
		System.out.println("Porcentaje de tiempo libre de cada empleada");
		for (int i=0; i<contadoresEjemplo.getCantidadServidores(); i++){
			List<Double> lista = contadoresEjemplo.getPorcentajeTiempoLibre(62.428);
			System.out.println("Empleada " + i + ": " + lista.get(i));
		}


	}

}
