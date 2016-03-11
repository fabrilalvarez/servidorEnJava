package servidorchat;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class InputThread extends Thread {

    Mensaje mensaje;

    String HOST;
    int PORT;

    ServerSocket server;
    Socket socket;
    InetSocketAddress addr;

    public InputThread(String HOST, int PORT, Mensaje mensaje) {
        this.HOST = HOST;
        this.PORT = PORT;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        try {
            System.out.println("Esperando conexiones");
            addr = new InetSocketAddress(HOST, PORT);
            server = new ServerSocket();
            server.bind(addr);
            System.out.println("Conexion establecida" + addr);
            System.out.println("Esperando mensajes...");
            socket = server.accept();
            InputStream is = socket.getInputStream();
            byte[] msj = new byte[25];
            is.read(msj);
            // Transformamos el mensaje recibido y le damos forma
            mensaje.recibir("Fran: " + new String(msj) + "\n");
            System.out.println("mensaje recibido y procesado: " + mensaje.getMensaje());
        } catch (IOException ex) {
            Logger.getLogger(InputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrar() {
        try {
            socket.close();
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(InputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
