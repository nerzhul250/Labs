package mundo;

import java.awt.PageAttributes;
import java.util.ArrayList;

import colas.ColaEnlazada;
import colas.ICola;
import pilas.IPila;
import tablasHash.ITablaHash;
import tablasHash.TablaHashEncadenada;

public class Parqueadero{ 
	
	private Bahia[] bahias;
	private ICola<Automovil>filaEntrada;
	private ITablaHash<Automovil,Integer> tabla;
	private ICola<Automovil>filaSalida;
	private int [] cantidadesBahias;
	
	int limiteVehiculos;
	int limiteVehiculosPorBahia;
	
	public Parqueadero(int numBahias,int totalCarrosIngresan, int capacidadBahia)  {
		limiteVehiculos=totalCarrosIngresan;
		limiteVehiculosPorBahia=capacidadBahia;
		bahias=crearBahias(capacidadBahia, numBahias);
		tabla=new TablaHashEncadenada<Automovil,Integer>(numBahias);
		cantidadesBahias = new int[numBahias*2];
		filaEntrada=new ColaEnlazada<Automovil>();
		filaSalida=new ColaEnlazada<Automovil>();
	}	

	public Bahia[] crearBahias(int capacidadPorBahia,int numeroDeBahias) {
		
		Bahia retorno[]=new Bahia[numeroDeBahias];
		
		for(int i=0;i<numeroDeBahias;i++) {
			Bahia nueva=new Bahia();
			retorno[i]=nueva;
		}
		return retorno;
	}
	
	public void llenarBahias(boolean simulacion) throws InterruptedException {
		boolean es=false;
		for(int i=0;i<getBahias().length && !es;i++){
			for(int j=0;j<limiteVehiculosPorBahia && !es;j++){
				System.out.println("funciona el hilo principal");
				if(simulacion)
				Thread.sleep(ParkingManager.PAUSAESTANDAR);
				Automovil beta=filaEntrada.deQueue();
				if(beta==null){
					es=true;
					break;
				}
			    getBahias()[i].getPila().push(beta);
			    tabla.insert(beta,i);
			    cantidadesBahias[i]= cantidadesBahias[i]+1;
			}
			}	
		}	
	
	/*
	 * Descripcion:retorna la cantidad de movs n
	 */
	public void sacarCarro(Automovil a, boolean simulado) throws InterruptedException{
		Integer b=tabla.find(a);
		Bahia actual=getBahias()[b];
		actual.movsParaSacarCarro(a, cantidadesBahias, b, simulado);
		actual.deColaAPila(cantidadesBahias, b, simulado);
	}
	public String darResultado(boolean simulado) throws InterruptedException{
		String retorno="";
		while(getFilaSalida().isEmpty()!=true) {
			sacarCarro(getFilaSalida().deQueue(), simulado);
		}
		for (int i = 0; i < bahias.length; i++) {
			if(i!=bahias.length-1){
				retorno+=bahias[i].getCarrosMovidos()+" ";				
			}else{
				retorno+=bahias[i].getCarrosMovidos();				
			}
		}
		return retorno;
	}

	public int[] getCantidadesBahias() {
		return cantidadesBahias;
	}

	public Bahia[] getBahias() {
		return bahias;
	}


	public void setBahias(Bahia[] bahias) {
		this.bahias = bahias;
	}


	public ICola<Automovil> getFilaEntrada() {
		return filaEntrada;
	}


	public void setFilaEntrada(ICola<Automovil> filaEntrada) {
		this.filaEntrada = filaEntrada;
	}


	public ITablaHash<Automovil, Integer> getTabla() {
		return tabla;
	}


	public void setTabla(ITablaHash<Automovil, Integer> tabla) {
		this.tabla = tabla;
	}


	public ICola<Automovil> getFilaSalida() {
		return filaSalida;
	}


	public void setFilaSalida(ICola<Automovil> filaSalida) {
		this.filaSalida = filaSalida;
	}


	public int getLimiteVehiculos() {
		return limiteVehiculos;
	}


	public void setLimiteVehiculos(int limiteVehiculos) {
		this.limiteVehiculos = limiteVehiculos;
	}


	public int getLimiteVehiculosPorBahia() {
		return limiteVehiculosPorBahia;
	}


	public void setLimiteVehiculosPorBahia(int limiteVehiculosPorBahia) {
		this.limiteVehiculosPorBahia = limiteVehiculosPorBahia;
	}
}
