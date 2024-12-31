package it.mbaziekone.book_e_commerce.model.dto;

public class PurchaseResponse {
	
private String orderTrackingNumber;
	
	public PurchaseResponse(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}
}
