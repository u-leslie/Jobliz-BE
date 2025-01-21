package com.leslie.Joblz.mappers;

import com.leslie.Joblz.dtos.CategoryDto;
import com.leslie.Joblz.entities.Category;

public class CategoryMapper {

    public static CategoryDto mapToCategoryDto (Category category){
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getJob()
        );
    }
    public static Category mapToCategory (CategoryDto categoryDto){
        return new Category(
                categoryDto.getId(),
                categoryDto.getName(),
                categoryDto.getDescription(),
                categoryDto.getJob()
        );
    }
}
