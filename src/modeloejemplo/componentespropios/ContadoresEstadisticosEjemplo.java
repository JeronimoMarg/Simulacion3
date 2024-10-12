package modeloejemplo.componentespropios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import des.ContadoresEstadisticos;

/* Variables que almacenan información estadística referida al comportamiento del sistema. */

public class ContadoresEstadisticosEjemplo extends ContadoresEstadisticos {
	
	private int cantSolicitudesProcesadas;
	private int beneficiosObtenidos;
	private double longitudPromedioClientesEnCola;
	private double sumaTiempoClientes;
	private ArrayList<Integer> cantidadAtendidoPorServidor;
	private ArrayList<Double> cantidadTiempoProcesadoPorServidor;

	private static final int cantidadDeServidores = 2;

	public ContadoresEstadisticosEjemplo() {
		super();
	}

	public void inicializar() {
		cantSolicitudesProcesadas = 0;
		beneficiosObtenidos = 0;
		longitudPromedioClientesEnCola = 0;
		sumaTiempoClientes = 0;
		cantidadAtendidoPorServidor = new ArrayList<>(cantidadDeServidores);
		for(int i = 0; i<cantidadDeServidores; i++){
			cantidadAtendidoPorServidor.add(0);
		}
		cantidadTiempoProcesadoPorServidor = new ArrayList<>(cantidadDeServidores);
		for(int i=0 ;i <cantidadDeServidores; i++){
			cantidadTiempoProcesadoPorServidor.add(0.0);
		}
	}

	public double getLongitudPromedioClientesEnCola(){
		return longitudPromedioClientesEnCola;
	}

	public void actualizarLongitudPromedioClientesEnCola(int cantidadEnCola){
		//Si es la primera vez que se actualiza la longitud promedio entonces es la cola actual.
		if (longitudPromedioClientesEnCola != 0){
			longitudPromedioClientesEnCola = (longitudPromedioClientesEnCola + cantidadEnCola) / 2;
		}else{
			longitudPromedioClientesEnCola = cantidadEnCola;
		}
		System.out.println("Se actualiza la longitud promedio de clientes en cola es de: " + longitudPromedioClientesEnCola);
	}

	public int getCantProcesadas() {
		return cantSolicitudesProcesadas;
	}

	public void actualizarCantProcesadas() {
		cantSolicitudesProcesadas++;
		System.out.println("Se actualiza las cantidades procesadas: " + cantSolicitudesProcesadas);		
	}

	public void actualizarBeneficios(int beneficio){
		beneficiosObtenidos += beneficio;
		System.out.println("Se actualiza la cantidad de beneficios: " + beneficiosObtenidos);	
	}

	public int getBeneficiosObtenidos(){
		return beneficiosObtenidos;
	}

    public void actualizarSumaTiempoClientes(double tiempoCliente) {
		sumaTiempoClientes += tiempoCliente;
		System.out.println("Se actualiza la suma de tiempo de clientes, el promedio es de: " + sumaTiempoClientes/cantSolicitudesProcesadas);	
    }

	public double getTiempoPromedioClientes(){
		return sumaTiempoClientes/cantSolicitudesProcesadas;
	}

	public void actualizarCantidadAtendidoPorServidor(int numeroServidor){
		cantidadAtendidoPorServidor.set(numeroServidor, cantidadAtendidoPorServidor.get(numeroServidor)+1);
		System.out.println("Se actualiza la cantidad de atendidos por el servidor " + numeroServidor + " = " + cantidadAtendidoPorServidor.get(numeroServidor));	
	}

	public List<Double> getTasaDeAtencion(double cantidadMinutos){
		return cantidadAtendidoPorServidor.stream().map(c -> c / (cantidadMinutos/60)).map(c-> c.doubleValue()).collect(Collectors.toList());
	}

	public void actualizarCantidadTiempoProcesadoPorServidor(int numeroServidor, double tiempoProcesamiento){
		cantidadTiempoProcesadoPorServidor.set(numeroServidor, cantidadTiempoProcesadoPorServidor.get(numeroServidor) + tiempoProcesamiento);
		System.out.println("Se actualiza el tiempo procesado por el servidor " + numeroServidor + " = " + cantidadTiempoProcesadoPorServidor.get(numeroServidor));
	}

	public List<Double> getPorcentajeTiempoLibre(double cantidadMinutos){
		return cantidadTiempoProcesadoPorServidor.stream()
											.map(t -> 1 - (t/(cantidadMinutos)))
											.map(t -> t*100)
											.collect(Collectors.toList());
	}

	public int getCantidadServidores(){
		return cantidadDeServidores;
	}

}
