package main.java.entities;

import java.math.BigDecimal;

public class Sale {
		private String type;
		private BigDecimal amount=BigDecimal.ZERO;
		private Integer quantity;
		public synchronized String getType() {
			return type;
		}
		public synchronized void setType(String type) {
			this.type = type;
		}
		public synchronized BigDecimal getAmount() {
			return amount;
		}
		public synchronized void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public synchronized Integer getQuantity() {
			return quantity;
		}
		public synchronized void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}		

}
