package uz.pdp.pdpspring6thlessonhomework2nd.payload;

import lombok.Data;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.PacketType;

import java.util.List;

@Data
public class PacketDtoForClients {
    private PacketType packetType;
    private String name;
    private int amount;
    private int cost;
    private int duration;
    private boolean isTariff;
    private List<String> availableTariffs;
}
