package org.gazizulin.WeatherSensorsProject3.util;

import lombok.Data;

/**
 * @author Timur Gazizulin
 */
@Data
public class SensorErrorResponse {

    private String message;

    private long timestamp;

    public SensorErrorResponse(){}

    public SensorErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
