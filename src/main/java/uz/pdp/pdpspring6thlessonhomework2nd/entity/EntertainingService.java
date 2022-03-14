package uz.pdp.pdpspring6thlessonhomework2nd.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class EntertainingService extends AbsEntity {

    private String name;

    private double price;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private ServiceCategory serviceCategory;

    private Timestamp expiredDate;
    @ManyToOne
    private SimCard simCard;
    private Integer count;

}
