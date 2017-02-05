package Buscaminas;

import javax.swing.JFrame;

import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Esta clase contiene la ventana de "Bienvenida" creada para el programa
 * "Buscaminas".
 * 
 * @author David Navarro de la Morena.
 * @version 1.0 - 08/05/2015
 */
public class VistaInicio implements VistaInicioIntf{
	private Controlador controlador;
	private JFrame frmBuscaMinas;
	private JTextField txtFilas;
	private JTextField txtColumnas;
	private JTextField txtNmeroDeMinas;
	private String tipoTablero;
	private int alto, ancho, minas;

	/**
	 * Este metodo devuelve el nivel seleccionado por el usuario.
	 * 
	 * @return String - Nivel seleccionado ("Nivel Pricipiante",
	 *         "Nivel Intermedio", "Nivel Avanzado" o "Nivel Personalizado").
	 * @wbp.parser.entryPoint
	 */

	public VistaInicio() {
		this.alto = 0;
		this.ancho = 0;
		this.minas = 0;
		this.tipoTablero ="";
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void iniciar() {
		initialize();
	}

	//
	// Clase privada encargada de TODA la ventana y su contenido.
	//
	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		//
		// <------------------------------>Configuracion_de_la_Ventana<------------------------------>
		//
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		frmBuscaMinas = new JFrame();
		frmBuscaMinas.setTitle("Busca Minas");
		frmBuscaMinas.setIconImage(Toolkit.getDefaultToolkit().getImage(
				VistaInicio.class.getResource("/imagenes/buscaMinasLogo.png")));
		frmBuscaMinas.setResizable(false);
		frmBuscaMinas.setVisible(true);
		frmBuscaMinas.setBounds(100, 100, 300, 195);
		frmBuscaMinas.setLocationRelativeTo(null);
		frmBuscaMinas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		// <------------------------------>Contido_de_interacción_con_el_usuario<------------------------------>
		//
		// Creamos el botón desplegable.
		//
		JLabel lblSeleccionaElTipo = new JLabel(
				"Selecciona el tipo de tablero para jugar.");
		lblSeleccionaElTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//
		//
		//
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			// Configuramos la opción de "Nivel Persoanlizado"
			public void itemStateChanged(ItemEvent arg0) {
				// Cuando obtiene el foco.
				if (comboBox.getSelectedItem() == "Nivel Personalizado") {
					txtFilas.setEnabled(true);
					txtColumnas.setEnabled(true);
					txtNmeroDeMinas.setEnabled(true);
					// Cuando pierde el foco.
				} else {
					txtFilas.setEnabled(false);
					txtColumnas.setEnabled(false);
					txtNmeroDeMinas.setEnabled(false);
				}
			}
		});
		//
		// Añadimos los distintos elementos del desplegable.
		//
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Nivel Pricipiante", "Nivel Intermedio", "Nivel Avanzado",
				"Nivel Personalizado" }));
		comboBox.setMaximumRowCount(4);
		//
		// Creamos los campos para el tablero personalixado. Solo estarán
		// disponibles si se selecciona el modo "Personalizado"
		//
		// Casilla para el número de filas.
		//
		txtFilas = new JTextField();
		txtFilas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if ((car < '0' || car > '9'))
					evt.consume();
			}
		});
		txtFilas.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtFilas.setForeground(Color.BLACK);
				if (txtFilas.getText().equals("Filas (9 a 24)")) {
					txtFilas.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtFilas.getText().equals("")) {
					txtFilas.setText("Filas (9 a 24)");
					txtFilas.setForeground(Color.GRAY);
				}
			}
		});
		txtFilas.setEnabled(false);
		txtFilas.setText("Filas (9 a 24)");
		txtFilas.setForeground(Color.GRAY);
		txtFilas.setColumns(10);
		//
		// Casilla para el número de columnas.
		//
		txtColumnas = new JTextField();
		txtColumnas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if ((car < '0' || car > '9'))
					evt.consume();
			}
		});
		txtColumnas.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtColumnas.setForeground(Color.BLACK);
				if (txtColumnas.getText().equals("Columnas (9 a 30)")) {
					txtColumnas.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtColumnas.getText().equals("")) {
					txtColumnas.setText("Columnas (9 a 30)");
					txtColumnas.setForeground(Color.GRAY);
				}
			}
		});
		txtColumnas.setEnabled(false);
		txtColumnas.setForeground(Color.GRAY);
		txtColumnas.setText("Columnas (9 a 30)");
		txtColumnas.setColumns(10);
		//
		// Casilla para el número de minas.
		//
		txtNmeroDeMinas = new JTextField();
		txtNmeroDeMinas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if ((car < '0' || car > '9'))
					evt.consume();
			}
		});
		txtNmeroDeMinas.setEnabled(false);
		txtNmeroDeMinas.setForeground(Color.GRAY);
		txtNmeroDeMinas.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNmeroDeMinas.setForeground(Color.BLACK);
				if (txtNmeroDeMinas.getText().equals("Minas (10 a 668)")) {
					txtNmeroDeMinas.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtNmeroDeMinas.getText().equals("")) {
					txtNmeroDeMinas.setText("Minas (10 a 668)");
					txtNmeroDeMinas.setForeground(Color.GRAY);
				}
			}
		});
		txtNmeroDeMinas.setText("Minas (10 a 668)");
		txtNmeroDeMinas.setColumns(10);
		//
		// Creamos el boton para empezar.
		//
		JButton btnEmpezar = new JButton("Empezar");
		btnEmpezar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//
				// <------------------------------>_#_#_#_#_#_<------------------------------>
				//
				tipoTablero = comboBox.getSelectedItem().toString();

				if (comboBox.getSelectedItem() == "Nivel Personalizado") {
					try {
						alto = Integer.parseInt(txtFilas.getText());
						ancho = Integer.parseInt(txtColumnas.getText());
						minas = Integer.parseInt(txtNmeroDeMinas.getText());

						if (alto < 9 || alto > 24) {
							JOptionPane
									.showMessageDialog(
											null,
											"Has introducido uno o varios valores no permitios. Recuerda:\n     Número de filas: Entre 9 y 24 (ambos inclusive).\n     Número de columnas: Entre 9 y 30 (ambos inclusive).\n     Número de minas: Entre 10 y 668 (ambos inclusive).",
											"¡Dato Invalido!", 2);
						} else if (ancho < 9 || ancho > 30) {
							JOptionPane
									.showMessageDialog(
											null,
											"Has introducido uno o varios valores no permitios. Recuerda:\n     Número de filas: Entre 9 y 24 (ambos inclusive).\n     Número de columnas: Entre 9 y 30 (ambos inclusive).\n     Número de minas: Entre 10 y 668 (ambos inclusive).",
											"¡Dato Invalido!", 2);
						} else if (minas < 10 || minas > 668) {
							JOptionPane
									.showMessageDialog(
											null,
											"Has introducido uno o varios valores no permitios. Recuerda:\n     Número de filas: Entre 9 y 24 (ambos inclusive).\n     Número de columnas: Entre 9 y 30 (ambos inclusive).\n     Número de minas: Entre 10 y 668 (ambos inclusive).",
											"¡Dato Invalido!", 2);
						} else {
							int numCasillas = alto * ancho;
							// numCasillas *= 0.2;
							if (minas >= numCasillas) {
								JOptionPane
										.showMessageDialog(
												null,
												"Has introducido demasiadas minas para el número de filas y columnas introducidas.\n Como máximo, puedes introducir "
														+ numCasillas
														+ " minas.",
												"¡Demasiadas Minas!", 2);
							} else {
								controlador.datosTablero(alto, ancho, minas, tipoTablero);
								frmBuscaMinas.setVisible(false);
							}
						}
					} catch (Exception e) {
						JOptionPane
								.showMessageDialog(
										null,
										"Has dejado algún campo vacio.\n Para jugar, llena los tres campos, o selecciona uno de los nivel prediseñados.",
										"¡Campos Vacios!", 0);
					}
				} else {
					alto = ancho = minas = 0;
					controlador.datosTablero(alto, ancho, minas, tipoTablero);
					frmBuscaMinas.setVisible(false);
				}

			}
		});

		JLabel lblV = new JLabel("V 1.6");
		lblV.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblV.setForeground(Color.GRAY);
		//
		// <------------------------------>Grupos_y_ordenación_en_la_ventana<------------------------------>
		//
		GroupLayout groupLayout = new GroupLayout(
				frmBuscaMinas.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(6)
																		.addComponent(
																				lblV)
																		.addContainerGap())
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.LEADING)
																		.addGroup(
																				groupLayout
																						.createSequentialGroup()
																						.addGroup(
																								groupLayout
																										.createParallelGroup(
																												Alignment.TRAILING)
																										.addComponent(
																												comboBox,
																												0,
																												239,
																												Short.MAX_VALUE)
																										.addComponent(
																												lblSeleccionaElTipo,
																												Alignment.LEADING))
																						.addContainerGap(
																								43,
																								Short.MAX_VALUE))
																		.addGroup(
																				groupLayout
																						.createSequentialGroup()
																						.addComponent(
																								txtFilas,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(
																								txtColumnas,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addContainerGap(
																								43,
																								Short.MAX_VALUE))
																		.addGroup(
																				groupLayout
																						.createSequentialGroup()
																						.addComponent(
																								txtNmeroDeMinas,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED,
																								71,
																								Short.MAX_VALUE)
																						.addComponent(
																								btnEmpezar)
																						.addContainerGap())))));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblSeleccionaElTipo)
										.addGap(18)
										.addComponent(comboBox,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																txtFilas,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtColumnas,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnEmpezar))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtNmeroDeMinas,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblV).addGap(8)));
		frmBuscaMinas.getContentPane().setLayout(groupLayout);
	}
}
