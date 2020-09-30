use amiquip::{Connection, ConsumerMessage, ConsumerOptions, Result, QueueDeclareOptions};
use std::thread;
use std::time::Duration;

const TASK_QUE: &'static str="task_que";

fn main() -> Result<()> {

   let mut con = Connection::insecure_open("amqp://guest:guest@localhost:5672")?;
   let channel = con.open_channel(None)?;

   let que = channel.queue_declare(
      TASK_QUE, 
      QueueDeclareOptions {
         durable: true,
         ..QueueDeclareOptions::default()
      },
   )?;

   channel.qos(0, 1, false)?;

   let consumer = que.consume(ConsumerOptions::default())?;
   println!("Waiting for messages:");
   
   for(i, msg ) in consumer.receiver().iter().enumerate() {
      match msg {
         ConsumerMessage::Delivery(delivery) => {
            let body = String::from_utf8_lossy(&delivery.body);
            println!("({:>3}) Received [{}]", i, body);

            let dowork = delivery.body.iter().filter(|&&b| b ==b'.').count();
            thread::sleep(Duration::from_secs(dowork as u64));
            println!("({:>3}) ... done working", i);
            consumer.ack(delivery)?;
         }
         other => {
            println!("Consumer ended: {:?}", other);
            break;
         }
      }
   }

   con.close()?;
   
   Ok(())
}