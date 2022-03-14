package uz.pdp.pdpspring6thlessonhomework2nd.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import uz.pdp.pdpspring6thlessonhomework2nd.entity.SimCard;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.ActionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailDto {
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    private SimCard simCard;
    private Float amount;
}
