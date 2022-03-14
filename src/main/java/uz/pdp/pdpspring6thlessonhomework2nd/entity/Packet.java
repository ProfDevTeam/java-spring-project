package uz.pdp.pdpspring6thlessonhomework2nd.entity;

import lombok.Data;

import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.PacketType;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Packet extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private PacketType packetType;

    @Column(nullable = false,unique = true)
    private String name;

    private int amount;

    private int cost;

    private int duration;

    private boolean isTariff;

    @OneToMany
    private List<Tariff> availableTariffs;


}
