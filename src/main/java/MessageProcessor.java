package main.java;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import main.java.entities.Message;
import main.java.entities.Message1;
import main.java.entities.Message2;
import main.java.entities.Message3;
import main.java.entities.Sale;


public class MessageProcessor {

	//Since the requirements of this application are so strongly connected to global properties and features such as number of messages that the application has accepted(msgCounter),
	//I've preferred to use centralized control through this class with static functions.
	//Although multi-threading was not a requirement, I've chosen to used synchronized structures and methods to access the shared variables.
	//We will keep our sales in a memory structure such as a synchronized ArrayList<Sale>
	//MessageCounter must be static (or even better stored in ServletContext) so as
	//all incoming requests add to the same list.
	
	public static List<Sale> list = Collections.synchronizedList(new ArrayList<Sale>());
	public static List<Message3> adjustmentList = new ArrayList<Message3>();
	public static HashMap<String, BigDecimal> reportTotals = new HashMap<String, BigDecimal>();
	public static HashMap<String, Integer> reportNumber = new HashMap<String, Integer>();
	//it could be ConcurrentHashMap for concurrent read/writes (not our case), if we did not use it in synchronized method 

	public synchronized static void process(Message message, int msgCounter) {
        if(message instanceof Message1){
        	((Message1)message).saveSales();
        }
        else if(message instanceof Message2){
        	((Message2)message).saveSales();
        }
        else if(message instanceof Message3){
        	((Message3)message).saveSales();
        }
		createReports();//report after each 10 messages
		if(msgCounter==50){
			createAdjustmentReport();//final report, after 50 messages
		}
	}
	
	public static synchronized boolean checkCounter(ServletContext context){//method that checks msgCounter and allows or stops incoming requests
		int msgCounter = (int) context.getAttribute("msgCounter");
		if (msgCounter >= 50) {
			return false;
		}
		msgCounter++;//if message accepted, increases mesgCounter and re-set it to servletContext accordingly.
		context.setAttribute("msgCounter", msgCounter);
		return true;
	}


	private static void createReports(){
		if(MessageProcessor.list.size() % 10 == 0){
			//after each 10 messages
			for(int x=MessageProcessor.list.size()-10; x <MessageProcessor.list.size(); x++){//why calculate all report from scratch, we process only the 10 last added elements.
				Sale s = MessageProcessor.list.get(x);
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
			System.out.println("************Starting report************");
			for(String k: reportNumber.keySet()){//print all lines, both maps have the same key values
				System.out.println(k + ":" + reportNumber.get(k) + " " + k + "s are sold, Total amount:" + reportTotals.get(k));
			}
			System.out.println("************Report finished************");
		}
	}

	

	

	private static void createAdjustmentReport(){//the final report, when msgCounter reaches 50 messages
		//time for reporting
		System.out.println("I'm pausing now................Please don't bother");
		for(Message3 m: adjustmentList){//this list contains only adjustment messages(Message3)
			System.out.println(m.toString());
		}
	}



}
