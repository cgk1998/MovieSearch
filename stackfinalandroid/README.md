# stackFinal Integration instructions

## android-app
This is the android app for favorite movies

### local
To run this project locally on an emulator, press the play-button from
Android studio.
### remote
n/a



### local
To run this project locally you can either run in development mode from
IntelliJ, or packing into a docker container and run on a tomcat server
using the following commands:

mvn clean package -Pproduction

//to build the docker image
docker build -t [your-dockerhub-id]/vaadin-books .

//to run it locally. this will be available at localhost:8888
docker run -it --rm -p 8888:8080 [your-dockerhub-id]/vaadin-books

### remote
Once you have a docker image, you may deploy to AWS elastic beanstalk.
Please choose Tomcat as the platform. Do not attempt to deploy into
LightSail.


## mongoDb
This is the persistence layer for favorite places...
### local
Run in a docker container:
docker run -ti --rm -p 27017:27017 mongo:4.0

### remote
Deploy to a docker container on Lightsail....