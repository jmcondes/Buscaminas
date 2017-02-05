package Buscaminas;
/**
 * 
 * @author David Navarro de la Morena, Jos� Manuel Condes Moreno, Luis Mart�n Tallafigo Gonz�lez.
 *
 */
public interface ControladorIntf {
	
	public void setVista(Vista vista);
	public void setModelo(Modelo modelo);
	public void setVistaInicio(VistaInicio vistaIni);
	public void datosTablero(int alto, int ancho, int minas, String tipoTablero);
	public void enviarCoordenadas(int i, int j);
	public void volverMenu();
	public void colocarBandera(int i, int j);
	public void reiniciarVista();

}
