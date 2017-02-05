package Buscaminas;
/**
 * 
 * @author David Navarro de la Morena, José Manuel Condes Moreno, Luis Martín Tallafigo González.
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
