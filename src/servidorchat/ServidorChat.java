package servidorchat;

/**
 *
 * @author Fran
 */
public class ServidorChat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean SERVER_ON = true;
        Mensaje mensaje = new Mensaje();
        InputThread entrada = new InputThread("192.168.1.37", 5557, mensaje);
        OutputThread salida = new OutputThread("192.168.1.37", 5556, mensaje);
        do {
            entrada.start();
            salida.start();
        } while (SERVER_ON == true);
    }

}
