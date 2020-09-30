use amiquip::{Connection, Exchange, Publish, Result};

use std::fs::File;
use std::io::{self, prelude::*, BufReader};

fn main() -> Result<()>{

    let url = "amqp://guest:guest@localhost:5672";
    let _quename = "hello";
    let mut connection = Connection::insecure_open(&url)?;

    let channel = connection.open_channel(None)?;

    let exchange = Exchange::direct(&channel);

    
    let quotes = open_quotes();
    let mut counter = 0;

    for line in quotes.unwrap().lines() {
        counter = counter+1;
        let msg = Publish::new(line.as_bytes(),_quename.to_string());
        println!("Sending: {:?}", counter);
        exchange.publish(msg)
                .expect("Failed to publish msg");
        if counter == 40000 {
            break;
        }
    }

    // Publish a message to the "hello" queue.
    
    println!("Sent {:?} messages", counter);
    connection.close()?;
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