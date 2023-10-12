package org.gazizulin.WeatherSensorsProject3.conrollers;

import jakarta.validation.Valid;
import org.gazizulin.WeatherSensorsProject3.DTO.MeasurementDTO;
import org.gazizulin.WeatherSensorsProject3.models.Measurement;
import org.gazizulin.WeatherSensorsProject3.services.MeasurementsService;
import org.gazizulin.WeatherSensorsProject3.util.MeasurementErrorResponse;
import org.gazizulin.WeatherSensorsProject3.util.MeasurementValidationException;
import org.gazizulin.WeatherSensorsProject3.util.MeasurementValidator;
import org.gazizulin.WeatherSensorsProject3.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Timur Gazizulin
 */
@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final ModelMapper modelMapper;
    private final MeasurementsService measurementsService;

    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementsController(ModelMapper modelMapper, MeasurementsService measurementsService, MeasurementValidator measurementValidator) {
        this.modelMapper = modelMapper;
        this.measurementsService = measurementsService;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    public List<MeasurementDTO> measurements(){
        return measurementsService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    @GetMapping("/rainyDaysCount")
    public Map<String, Long> rainyDaysCount(){
        Map<String, Long> map = new HashMap<>();
        map.put("count", measurementsService.rainyDaysCount());
        return map;

    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult br){

        measurementValidator.validate(convertFromDTO(measurementDTO), br);

        if (br.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            br.getFieldErrors().forEach(er -> errorMessage
                    .append(er.getField())
                    .append(" - ")
                    .append(er.getDefaultMessage())
                    .append(";"));

            throw new MeasurementValidationException(errorMessage.toString());

        }

        measurementsService.save(convertFromDTO(measurementDTO));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    private MeasurementDTO convertToDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertFromDTO(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }


    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> catchValidationException(MeasurementValidationException e){
        MeasurementErrorResponse errorResponse = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

}
