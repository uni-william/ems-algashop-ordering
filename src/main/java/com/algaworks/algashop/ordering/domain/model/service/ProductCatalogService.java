package com.algaworks.algashop.ordering.domain.model.service;

import com.algaworks.algashop.ordering.domain.model.valueObject.Product;
import com.algaworks.algashop.ordering.domain.model.valueObject.id.ProductId;

import java.util.Optional;

public interface ProductCatalogService {

    Optional<Product> ofId(ProductId productId);
}
