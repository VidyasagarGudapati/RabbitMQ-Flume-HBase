RabbitMQ-Flume-HBase
====================

Streaming Real-time producing data to Apache HBase tables using Apache Flume and RabbitMQ Server
================================================================================================

Install RabbitMQ on Ubuntu Machine. You can find this at below link:

https://coderwall.com/p/gbrxka


Preparing Java class acts like a Data Producer
===============================================================

Create a Eclipse Java Project with the class attached. 

Add the following JAR's to BuildPath of Java Project:

commons-cli-1.1.jar

commons-io-1.2.jar

rabbitmq-client.jar

rabbitmq-client-tests.jar

junit.jar

Right click and Run the Class as Java Application.


Preparing Flume to collect data and to insert into HBase tables
=======================================================================

Create agent.properties and collector.properties files under $FLUME_HOME/conf

Add HBase Jar to lib dir of $FLUME_HOME.

And, start both the agent and collector flume files using following commands

flume-ng agent -n agent -c conf -f conf/agent.conf  -Dflume.root.logger=DEBUG,console --conf ./cong/

flume-ng agent -n collector -c conf -f conf/collector.conf  -Dflume.root.logger=DEBUG,console --conf ./cong/

Description
========================================================================

Here we're Sending data from Sample Java class and Collecting data at flume agent and parsing this data to HBase through flume collector. 

Avro plays very key role in this action. When HBase is not up and running, usually we end in loosing data. Instead, Avro acts like a container and it collects data. 

As soon as HBase is up and running, Avro immediately inserts data to HBase table. 

This way we don't loose real-time data which is inserting to HBase tables. 



