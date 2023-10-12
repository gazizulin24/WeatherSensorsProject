package org.gazizulin.WeatherSensorsProject3.util;

import org.gazizulin.WeatherSensorsProject3.models.Sensor;

/**
 * @author Timur Gazizulin
 */
public class SensorValidationException extends RuntimeException{

    public SensorValidationException(String message){super(message);}
}
