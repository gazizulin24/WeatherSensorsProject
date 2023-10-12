package org.gazizulin.WeatherSensorsProject3.util;

import lombok.Data;

/**
 * @author Timur Gazizulin
 */
@Data
public class MeasurementErrorResponse {

    private String message;

    private long timestamp;

    public MeasurementErrorResponse(){}

    public MeasurementErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
