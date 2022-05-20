import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG;

public class Producer {

    public static void main(String[] args) {

        String clientId = "my-producer";
        Properties props = new Properties();

        props.put(BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9093,localhost:9094");
        props.put(KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        props.put(VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        props.put(ACKS_CONFIG, "all");
        props.put(CLIENT_ID_CONFIG, clientId);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        int nuOfRecords = 100;
        String topic = "numbers";

        try {
            for (int i = 0; i <= nuOfRecords; i++) {
                String message = String.format("Producer %s has sent message %s at %s", clientId, i, new Date());
                System.out.println(message);
                producer.send(new ProducerRecord<>(topic, Integer.toString(i), message));
                Thread.sleep(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.flush();
            producer.close();
        }

    }

}
