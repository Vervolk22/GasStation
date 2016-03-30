package com.andreyzholudev.gasstation.presentation;

import com.andreyzholudev.gasstation.dataaccess.dal.PurchaseDAO;
import com.andreyzholudev.gasstation.dataaccess.entities.BaseEntity;
import com.andreyzholudev.gasstation.dataaccess.entities.PurchaseEntity;
import com.andreyzholudev.gasstation.presentation.utilities.DataTableParametersGetter;
import com.andreyzholudev.gasstation.presentation.utilities.PurchaseComparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    private static PurchaseDAO purchaseDAO = new PurchaseDAO();

    @RequestMapping(value="/addlot")
    public String addLot() {
        return "addlot";
    }

    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public void lots(HttpServletRequest request, HttpServletResponse response) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        List<BaseEntity> list = purchaseDAO.read();
        DataTableParametersGetter getter = new DataTableParametersGetter(request);
        Collections.sort(list, new PurchaseComparator(getter.getSortingColumnsNumber(),
                getter.getSortingColumns(), getter.getDirections()));
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        int start = getter.getStartNum();
        int toDisplay = getter.getNumRecordsToDisplay();
        int end = (start + toDisplay) < list.size() ? start + toDisplay : list.size();
        for(int i = start; i < end; i++) {
            PurchaseEntity purchase = (PurchaseEntity)list.get(i);
            arrayBuilder.add(Json.createArrayBuilder()
                    .add(purchase.getId())
                    .add(purchase.getAmount())
                    .add(purchase.getPaid())
                    .add(purchase.getTime().toString())
                    .add(purchase.getCashierId())
                    .add(purchase.getClientId() == null ? "unknown" :
                            purchase.getClientId().toString())
                    .add(purchase.getDayId())
                    .add(purchase.getFuelId()));
        }
        builder.add("aaData", arrayBuilder);
        builder.add("sEcho", getter.getEchoParameter());
        builder.add("iTotalRecords", purchaseDAO.readCount());
        builder.add("iDisplayStart", start);
        builder.add("iDisplayRecords", toDisplay);
        builder.add("iTotalDisplayRecords", list.size());
        response.setContentType("application/Json");
        try {
            response.getWriter().write(builder.build().toString());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
