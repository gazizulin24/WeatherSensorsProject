package org.gazizulin.WeatherSensorsProject3.repositories;

import org.gazizulin.WeatherSensorsProject3.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Timur Gazizulin
 */

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    public long countByRainingIsTrue();
}
