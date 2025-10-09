package br.com.movieflix.controller;

import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.request.CategoryRequest;
import br.com.movieflix.response.CategoryResponse;
import br.com.movieflix.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        Category category = categoryService.create(CategoryMapper.toCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
