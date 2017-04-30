package main.java.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import main.java.configuration.MessageAppConfiguration;
import main.java.entities.Sale;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MessageAppConfiguration.class})
@WebAppConfiguration  
public class MyTest {



	@Test
	public void test_code_from_MessageProcessor_createReport() {

		List<Sale> list = Collections.synchronizedList(new ArrayList<Sale>());
		Sale s0 = new Sale();
		s0.setType("apple");
		s0.setQuantity(1);
		s0.setAmount(BigDecimal.ONE);
		Sale s2 = new Sale();
		s2.setType("banana");
		s2.setQuantity(2);
		s2.setAmount(BigDecimal.ONE);
		Sale s3 = new Sale();
		s3.setType("fig");
		s3.setQuantity(3);
		s3.setAmount(BigDecimal.ONE);
		Sale s33 = new Sale();
		s33.setType("apple");
		s33.setQuantity(1);
		s33.setAmount(BigDecimal.TEN);
		Sale s4 = new Sale();
		s4.setType("banana");
		s4.setQuantity(2);
		s4.setAmount(BigDecimal.TEN);
		Sale s5 = new Sale();
		s5.setType("fig");
		s5.setQuantity(3);
		s5.setAmount(BigDecimal.TEN);
		Sale s6 = new Sale();
		s6.setType("lemon");
		s6.setQuantity(2);
		s6.setAmount(BigDecimal.TEN);
		Sale s7 = new Sale();
		s7.setType("lemon");
		s7.setQuantity(1);
		s7.setAmount(BigDecimal.ONE);
		Sale s8 = new Sale();
		s8.setType("lemon");
		s8.setQuantity(1);
		s8.setAmount(BigDecimal.ONE);
		Sale s9 = new Sale();
		s9.setType("lemon");
		s9.setQuantity(1);
		s9.setAmount(BigDecimal.ONE);


		list.add(s0);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		list.add(s6);
		list.add(s7);
		list.add(s8);
		list.add(s9);
		list.add(s33);

		HashMap<String, BigDecimal> reportTotals = new HashMap<String, BigDecimal>();
		HashMap<String, Integer> reportNumber = new HashMap<String, Integer>();

		for(int x=list.size()-10; x <list.size(); x++){//why calculate all report from scratch, we process only the 10 last added elements.
			Sale s = list.get(x);
			if(reportNumber.get(s.getType())==null){//if report does not contain this type, add the type and number of occurencies
				reportNumber.put(s.getType(), s.getQuantity());
			}else{
				reportNumber.put(s.getType(), reportNumber.get(s.getType())+s.getQuantity());//else retrieve the type and add number of occcurencies
			}

			if(reportTotals.get(s.getType())==null){//if report does not contain this type, add the type and current amount
				reportTotals.put(s.getType(), s.getAmount().multiply(new BigDecimal(s.getQuantity())));
			}else{
				reportTotals.put(s.getType(), reportTotals.get(s.getType()).add(s.getAmount().multiply(new BigDecimal(s.getQuantity()))));//else retrieve the type and add current amount
			}
		}


		assertEquals(2, reportNumber.get("apple"), 0);
		assertEquals(4, reportNumber.get("banana"), 0);
		assertEquals(6, reportNumber.get("fig"), 0);
		assertEquals(5, reportNumber.get("lemon"), 0);
		
		assertEquals(new BigDecimal(11).doubleValue(), reportTotals.get("apple").doubleValue(), 0);
		assertEquals(new BigDecimal(22).doubleValue(), reportTotals.get("banana").doubleValue(), 0);
		assertEquals(new BigDecimal(33).doubleValue(), reportTotals.get("fig").doubleValue(), 0);
		assertEquals(new BigDecimal(23).doubleValue(), reportTotals.get("lemon").doubleValue(), 0);



	}

}
