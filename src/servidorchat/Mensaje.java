package servidorchat;

/**
 *
 * @author Fran
 */
public class Mensaje {

    public static String mensaje;
    public static boolean bandera = false;

    public Mensaje() {

    }

    public synchronized void recibir(String msj) {
        setMensaje(msj);
        bandera = true;
        notify();
    }

    public synchronized String enviar() throws InterruptedException {
        if (bandera == false) {
            wait();
        } else {
            setMensaje(getMensaje());
        }
        return getMensaje();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
