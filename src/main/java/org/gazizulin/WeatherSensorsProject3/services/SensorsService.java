package org.gazizulin.WeatherSensorsProject3.services;

import org.gazizulin.WeatherSensorsProject3.models.Sensor;
import org.gazizulin.WeatherSensorsProject3.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author Timur Gazizulin
 */
@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorRepository sensorRepository;


    @Autowired
    public SensorsService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        enrichSensor(sensor);
        sensorRepository.save(sensor);
    }

    private void enrichSensor(Sensor sensor){
        sensor.setCreatedAt(LocalDateTime.now());
    }

}
