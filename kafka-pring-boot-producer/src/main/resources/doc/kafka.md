##### kafka入门
1. 启动zookeeper
~~~
    bin/zookeeper-server-start.sh config/zookeeper.properties
~~~
2. 启动kafka
~~~
    修改/config/server.properties
    listeners=PLAINTEXT://192.168.183.128:9092

    bin/kafka-server-start.sh config/server.properties
~~~

3. 创建主题
~~~
    bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server 192.168.183.128:9092
    
    bin/kafka-topics.sh --bootstrap-server 192.168.183.128:9092 --create --topic my_topic_name --partitions 20 --replication-factor 3 \
    --config x=y
    
    
    bin/kafka-topics.sh --create --topic log --bootstrap-server '192.168.183.140:9092,192.168.183.141:9092,192.168.183.142:9002' --replication-factor 3 --partitions 10
~~~

4. 发送消息
~~~
    bin/kafka-console-producer.sh --broker-list 192.168.183.128:9092 --topic quickstart-events
~~~

5. 消费消息
~~~
    bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server 192.168.183.128:9092
~~~

6. 查看topic
~~~
    bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server 192.168.183.128:9092
    
    bin/kafka-topics.sh --list --bootstrap-server 192.168.183.128:9092
    
    # 删除topic
    bin/kafka-topics.sh --bootstrap-server 192.168.183.128:9092 --delete --topic quickstart-events

~~~

7. IMPORT/EXPORT YOUR DATA AS STREAMS OF EVENTS WITH KAFKA CONNECT
~~~
    vi config/connect-standalone.properties
    bootstrap.servers=192.168.183.128:9092
    plugin.path=libs/connect-file-3.4.0.jar
    # 创建测试文件
    echo -e "foo\nbar" > test.txt
    bin/connect-standalone.sh config/connect-standalone.properties config/connect-file-source.properties config/connect-file-sink.properties
    # 查看文件处理内容
    bin/kafka-console-consumer.sh --bootstrap-server 192.168.183.128:9092 --topic connect-test --from-beginning
    # 动态添加文件内容
    echo Another line>> test.txt
~~~

8. 验证配置
~~~
./kafka-verifiable-producer.sh --max-messages 10000 --acks -1   --topic app_order --bootstrap-server "192.168.183.140:9092,192.168.183.141:9092,192.168.183.142:9092" 
~~~
