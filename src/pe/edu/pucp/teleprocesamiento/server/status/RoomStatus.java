/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.teleprocesamiento.server.status;

/**
 *
 * @author carlos
 */
public class RoomStatus {

    public static final int LIVINGROOM = 0;
    public static final int BEDROOM_1 = 1;
    public static final int BEDROOM_2 = 2;
    public static final int BEDROOM_3 = 3;
    public static final int BATHROOM = 4;
    public static final String LIGHT_IS_ON = "ON";
    public static final String LIGHT_IS_OFF = "OFF";
    private boolean livingRoomLightOn;
    private boolean bedroom1LightOn;
    private boolean bedroom2LightOn;
    private boolean bedroom3LightOn;
    private boolean bathroomLightOn;

    private RoomStatus() {
        this.livingRoomLightOn = false;
        this.bedroom1LightOn = false;
        this.bedroom2LightOn = false;
        this.bedroom3LightOn = false;
        this.bathroomLightOn = false;
    }

    public String getLightStatus(int roomCode) {
        boolean lightStatus = false;
        switch (roomCode) {
            case LIVINGROOM:
                lightStatus = livingRoomLightOn;
                break;
            case BEDROOM_1:
                lightStatus = bedroom1LightOn;
                break;
            case BEDROOM_2:
                lightStatus = bedroom2LightOn;
                break;
            case BEDROOM_3:
                lightStatus = bedroom3LightOn;
                break;
            case BATHROOM:
                lightStatus = bathroomLightOn;
                break;
        }
        return lightStatus ? LIGHT_IS_ON : LIGHT_IS_OFF;
    }

    public String turnLightOn(int roomCode) {
        switch (roomCode) {
            case LIVINGROOM:
                livingRoomLightOn = true;
                break;
            case BEDROOM_1:
                bedroom1LightOn = true;
                break;
            case BEDROOM_2:
                bedroom2LightOn = true;
                break;
            case BEDROOM_3:
                bedroom3LightOn = true;
                break;
            case BATHROOM:
                bathroomLightOn = true;
                break;
        }
        return LIGHT_IS_ON;
    }

    public String turnLightOff(int roomCode) {
        switch (roomCode) {
            case LIVINGROOM:
                livingRoomLightOn = false;
                break;
            case BEDROOM_1:
                bedroom1LightOn = false;
                break;
            case BEDROOM_2:
                bedroom2LightOn = false;
                break;
            case BEDROOM_3:
                bedroom3LightOn = false;
                break;
            case BATHROOM:
                bathroomLightOn = false;
                break;
        }
        return LIGHT_IS_OFF;
    }

    public static RoomStatus getInstance() {
        return RoomStatusHolder.instance;
    }

    private static final class RoomStatusHolder {

        static final RoomStatus instance = new RoomStatus();
    }
}
