package pe.edu.pucp.teleprocesamiento.server.form;

import java.io.IOException;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import pe.edu.pucp.teleprocesamiento.server.status.RoomStatus;

/**
 * Form for the Server Report.
 *
 * @author Carlos G. Gavidia
 */
public class ServerForm extends Form {

    public static final String SCREEN_TITLE = "Java ME Playground Server";

    public ServerForm() throws IOException {
        super(SCREEN_TITLE);
        refreshForm();
    }

    public void refreshForm() {
        this.deleteAll();
        this.append("Estado actual");

        RoomStatus roomStatus = RoomStatus.getInstance();

        StringItem livingRoomStringItem = new StringItem("Iluminación - Sala:",
                roomStatus.getLightStatus(RoomStatus.LIVINGROOM));
        this.append(livingRoomStringItem);
        StringItem wifiStringItem = new StringItem("Wi-fi - Sala:",
                String.valueOf(roomStatus.getWifiStatus()));
        this.append(wifiStringItem);
        StringItem bedroom1StringItem = new StringItem("Iluminación - Dormitorio 1:",
                roomStatus.getLightStatus(RoomStatus.BEDROOM_1));
        this.append(bedroom1StringItem);
        StringItem bedroom2StringItem = new StringItem("Iluminación - Dormitorio 2:",
                roomStatus.getLightStatus(RoomStatus.BEDROOM_2));
        this.append(bedroom2StringItem);
        StringItem bedroom3StringItem = new StringItem("Iluminación - Dormitorio 3:",
                roomStatus.getLightStatus(RoomStatus.BEDROOM_3));
        this.append(bedroom3StringItem);
        StringItem bathroomStringItem = new StringItem("Iluminación - Baño:",
                roomStatus.getLightStatus(RoomStatus.BATHROOM));
        this.append(bathroomStringItem);
        StringItem tempStringItem = new StringItem("Temperatura - General:",
                String.valueOf(roomStatus.getCurrentTemperature()));
        this.append(tempStringItem);

    }
}
