package main.java.entities;
import javax.xml.bind.annotation.XmlRootElement;


import main.java.MessageProcessor;

@XmlRootElement
public class Message1 extends Message{
	
	
	public boolean isValid(){
		if(this.getAmount()==null || this.getType()==null){
			return false;
		}
		return true;
	}
	
	public void saveSales() {

		Sale sale = new Sale();
		sale.setType(this.getType());
		sale.setAmount(this.getAmount());
		sale.setQuantity(1);

		MessageProcessor.list.add(sale);	
	
	}
	
}
