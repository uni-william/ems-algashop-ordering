package com.algawork.algashop.ordering.domain.exception;

import com.algawork.algashop.ordering.domain.valueObject.id.ProductId;

public class ProductOutOfStockException extends DomainException{
    public ProductOutOfStockException(ProductId id) {
        super(String.format(ErrorMessages.ERROR_PRODUCT_IS_OUT_STOCK, id));
    }
}
