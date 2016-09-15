# MyDockerApp

### create an overlay network
### create a swarm cluster (3 nodes : 1 master and 2 workers)
###push the docker image in docker hub : mi docker:build -DpushImage
### deploy the back-end service
docker service create --endpoint-mode dnsrr --name backend --network mynet scizeron/mydockerapp:0.0.2-SNAPSHOT
### deploy the front-end service
docker service create --endpoint-mode vip --name frontend --network mynet --publish 80:8080  scizeron/mydockerapp:0.0.2-SNAPSHOT --myService=backend:8080
### invoke the front-end service which will query the back-end service
curl localhost/call
