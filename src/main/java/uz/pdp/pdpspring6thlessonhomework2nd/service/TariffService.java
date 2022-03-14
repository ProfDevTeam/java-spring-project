package uz.pdp.pdpspring6thlessonhomework2nd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Tariff;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.ClientType;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.ApiResponse;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.TariffDto;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.TariffDtoForClient;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.TariffRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TariffService {

    @Autowired
    TariffRepository tariffRepository;


    public ApiResponse addTariff(TariffDto tariffDto) {
        boolean exists = tariffRepository.existsByName(tariffDto.getName());
        if (exists)
            return new ApiResponse("Bunday nomli tarif mavjud !", false);
        Tariff tariff = new Tariff();
        tariff.setName(tariffDto.getName());
        tariff.setPrice(tariffDto.getPrice());
        tariff.setSwitchPrice(tariffDto.getSwitchPrice());
        tariff.setExpireDate(tariffDto.getExpireDate());
        if (tariffDto.getClientTypeId() == 1) {
            tariff.setClientType(ClientType.USER);
        } else if (tariffDto.getClientTypeId() == 2) {
            tariff.setClientType(ClientType.COMPANY);
        } else {
            return new ApiResponse("Client type id wrong !", false);
        }
        tariff.setMb(tariffDto.getMb());
        tariff.setMin(tariffDto.getMin());
        tariff.setSms(tariffDto.getSms());
        tariff.setMbCost(tariffDto.getMbCost());
        tariff.setSmsCost(tariffDto.getSmsCost());
        tariff.setMinCost(tariffDto.getMinCost());
        tariffRepository.save(tariff);
        return new ApiResponse("Tariff saved !", true);
    }


    public ApiResponse editTariff(UUID id, TariffDto tariffDto) {
        Optional<Tariff> optionalTariff = tariffRepository.findById(id);
        if (!optionalTariff.isPresent())
            return new ApiResponse("Tariff id wrong", false);
        Tariff tariff = optionalTariff.get();
        tariff.setName(tariffDto.getName());
        tariff.setPrice(tariffDto.getPrice());
        tariff.setSwitchPrice(tariffDto.getSwitchPrice());
        tariff.setExpireDate(tariffDto.getExpireDate());
        if (tariffDto.getClientTypeId() == 1) {
            tariff.setClientType(ClientType.USER);
        } else if (tariffDto.getClientTypeId() == 2) {
            tariff.setClientType(ClientType.COMPANY);
        } else {
            return new ApiResponse("Client type id wrong !", false);
        }
        tariff.setMb(tariffDto.getMb());
        tariff.setMin(tariffDto.getMin());
        tariff.setSms(tariffDto.getSms());
        tariff.setMbCost(tariffDto.getMbCost());
        tariff.setSmsCost(tariffDto.getSmsCost());
        tariff.setMinCost(tariffDto.getMinCost());
        tariffRepository.save(tariff);
        return new ApiResponse("Tariff edited !", true);
    }

    public ApiResponse deleteTariff(UUID id) {
        Optional<Tariff> optionalTariff = tariffRepository.findById(id);
        if (!optionalTariff.isPresent())
            return new ApiResponse("Tariif id wrong !", false);
        Tariff tariff = optionalTariff.get();
        tariffRepository.delete(tariff);
        return new ApiResponse("Tariff deleted", true);
    }

    public List<Tariff> getAllForStaff() {
        return tariffRepository.findAll();
    }


    public List<String> getAllForClient() {
        List<Tariff> tariffList = tariffRepository.findAll();
        List<String> list = new ArrayList<>();
        for (Tariff tariff : tariffList) {
            list.add(tariff.getName());
        }
        return list;
    }


    public TariffDtoForClient getTariffInfo(String tariffName) {
        Tariff tariff = tariffRepository.findByName(tariffName);
        TariffDtoForClient tariffInfo = new TariffDtoForClient();
        tariffInfo.setName(tariff.getName());
        tariffInfo.setPrice(tariff.getPrice());
        tariffInfo.setSwitchPrice(tariff.getSwitchPrice());
        tariffInfo.setExpireDate(tariff.getExpireDate());
        tariffInfo.setSms(tariff.getSms());
        tariffInfo.setMin(tariff.getMin());
        tariffInfo.setMb(tariff.getMb());
        tariffInfo.setSmsCost(tariff.getSmsCost());
        tariffInfo.setMinCost(tariff.getMinCost());
        tariffInfo.setMbCost(tariff.getMbCost());
        return tariffInfo;
    }
}
