package Class.Services.Implementation;

import Class.DTO.RecipeDTO;
import Class.Entity.Recipe;
import Class.Repository.RecipeRepo;
import Class.Services.RecipeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
@Service
public class RecipeSerImpl implements RecipeSer {

    @Autowired
    private RecipeRepo recipeRepo ;

    @Override
    public void addOrEdit(Recipe recipe) {
        recipeRepo.save(recipe);
    }

    @Override
    public void delete(int id_r) {
        recipeRepo.delete(id_r);
    }

    @Override
    public Recipe findOneById(int id_r) {
        return recipeRepo.findOne(id_r);
    }

    @Override
    public void edit(int id_r, String name, String category, String description, MultipartFile multipartFile) {
        Recipe recipe = recipeRepo.findOne(id_r);
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setCategory(category);
        try {
            recipe.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        recipeRepo.save(recipe);
    }


    @Override
    public RecipeDTO findOne(int id_r) {
        Recipe recipe = recipeRepo.findOne(id_r);
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId_r(recipe.getId_r());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setCategory(recipe.getCategory());
        recipeDTO.setDescription(recipe.getDescription());
        String image = Base64.getEncoder().encodeToString(recipe.getImage());
        recipeDTO.setImage(image);
        return recipeDTO;
    }

    @Override
    public List<RecipeDTO> findAll() {
        List<RecipeDTO> recipeDTOs = new ArrayList<>();
        List<Recipe> recipeList = recipeRepo.findAll();
        for (Recipe recipe : recipeList) {
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setId_r(recipe.getId_r());
            recipeDTO.setName(recipe.getName());
            recipeDTO.setCategory(recipe.getCategory());
            recipeDTO.setDescription(recipe.getDescription());
            String image = Base64.getEncoder().encodeToString(recipe.getImage());
            recipeDTO.setImage(image);
            recipeDTOs.add(recipeDTO);
        }
        return recipeDTOs;
    }

    @Override
    public List<Recipe> findAllNoI() {
        return recipeRepo.findAll();
    }
}
