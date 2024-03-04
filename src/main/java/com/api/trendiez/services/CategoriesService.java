package com.api.trendiez.services;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoriesService {
    private final CategoriesService categoriesService;

    public CategoriesService(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    public Collection<Object> getAllCategories() {
        return categoriesService.getAllCategories();
    }
}
