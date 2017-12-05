docker stop go-admin
docker rm go-admin
docker build --no-cache -t go/go-admin:v1 -t go/go-admin:latest .
docker run -d --net red1 --ip 172.19.0.10 -p 9010:8080 --name go-admin go/go-admin:v1