package org.gazizulin.WeatherSensorsProject3.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * @author Timur Gazizulin
 */
@Data
public class MeasurementDTO {


    @Min(value = -100, message = "value value must be between -100 and 100")
    @Max(value = 100, message = "value value must be between -100 and 100")
    private double value;

    @NotNull(message = "raining value must not be null")
    private boolean raining;

    @NotNull(message = "sensor must not be null")
    private SensorDTO sensor;
}
