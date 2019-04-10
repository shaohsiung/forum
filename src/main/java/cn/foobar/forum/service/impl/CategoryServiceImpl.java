package cn.foobar.forum.service.impl;

import cn.foobar.forum.entity.Category;
import cn.foobar.forum.repository.CategoryRepository;
import cn.foobar.forum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author CategoryServiceImpl
 * @Date 2019/4/6 11:49
 * @Version 1.0.0
 * @Description
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long catId) {
        return categoryRepository.findById(catId).get();
    }
}
