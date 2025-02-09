package com.example.userordersproject.service;

import com.example.userordersproject.dto.ProductReadOnlyDTO;
import com.example.userordersproject.mapper.Mapper;
import com.example.userordersproject.model.Product;
import com.example.userordersproject.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final Mapper mapper;

    @Transactional
    @Override
    public List<ProductReadOnlyDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductReadOnlyDTO> productsDTO = products.stream()
                .map(mapper::mapToProductReadOnlyDTO)
                .collect(Collectors.toList());

        return productsDTO;
    }

}

