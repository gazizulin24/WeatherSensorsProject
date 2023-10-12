package org.gazizulin.WeatherSensorsProject3.repositories;

import org.gazizulin.WeatherSensorsProject3.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Timur Gazizulin
 */
@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    public boolean existsByName(String name);

    public Optional<Sensor> findByName(String name);
}
