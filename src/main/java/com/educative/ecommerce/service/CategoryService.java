package com.educative.ecommerce.service;

import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> listCategory() {
        return categoryRepo.findAll();
    }

    public void editCategory(int categoryId, Category updateCategory) throws Exception {
        if (lock.writeLock().tryLock()) {
            try {
                Category category = categoryRepo.getById(categoryId);
                category.setCategoryName(updateCategory.getCategoryName());
                category.setDescription(updateCategory.getDescription());
                category.setImageUrl(updateCategory.getImageUrl());
                categoryRepo.save(category);
            } finally {
                lock.writeLock().unlock();
            }
        } else {
            throw new Exception("failed to acquire lock");
        }
    }

    public boolean findById(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }

}
