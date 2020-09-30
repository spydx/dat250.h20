use amiquip::{Connection, Exchange, Publish, Result,QueueDeclareOptions, AmqpProperties};

const TASK_QUE : &'static str = "task_que";

fn main() -> Result<()> {
   println!("[ > Opening connection");
   let mut con = Connection::insecure_open("amqp://guest:guest@localhost:5672")?;
   let channel = con.open_channel(None)?;
   
   println!("[X> Creating que");
   let _ = channel.queue_declare(
      TASK_QUE,
      QueueDeclareOptions {
          durable: true,
          ..QueueDeclareOptions::default()
      },
  )?;

   let exchange = Exchange::direct(&channel);

   let msg = "Hello world.......";
   println!("[ > Preparing msg ");
   exchange.publish(Publish::with_properties(
      msg.as_bytes(),
      TASK_QUE,
      // delivery_mode 2 makes message persistent
      AmqpProperties::default().with_delivery_mode(2),
   ))?;

   println!("[X> msg sendt");
   println!("[X> Closing Connection");

   con.close()?;

   Ok(())

}