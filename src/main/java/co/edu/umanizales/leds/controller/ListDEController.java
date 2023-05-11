package co.edu.umanizales.leds.controller;

import co.edu.umanizales.leds.controller.dto.ResponseDTO;
import co.edu.umanizales.leds.model.Led;
import co.edu.umanizales.leds.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path="/oneledon")
    public ResponseEntity<ResponseDTO> oneLedOn(@PathVariable int id){
        listDEService.getLeds().oneLedOn(id);
        return new ResponseEntity<>(new ResponseDTO(200, "The led is on", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/oneledoff")
    public ResponseEntity<ResponseDTO> oneLedOff(@PathVariable int id){
        listDEService.getLeds().oneLedOff(id);
        return new ResponseEntity<>(new ResponseDTO(200, "The led is off", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/allledson")
    public ResponseEntity<ResponseDTO> allLedsOn(){
        listDEService.getLeds().allLedsOn();
        return new ResponseEntity<>(new ResponseDTO(200, "All leds are on", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/allledsoff")
    public ResponseEntity<ResponseDTO> allLedsOff(){
        listDEService.getLeds().allLedsOff();
        return new ResponseEntity<>(new ResponseDTO(200, "All leds are off", null),
                HttpStatus.OK);
    }

    @GetMapping(path="/simplerestart")
    public ResponseEntity<ResponseDTO> simpleRestart(){
        listDEService.getLeds().simpleRestart();
        return new ResponseEntity<>(new ResponseDTO(200, "Leds are rebooted", null),
                HttpStatus.OK);
    }
    @GetMapping(path="/sleep")
    public ResponseEntity<ResponseDTO> sleep(){
        listDEService.getLeds().sleep();
        return new ResponseEntity<>(new ResponseDTO(200, "Sleep have been used on leds", null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/turnlightshalf")
    public ResponseEntity<ResponseDTO> turnLightsByHalf(){
        try{
            listDEService.getLeds().turnLightsByHalf();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Lights are on by that order", null),
                HttpStatus.OK);

    }
}
