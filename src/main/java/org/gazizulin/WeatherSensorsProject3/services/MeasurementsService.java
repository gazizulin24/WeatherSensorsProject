package org.gazizulin.WeatherSensorsProject3.services;

import org.gazizulin.WeatherSensorsProject3.models.Measurement;
import org.gazizulin.WeatherSensorsProject3.repositories.MeasurementRepository;
import org.gazizulin.WeatherSensorsProject3.repositories.SensorRepository;
import org.gazizulin.WeatherSensorsProject3.util.SensorDoesntExistException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Timur Gazizulin
 */
@Controller
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementsService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }


    public long rainyDaysCount(){
        return measurementRepository.countByRainingIsTrue();
    }

    @Transactional
    public void save(Measurement measurement){
        enrichMeasurement(measurement);
        measurement.setSensor(sensorRepository.findByName(measurement.getSensor().getName()).get());
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement){
        measurement.setAdded_at(LocalDateTime.now());
    }
}
