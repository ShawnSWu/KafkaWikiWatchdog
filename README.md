# WikiWatchDog

This is a Wikimedia changes record search tool that can explore the real-time change history of Wikimedia. Additionally, it provides a search experience similar to Google's search page.

This project aims to experiment with the native functionality of Kafka, observing and learning how it works. As such, no extended features like Kafka Connect, Kafka Streams, or KRaft mode are utilized.

The data source is the real-time change data provided by the Wikimedia Foundation.


**System arch:**
![](https://i.imgur.com/qtlqgrK.png)

## Why one ZK three Broker
Typically, at least three Zookeepers are needed for fault tolerance. However, for the focus of this project on Kafka, it is assumed that the Zookeeper is definitely stable. That's why only one Zookeeper is configured in this project.

## Wht three Broker
At least three brokers are typically required to achieve basic fault tolerance in Kafka.
This allows Kafka to continue providing reliable services even in the event of a broker failure, thanks to the election mechanism facilitated by Zookeeper, which selects a new leader broker to ensure uninterrupted service.


### Summary
In general, the selection of an appropriate number of Zookeepers and Brokers should be based on the magnitude of the traffic. Conducting stress tests would be a good approach to assess the system's performance under varying workloads.

--
## Preview
![](https://i.imgur.com/PudkIAT.gif)

## Front-End Vue project
[https://github.com/ShawnSWu/WikiHistory-Searchor](https://github.com/ShawnSWu/WikiHistory-Searchor)