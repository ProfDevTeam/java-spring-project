package uz.pdp.pdpspring6thlessonhomework2nd.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallDto {
    private String code;
    private String number;
    private double seconds;
}
