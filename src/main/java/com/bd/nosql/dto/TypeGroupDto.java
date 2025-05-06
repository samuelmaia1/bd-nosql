package com.bd.nosql.dto;

import java.util.List;

public class TypeGroupDto {
    private String type;
    private List<CategoryDto> categories;

    public TypeGroupDto(String type, List<CategoryDto> categories) {
        this.type = type;
        this.categories = categories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
}
