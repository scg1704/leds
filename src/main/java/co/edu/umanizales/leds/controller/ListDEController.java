package co.edu.umanizales.leds.controller;

import co.edu.umanizales.leds.controller.dto.ResponseDTO;
import co.edu.umanizales.leds.model.Led;
import co.edu.umanizales.leds.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/leds")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getLeds(){
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.getLeds().getHead(), null),
                HttpStatus.OK);
    }

    @GetMapping(path="/addtoend")
    public ResponseEntity<ResponseDTO> addToEnd(@RequestBody Led led){
        listDEService.getLeds().addToEnd(led);
        return new ResponseEntity<>(new ResponseDTO(200, "The led has been added", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/addtostart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody Led led){
        listDEService.getLeds().addToStart(led);
        return new ResponseEntity<>(new ResponseDTO(200, "The led has been added", null),
                HttpStatus.OK);
    }
}
