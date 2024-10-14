package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;
import tacos.data.IngredientRepository;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingRepo) {
        return args -> {
            ingRepo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            ingRepo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            ingRepo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            ingRepo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            ingRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            ingRepo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            ingRepo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            ingRepo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            ingRepo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            ingRepo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        };
    }


}
