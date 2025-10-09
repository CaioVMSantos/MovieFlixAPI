package br.com.movieflix.controller;

import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.request.CategoryRequest;
import br.com.movieflix.response.CategoryResponse;
import br.com.movieflix.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = categoryService.findAllCategories();
        return categories.stream().map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    @PostMapping()
    public CategoryResponse createCategory(@RequestBody CategoryRequest request){
        Category category = categoryService.createCategory(CategoryMapper.toCategory(request));
        return CategoryMapper.toCategoryResponse(category);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id){
        Optional<Category> optCategory = categoryService.findById(id);
        if (optCategory.isPresent()) {
            return CategoryMapper.toCategoryResponse(optCategory.get());
        }
        else {
            return null;
        }
    }

    @DeleteMapping({"/{id}"})
    public void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteById(id);
    }

}
