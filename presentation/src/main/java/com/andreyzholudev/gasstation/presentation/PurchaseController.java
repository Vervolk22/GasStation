package com.andreyzholudev.gasstation.presentation;

import com.andreyzholudev.gasstation.dataaccess.dal.*;
import com.andreyzholudev.gasstation.dataaccess.entities.*;
import com.andreyzholudev.gasstation.presentation.utilities.DataTableParametersGetter;
import com.andreyzholudev.gasstation.presentation.utilities.PurchaseComparator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;


@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    private static PurchaseDAO purchaseDAO = new PurchaseDAO();
    private static UserDAO userDAO = new UserDAO();
    private static SimpleUserDAO simpleUserDAO = new SimpleUserDAO();
    private static ClientDAO clientDAO = new ClientDAO();
    private static FuelDAO fuelDAO = new FuelDAO();
    private static CashierDAO cashierDAO = new CashierDAO();
    private static DayDAO dayDAO = new DayDAO();
    private static BranchDAO branchDAO = new BranchDAO();
    private static EveryDayPriceInfoDAO everyDayPriceInfoDAO = new EveryDayPriceInfoDAO();

    @RequestMapping(value = "/addpurchase", method = RequestMethod.GET)
    public String addPurchase(HttpServletRequest request, HttpServletResponse response) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SimpleUserEntity simpleUser = simpleUserDAO.readByUsername(auth.getName());
        purchaseEntity.setCashier(simpleUser.getCashier());
        purchaseEntity.setClient(new ClientEntity());
        purchaseEntity.setFuel(new FuelEntity());
        //changeEncodings(purchaseEntity);
        request.setAttribute("fuelPrices", getDateAndPrice());
        request.setAttribute("purchaseEntity", purchaseEntity);
        request.setAttribute("clients", clientDAO.read());
        List<FuelEntity> list = fuelDAO.read();
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getId(), list.get(i).getName());
        }
        request.setAttribute("fuels", map);
        return "addpurchase";
    }

    @RequestMapping(value = "/addpurchase", method = RequestMethod.POST)
    public String addPurchase(HttpServletRequest request, HttpServletResponse response,
                         @Valid PurchaseEntity purchaseEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            changeEncodings(purchaseEntity);
            request.setAttribute("purchaseEntity", purchaseEntity);
            request.setAttribute("fuelPrices", getDateAndPrice());
            request.setAttribute("clients", clientDAO.read());
            request.setAttribute("fuels", fuelDAO.read());
            return "addpurchase";
        } else {
            changeEncodings(purchaseEntity);
            purchaseEntity.setDay(dayDAO.readByString(new java.sql.Date(new java.util.Date().getTime())));
            purchaseEntity.setTime(new Time(new java.util.Date().getTime()));
            purchaseEntity.setClient(null);
            purchaseDAO.create(purchaseEntity);
            return "index";
        }
    }

    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    public void purchases(HttpServletRequest request, HttpServletResponse response) {
        //UserEntity user = userDAO.read(3);

        response.setCharacterEncoding("UTF-8");
        JsonObjectBuilder builder = Json.createObjectBuilder();
        DataTableParametersGetter getter = new DataTableParametersGetter(request);
        List<PurchaseEntity> list = purchaseDAO.read();
        int t = getter.getSortingColumnsNumber();
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

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public @ResponseBody List<Tag> clients(HttpServletRequest request, HttpServletResponse response) {
        List<ClientEntity> list = clientDAO.read();
        List<Tag> data = new ArrayList<Tag>(    );
        String tagName = request.getParameter("tagName");
        for(ClientEntity ce : list) {
            if (ce.getName().contains(tagName)) {
                data.add(new Tag(ce.getId(), ce.getName()));
            }
        }

        return data;
    }

    private void changeEncodings(PurchaseEntity purchaseEntity) {
        try {
            purchaseEntity.getCashier().setName(new String(purchaseEntity.getCashier().getName().getBytes("iso-8859-1"), "UTF-8"));
            purchaseEntity.getFuel().setName(new String(purchaseEntity.getFuel().getName().getBytes("iso-8859-1"), "UTF-8"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Map<FuelEntity, Integer> getDateAndPrice() {
        DayEntity dayEntity = new DayEntity();
        dayEntity.setDate(new java.sql.Date(new java.util.Date().getTime()));
        DayEntity dayEntity2 = dayDAO.readByString(dayEntity.getDate());
        if (!dayEntity.getDate().toString().equals(dayEntity2.getDate().toString())) {
            dayEntity.setId(dayEntity2.getId() + 1);
            dayDAO.create(dayEntity);
            dayEntity2 = dayEntity;
            generatePrices(dayEntity2);
        }
        return readPrices(dayEntity2);
    }

    private Map<FuelEntity, Integer> readPrices(DayEntity dayEntity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SimpleUserEntity simpleUser = simpleUserDAO.readByUsername(auth.getName());
        BranchEntity branchEntity = simpleUser.getCashier().getBranch();
        List<EverydayPriceInfoEntity> prices = everyDayPriceInfoDAO.read(dayEntity, branchEntity);
        Map<FuelEntity, Integer> pricesMap = new HashMap<>(prices.size());
        for(int i = 0; i < prices.size(); i++) {
            pricesMap.put(prices.get(i).getFuelEntity(), prices.get(i).getFuelPrice());
        }
        return pricesMap;
    }

    private void generatePrices(DayEntity dayEntity) {
        List<BranchEntity> brances = branchDAO.read();
        List<FuelEntity> fuels = fuelDAO.read();
        Random random = new Random();
        for(int i = 0; i < fuels.size(); i++) {
            for(int j = 0; j < brances.size(); j++) {
                EverydayPriceInfoEntity price = new EverydayPriceInfoEntity();
                price.setBranchEntity(brances.get(j));
                price.setFuelEntity(fuels.get(i));
                price.setDayEntity(dayEntity);
                price.setFuelPrice(random.nextInt(5000) + 8000);
                everyDayPriceInfoDAO.create(price);
            }
        }
    }
}
