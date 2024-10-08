package modeloejemplo.componentespropios;

import des.ContadoresEstadisticos;

/* Variables que almacenan información estadística referida al comportamiento del sistema. */

public class ContadoresEstadisticosEjemplo extends ContadoresEstadisticos {
	
	private int cantSolicitudesProcesadas;
	private int beneficiosObtenidos;
	private double longitudPromedioClientesEnCola;
	private double sumaTiempoClientes;

	public ContadoresEstadisticosEjemplo() {
		super();
	}

	public void inicializar() {
		cantSolicitudesProcesadas = 0;
		beneficiosObtenidos = 0;
		longitudPromedioClientesEnCola = 0;
		sumaTiempoClientes = 0;
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

	

}
