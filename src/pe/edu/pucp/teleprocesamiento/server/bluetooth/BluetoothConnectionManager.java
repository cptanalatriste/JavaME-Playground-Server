/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.teleprocesamiento.server.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import pe.edu.pucp.teleprocesamiento.server.form.ServerForm;
import pe.edu.pucp.teleprocesamiento.server.status.RoomStatus;

/**
 *
 * @author m523
 */
public class BluetoothConnectionManager extends Thread {

    public static final String CONNECTION_PROTOCOL = "btspp";
    public static final String TARGET = "localhost";
    public static final String CLASS_IDENTIFIER = "11111111111111111111111111111111";
    public static final String SERVICE_NAME = "JavaMEPlaygroundServer";
    public static final String ENABLE_WIFI_MESSAGE = "ENABLE";
    private StreamConnectionNotifier connectionNotifier;
    private StreamConnection connection;
    private ServerForm serverForm;

    public BluetoothConnectionManager(ServerForm serverForm) throws IOException {
        this.serverForm = serverForm;
        String connectionString = CONNECTION_PROTOCOL + "://" + TARGET + ":"
                + CLASS_IDENTIFIER + ";" + "name=" + SERVICE_NAME;
        connectionNotifier = (StreamConnectionNotifier) Connector.open(
                connectionString);
    }

    public void run() {
        try {
            System.out.print("Starting bluetooth listening \n");
            connection = connectionNotifier.acceptAndOpen();
            InputStream inputStream = connection.openDataInputStream();
            byte data[] = new byte[inputStream.read()];
            int length = 0;
            while (length != data.length) {
                int dataRead = inputStream.read(data, length,
                        data.length - length);
                if (dataRead == -1) {
                    System.out.println("character: " + dataRead);
                }
                length += dataRead;
            }
            inputStream.close();
            connection.close();
            String dataFromClient = new String(data);
            System.out.println("dataFromClient: " + dataFromClient);
            RoomStatus roomStatus = RoomStatus.getInstance();
            if (ENABLE_WIFI_MESSAGE.equals(dataFromClient)) {
                roomStatus.enableWifi();
            } else {
                roomStatus.disableWifi();
            }
            serverForm.refreshForm();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void stopServer() throws IOException {
        if (connection != null) {
            connection.close();
        }
        connectionNotifier.close();
    }
}
