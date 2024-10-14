package modeloejemplo.componentespropios;

import java.util.List;

import des.ContadoresEstadisticos;
import des.GeneradorDeReportes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/* Subprograma que calcula las estimaciones de las medidas de performance 
 * (a partir de los Contadores Estadísticos). */
 
 public class GeneradorDeReportesEjemplo extends GeneradorDeReportes {
 
	 public void run(ContadoresEstadisticos contadores) {
		 ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		 
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter("reporte.txt"))) {
			 writer.write("------------------------------------------------------\n");
			 writer.write("***GENERADOR DE REPORTES *** \n");
			 writer.write("------------------------------------------------------\n");
			 
			 writer.write("Beneficios totales en $: " + contadoresEjemplo.getBeneficiosObtenidos() + "\n");
			 writer.write("Longitud promedio de clientes en cola: " + contadoresEjemplo.getLongitudPromedioClientesEnCola() + "\n");
			 writer.write("Tiempo promedio de los clientes en el kiosko: " + contadoresEjemplo.getTiempoPromedioClientes() + "\n");
			 
			 for (int i = 0; i < contadoresEjemplo.getCantidadServidores(); i++) {
				 List<Double> lista = contadoresEjemplo.getTasaDeAtencion(8 * 60.0);
				 writer.write("Tasa de atencion (clientes por hora) de la empleada " + i + ": " + lista.get(i) + "\n");
			 }
 
			 writer.write("Porcentaje de tiempo libre de cada empleada\n");
			 for (int i = 0; i < contadoresEjemplo.getCantidadServidores(); i++) {
				 List<Double> lista = contadoresEjemplo.getPorcentajeTiempoLibre(8 * 60.0);
				 writer.write("Empleada " + i + ": " + lista.get(i) + "\n");
			 }
			 
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }
}
