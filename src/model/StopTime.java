package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class StopTime {
    private String tripId;
    private LocalTime arrivalTime;
    private int stopId;

}
