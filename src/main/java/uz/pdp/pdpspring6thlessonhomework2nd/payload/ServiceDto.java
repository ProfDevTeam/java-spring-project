package uz.pdp.pdpspring6thlessonhomework2nd.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.template.AbsEntity;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceDto extends AbsEntity {
    private String name;

    private double price;

    private Integer serviceCategoryID;

    private Timestamp expiredDate;
    private String categoryName;
}
