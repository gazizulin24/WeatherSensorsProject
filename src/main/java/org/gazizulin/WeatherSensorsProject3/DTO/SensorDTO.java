package org.gazizulin.WeatherSensorsProject3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

/**
 * @author Timur Gazizulin
 */
@Data
public class SensorDTO {

    @NotEmpty(message = "name must be not empty")
    @Size(min=3, max=30, message = "sensor name length must be between 3 and 30 characters")
    private String name;
}
