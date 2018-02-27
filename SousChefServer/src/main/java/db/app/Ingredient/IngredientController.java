package db.app.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping("/all")
    public List<Ingredient> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

    @RequestMapping("/type/{type}")
    public List<Ingredient> getAllByType(@PathVariable IngredientType type) {
        return ingredientService.getAllByType(type);
    }

    @RequestMapping("/{name}")
    public Ingredient getIngredient(@PathVariable String name){
        return ingredientService.getIngredient(name);
    }

    @RequestMapping(method= RequestMethod.POST, value="/add")
    public void addIngredient(@RequestBody Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{name}")
    public void deleteIngredient(@PathVariable String name){
        ingredientService.deleteIngredient(name);
    }
}
