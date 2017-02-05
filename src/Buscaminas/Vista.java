package Buscaminas;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * 
 * @author David Navarro de la Morena, José Manuel Condes Moreno, Luis Martín Tallafigo González.
 * @version 6.0 - 15/05/2015
 */
public class Vista implements VistaIntf{

	@SuppressWarnings("unused")
	private Modelo modelo;
	private ModeloMarcador marcador;
	private Controlador controlador;
	private JFrame frame;
	private JPanel tablero;
	private JButton[][] casilla;
	private int alto;
	private int ancho;
	@SuppressWarnings("unused")
	private int minas;
	private ImageIcon bandera;
	private JPanel header;
	private JTextField textPuntuacion;
	private JTextField textTiempo;
	private JPanel panel;
	private JLabel lblNivel;
	private String nivel;
	private JMenuBar barraMenu;
	private JMenu menuJuego, menuOpciones, menuAyuda;
	private JMenuItem itmNuevoJuego, itmReiniciar, itmSonido,
			itmRedimensionable, itmAcercaDe, itmVersion;
	private boolean redimensionable, sonido;
	private Timer timer;
	private int current;
	
	public Vista() {
		this.alto = 0;
		this.ancho = 0;
		this.minas = 0;
		this.nivel = "";
		this.timer = new Timer(1000, new timerActionListener());
	}
	/**
	 * Este método da valor a los atributos que describen el tablero e inicializa la ventana principal de juego
	 * con los parámetros que recibe del controlador.
	 * @param alto
	 * @param ancho
	 */
	public void VistaJuego(int alto, int ancho, int minas, String tipoTablero) {
		this.alto = alto;
		this.ancho = ancho;
		this.minas = minas;
		this.nivel = tipoTablero;
		this.bandera = new ImageIcon(
				Vista.class.getResource("/imagenes/bandera_escalada.png"));
		redimensionable = false;
		sonido = true;
		initialize();
	}
	/**
	 * Este método cierra el frame cuando se reinicia la partida para liberar la memoria utilizada.
	 */
	public void cerrarFrame(){
		//Destruye el frame activo sin cerrar la aplicación.
		frame.dispose();
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void setModeloMarcador(ModeloMarcador marcador){
		this.marcador = marcador;
	}
	
	public void desactivar() {
		frame.setVisible(false);
	}
	
	public JButton getCasilla(int i, int j){
		return casilla[i][j];
	}
	
	public void actualizarMarcador(){
		textPuntuacion.setText(Integer.toString(marcador.getPuntos()));
	}
	/**
	 * Este método sirve para crear una barra de navegación en la ventana.
	 */
	private void addMenu() {
		// Inicializamos la barraMenu
		barraMenu = new JMenuBar();
		//
		// Inicializamos el menu Juego
		//
		menuJuego = new JMenu("Juego");
		barraMenu.add(menuJuego);
		// Declaramos y añadimos el submenu "Nuevo Juego".
		itmNuevoJuego = new JMenuItem("Nuevo Juego");
		itmNuevoJuego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				controlador.volverMenu();
			}
		});
		menuJuego.add(itmNuevoJuego);
		// Declaramos y añadimos el submenu "Reiniciar Partida".
		itmReiniciar = new JMenuItem("Reiniciar Partida");
		itmReiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				controlador.reiniciarVista();
			}
		});
		menuJuego.add(itmReiniciar);
		//
		// Inicializamos el menu Opciones
		//
		menuOpciones = new JMenu("Opciones");
		barraMenu.add(menuOpciones);
		// Declaramos y añadimos la opcion "Sonido".
		itmSonido = new JMenuItem("Sonido ON");
		itmSonido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sonido) {
					// frame.setResizable(false);
					itmSonido.setText("Sonido OFF");
					sonido = false;
				} else {
					// frame.setResizable(true);
					itmSonido.setText("Sonido ON");
					sonido = true;
				}
			}
		});
		menuOpciones.add(itmSonido);
		// Declaramos y añadimos la opcion "Redimensionable".
		itmRedimensionable = new JMenuItem("Redimensionable OFF");
		itmRedimensionable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (redimensionable) {
					frame.setResizable(false);
					itmRedimensionable.setText("Redimensionable OFF");
					redimensionable = false;
				} else {
					frame.setResizable(true);
					itmRedimensionable.setText("Redimensionable ON");
					redimensionable = true;
				}
			}
		});
		menuOpciones.add(itmRedimensionable);
		//
		// Inicializamos el menu "Ayuda".
		//
		menuAyuda = new JMenu("Ayuda");
		barraMenu.add(menuAyuda);
		// Declaramos y añadimos el submenu "Acerca De".
		itmAcercaDe = new JMenuItem("Acerca De");
		itmAcercaDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Este programa ha sido desarrollado por:\n - José Manuel Condes Moreno\n - David Navarro de la Morena\n - Luis Martín Tallafigo González\n como parte de un proyecto de desarrollo del profesor Pedro Camacho Ortega, del centro de estudios U-Tad. ",
								"Acerca De",
								1,
								new ImageIcon(
										Vista.class
												.getResource("/imagenes/Smiley Icon (5).png")));
			}
		});
		menuAyuda.add(itmAcercaDe);
		// Declaramos y añadimos el submenu "Version".
		itmVersion = new JMenuItem("Versión");
		itmVersion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String esp = "          ";
				JOptionPane.showMessageDialog(null, "Buscaminas versión:\n"
						+ esp + "1.6 \n Fecha de última actualización:\n" + esp
						+ "14 de Mayo de 2015");
			}
		});
		menuAyuda.add(itmVersion);

	}

	//
	// Método privado encargado de TODA la ventana y su contenido.
	//
	private void initialize() {
		//
		// <------------------------------>Configuracion_de_la_Ventana<------------------------------>
		//
		//Cada vez que inicializamos la vista ponemos el contador del timer a 0.
		this.current = 0;
		addMenu();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		frame = new JFrame("Buscaminas");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				VistaInicio.class.getResource("/imagenes/buscaMinasLogo.png")));
		tablero = new JPanel();
		casilla = new JButton[alto][ancho];
		tablero.setLayout(new GridLayout(alto, ancho));
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				casilla[i][j] = new JButton();
				tablero.add(casilla[i][j]);
				casilla[i][j].addMouseListener(new Escuchador());
			}
		}
		tablero.setSize((ancho * 50), (alto * 50));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(tablero, BorderLayout.CENTER);

		header = new JPanel();
		frame.getContentPane().add(header, BorderLayout.NORTH);
		GridBagLayout gbl_header = new GridBagLayout();
		gbl_header.columnWidths = new int[] { 116, 0, 116, 0 };
		gbl_header.rowHeights = new int[] { 0, 22, 0 };
		gbl_header.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_header.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		header.setLayout(gbl_header);

		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.gridwidth = 3;
		gbc_toolBar.anchor = GridBagConstraints.NORTH;
		gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		header.add(barraMenu, gbc_toolBar);

		textPuntuacion = new JTextField();
		textPuntuacion.setEditable(false);
		textPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textPuntuacion = new GridBagConstraints();
		gbc_textPuntuacion.anchor = GridBagConstraints.WEST;
		gbc_textPuntuacion.insets = new Insets(0, 0, 0, 5);
		gbc_textPuntuacion.gridx = 0;
		gbc_textPuntuacion.gridy = 1;
		header.add(textPuntuacion, gbc_textPuntuacion);
		textPuntuacion.setColumns(10);

		panel = new JPanel();
		@SuppressWarnings("unused")
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		header.add(panel, gbc_panel);

		lblNivel = new JLabel(this.nivel);
		lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNivel);

		textTiempo = new JTextField(this.current + "");
		textTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		textTiempo.setEditable(false);
		GridBagConstraints gbc_textTiempo = new GridBagConstraints();
		gbc_textTiempo.anchor = GridBagConstraints.EAST;
		gbc_textTiempo.gridx = 2;
		gbc_textTiempo.gridy = 1;
		header.add(textTiempo, gbc_textTiempo);
		textTiempo.setColumns(10);
		frame.pack();
		frame.setSize((ancho * 50), (alto * 50) + 45);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	/*
	 * Esta es la clase que crea las acciones para el Timer. Cada vez que se actualiza el timer
	 * se envia el valor del atributo current al JTextField aumentado una unidad.
	 */
	private class timerActionListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			    textTiempo.setText(++current + "");
			  }
	}
	/**
	 * Esta clase sirve para crear los escuchadores para cada botón de la matriz.
	 * @author José Manuel Condes Moreno.
	 *
	 */
	private class Escuchador implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			/*
			 * Se va recorriendo la matriz bidimensional y cuando se encuentra el botón origen del evento
			 * se envían las coordenadas al controlador. Con el botón izquierdo del ratón se descubre el 
			 * botón y con el derecho se coloca una bandera.
			 */
			boolean start = true;
			for (int i = 0; i < alto; i++) {
				for (int j = 0; j < ancho; j++) {
					if (arg0.getSource() == casilla[i][j]&&casilla[i][j].isEnabled()) {
						if ((arg0.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
							if(sonido){
								controlador.activarSonido(1);
							}							
							if(start){
								timer.start();
								start = false;
							}
							controlador.enviarCoordenadas(i, j);
						} else {
							if(sonido){
								controlador.activarSonido(2);
							}	
							controlador.colocarBandera(i, j);							
						}						
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
	/**
	 * Método para colocar una bandera sobre el botón con el click derecho del ratón.
	 * @param i
	 * @param j
	 */
	public void colocarBandera(int i, int j) {		
		casilla[i][j].setIcon(bandera);
	}
	/**
	 * Método para eliminar una bandera del botón.
	 * @param i
	 * @param j
	 */
	public void eliminarBandera(int i, int j) {
		casilla[i][j].setIcon(null);
	}
	/**
	 * Método para lanzar una ventana con el mensaje de que se ha ganado o perdido la partida.
	 * @param victoria
	 */
	public void ventanaFinal(boolean victoria) {
		timer.stop();
		if (victoria) {
			JOptionPane.showMessageDialog(
					null,
					"Enhorabuena, has ganado la partida.",
					"¡Has ganado!",
					1,
					new ImageIcon(Vista.class
							.getResource("/imagenes/Smiley Icon (2).png")));
		} else {
			if(sonido){
				controlador.activarSonido(0);
			}
			JOptionPane.showMessageDialog(
					null,
					"Has perdido, vuelve a intentarlo.",
					"¡Has perdido!",
					1,
					new ImageIcon(Vista.class
							.getResource("/imagenes/Smiley Icon (1).png")));
		}
	}

}
