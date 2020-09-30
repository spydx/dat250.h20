# DAT250: Experience Assignment 7

Author: Kenneth Fossen 577136@stud.hvl.no

## Tasks

| Task | Done |
| --- | --- |
| Experiment 1: Installation | :white_check_mark: |
| Experiment 2: Hello World | :white_check_mark:  |
| Experiment 3: Queue messaging | :white_check_mark: |
| Experiment 4: Topics | :white_check_mark: |
| Pending issues | :white_check_mark: |

### Experiment 1: Installation

Start by doing an installation as described here: https://www.rabbitmq.com/download.html

```sh
docker run -d --name rabbitmq -p 5672:5672 \
    -p 15672:15672 rabbitmq:3-management
docker start rabbitmq
docker stop rabbitmq
```

### Experiment 2: Hello World

Complete the ["Hello World" tutorial](https://www.rabbitmq.com/getstarted.html)

You can freely choose the programming language.

```sh
> cd rabbitmq/
> docker start rabbitmq
> cargo run --example hello_consumer
...
(39997) Received [People who don't take risks generally make about two big mistakes a year. People who do take risks generally make about two big mistakes a year.]
(39998) Received [If you care enough for a result, you will most certainly attain it.]
(39999) Received [If you command wisely, you'll be obeyed cheerfully.]

> cargo run --example hello_sender
Sending: 39997
Sending: 39998
Sending: 39999
Sending: 40000
Sent 40000 messages

> docker stop rabbitmq
```

### Experiment 3: Queue messaging

Complete the ["Work queues" tutorial](https://www.rabbitmq.com/getstarted.html)

You can freely choose the programming language.

```sh
> cd rabbitmq/
> docker start rabbitmq
> cargo run --example workque_task
    Finished dev [unoptimized + debuginfo] target(s) in 0.07s
     Running `target/debug/examples/workque_task`
 [ > Opening connection
 [X> Creating que
 [ > Preparing msg 
 [X> msg sendt
 [X> Closing Connection
> cargo run --example workque_handler
    Finished dev [unoptimized + debuginfo] target(s) in 1.45s
     Running `target/debug/examples/workque_handler`
Waiting for messages:
(  0) Received [Hello world..]
(  0) ... done working
(  1) Received [Hello world.....]
(  1) ... done working
(  2) Received [Hello world..]
(  2) ... done working
(  3) Received [Hello world.......]
(  3) ... done working
(  4) Received [Hello world.......]>
> docker stop rabbitmq
```

### Experiment 4: Topics

Complete the ["Publish/subscribe" tutorial](https://www.rabbitmq.com/getstarted.html)

You can freely choose the programming language.

```sh
> docker start rabbitmq

> cargo run --example topic_handler
(3998) LONG:It is strange to be known so universally and yet to be so lonely.
(3999) SHORT:Alone we can do so little together we can do so much.

> cargo run --example topic_publisher
Sent LONG:It is strange to be known so universally and yet to be so lonely.
Sent SHORT:Alone we can do so little together we can do so much.
Sent 1000: Short:41 Long:959

> docker stop rabbitmq
````

### Pending issues

No