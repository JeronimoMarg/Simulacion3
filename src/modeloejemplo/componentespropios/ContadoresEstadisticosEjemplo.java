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

	private static final int cantidadDeServidores = 1;

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
	}

	public int getCantProcesadas() {
		return cantSolicitudesProcesadas;
	}

	public void actualizarCantProcesadas() {
		cantSolicitudesProcesadas++;		
	}

	public void actualizarBeneficios(int beneficio){
		beneficiosObtenidos += beneficio;
	}

	public int getBeneficiosObtenidos(){
		return beneficiosObtenidos;
	}

    public void actualizarSumaTiempoClientes(double tiempoCliente) {
		sumaTiempoClientes += tiempoCliente;
    }

	public double getTiempoPromedioClientes(){
		return sumaTiempoClientes/cantSolicitudesProcesadas;
	}

	public void actualizarCantidadAtendidoPorServidor(int numeroServidor){
		cantidadAtendidoPorServidor.set(numeroServidor, cantidadAtendidoPorServidor.get(numeroServidor)+1);
	}

	public List<Double> getTasaDeAtencion(int cantidadHoras){
		return cantidadAtendidoPorServidor.stream().map(c -> c / cantidadHoras).map(c-> c.doubleValue()).collect(Collectors.toList());
	}

	public void actualizarCantidadTiempoProcesadoPorServidor(int numeroServidor, double tiempoProcesamiento){
		cantidadTiempoProcesadoPorServidor.set(numeroServidor, cantidadTiempoProcesadoPorServidor.get(numeroServidor) + tiempoProcesamiento);
	}

	public List<Double> getPorcentajeTiempoLibre(int cantidadHoras){
		return cantidadTiempoProcesadoPorServidor.stream()
											.map(t -> 1 - (t/(cantidadHoras*60.0)))
											.map(t -> t*100)
											.collect(Collectors.toList());
	}

	public int getCantidadServidores(){
		return cantidadDeServidores;
	}

}
