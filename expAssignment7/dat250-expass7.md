# DAT250: Experience Assignment 7

Author: Kenneth Fossen 577136@stud.hvl.no

## Tasks

| Task | Done |
| --- | --- |
|  Experiment 1: Installation | :white_check_mark: |
|  Experiment 2: Hello World | :white_check_mark:  |
|  Experiment 3: Queue messaging |  |
|  Experiment 4: Topics |  |




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
> cargo run --example hello_sender
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