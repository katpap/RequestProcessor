package main.java.entities;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;

import main.java.MessageProcessor;

@XmlRootElement
public class Message1 extends Message{
	
	@Autowired
	ServletContext context;
	
    
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
