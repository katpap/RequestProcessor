package main.java.entities;

import java.math.BigDecimal;

public class Sale {
		private String type;
		private BigDecimal amount=BigDecimal.ZERO;
		private Integer quantity;
		public  String getType() {
			return type;
		}
		public  void setType(String type) {
			this.type = type;
		}
		public  BigDecimal getAmount() {
			return amount;
		}
		public  void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public  Integer getQuantity() {
			return quantity;
		}
		public  void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}		

}
