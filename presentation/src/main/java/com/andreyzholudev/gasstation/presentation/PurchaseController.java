package com.andreyzholudev.gasstation.presentation;

import com.andreyzholudev.gasstation.dataaccess.dal.PurchaseDAO;
import com.andreyzholudev.gasstation.dataaccess.dal.UserDAO;
import com.andreyzholudev.gasstation.dataaccess.entities.PurchaseEntity;
import com.andreyzholudev.gasstation.dataaccess.entities.SimpleUserEntity;
import com.andreyzholudev.gasstation.dataaccess.entities.UserEntity;
import com.andreyzholudev.gasstation.presentation.utilities.DataTableParametersGetter;
import com.andreyzholudev.gasstation.presentation.utilities.PurchaseComparator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private static UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/addpurchase", method = RequestMethod.GET)
    public String addPurchase(HttpServletRequest request, HttpServletResponse response) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SimpleUserEntity simpleUser = userDAO.readByUsername(auth.getName());
        purchaseEntity.setCashier(user.get);
        /*purchaseEntity.set
        lotEntity.setCashier(u);
        CategoryEntity cat = new CategoryEntity();
        lotEntity.setCategory(cat);
        lotEntity.setUserForLot(user);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        lotEntity.setStarttime(new Date());
        lotEntity.setEndtime(calendar.getTime());
        request.setAttribute("lotEntity", purchaseEntity);*/
        //request.setAttribute("categories", categoryService.readAllAsMap());
        return "addpurchase";
    }

    /*@RequestMapping(value = "/addlot", method = RequestMethod.POST)
    public String addPurchase(HttpServletRequest request, HttpServletResponse response,
                         @Valid LotEntity lotEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            request.setAttribute("lotEntity", lotEntity);
            request.setAttribute("categories", categoryService.readAllAsMap());
            return "addlot";
        } else {
            lotService.create(lotEntity);
            return "index";
        }
    }*/

    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public void purchases(HttpServletRequest request, HttpServletResponse response) {
        UserEntity user = userDAO.read(3);


        response.setCharacterEncoding("UTF-8");
        JsonObjectBuilder builder = Json.createObjectBuilder();
        DataTableParametersGetter getter = new DataTableParametersGetter(request);
        List<PurchaseEntity> list = purchaseDAO.read();
        Collections.sort(list, new PurchaseComparator(getter.getSortingColumnsNumber(),
                getter.getSortingColumns(), getter.getDirections()));
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        int start = getter.getStartNum();
        int toDisplay = getter.getNumRecordsToDisplay();
        int end = (start + toDisplay) < list.size() ? start + toDisplay : list.size();
        for (int i = start; i < end; i++) {
            PurchaseEntity purchase = (PurchaseEntity) list.get(i);
            String s = purchase.getClient() == null ? "null" : purchase.getClient().toString();
            arrayBuilder.add(Json.createArrayBuilder()
                    .add(purchase.getId())
                    .add(purchase.getAmount())
                    .add(purchase.getPaid())
                    .add(purchase.getFuel().toString())
                    .add(purchase.getDay().toString())
                    .add(purchase.getTime().toString())
                    .add(purchase.getCashier() == null ? "unknown" :
                            purchase.getCashier().toString())
                    .add(purchase.getClient() == null ? "unknown" :
                            purchase.getClient().toString())
            );
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
