package main.java.entities;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public abstract class Message {

	
	private BigDecimal amount = BigDecimal.ZERO;
	private String type;
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public abstract boolean isValid();
	
	public abstract void saveSales();
	
}
