package org.gazizulin.WeatherSensorsProject3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Timur Gazizulin
 */
@Data
@Entity
@Table(name="sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Column(name="name")
    @Size(min=3, max=30, message = "sensor name length must be between 3 and 30 characters")
    private String name;


    @Column(name="created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;
}
