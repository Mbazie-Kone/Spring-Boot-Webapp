package it.mbaziekone.book_e_commerce.service;

import it.mbaziekone.book_e_commerce.model.dto.Purchase;
import it.mbaziekone.book_e_commerce.model.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);
}
