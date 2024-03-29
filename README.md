# Wallet
Client-server wallet implementation

## How to run
Copy repository into your local machine.

### Server
Execute in root
For linux 
```
./gradlew :server:bootRun 
```

For windows
```
gradlew :server:bootRun
```

### Client
The wallet client emulates a number of users concurrently using the wallet. 
To run client with certain parameters it need to execute

For Linux
```
./gradlew :client:run --args='<users> <threads_per_user> <rounds_per_thread>'
```
For Windows
```
gradlew :client:run --args='<users> <threads_per_user> <rounds_per_thread>'
```
Where

**Users** is number of concurrent users emulated,

**Thread_per_user** is number of concurrent requests a user will make,

**Rounds_per_thread** is number of rounds each thread is executing

### Database
Database is designed as docker container. 
It need to have Docker installed on developer station.
To run database need to go to **./database** folder and run 
```$xslt
bash db
``` 
As result will be built docker image, based on it run mysql container.