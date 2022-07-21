package com.gs.GameStore.controller.mvc;

import com.gs.GameStore.controller.entity.Product;
import com.gs.GameStore.controller.repository.ProductRepository;
import com.gs.GameStore.controller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/productsControl")
    public String viewProducts(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("listaProizvoda", productList);
        return "productsMgm";
    }

    @GetMapping("/deleteProduct/{product_name}")
    public String deleteProduct(@PathVariable("product_name") String name) {
        Product product = productRepository.findByName(name);
        productRepository.delete(product);
        return "redirect:/";
    }

    @GetMapping("/productAdd")
    public String addNewProduct(Model model) {
        Product product = new Product();
        model.addAttribute("prazanProduct", product);
        return "newProduct";
    }

    @PostMapping("/productImplementation")
    public String productImplementation(@ModelAttribute("prazanProduct") Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/products")
    public String viewProduct() {
        return "products";
    }

    @GetMapping("/productDetails")
    public String viewProductDetails() {
        return "productDetails";
    }
}
