package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.data.entity.Ingredient;
import tacos.data.entity.Ingredient.Type;
import tacos.data.entity.Taco;
import tacos.data.entity.TacoOrder;
import tacos.data.IngredientRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
//@RequestMapping("/design")
@SessionAttributes("tacoOrder")
//@ComponentScan
//@PreAuthorize("hasAuthority('User')")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping("/design")
    public String showDesignForm(){
        return "design";
    }

    @PostMapping("/design")
    public String processTaco(
            @Valid Taco taco, Errors errors,
            @ModelAttribute TacoOrder tacoOrder) {

        if(errors.hasErrors()) {
//            System.out.println(errors.getAllErrors());
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}
