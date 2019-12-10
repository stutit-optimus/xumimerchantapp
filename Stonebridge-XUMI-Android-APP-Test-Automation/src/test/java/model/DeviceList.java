package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is a modal class which saves the json response of the api call
 *
 * @author Optimus
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceList {

    @JsonProperty
    public Device[] data;

    /**
     * This method returns the device id against the deviceName sent in the parameters
     *
     * @param deviceName name of the device
     * @return String device id
     */
    public String getDeviceId(String deviceName) {
        String id = null;
        for (int index = 0; index < data.length; index++) {
            if (data[index].name.trim().equalsIgnoreCase(deviceName.trim())) {
                id = data[index].id;
                return id;
            }
        }
        return id;
    }

    /**
     * This method returns the device id against the deviceName sent in the parameters
     *
     * @param deviceName name of the device
     * @return String device status
     */
    public String getDeviceStatus(String deviceName) {
        String status = null;
        for (int index = 0; index < data.length; index++) {
            if (data[index].name.trim().equalsIgnoreCase(deviceName.trim())) {
                status = data[index].status;
                return status;
            }
        }
        return status;
    }
}
