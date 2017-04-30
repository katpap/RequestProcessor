package main.java.entities;
import javax.xml.bind.annotation.XmlRootElement;

import main.java.MessageProcessor;

@XmlRootElement
public class Message2 extends Message{

	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	public boolean isValid(){
		
		if(this.getAmount()==null || this.getType()==null || this.getQuantity()==null){
			return false;
		}
		return true;
	}
	
	public void saveSales() {

		Sale sale = new Sale();
		sale.setType(this.getType());
		sale.setAmount(this.getAmount());
		sale.setQuantity(this.getQuantity());

		MessageProcessor.list.add(sale);	
	}
	
	
}
