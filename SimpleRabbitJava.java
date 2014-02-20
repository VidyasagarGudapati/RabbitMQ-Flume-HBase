/**
 * 
 */

/**
 * @author Vidyasagar Gudapati
 *
 */

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleRabbitJava {

    public final static String CONFIG_HOSTNAME="localhost";
    public final static int CONFIG_PORT=5672;
    public final static String CONFIG_USERNAME="admin";
    public final static String CONFIG_PASSWORD="password";
    public final static String CONFIG_VIRTUALHOST="/";
    public final static int CONFIG_CONNECTIONTIMEOUT=30000;
    public final static String CONFIG_EXCHANGENAME="simple_exchange";
    public final static String CONFIG_QUEUENAME="simple_queue";

    @SuppressWarnings("deprecation")
	public static void main( String[] argv) throws IOException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(CONFIG_HOSTNAME);
        factory.setPort(CONFIG_PORT);
        factory.setUsername(CONFIG_USERNAME);
        factory.setPassword(CONFIG_PASSWORD);
        factory.setVirtualHost(CONFIG_VIRTUALHOST);
        factory.setConnectionTimeout(CONFIG_CONNECTIONTIMEOUT);
             
        
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        java.util.Date date= new java.util.Date();
        BasicProperties props  = new BasicProperties();
        Map<String, Object> headers = new HashMap<String, Object>();
        List<String> ccList = new ArrayList<String>();
        
        
        String message, msg;
        for(int i=0;i<20;i++){
        	  String id="123"+i;
        	  String id2="345"+i;
        	  Timestamp timestamp=new Timestamp(date.getTime());
        	  message=id+"-"+id2+"-"+timestamp+"	{\"id\":\"1234567\",\"id2\":\"T13213451\",\"sno\":\"1234567810\",\"typecode\":\"A01\"}";
    	    
    	      System.out.println("Sent '" + message + "'");
      	    ccList.add(message);
            headers.put(message, ccList);
            props.setHeaders(headers);
            channel.basicPublish("", CONFIG_QUEUENAME, props, message.getBytes());
        }
      
        channel.close();
        connection.close();
    }
}
