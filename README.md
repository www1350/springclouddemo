# springclouddemo
如果eurekaServer要使用集群配置

A
server.port=1111
eureka.client.serviceUrl.defaultZone=http://localhost:1112/eureka/  

B
server.port=1112
eureka.client.serviceUrl.defaultZone=http://localhost:1111/eureka/  

服务注册和发现
eureka.client.serviceUrl.defaultZone=http://peer1:1111/eureka/,http://peer2:1112/eureka/  

