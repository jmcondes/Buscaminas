package Buscaminas;
/**
 * Esta clase contiene el main que inicia la aplicación. Además crea todos las referencias
 * de objetos de las distintas clases para acceder a los métodos e inicia esos objetos en
 * las correspondientes clases.
 * 
 * @author David Navarro de la Morena, José Manuel Condes Moreno, Luis Martín Tallafigo González.
 * @version 6.0 - 16/05/2015
 */
public final class Buscaminas {

	private void ejecuta() {
		
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador();
		VistaInicio vistaIni = new VistaInicio();
		ModeloMarcador marcador = new ModeloMarcador();
		ModeloSonido modelosonido = new ModeloSonido();
		//Métodos setter para iniciar los objetos en cada clase.
		vistaIni.setControlador(controlador);
		modelo.setVista(vista);
		modelo.setModeloMarcador(marcador);
		vista.setModeloMarcador(marcador);
		vista.setControlador(controlador);
		vista.setModelo(modelo);
		controlador.setModeloSonido(modelosonido);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		controlador.setVistaInicio(vistaIni);
		marcador.setVista(vista);
		//Inicia la vista de inicio en la clase VistaInicio
		vistaIni.iniciar();
		
	}

	public static void main(String[] args) {

		Buscaminas bMinas = new Buscaminas();
		bMinas.ejecuta();
		
	}

}