package org.gazizulin.WeatherSensorsProject3.conrollers;

import jakarta.validation.Valid;
import org.gazizulin.WeatherSensorsProject3.DTO.SensorDTO;
import org.gazizulin.WeatherSensorsProject3.models.Sensor;
import org.gazizulin.WeatherSensorsProject3.services.SensorsService;
import org.gazizulin.WeatherSensorsProject3.util.SensorDoesntExistException;
import org.gazizulin.WeatherSensorsProject3.util.SensorErrorResponse;
import org.gazizulin.WeatherSensorsProject3.util.SensorValidationException;
import org.gazizulin.WeatherSensorsProject3.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Timur Gazizulin
 */
@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final ModelMapper modelMapper;
    private final SensorsService sensorsService;

    private final SensorValidator sensorValidator;


    @Autowired
    public SensorsController(SensorsService sensorsService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult br){

        sensorValidator.validate(convertFromDTO(sensorDTO), br);
        if (br.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            br.getFieldErrors().forEach(er -> errorMessage
                    .append(er.getField())
                    .append(" - ")
                    .append(er.getDefaultMessage())
                    .append(";"));

            throw new SensorValidationException(errorMessage.toString());
        }

        sensorsService.save(convertFromDTO(sensorDTO));

        return ResponseEntity.ok(HttpStatus.CREATED);
    }



    private Sensor convertFromDTO(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }


    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> catchValidationException(SensorValidationException e){
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> catchSensorDoesntExists(SensorDoesntExistException e){
        SensorErrorResponse response = new SensorErrorResponse("sensor with this name doesnt exists",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
