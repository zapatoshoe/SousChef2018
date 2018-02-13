package db.app.Ingredient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping("/all")
    public List<Ingredient> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

    @RequestMapping(method= RequestMethod.POST, value="/add")
    public void addIngredient(@RequestBody Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
    }
}
