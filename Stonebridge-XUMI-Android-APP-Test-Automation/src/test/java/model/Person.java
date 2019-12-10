package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is a modal class which saves the json response of the api call
 *
 * @author Optimus
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @JsonProperty
    public String id;
    @JsonProperty
    public String targetName;

    public String getId() {
        return id;
    }

    public String getTargetName() {
        return targetName;
    }
}
