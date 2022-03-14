package uz.pdp.pdpspring6thlessonhomework2nd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import uz.pdp.pdpspring6thlessonhomework2nd.component.DetailExporter;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Details;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.SimCard;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.ActionType;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.ApiResponse;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.DetailDto;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.DetailsRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("ransomware")
public class DetailsService {

    @Autowired
    DetailsRepository detailsRepository;

    @Autowired
    DetailExporter exporter;

    public ApiResponse exportToPdf(HttpServletResponse response, List<Details> detailsList, String code) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=detail_" + currentTime + ".pdf";//fayl nomini o'zgartirish kk

        response.setHeader(headerKey, headerValue);

        exporter.exportPDF(response, detailsList, code);
        return new ApiResponse("Successfully exported", true);
    }

    public ApiResponse exportToExcel(HttpServletResponse response, List<Details> detailsList) throws IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=user_"+currentTime+".xlsx";

        response.setHeader(headerKey,headerValue);
        exporter.exportExcel(response, detailsList);
        return new ApiResponse("Successfully exported", true);
    }


    public ApiResponse getAllBySimCard(HttpServletResponse response, Integer stat) throws IOException {
        SimCard card = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Details> detailsList = detailsRepository.findAllBySimCard(card);
        if (stat==0)
            return exportToPdf(response, detailsList, card.getCode());
        if (stat==1)
            return exportToExcel(response, detailsList);
        if (stat==2)
            return new ApiResponse("List for details",true,detailsList);

        return new ApiResponse("Error!",false);

    }


    public ApiResponse getAllBySimCardAndActionType(HttpServletResponse response, String action, Integer stat) throws IOException {
        SimCard card = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ActionType actionType = null;
        for (ActionType value : ActionType.values()) {
              if (value.toString().equalsIgnoreCase(action)){
                  actionType = value;
                  break;
                 }
              }
        if (actionType == null)
             return new ApiResponse("Error action type!", false);

        List<Details> detailsList = detailsRepository.findAllByActionTypeAndSimCard(actionType,card);
        if (stat==0)
            return exportToPdf(response, detailsList, card.getCode());
        if (stat==1)
            return exportToExcel(response, detailsList);
        if (stat==2)
            return new ApiResponse("List for details",true,detailsList);

        return new ApiResponse("Error!",false);
    }


    public ApiResponse add(DetailDto detailDto){
        SimCard principal = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal.getId().toString().equals(detailDto.getSimCard().getId().toString())){
                Details details = new Details();
                details.setAmount(detailDto.getAmount());
                details.setActionType(detailDto.getActionType());
                details.setSimCard(detailDto.getSimCard());
                detailsRepository.save(details);
                return new ApiResponse("Details saqlandi!", true);
            }

        return new ApiResponse("Error!", false);
    }
}