use amiquip::{
   Connection, ConsumerMessage, ConsumerOptions, 
   ExchangeDeclareOptions, ExchangeType, FieldTable,
   QueueDeclareOptions, Result
};

fn main() -> Result<()> {

   let mut con = Connection::insecure_open("amqp://guest:guest@localhost:5672")?;
   let channel = con.open_channel(None)?;


   let exchange = channel.exchange_declare(
      ExchangeType::Topic,
      "quotes",
      ExchangeDeclareOptions::default(),
   )?;

   let que = channel.queue_declare(
      "", 
      QueueDeclareOptions{
         exclusive: true,
         ..QueueDeclareOptions::default()
      },
   )?;

   println!("Que is ready {}", que.name());
   que.bind(&exchange, "SHORT", FieldTable::new())?;
   que.bind(&exchange, "LONG", FieldTable::new())?;
   

   let consumer = que.consume(
      ConsumerOptions {
         no_ack:true,
         ..ConsumerOptions::default()
      }
   )?;

   println!("Waiting for quotes");
   let mut short = 0;
   let mut long = 0;

   for (i, msg) in consumer.receiver().iter().enumerate() {
      match msg {
         ConsumerMessage::Delivery(delivery) => {
            if delivery.routing_key == "SHORT" {
               short = short +1;
            } else {
               long = long +1;
            }
            let body = String::from_utf8_lossy(&delivery.body);
            println!("({:3}) {}:{}", i, delivery.routing_key, body);
         }
         other => {
            println!("No more quptes {:?}", other);
            break;
         }
      }
   }

   con.close()?;
   Ok(())
   
}