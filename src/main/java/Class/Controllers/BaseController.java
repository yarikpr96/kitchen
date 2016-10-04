package Class.Controllers;


import Class.DTO.AProductDTO;
import Class.DTO.ProductDTO;
import Class.DTO.RecipeDTO;
import Class.Entity.*;
import Class.Services.*;
import Class.Validations.CustomersVal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class BaseController {
    @Autowired
    private ProductSer productSer;
    @Autowired
    private CustomerSer customerSer;
    @Autowired
    private KitchenSer kitchenSer;
    @Autowired
    private RecipeSer recipeSer;
    @Autowired
    private CustomersVal customersVal;
    @Autowired
    private AProductSer aProductSer;

    //Щоб вводити дати
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String home() {
        return "views-base-home";
    }

    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    public String login() {
        return "views-base-login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("customer", new Customer());
        return "views-base-registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute Customer customer, BindingResult bindingResult) {
        customersVal.validate(customer, bindingResult);
        if (bindingResult.hasErrors()) {
            return "views-base-registration";
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
            customerSer.addOrEdit(customer);
            return "redirect:/loginpage";
        }
    }

    //Всі продукти
    @RequestMapping(value = "/allProduct", method = RequestMethod.GET)
    private String allProduct(Model model) {
        List<ProductDTO> productDTOs = productSer.findAll();
        model.addAttribute("product", productDTOs);
        return "views-product-allProduct";
    }

    //новий прод
    @RequestMapping(value = "/newProduct", method = RequestMethod.GET)
    private String newProduct() {
        return "views-product-newProduct";
    }

    // новий прод введ
    @RequestMapping(value = "/newProduct", method = RequestMethod.POST)
    public String newProduct(@RequestParam("name") String name, @RequestParam("date") Date date, @RequestParam("image") MultipartFile multipartFile) {
        Product product = new Product();
        product.setName(name);
        product.setDate(date);

        try {
            product.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        productSer.addOrEdit(product);
        return "redirect:/allProduct";
    }

    //редаг прод форма
    @RequestMapping(value = "/productEdit/{id_p}", method = RequestMethod.GET)
    public String productEdit(@PathVariable String id_p, Model model) {
        model.addAttribute("product", productSer.findOne(Integer.parseInt(id_p)));
        return "views-product-productEdit";
    }

    // редаг прод введ
    @RequestMapping(value = "/productEdit", method = RequestMethod.POST)
    public String productEdit(@RequestParam("id_p") int id_p, @RequestParam("name") String name, @RequestParam("date") Date date, @RequestParam("image") MultipartFile multipartFile) {
        productSer.edit(id_p, name, date, multipartFile);
        return "redirect:/allProduct";
    }

    //видал прод
    @RequestMapping(value = "/product/delete/{id_p}", method = RequestMethod.POST)
    public String delete(@PathVariable String id_p) {
        productSer.delete(Integer.parseInt(id_p));
        return "redirect:/allProduct";
    }

    // з продуктів в кухню бд
    @RequestMapping(value = "/product/{id_p}", method = RequestMethod.GET)
    public String addToBasket(@ModelAttribute Kitchen kitchen, Principal principal, @PathVariable String id_p) {
        Product product = productSer.findOneById(Integer.parseInt(id_p));
        kitchen.setProduct(product);
        kitchen.setCustomer(customerSer.findOne(Integer.parseInt(principal.getName())));
        kitchenSer.addOrEdit(kitchen);
        return "redirect:/allProduct";
    }

    //Всі прод які є в певного юзера на кухні
    @RequestMapping(value = "/allKitchen", method = RequestMethod.GET)
    private String allKitchen(Model model, Principal principal) {
        List<Kitchen> kitchenList = kitchenSer.findAll();
        List<Kitchen> list = new ArrayList<>();
        for (Kitchen kitchen1 : kitchenList) {
            if (kitchen1.getCustomer().getId() == Integer.parseInt(principal.getName())) {
                list.add(kitchen1);
            }
        }
        List<ProductDTO> productDTOs = productSer.findAll();
        List<ProductDTO> listP = new ArrayList<>();
        for (Kitchen kitchen : list) {
            for (ProductDTO productDTO : productDTOs) {
                if (productDTO.getId_p() == kitchen.getProduct().getId_p()) {
                    listP.add(productDTO);
                }
            }
        }
        model.addAttribute("kitchen", listP);
        return "views-kitchen-allKitchen";
    }

    // видал з кухні
    @RequestMapping(value = "/kitchen/delete/{id_p}", method = RequestMethod.POST)
    public String deleteK(@PathVariable String id_p) {
        List<Kitchen> kitchenList = kitchenSer.findAll();
        int count = 0;
        for (Kitchen kitchen : kitchenList) {
            if (kitchen.getProduct().getId_p() == Integer.parseInt(id_p) && (count == 0)) {
                kitchenSer.delete(kitchen.getId_k());
                productSer.delete(Integer.parseInt(id_p));
                count++;
            }
        }
        return "redirect:/allKitchen";
    }

    // редаг дати прод форм
    @RequestMapping(value = "/kitchenEdit/{id_p}", method = RequestMethod.GET)
    public String kitchenEdit(@PathVariable String id_p, Model model) {
        model.addAttribute("product", productSer.findOne(Integer.parseInt(id_p)));
        return "views-kitchen-kitchenEdit";
    }

    // редаг дати прод ввід
    @RequestMapping(value = "/kitchenEdit", method = RequestMethod.POST)
    public String kitchenEdit(@RequestParam("id_p") int id_p, @RequestParam("date") Date date) {
        productSer.editt(id_p, date);
        return "redirect:/allKitchen";
    }

    // продукти що минає термін придатності 3 дня
    @RequestMapping(value = "/endKitchen", method = RequestMethod.GET)
    private String endKitchen(Model model, Principal principal) {
        List<Kitchen> kitchenList = kitchenSer.findAll();
        List<Kitchen> list = new ArrayList<>();
        for (Kitchen kitchen1 : kitchenList) {
            if (kitchen1.getCustomer().getId() == Integer.parseInt(principal.getName())) {
                list.add(kitchen1);
            }
        }
        List<ProductDTO> productDTOss = productSer.findAll();
        List<ProductDTO> listend = new ArrayList<>();
        Date date = Calendar.getInstance().getTime();
        for (ProductDTO productDTO : productDTOss) {
            if (productDTO.getDate().getYear() == date.getYear()) {
                if (productDTO.getDate().getMonth() == date.getMonth()) {
                    if (productDTO.getDate().getDay() - date.getDay() == 3 || productDTO.getDate().getDay() - date.getDay() == 2 || productDTO.getDate().getDay() - date.getDay() == 1 ) {
                        listend.add(productDTO);
                    }
                }
            }
        }

        model.addAttribute("endKitchen", listend);
        return "views-kitchen-endKitchen";
    }

    //Всі рецепти
    @RequestMapping(value = "/allRecipe", method = RequestMethod.GET)
    private String allRecipe(Model model) {
        List<RecipeDTO> recipeDTOs = recipeSer.findAll();
        model.addAttribute("recipe", recipeDTOs);
        return "views-recipe-allRecipe";
    }

    //додати продукт  в рецент
    @RequestMapping(value = "/productres/{id_ap}", method = RequestMethod.GET)
    public String addToBasketPtR(HttpSession httpSession, @PathVariable String id_ap) {
        List<AProduct> productList = (List<AProduct>) httpSession.getAttribute("product");
        AProduct product = aProductSer.findOneById(Integer.parseInt(id_ap));
        if (productList == null) {
            productList = new ArrayList<AProduct>();
        }
        productList.add(product);
        httpSession.setAttribute("product", productList);
        return "redirect:/allAProduct";
    }

    //новий рецепт форма
    @RequestMapping(value = "/newRecipe", method = RequestMethod.GET)
    private String newRecipe(HttpSession httpSession, Model model) {
        List<AProduct> productList = (List<AProduct>) httpSession.getAttribute("product");
        if (productList == null) {
            productList = new ArrayList<>();
        }
        model.addAttribute("product", productList);

        return "views-recipe-newRecipe";
    }

    //новий рецепт ввід
    @RequestMapping(value = "/newRecipe", method = RequestMethod.POST)
    public String newRecipe(HttpSession httpSession, @RequestParam("name") String
            name, @RequestParam("category") String category, @RequestParam("description") String
                                    description, @RequestParam("image") MultipartFile multipartFile) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setCategory(category);
        recipe.setDescription(description);
        List<AProduct> productList = (List<AProduct>) httpSession.getAttribute("product");
        if (productList == null) {
            productList = new ArrayList<>();
        }
        recipe.setProductList(productList);
        try {
            recipe.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        recipeSer.addOrEdit(recipe);
        httpSession.removeAttribute("product");
        return "redirect:/allRecipe";
    }

    //редагування рецепта форма
    @RequestMapping(value = "/recipeEdit/{id_r}", method = RequestMethod.GET)
    public String recipeEdit(@PathVariable String id_r, Model model) {
        model.addAttribute("recipe", recipeSer.findOne(Integer.parseInt(id_r)));
        return "views-recipe-recipeEdit";
    }

    //редагування рецепта ввід
    @RequestMapping(value = "/recipeEdit", method = RequestMethod.POST)
    public String recipeEdit(@RequestParam("id_r") int id_r, @RequestParam("name") String name,
                             @RequestParam("category") String category, @RequestParam("description") String description,
                             @RequestParam("image") MultipartFile multipartFile) {
        recipeSer.edit(id_r, name, description, category, multipartFile);
        return "redirect:/allRecipe";
    }

    //видалення рецепта
    @RequestMapping(value = "/recipe/delete/{id_r}", method = RequestMethod.POST)
    public String deleteR(@PathVariable String id_r) {
        recipeSer.delete(Integer.parseInt(id_r));
        return "redirect:/allRecipe";
    }


    //Всі продукти
    @RequestMapping(value = "/allAProduct", method = RequestMethod.GET)
    private String allAProduct(Model model) {
        List<AProductDTO> productDTOs = aProductSer.findAll();
        model.addAttribute("aproduct", productDTOs);
        return "views-aproduct-allAProduct";
    }

    //новий прод
    @RequestMapping(value = "/newAProduct", method = RequestMethod.GET)
    private String newAProduct() {
        return "views-aproduct-newAProduct";
    }

    // новий прод введ
    @RequestMapping(value = "/newAProduct", method = RequestMethod.POST)
    public String newAProduct(@RequestParam("name") String name, @RequestParam("date") Date
            date, @RequestParam("image") MultipartFile multipartFile) {
        AProduct product = new AProduct();
        product.setName(name);
        product.setDate(date);

        try {
            product.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        aProductSer.addOrEdit(product);
        return "redirect:/allAProduct";
    }

    //редаг прод форма
    @RequestMapping(value = "/aproductEdit/{id_ap}", method = RequestMethod.GET)
    public String aproductEdit(@PathVariable String id_ap, Model model) {
        model.addAttribute("product", aProductSer.findOne(Integer.parseInt(id_ap)));
        return "views-aproduct-aproductEdit";
    }

    // редаг прод введ
    @RequestMapping(value = "/aproductEdit", method = RequestMethod.POST)
    public String aproductEdit(@RequestParam("id_ap") int id_ap, @RequestParam("name") String name,
                               @RequestParam("date") Date date, @RequestParam("image") MultipartFile multipartFile) {
        aProductSer.edit(id_ap, name, date, multipartFile);
        return "redirect:/allAProduct";
    }

    //вид прод
    @RequestMapping(value = "/aproduct/delete/{id_ap}", method = RequestMethod.POST)
    public String deletea(@PathVariable String id_ap) {
        aProductSer.delete(Integer.parseInt(id_ap));
        return "redirect:/allAProduct";

    }

    //з загальних продв свої прод
    @RequestMapping(value = "/aproduct/{id_ap}", method = RequestMethod.GET)
    public String addToP(@ModelAttribute Kitchen kitchen, Principal principal, @PathVariable String id_ap) {
        AProduct aProduct = aProductSer.findOneById(Integer.parseInt(id_ap));
        Product product = new Product();
        product.setName(aProduct.getName());
        product.setDate(aProduct.getDate());
        product.setImage(aProduct.getImage());
        product.setCustomer(customerSer.findOne(Integer.parseInt(principal.getName())));
        productSer.addOrEdit(product);
        kitchen.setProduct(product);
        kitchen.setCustomer(customerSer.findOne(Integer.parseInt(principal.getName())));
        kitchenSer.addOrEdit(kitchen);
        return "redirect:/allKitchen";
    }

    /*
    ГAМНОКОД 88 ЛВЛ  ЯКЩО ВИ БАЧИТЕ ЦЕЙ ФРАГМЕНТ КОДУ ЗАБУДЬТЕ 18+ БЕЗ РЕЄСТРАЦІЇ ТА СМС
    ГAМНОКОД 88 ЛВЛ  ЯКЩО ВИ БАЧИТЕ ЦЕЙ ФРАГМЕНТ КОДУ ЗАБУДЬТЕ 18+ БЕЗ РЕЄСТРАЦІЇ ТА СМС
    ГAМНОКОД 88 ЛВЛ  ЯКЩО ВИ БАЧИТЕ ЦЕЙ ФРАГМЕНТ КОДУ ЗАБУДЬТЕ 18+ БЕЗ РЕЄСТРАЦІЇ ТА СМС
    ГAМНОКОД 88 ЛВЛ  ЯКЩО ВИ БАЧИТЕ ЦЕЙ ФРАГМЕНТ КОДУ ЗАБУДЬТЕ 18+ БЕЗ РЕЄСТРАЦІЇ ТА СМС
     */
    @RequestMapping(value = "/allMyEat", method = RequestMethod.GET)
    private String allMyEat(Model model) {
        List<Recipe> recipeList = recipeSer.findAllNoI();
        Set<Recipe> List = new HashSet<>();
        List<ProductDTO> productList = productSer.findAll();
        List<RecipeDTO> recipeDTOs = recipeSer.findAll();
        Set<RecipeDTO> set = new HashSet<>();
        int a = 0;
        for (Recipe recipe : recipeList) {
            for (AProduct aProduct : recipe.getProductList()) {
                for (ProductDTO productDTO : productList) {
                    if (aProduct.getName().equals(productDTO.getName())) {
                        a++;
                        if (a == recipe.getProductList().size()) {
                            List.add(recipe);
                        }
                    }
                }
            }
            a = 0;
        }
        for (Recipe recipe : List) {
            for (RecipeDTO recipeDTO : recipeDTOs) {
                if (recipe.getName().equals(recipeDTO.getName())) {
                    set.add(recipeDTO);
                }
            }
        }
        model.addAttribute("myrecipe", set);
        return "views-recipe-allMyEat";
    }
}