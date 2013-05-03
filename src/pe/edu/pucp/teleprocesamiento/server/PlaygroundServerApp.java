package pe.edu.pucp.teleprocesamiento.server;

import pe.edu.pucp.teleprocesamiento.server.http.HttpConnectionProcessor;
import java.io.IOException;
import java.util.Timer;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;
import pe.edu.pucp.teleprocesamiento.server.form.ServerForm;
import pe.edu.pucp.teleprocesamiento.server.sms.SmsAlertManager;

/**
 * @author carlos
 */
public class PlaygroundServerApp extends MIDlet {

    public static final int SMS_PERIOD = 2000;
    private StreamConnectionNotifier connectionNotifier;

    public void startApp() {
        initialize();
    }

    public void pauseApp() {
        try {
            if (connectionNotifier != null) {
                connectionNotifier.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alertScreen = new Alert("Error", "Ha ocurrido un error inesperado",
                    null, AlertType.ERROR);
            Display.getDisplay(this).setCurrent(alertScreen);
        }
    }

    public void destroyApp(boolean unconditional) {
        try {
            if (connectionNotifier != null) {
                connectionNotifier.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alertScreen = new Alert("Error", "Ha ocurrido un error inesperado",
                    null, AlertType.ERROR);
            Display.getDisplay(this).setCurrent(alertScreen);

        }
    }

    private void initialize() {
        try {
            ServerForm serverForm = new ServerForm();
            Display.getDisplay(this).setCurrent(serverForm);
            //startHttpListening(serverForm);
            startTempMonitoring(serverForm);
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alertScreen = new Alert("Error", "Ha ocurrido un error inesperado",
                    null, AlertType.ERROR);
            Display.getDisplay(this).setCurrent(alertScreen);
        }
    }

    private void startHttpListening(ServerForm serverForm) throws IOException {
        connectionNotifier = (StreamConnectionNotifier) Connector.open("socket://:80");
        while (true) {
            StreamConnection connection = connectionNotifier.acceptAndOpen();
            HttpConnectionProcessor serverConnection = new HttpConnectionProcessor(
                    connection, serverForm);
            serverConnection.start();
        }
    }

    private void startTempMonitoring(ServerForm serverForm) {
        Timer timer = new Timer();
        SmsAlertManager alertManager = new SmsAlertManager(serverForm);
        timer.schedule(alertManager, 0, SMS_PERIOD);
    }
}
