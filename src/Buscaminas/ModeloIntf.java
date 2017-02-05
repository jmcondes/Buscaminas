package Buscaminas;
/**
 * 
 * @author David Navarro de la Morena, Jos� Manuel Condes Moreno, Luis Mart�n Tallafigo Gonz�lez.
 *
 */
public interface ModeloIntf {
	
	public void setVista(Vista vista);
	public void setModeloMarcador(ModeloMarcador marcador);
	public void setDatos(int alto, int ancho, int minas, String tipoTablero);
	public void comprobarCasilla(int i, int j);
	public void guardarBandera(int i, int j);
	
}
