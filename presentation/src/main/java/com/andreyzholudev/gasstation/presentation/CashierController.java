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

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.Time;
import java.util.*;

/**
 * Created by AndreyZholudev on 5/2/2016.
 */
@Controller
@RequestMapping("/cashier")
public class CashierController {
    private static PurchaseDAO purchaseDAO = new PurchaseDAO();
    private static UserDAO userDAO = new UserDAO();
    private static SimpleUserDAO simpleUserDAO = new SimpleUserDAO();
    private static ClientDAO clientDAO = new ClientDAO();
    private static FuelDAO fuelDAO = new FuelDAO();
    private static CashierDAO cashierDAO = new CashierDAO();
    private static DayDAO dayDAO = new DayDAO();
    private static BranchDAO branchDAO = new BranchDAO();
    private static EveryDayPriceInfoDAO everyDayPriceInfoDAO = new EveryDayPriceInfoDAO();

    @RequestMapping(value = "/addcashier", method = RequestMethod.GET)
    public String addPurchase(HttpServletRequest request, HttpServletResponse response) {
        CashierEntity cashierEntity = new CashierEntity();
        cashierEntity.setBranch(new BranchEntity());
        List<BranchEntity> list = new ArrayList<>(branchDAO.read());
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getId(), list.get(i).getAddress().toString());
        }
        request.setAttribute("cashierEntity", cashierEntity);
        request.setAttribute("branches", map);
        request.setAttribute("password", "");
        return "addcashier";
    }

    @RequestMapping(value = "/addcashier", method = RequestMethod.POST)
    public String addPurchase(HttpServletRequest request, HttpServletResponse response,
                              @Valid CashierEntity cashierEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<BranchEntity> list = new ArrayList<>(branchDAO.read());
            Map<Integer, String> map = new HashMap<Integer, String>();
            for(int i = 0; i < list.size(); i++) {
                map.put(list.get(i).getId(), list.get(i).getAddress().toString());
            }
            request.setAttribute("cashierEntity", cashierEntity);
            request.setAttribute("branches", map);
            request.setAttribute("password", "");
            return "addpurchase";
        } else {
            AuthorityEntity authorityEntity = new AuthorityEntity();
            authorityEntity.setId(2);
            UserEntity userEntity = new UserEntity();
            userEntity.setAccountNonExpired(true);
            userEntity.setAccountNonLocked(true);
            userEntity.setCredentialsNonExpired(true);
            Set<AuthorityEntity> set = new HashSet<>();
            set.add(authorityEntity);
            userEntity.setAuthorities(set);
            userEntity.setUsername(cashierEntity.getName());
            String s = (String)request.getAttribute("password");
            userEntity.setPassword((String) request.getAttribute("password"));
            userDAO.create(userEntity);
            SimpleUserEntity simpleUserEntity = simpleUserDAO.readByUsername(cashierEntity.getName());
            cashierEntity.setStartdate(new java.sql.Date(new java.util.Date().getTime()));
            cashierEntity.setSimpleUser(simpleUserEntity);
            cashierDAO.create(cashierEntity);
            return "index";
        }
    }

    @RequestMapping(value = "/cashiers", method = RequestMethod.GET)
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
}
