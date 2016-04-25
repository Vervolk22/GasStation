package com.andreyzholudev.gasstation.presentation;

import com.andreyzholudev.gasstation.dataaccess.dal.*;
import com.andreyzholudev.gasstation.dataaccess.entities.*;
import com.andreyzholudev.gasstation.presentation.utilities.DataTableParametersGetter;
import com.andreyzholudev.gasstation.presentation.utilities.PurchaseComparator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


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
        getDateAndPrice();


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
        request.setAttribute("purchaseEntity", purchaseEntity);
        request.setAttribute("clients", clientDAO.read());
        request.setAttribute("fuels", fuelDAO.read());
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
        //UserEntity user = userDAO.read(3);

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
        return null;
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
