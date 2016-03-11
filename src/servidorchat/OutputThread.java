package servidorchat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class OutputThread extends Thread {

    Mensaje mensaje;

    String HOST;
    int PORT;

    ServerSocket server;
    Socket socket;
    InetSocketAddress addr;

    public OutputThread(String HOST, int PORT, Mensaje mensaje) {
        this.HOST = HOST;
        this.PORT = PORT;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        try {
            System.out.println("Esperando conexiones");
            server = new ServerSocket();
            addr = new InetSocketAddress(HOST, PORT);
            server.bind(addr);
            System.out.println("Conexion establecida " + addr);
            System.out.println("Esperando peticiones...");
            socket = server.accept();
            OutputStream os = socket.getOutputStream();
            // mandamos el dato ya procesado
            os.write(mensaje.enviar().getBytes());
            System.out.println("enviado.. " + mensaje.enviar() + " en forma de bytes: " + mensaje.enviar().getBytes());
        } catch (IOException ex) {
            Logger.getLogger(OutputThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(OutputThread.class.getName()).log(Level.SEVERE, null, ex);
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
