package uz.pdp.pdpspring6thlessonhomework2nd.payload;

import lombok.Data;

@Data
public class PaymentDto {
    private String simCardNumber;

    private String payType;

    private double amount;

    private String payerName;

    private String payerId;
}
