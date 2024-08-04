package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stop {
    private int stopId;
    private String stopName;
}
