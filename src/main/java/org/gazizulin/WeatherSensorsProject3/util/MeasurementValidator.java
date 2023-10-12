package org.gazizulin.WeatherSensorsProject3.util;

import org.gazizulin.WeatherSensorsProject3.models.Measurement;
import org.gazizulin.WeatherSensorsProject3.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Timur Gazizulin
 */
@Component
public class MeasurementValidator implements Validator {

    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if(!sensorRepository.existsByName(measurement.getSensor().getName())){
            errors.rejectValue("sensor", "","sensor doesnt exists");
        }
    }
}
