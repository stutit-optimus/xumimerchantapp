package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is a modal class which saves the json response of the api call
 *
 * @author Optimus
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Device {

    @JsonProperty
    public String id;
    @JsonProperty
    public String name;
    @JsonProperty
    public String status;
}
