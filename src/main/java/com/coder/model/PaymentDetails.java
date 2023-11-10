package com.coder.model;

import com.coder.user.domain.PaymentStatus;

public class PaymentDetails {

	private String paymentMethod;
	
	private PaymentStatus status;
	
	private String paymentId;
	
	private String razorpayPaymentLinkId;
	
	private String razorpayPaymentLinkReferenceId;
	
	private String razorpayPaymentLinkStatus;
	
	private String razorpayPaymentId;
	
	public PaymentDetails() {
		// TODO Auto-generated constructor stub
	}
	
	
	


	public PaymentDetails(String paymentMethod, PaymentStatus status, String paymentId, String razorpayPaymentLinkId,
			String razorpayPaymentLinkReferenceId, String razorpayPaymentLinkStatus, String razorpayPaymentId) {
		super();
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.paymentId = paymentId;
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
		this.razorpayPaymentId = razorpayPaymentId;
	}





	public String getPaymentMethod() {
		return paymentMethod;
	}





	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}





	public PaymentStatus getStatus() {
		return status;
	}





	public void setStatus(PaymentStatus status) {
		this.status = status;
	}





	public String getPaymentId() {
		return paymentId;
	}





	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}





	public String getRazorpayPaymentLinkId() {
		return razorpayPaymentLinkId;
	}





	public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
	}





	public String getRazorpayPaymentLinkReferenceId() {
		return razorpayPaymentLinkReferenceId;
	}





	public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
	}





	public String getRazorpayPaymentLinkStatus() {
		return razorpayPaymentLinkStatus;
	}





	public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
	}





	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}





	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}


	




	
	
	
}
