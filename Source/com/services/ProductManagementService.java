package com.services;

import com.entities.Product;

public interface ProductManagementService {

	Product[] getProducts();

	Product getProductById(int productIdToAddToCart);

}
