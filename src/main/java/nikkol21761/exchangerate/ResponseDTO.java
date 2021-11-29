package nikkol21761.exchangerate;

import lombok.Data;

import java.util.HashMap;

@Data
public class ResponseDTO {

    private String disclaimer;
    private String license;
    private long timestamp;
    private String base;
    private HashMap<String, Double> rates;
}
