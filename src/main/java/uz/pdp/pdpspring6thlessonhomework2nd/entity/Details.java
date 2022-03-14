package uz.pdp.pdpspring6thlessonhomework2nd.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.ActionType;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Details extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private ActionType actionType;



    @ManyToOne
    private SimCard simCard;

    private Float amount;
}
