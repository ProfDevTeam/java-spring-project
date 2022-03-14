package uz.pdp.pdpspring6thlessonhomework2nd.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class TariffDto {
    @NotNull
    @Column(unique = true)
    private String name;
    private double price;
    private double switchPrice;
    private int expireDate;
    private int mb;
    private int sms;
    private int min;
    private int mbCost;
    private int smsCost;
    private int minCost;
    private Integer clientTypeId;

}
