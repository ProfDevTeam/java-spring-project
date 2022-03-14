package uz.pdp.pdpspring6thlessonhomework2nd.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import uz.pdp.pdpspring6thlessonhomework2nd.entity.Details;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.SimCard;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.ActionType;

import java.util.List;
import java.util.UUID;


public interface DetailsRepository extends JpaRepository<Details, UUID> {
    List<Details> findAllByActionTypeAndSimCard(ActionType actionType, SimCard simCard);
    List<Details> findAllBySimCard(SimCard simCard);
}
