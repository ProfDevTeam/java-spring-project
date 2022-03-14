package uz.pdp.pdpspring6thlessonhomework2nd.entity;

import lombok.Data;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.PayType;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
@Data
public class Payment extends AbsEntity {

    @OneToOne
    private SimCard simCard;

    @Enumerated(EnumType.STRING)
    private PayType payType;

    private double amount;

    private String payerName;

    private String payerId;


}
