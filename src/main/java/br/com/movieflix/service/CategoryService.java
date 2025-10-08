package br.com.movieflix.service;

import br.com.movieflix.entity.Category;
import br.com.movieflix.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAllCategories(){
        return repository.findAll();
    }
}
