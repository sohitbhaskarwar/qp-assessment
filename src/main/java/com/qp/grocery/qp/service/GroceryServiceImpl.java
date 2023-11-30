package com.qp.grocery.qp.service;

import com.qp.grocery.qp.common.Constant;
import com.qp.grocery.qp.entity.ProductEntity;
import com.qp.grocery.qp.request.ProductRequest;
import com.qp.grocery.qp.request.ProductUpdateRequest;
import com.qp.grocery.qp.response.ProductResponse;
import com.qp.grocery.qp.respository.ProductRepository;
import com.qp.grocery.qp.util.EntityToResponseUtil;
import com.qp.grocery.qp.util.RequestToEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GroceryServiceImpl implements GroceryService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse addGroceryProduct(ProductRequest productRequest) {
        ProductEntity productEntity =
                RequestToEntityUtil.fetchProductEntityFromRequest(productRequest);
        productEntity = productRepository.save(productEntity);

        return EntityToResponseUtil.fetchCreateProductResponse(productEntity);
    }

    @Override
    public List<ProductResponse> fetchAllGroceryDetails(Boolean isActive) {
        List<ProductEntity> productEntityList;
        if (Objects.isNull(isActive)) {
            productEntityList = productRepository.findAll();
        } else {
            productEntityList = productRepository.findAllByIsActive(isActive);
        }
        return EntityToResponseUtil.fetchAllProductDetails(productEntityList);
    }

    @Override
    public ProductResponse updateProductDetails(Long id, ProductUpdateRequest productUpdateRequest) {
        Optional<ProductEntity> existingProductEntityOptional = productRepository.findById(id);

        ProductEntity existingProductEntity =
                RequestToEntityUtil.fetchProductEntityFromRequestForUpdate(existingProductEntityOptional.get(),
                        productUpdateRequest);

        return EntityToResponseUtil.fetchCreateProductResponse(existingProductEntity);
    }

    @Override
    public String deleteProduct(Long id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isPresent()) {
            productEntityOptional.get().setIsActive(false);
            productRepository.save(productEntityOptional.get());
            return Constant.DELETE_PRODUCT_MSG;
        }
        return Constant.ERROR_PRODUCT_MSG;
    }
}
