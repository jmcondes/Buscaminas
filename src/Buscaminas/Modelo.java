package Buscaminas;

import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

/**
 * Esta clase contiene y procesa todos los datos del juego, adem�s se encarga de
 * enviar a la vista los datos para que se actualice.
 * 
 * @author David Navarro de la Morena, Jos� Manuel Condes Moreno, Luis Mart�n Tallafigo Gonz�lez.
 * @version 6.0 - 15/05/2015
 */
public class Modelo implements ModeloIntf{

	private Vista vista;
	private ModeloMarcador marcador;
	private String[][] infTablero;
	private String[][] descubierto;
	private String[][] banderas;
	private int filas;
	private int columnas;
	private int minas;
	private int numBanderas;
	private String tipoTablero;

	public Modelo() {
		this.columnas = 0;
		this.filas = 0;
		this.minas = 0;
		this.numBanderas = 0;
		this.tipoTablero = "";
	}
	
	public void setVista(Vista vista) {
		this.vista = vista;
	}
	
	public void setModeloMarcador(ModeloMarcador marcador){
		this.marcador = marcador;
	}
	/**
	 * Este m�todo recibe el tipo de tablero introducido por el usuario e inicializa los atributos seg�n
	 * la opci�n elegida.
	 * Adem�s avisa al controlador para inicializar la vista de juego con esos datos, y llama a los m�todos
	 * que crean los arrays, colocan las minas y dan valor a las casillas.
	 * @param alto
	 * @param ancho
	 * @param minas
	 * @param tipoTablero
	 */
	public void setDatos(int alto, int ancho, int minas, String tipoTablero){
		this.tipoTablero = tipoTablero;
		if (tipoTablero.equals("Nivel Pricipiante")) {
			this.filas = 9;
			this.columnas = 9;
			this.minas = 10;
		} else if (tipoTablero.equals("Nivel Intermedio")) {
			this.filas = 16;
			this.columnas = 16;
			this.minas = 40;
		} else if (tipoTablero.equals("Nivel Avanzado")) {
			this.filas = 16;
			this.columnas = 30;
			this.minas = 99;
		} else {
			this.filas = alto;
			this.columnas = ancho;
			this.minas = minas;
		}
		vista.VistaJuego(this.filas, this.columnas, this.minas, this.tipoTablero);
		crearArrays(this.filas,this.columnas);
		colocarMinas(this.minas);
		numerarCasillas();
		marcador.setPuntos(this.minas);
	}
	/**
	 * M�todo que inicializa los arrays.
	 * @param filas
	 * @param columnas
	 */
	private void crearArrays(int filas, int columnas) {
		infTablero = new String[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				infTablero[i][j] = "H";
			}
		}
		descubierto = new String[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				descubierto[i][j] = "H";
			}
		}
		banderas = new String[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				banderas[i][j] = "#";
			}
		}
	}
	/**
	 * M�todo para colocar las minas en posiciones aleatorias de filas y columnas.
	 * @param minas
	 */
	private void colocarMinas(int minas) {
		int numMinas = minas;
		this.minas = minas;
		this.numBanderas = minas;
		while (numMinas > 0) {
			int fila = (int) (Math.random() * (this.filas));
			int columna = (int) (Math.random() * (this.columnas));
			if (!infTablero[fila][columna].equals("M")) {
				infTablero[fila][columna] = "M";
				descubierto[fila][columna] = "M";
				numMinas--;
			}
		}
		this.numBanderas = minas;
	}
	/**
	 * M�todo para contar las minas alrededor de cada casilla y guardar ese valor en el array.
	 */
	private void numerarCasillas() {

		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				if (!infTablero[i][j].equals("M")) {
					int minasAlrededor = 0;
					/*
					 * Para cada boton de alrededor se comprueba que est� dentro de los l�mites del array.
					 * En el caso de que sea igual a una mina se sube el contador de las minas.
					 */
					if (i - 1 >= 0 && j - 1 >= 0 && infTablero[i - 1][j - 1].equals("M")) {
							minasAlrededor++;
					}
					if (i - 1 >= 0 && infTablero[i - 1][j].equals("M")) {
							minasAlrededor++;
					}
					if (i - 1 >= 0 && j + 1 < this.columnas && infTablero[i - 1][j + 1].equals("M")) {
							minasAlrededor++;
					}
					if (j + 1 < this.columnas && infTablero[i][j + 1].equals("M")) {
							minasAlrededor++;
					}
					if (i + 1 < this.filas && j + 1 < this.columnas && infTablero[i + 1][j + 1].equals("M")) {
							minasAlrededor++;
					}
					if (i + 1 < this.filas && infTablero[i + 1][j].equals("M")) {
							minasAlrededor++;
					}
					if (i + 1 < this.filas && j - 1 >= 0 && infTablero[i + 1][j - 1].equals("M")) {
							minasAlrededor++;
					}
					if (j - 1 >= 0 && infTablero[i][j - 1].equals("M")) {
							minasAlrededor++;
					}
					infTablero[i][j] = Integer.toString(minasAlrededor);
					colorearNumeradas();
				}
			}
		}

		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				System.out.print(infTablero[i][j] + " ");
			}
			System.out.println();
		}
	}
	/**
	 * M�todo para dar color en la vista al texto de los botones con n�meros.
	 */
	private void colorearNumeradas(){
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				if(infTablero[i][j].equals("1")){
					vista.getCasilla(i, j).setForeground(Color.BLUE);
				}
				else if(infTablero[i][j].equals("2")){
					vista.getCasilla(i, j).setForeground(new Color(0, 128, 0));
				}
				else if(infTablero[i][j].equals("3")){
					vista.getCasilla(i, j).setForeground(Color.RED);
				}
				else if(infTablero[i][j].equals("4")){
					vista.getCasilla(i, j).setForeground(new Color(0, 0, 205));
				}
				else if(infTablero[i][j].equals("5")){
					vista.getCasilla(i, j).setForeground(new Color(0, 100, 0));
				}
				else if(infTablero[i][j].equals("6")){
					vista.getCasilla(i, j).setForeground(new Color(139, 0, 0));
				}
				else if(infTablero[i][j].equals("7")){
					vista.getCasilla(i, j).setForeground(new Color(0, 0, 128));
				}
				else{
					vista.getCasilla(i, j).setForeground(new Color(199, 21, 133));
				}
			}
		}
	}
	/**
	 * M�todo que recibe las coordenadas del bot�n pulsado en la vista por medio del controlador y
	 * comprueba la casilla en funci�n de la informaci�n del array.
	 * @param i
	 * @param j
	 */
	public void comprobarCasilla(int i, int j) {
		if (!banderas[i][j].equals("B")) {
			if (infTablero[i][j].equals("M")) {
				finalizaJuego(false);
			} else if (infTablero[i][j].equals("0")) {
				descubrir(i, j);
			} else {
				descubierto[i][j] = infTablero[i][j];
			}
			actualizarVista();
		}
	}
	/**
	 * M�todo para colocar o quitar banderas en las posiciones que recibe del controlador. Se limita el
	 * n�mero de banderas al n�mero de minas.
	 * @param i
	 * @param j
	 */
	public void guardarBandera(int i, int j) {
			if(!banderas[i][j].equals("B")){
				if (this.numBanderas > 0) {
					banderas[i][j] = "B";
					this.numBanderas--;
					vista.colocarBandera(i, j);
				}
			}
			else{
				banderas[i][j] = "#";
				this.numBanderas++;
				vista.eliminarBandera(i, j);
			}
			marcador.setPuntos(this.numBanderas);
	}	
	/**
	 * M�todo con recursividad que destapa las casillas que est�n al lado de una casilla vacia, almacenando
	 * su posici�n en el array de casillas descubiertas.
	 * @param fila
	 * @param columna
	 */
	private void descubrir(int fila, int columna) {
		/*
		 * En el primer if comprobamos las condiciones de borde por si en la primera pasada hubieramos destapado
		 * una casilla del contorno, y por tanto al restarle o sumarle una posici�n para destapar las casillas
		 * adyacentes se saliera de los l�mites del array.
		 */
		if (fila >= 0 && fila < this.filas && columna >= 0 && columna < this.columnas) {
			//Se ejecuta lo de dentro del if si la casilla no tiene bandera, est� vac�a y no est� descubierta.
			if (!banderas[fila][columna].equals("B") && infTablero[fila][columna].equals("0") && !descubierto[fila][columna].equals("D")) {
				//Cambiamos la condici�n a descubierta en el array.
				descubierto[fila][columna] = "D";
				//Se llama recursivamente al m�todo para las casillas vecinas.
				descubrir(fila - 1, columna);
				descubrir(fila, columna + 1);
				descubrir(fila + 1, columna);
				descubrir(fila, columna - 1);
				descubrir(fila - 1, columna - 1);
				descubrir(fila + 1, columna - 1);
				descubrir(fila - 1, columna + 1);
				descubrir(fila + 1, columna + 1);
			} else {
				/*
				 * En caso de que la casilla sea un n�mero no se emplea la recursividad, y simplemente se
				 * alamacena esa casilla en el array de posiciones descubiertas.
				 */
				if (!infTablero[fila][columna].equals("0")) {
					descubierto[fila][columna] = infTablero[fila][columna];
				}
			}
		}
	}
	/**
	 * M�todo que actualiza la vista seg�n el array donde se guardan las casillas descubiertas. Adem�s
	 * contabiliza estas casillas y verifica si se han destapado todos los botones que no contienen minas,
	 * lo que indica que se ha ganado el juego.
	 */
	private void actualizarVista() {
		int contDescubiertas = 0;
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				if (!banderas[i][j].equals("B") && !descubierto[i][j].equals("H") && !descubierto[i][j].equals("M")) {
					if (!descubierto[i][j].equals("D")) {
						//Si no es una casilla vac�a asignamos el n�mero al bot�n destapado.
						vista.getCasilla(i, j).setText(descubierto[i][j]);
					}
					contDescubiertas++;
					//Se desactivan los botones descubiertos.
					vista.getCasilla(i, j).setEnabled(false);
				}
			}
		}
		if (contDescubiertas == (this.filas * this.columnas - this.minas)) {
			finalizaJuego(true);
		}
	}
	/**
	 * Este m�todo finaliza el juego si se ha descubierto una mina o se ha ganado la partida y ordena a la vista
	 * descubrir todos los botones de las minas en caso de perder.
	 * @param resultado
	 */
	private void finalizaJuego(boolean resultado) {
		MouseListener[] listeners;
		boolean victoria = resultado;
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				//En caso de perder la partida se destapan los botones donde haya mina.
				if (infTablero[i][j].equals("M") && victoria==false) {
					//vista.getCasilla(i, j).setBackground(new Color(224, 255, 255));
					vista.getCasilla(i, j).setIcon(new ImageIcon(Vista.class.getResource("/imagenes/buscaminas_2.png")));
					vista.getCasilla(i, j).setDisabledIcon(new ImageIcon(Vista.class.getResource("/imagenes/buscaminas_2.png")));
					vista.getCasilla(i, j).setEnabled(false);
				}
			}
		}
		//Al acabar la partida eliminamos el listener de las casillas que no han sido descubiertas.
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				if(vista.getCasilla(i, j).isEnabled()){
					listeners = vista.getCasilla(i, j).getMouseListeners();
					for (MouseListener listn : listeners)
					{
						vista.getCasilla(i, j).removeMouseListener(listn);
					}
				}
			}
		}
		vista.ventanaFinal(victoria);
	}

}
