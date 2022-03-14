package uz.pdp.pdpspring6thlessonhomework2nd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Packet;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Tariff;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.PacketType;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.ApiResponse;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.PacketDto;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.PacketDtoForClients;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.PacketRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.TariffRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PacketService {
    @Autowired
    PacketRepository packetRepository;
    @Autowired
    TariffRepository tariffRepository;


    public ApiResponse addPacket(PacketDto packetDto) {
        boolean exists = packetRepository.existsByName(packetDto.getName());
        if (exists)
            return new ApiResponse("Bunday nomli paket mavjud", false);
        Packet packet = new Packet();
        packet.setName(packetDto.getName());
        if (packetDto.getPacketTypeId() == 1) {
            packet.setPacketType(PacketType.MB);
        } else if (packetDto.getPacketTypeId() == 2) {
            packet.setPacketType(PacketType.SMS);
        } else if (packetDto.getPacketTypeId() == 3) {
            packet.setPacketType(PacketType.MIN);
        } else {
            return new ApiResponse("Wrong packet type id !", false);
        }
        packet.setAmount(packetDto.getAmount());
        packet.setCost(packetDto.getCost());
        packet.setDuration(packetDto.getDuration());
        packet.setTariff(packetDto.isTariff());
        List<Tariff> tariffList = new ArrayList<>();
        List<String> availableTariffs = packetDto.getAvailableTariffs();
        for (String availableTariff : availableTariffs) {
            Tariff tariff = tariffRepository.findByName(availableTariff);
            tariffList.add(tariff);
        }
        packet.setAvailableTariffs(tariffList);
        packetRepository.save(packet);
        return new ApiResponse("Packet saved", true);

    }


    public List<Packet> getAllPackets() {
        return packetRepository.findAll();
    }


    public List<String> getAllPacketsForClients() {
        List<Packet> packetList = packetRepository.findAll();
        List<String> packetsName = new ArrayList<>();
        for (Packet packet : packetList) {
            packetsName.add(packet.getName());
        }
        return packetsName;
    }



    public ApiResponse editPacket(PacketDtoForClients packetDto, UUID id) {
        Optional<Packet> byId = packetRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Paket id si xato !", false);
        Packet packet = byId.get();
        packet.setName(packetDto.getName());
        packet.setAmount(packetDto.getAmount());
        packet.setCost(packetDto.getCost());
        packet.setDuration(packetDto.getDuration());
        packetRepository.save(packet);
        return new ApiResponse("Paket o'zgartirildi", true);
    }

    public ApiResponse deletePacket(UUID id) {
        Optional<Packet> optionalPacket = packetRepository.findById(id);
        if (!optionalPacket.isPresent())
            return new ApiResponse("Id wrong !", false);
        Packet packet = optionalPacket.get();
        packetRepository.delete(packet);
        return new ApiResponse("Packet deleted !", true);
    }

    public PacketDtoForClients getPacketInfo(String packetName) {
        Packet packet = packetRepository.findByName(packetName);
        PacketDtoForClients packetDto = new PacketDtoForClients();
        packetDto.setName(packet.getName());
        packetDto.setPacketType(packet.getPacketType());
        packetDto.setTariff(packet.isTariff());
        packetDto.setDuration(packet.getDuration());
        packetDto.setAmount(packet.getAmount());
        packetDto.setCost(packet.getCost());
        List<Tariff> availableTariffs = packet.getAvailableTariffs();
        List<String> tariffsList = new ArrayList<>();
        for (Tariff availableTariff : availableTariffs) {
            tariffsList.add(availableTariff.getName());
        }
        packetDto.setAvailableTariffs(tariffsList);
        return packetDto;
    }
}
