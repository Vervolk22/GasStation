package com.coherentsolutions.webauction.presentation;

import com.coherentsolutions.webauction.databaseaccess.entities.Category;
import com.coherentsolutions.webauction.databaseaccess.entities.Lot;
import com.coherentsolutions.webauction.databaseaccess.services.LotService;
import com.coherentsolutions.webauction.presentation.utilities.DataTableParametersGetter;
import com.coherentsolutions.webauction.presentation.utilities.LotComparator;
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
@RequestMapping("/lot")
public class PurchaseController {
    private static LotService lotService;

    public LotService getLotService() {
        return lotService;
    }

    public void setLotService(LotService lotService) {
        this.lotService = lotService;
    }

    @RequestMapping(value="/addlot")
    public String addLot() {
        return "addlot";
    }

    @RequestMapping(value = "/lots", method = RequestMethod.GET)
    public void lots(HttpServletRequest request, HttpServletResponse response) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Category cat = new Category();
        cat.setId(1);
        List<Lot> list = lotService.read(cat);
        DataTableParametersGetter getter = new DataTableParametersGetter(request);
        Collections.sort(list, new LotComparator(getter.getSortingColumnsNumber(),
                getter.getSortingColumns(), getter.getDirections()));
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        int start = getter.getStartNum();
        int toDisplay = getter.getNumRecordsToDisplay();
        int end = (start + toDisplay) < list.size() ? start + toDisplay : list.size();
        for(int i = start; i < end; i++) {
            Lot lot = list.get(i);
            arrayBuilder.add(Json.createArrayBuilder()
                    .add(lot.getId())
                    .add(lot.getName())
                    .add(lot.getStarttime().toString())
                    .add(lot.getEndtime().toString())
                    .add(lot.getCurrentbid())
                    .add(lot.getBids()));
        }
        builder.add("aaData", arrayBuilder);
        builder.add("sEcho", getter.getEchoParameter());
        builder.add("iTotalRecords", lotService.readCount());
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
