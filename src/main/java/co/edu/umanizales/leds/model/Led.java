package co.edu.umanizales.leds.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Led {
    private int id;
    private boolean status;
    private LocalTime dateOn;
    private LocalTime dateOff;
}
