package com.example.userordersproject.service;

import com.example.userordersproject.dto.ProductReadOnlyDTO;
import com.example.userordersproject.model.Product;

import java.util.List;

public interface IProductService {
    public List<ProductReadOnlyDTO> getAllProducts();

}
