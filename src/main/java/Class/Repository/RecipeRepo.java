package Class.Repository;

import Class.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
public interface RecipeRepo extends JpaRepository<Recipe,Integer> {
    @Query("FROM Recipe ")
    List<Recipe> findAll ();


}
