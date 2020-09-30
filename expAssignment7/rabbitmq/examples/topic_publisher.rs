use amiquip::{Connection, ExchangeDeclareOptions, ExchangeType, Publish, Result};
use std::fs::File;
use std::io::{self, prelude::*, BufReader};

fn main() -> Result<()> {

   let mut con = Connection::insecure_open("amqp://guest:guest@localhost:5672")?;
   let channel = con.open_channel(None)?;

   let exchange = channel.exchange_declare(
      ExchangeType::Topic,
      "quotes",
      ExchangeDeclareOptions::default(),
   )?;

   let quotes = open_quotes();
   let mut routingkey;
   let mut counter = 0;
   let mut short = 0;
   let mut long = 0;


   for msg in quotes.unwrap().lines() {
      if msg.len() <= 40 {
         routingkey = "SHORT";
         short = short+1;
      } else {
         routingkey = "LONG";
         long = long+1;
      }

      exchange.publish(
         Publish::new(msg.as_bytes(), routingkey.clone())
      )?;
      println!("Sent {}:{}", routingkey, msg);
      counter = counter+1;
      if counter == 1000 {
         println!("Sent {}: Short:{} Long:{}", counter, short, long);
         break;
      }
   }

   con.close()?;
   
   Ok(())
}

fn open_quotes() -> io::Result<String> {
   let file = File::open("sampledata/quotes.txt")?; // error handling

   let mut content = String::new();
   let mut reader = BufReader::new(file);
   reader.read_to_string(&mut content)
           .expect("Failed to read lines");

   Ok(content)
}