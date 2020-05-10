
import java.applet.AudioClip;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Musicos implements Runnable {

    //Atributos para inicializar objeto
    private String nombre;
    private String ruta;
    
    //Banderas para obtener estado del hilo
    private boolean estado;    
    private boolean pausado;

    // Objeto para reporducir audio
    private AudioClip clip;
    //Hilo
    private Thread thread;

    //constructor
    public Musicos(String nombre, String ruta) {
        clip = java.applet.Applet.newAudioClip(getClass().getResource(ruta));
        this.nombre = nombre;
        this.ruta = ruta;
    }//Musicos
    
    @Override
    public void run() {             
        clip.loop(); 
    }//run

    public void start() {
        estado = true;
        thread = new Thread(this);
        thread.start();
    }//start
    
    public void dormir(){
        try {
            pausado = true;
            clip.stop();
            thread.sleep(3000); //Dormir 3 segundos
            clip.play();
        } catch (InterruptedException ex) {
            Logger.getLogger(Musicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//dormir
    
    public void detener() {
        thread.interrupt();
        estado = false;
        pausado = false;
        clip.stop();
    }
    
    public String  getEstato(){
        if(pausado)
            return nombre+" se ha detenido 3 segundos";
        if(estado)
            return nombre+" ha empezado a tocar";
        return nombre+" ha parado de tocar";
    }
    
    //Getters & Setters
        
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isPausado() {
        return pausado;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

}
