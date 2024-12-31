package it.mbaziekone.book_e_commerce.service.impl;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.mbaziekone.book_e_commerce.model.Customer;
import it.mbaziekone.book_e_commerce.model.Order;
import it.mbaziekone.book_e_commerce.model.OrderItem;
import it.mbaziekone.book_e_commerce.model.dto.Purchase;
import it.mbaziekone.book_e_commerce.model.dto.PurchaseResponse;
import it.mbaziekone.book_e_commerce.repository.CustomerRepository;
import it.mbaziekone.book_e_commerce.service.CheckoutService;
import jakarta.transaction.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	private CustomerRepository customerRepository;
	
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		// retrieve the order info from dto
		Order order = purchase.getOrder();
		
		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		// populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(Item -> order.add(Item));
		
		// populate order with billingAddress and shippingAddress
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		// populate customer with order
		Customer customer = purchase.getCustomer();
		customer.add(order);
		
		// save to the database
		customerRepository.save(customer);
		
		// return a response
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		
		// generate a random UUID number (UUID version-4)
		// for details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
		return UUID.randomUUID().toString();
	}
}
