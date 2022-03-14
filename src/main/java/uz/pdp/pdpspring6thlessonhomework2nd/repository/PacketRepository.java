package uz.pdp.pdpspring6thlessonhomework2nd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.pdp.pdpspring6thlessonhomework2nd.entity.Packet;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.PacketType;

import java.util.Optional;
import java.util.UUID;


public interface PacketRepository extends JpaRepository<Packet, UUID> {
    boolean existsByName(String name);

    Packet findByName(String name);

    Optional<Packet> findByPacketTypeAndAmount(PacketType packetType, int amount);
}
