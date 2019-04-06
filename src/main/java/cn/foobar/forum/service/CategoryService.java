package cn.foobar.forum.service;

import cn.foobar.forum.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 管理员创建分类
     * @param category
     */
    void createCategory(Category category);

    /**
     * 获取所有分类信息
     * @return
     */
    List<Category> getAllCategories();

    /**
     * 根据catId获取分类
     * @param catId
     * @return
     */
    Category findCategoryById(Long catId);
}
