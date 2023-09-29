# stackFinal Integration instructions

## android-app
This is the android app for favorite movies
### local
To run this project locally on an emulator, press the play-button from
Android studio.
### remote
n/a


## quarkusRest
This is the persistence layer for favorite movies
### local
Run in a docker container:
docker run -ti --rm -p 27017:27017 mongo:4.0

### remote: Deploy to a docker container on Lightsail:


1. mvn clean
2. mvn package
3. docker build -f src/main/docker/Dockerfile.jvm -t {your docker account}/{name} .
4. docker push {name}
5. docker run -ti --rm -p 27017:27017 mongo:4.4
6. open lightsail and creat a new container
7. create two custom deployment with images {your docker account}/{name} and mongo:4.4

## awsContact
This is service which sends email
### local

1. sam build
2. sam local start-api


### remote

1. mvn clean package

2. sam build

3. sam deploy --guided


## cognito service

cognito service is in android application

to use your aws cognito user pool, change user_pool_id and client_id in main/res/values/strings.xml


