package Buscaminas;

import javax.sound.sampled.*;
/**
 * Esta clase es la que genera los sonidos del juego.
 * 
 * @author David Navarro de la Morena, José Manuel Condes Moreno, Luis Martín Tallafigo González.
 * @version 6.0 - 16/05/2015
 */
public class ModeloSonido implements ModeloSonidoIntf{
	
	private String ruta;
	
	public ModeloSonido(){
		ruta = null;
	}
	/**
	 * Método que ejecuta el clip de sonido según la ruta del parámetro.
	 */
	public void sonido(int tipo){
		switch(tipo){
			case 0:
				ruta = "/sonidos/Depth_Charge_Shorter.wav";
				break;
			case 1:
				ruta = "/sonidos/Snap.wav";
				break;
			case 2:
				ruta = "/sonidos/Click01.wav";
			default:
				break;
		}
		AudioInputStream ais = null;
		try{

			ais = AudioSystem.getAudioInputStream(ModeloSonido.class.getResource(ruta));
			Clip sound=AudioSystem.getClip();
			sound.open(ais);
			sound.start();

		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}