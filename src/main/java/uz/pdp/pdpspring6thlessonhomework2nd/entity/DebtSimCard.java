package uz.pdp.pdpspring6thlessonhomework2nd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DebtSimCard extends AbsEntity {

    @ManyToOne
    private SimCard simCard;

    private Timestamp expireDate;
    private double amount;


}
