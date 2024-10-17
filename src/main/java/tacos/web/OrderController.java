package tacos.web;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.support.SessionStatus;
import tacos.data.UserRepository;
import tacos.data.entity.TacoOrder;
import tacos.data.OrderRepository;
import tacos.data.entity.User;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
//@PreAuthorize("hasAuthority('User')")
public class OrderController {

    private final UserRepository userRepository;
    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo, UserRepository userRepository) {
        this.orderRepo = orderRepo;
        this.userRepository = userRepository;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute TacoOrder order) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }

        return "orderForm";
    }

    @PostMapping("/current")
    public String processOrder(
            @Valid TacoOrder tacoOrder, Errors errors,
            SessionStatus sessionStatus/*,
            @AuthenticationPrincipal User user*/) {


        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors());
            return "orderForm";
        }

//        tacoOrder.setUser(user);

        orderRepo.save(tacoOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }

}
