package main.java.entities;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;

import main.java.MessageProcessor;
import main.java.entities.Message3.Adjustment;

@XmlRootElement
public class Message3 extends Message{
	
	
	public enum Adjustment {//we limit the adjustment types with an enum as validation
		//as they are specified per requirements 
	   ADD,
	   SUBTRACT,
	   MULTIPLY;
	    
	}
	
	private Adjustment adjustment;
	private BigDecimal adjAmount;
	private String adjType;	
	
	public Adjustment getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(Adjustment adjustment) {
		this.adjustment = adjustment;
	}

	public BigDecimal getAdjAmount() {
		return adjAmount;
	}

	public void setAdjAmount(BigDecimal adjAmount) {
		this.adjAmount = adjAmount;
	}

	public String getAdjType() {
		return adjType;
	}

	public void setAdjType(String adjType) {
		this.adjType = adjType;
	}

	@Override
	public String toString() {
		return "Message3 [adjustment=" + adjustment + ", adjAmount=" + adjAmount + ", adjType=" + adjType+"]";
	}
	
	public boolean isValid(){
		
		if(this.getAmount()==null || this.getType()==null || this.getAdjAmount()==null || this.getAdjType()==null 
				||this.adjustment==null){
			return false;
		}
		return true;
	}
	
	public void saveSales() {

		MessageProcessor.adjustmentList.add(this);
		Sale sale = new Sale();
		sale.setType(this.getType());
		sale.setAmount(this.getAmount());
		sale.setQuantity(1);
		MessageProcessor.list.add(sale);
		adjustSales();
	}
	
	public void adjustSales(){
		
	    if(this.getAdjustment()!=null && this.getAdjAmount() != null){//safety checks to prevent exceptions
	    	
	    		if(this.getAdjustment().equals(Adjustment.ADD)){
	    			for(Sale s : MessageProcessor.list){//we loop in the list and check the elements one by one
	    				if(this.getAdjType().equals(s.getType())){//we adjust sales of the specified type only
	    					s.setAmount(s.getAmount().add(this.getAdjAmount()));
	    				}
	    			}
	    		}else if(this.getAdjustment().equals(Adjustment.SUBTRACT)){
	    			for(Sale s : MessageProcessor.list){
	    				if(this.getAdjType().equals(s.getType())){//we adjust sales of the specified type only
	    					s.setAmount(s.getAmount().subtract(this.getAdjAmount()).compareTo(BigDecimal.ZERO)<0
	    					? BigDecimal.ZERO:s.getAmount().subtract(this.getAdjAmount()));//in case the amount after the 
	    					//subtraction is negative, we store it as zero, no point in negative amounts in this case
	    					//unless the company intended to refund money for the sales of this type
	    				}
	    			}
	    		}else if(this.getAdjustment().equals(Adjustment.MULTIPLY)){
	    			for(Sale s : MessageProcessor.list){
	    				if(this.getAdjType().equals(s.getType())){//we adjust sales of the specified type only
	    					s.setAmount(s.getAmount().multiply(this.getAdjAmount()));
	    				}
	    			}
	    		}
    		}
    	}
	
	
	
	
	
}
