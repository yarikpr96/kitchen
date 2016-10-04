package Class.Services;

import Class.DTO.RecipeDTO;
import Class.Entity.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
public interface RecipeSer {

    void addOrEdit(Recipe recipe);

    void delete(int id_r);

    Recipe findOneById(int id_r);

    void edit(int id_r, String name, String category, String description, MultipartFile multipartFile);

    RecipeDTO findOne(int id_r);


    List<RecipeDTO> findAll();
    List<Recipe> findAllNoI();
}
