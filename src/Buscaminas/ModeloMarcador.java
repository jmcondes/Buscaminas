package Buscaminas;

/**
 * Clase que contiene los métodos para actualizar el marcador del juego.
 * 
 * @author David Navarro de la Morena, José Manuel Condes Moreno, Luis Martín Tallafigo González.
 * @version 6.0 - 16/05/2015
 */
public class ModeloMarcador implements ModeloMarcadorIntf{
	private Vista vista;
	private int puntos;

	public ModeloMarcador() {
		this.puntos = 0;
	}
	
	public void setVista(Vista vista){
		this.vista = vista;
	}
	
	public void setPuntos(int banderas){
		this.puntos = banderas;
		vista.actualizarMarcador();
	}
	
	public int getPuntos(){
		return this.puntos;	
	}
	
}