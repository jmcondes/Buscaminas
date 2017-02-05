package Buscaminas;
/**
 * Esta clase avisa al modelo para procesar la información de la jugada a partir de la interacción
 * del usuario con la vista.
 * 
 * @author David Navarro de la Morena, José Manuel Condes Moreno, Luis Martín Tallafigo González.
 *
 */
public class Controlador implements ControladorIntf{

	private Vista vista;
	private Modelo modelo;
	private VistaInicio vi;
	private ModeloSonido modelosonido;
	private int alto, ancho, minas;
	private String tipoTablero;

	public void setVista(Vista vista) {
		this.vista = vista;
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public void setVistaInicio(VistaInicio vistaIni) {
		this.vi = vistaIni;
	}

	public void setModeloSonido(ModeloSonido modelosonido){
		this.modelosonido = modelosonido;
	}
	/**
	 * Método que envia al modelo los datos del tablero introducidos por el usuario en la vista.
	 * @param alto
	 * @param ancho
	 * @param minas
	 * @param tipoTablero
	 */
	public void datosTablero(int alto, int ancho, int minas, String tipoTablero) {
		this.alto = alto;
		this.ancho = ancho;
		this.minas = minas;
		this.tipoTablero = tipoTablero;
		modelo.setDatos(this.alto, this.ancho, this.minas, this.tipoTablero);
	}
	/**
	 * Método que envia las coordenadas al modelo para comprobar la jugada.
	 * @param i
	 * @param j
	 */
	public void enviarCoordenadas(int i, int j) {
		modelo.comprobarCasilla(i, j);
	}
	/**
	 * Método para volver a la vista de inicio al pulsar sobre la opción de Nuevo Juego del menú.
	 */
	public void volverMenu() {
		vi.iniciar();
		vista.desactivar();
	}
	/**
	 * Método que manda al modelo las coordenadas del botón para poner o quitar banderas.
	 * @param i
	 * @param j
	 */
	public void colocarBandera(int i, int j) {
		modelo.guardarBandera(i, j);
	}
	/**
	 * Método para iniciar la ventana de juego con los datos procesados por el modelo.
	 * @param alto
	 * @param ancho
	 * @param minas
	 * @param tipoTablero
	 */
	
	public void activarSonido(int tipo){
		modelosonido.sonido(tipo);
	}
	/**
	 * Método que avisa al modelo para reiniciar la vista y cierra la ventana activa.
	 */
	public void reiniciarVista() {
		vista.cerrarFrame();
		modelo.setDatos(this.alto, this.ancho, this.minas, this.tipoTablero);
	}
}
