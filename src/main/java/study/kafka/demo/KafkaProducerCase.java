package study.kafka.demo;


import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;



public class KafkaProducerCase {

	public static void main(String[] args) {
		 Properties props = new Properties();
		 props.put("bootstrap.servers", "192.168.95.73:9092");
         props.put("acks", "all");
         props.put("retries", 0);
         props.put("batch.size", 16384);
         props.put("buffer.memory", 33554432);
         props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
         props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 
		 Producer<String, String> producer = new KafkaProducer<>(props);
		 for(int i = 0; i < 2; i++)
	            producer.send(new ProducerRecord("test2", Integer.toString(i), Integer.toString(i)+"bb"));
		 producer.close();
		 
	}
}
