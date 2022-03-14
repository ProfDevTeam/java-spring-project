package uz.pdp.pdpspring6thlessonhomework2nd.entity;

import lombok.Data;

import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.ClientType;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class Tariff extends AbsEntity {

    @Column(nullable = false,unique = true)
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

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

}
