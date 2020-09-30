# RabbitMQ examples

Kenneth Fossen

## Make sure you have RabbitMQ installed first

```sh
> docker run -d --name rabbitmq -p 5672:5672 \
    -p 15672:15672 rabbitmq:3-management
> docker start rabbitmq
> docker stop rabbitmq
```

## Run the examples

```sh
> cd rabbitmq/
> docker start rabbitmq
> cargo run --example hello_consumer
> cargo run --example hello_sender
> cargo run --example workque_task
> cargo run --example workque_handler
> cargo run --example topic_publisher
> cargo run --example topic_handler
> docker stop rabbitmq
```