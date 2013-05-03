package pe.edu.pucp.teleprocesamiento.server;

import pe.edu.pucp.teleprocesamiento.server.http.HttpConnectionProcessor;
import java.io.IOException;
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
            startServices(serverForm);
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alertScreen = new Alert("Error", "Ha ocurrido un error inesperado",
                    null, AlertType.ERROR);
            Display.getDisplay(this).setCurrent(alertScreen);
        }
    }

    private void startServices(ServerForm serverForm) throws IOException {
        HttpConnectionProcessor serverConnection = new HttpConnectionProcessor(serverForm);
        serverConnection.start();
        SmsAlertManager alertManager = new SmsAlertManager(serverForm);
        alertManager.start();
    }
}
