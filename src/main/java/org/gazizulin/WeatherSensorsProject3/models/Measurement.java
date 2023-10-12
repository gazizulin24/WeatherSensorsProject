package org.gazizulin.WeatherSensorsProject3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.gazizulin.WeatherSensorsProject3.DTO.SensorDTO;

import java.time.LocalDateTime;

/**
 * @author Timur Gazizulin
 */
@Data
@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="value")
    @Min(value = -100, message = "value value must be between -100 and 100")
    @Max(value = 100, message = "value value must be between -100 and 100")
    private double value;

    @Column(name="raining")
    @NotNull(message = "raining value must be not empty")
    private boolean raining;

    @ManyToOne()
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    @NotNull(message = "sensor must be not empty")
    private Sensor sensor;

    @Column(name="added_at")
    private LocalDateTime added_at;
}
