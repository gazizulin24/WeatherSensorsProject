package org.gazizulin.WeatherSensorsProject3.util;

import org.gazizulin.WeatherSensorsProject3.models.Sensor;
import org.gazizulin.WeatherSensorsProject3.repositories.SensorRepository;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * @author Timur Gazizulin
 */
@Component
public class SensorValidator implements Validator {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorRepository.existsByName(sensor.getName())){
            errors.rejectValue("name", "", "sensor with same name already exists");
        }
    }
}
