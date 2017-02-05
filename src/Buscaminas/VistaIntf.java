package Buscaminas;

import javax.swing.JButton;

/**
 * 
 * @author David Navarro de la Morena, Jos� Manuel Condes Moreno, Luis Mart�n Tallafigo Gonz�lez.
 * @version 6.0 - 16/05/2015
 */
public interface VistaIntf {
	
	public void VistaJuego(int alto, int ancho, int minas, String tipoTablero);
	public void cerrarFrame();
	public void setModelo(Modelo modelo);
	public void setControlador(Controlador controlador);
	public void setModeloMarcador(ModeloMarcador marcador);
	public JButton getCasilla(int i, int j);
	public void desactivar();
	public void actualizarMarcador();
	public void colocarBandera(int i, int j);
	public void eliminarBandera(int i, int j);
	public void ventanaFinal(boolean victoria);

}
