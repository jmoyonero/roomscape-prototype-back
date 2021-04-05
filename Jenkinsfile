pipeline {
  environment {
    imagename = "prototype"
    dockerImage = ''
  }
  agent {
      docker {
          image 'maven:3.6.3-openjdk-11'
          args '-v /root/.m2:/root/.m2'
      }
  }
  stages {
    stage('Build APP') {
      steps {
          sh 'mvn clean'
          sh 'mvn install'
      }
    }
    stage('Building Docker Image') {
      steps{
        script {
          dockerImage = docker.build imagename
        }
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $imagename:$BUILD_NUMBER"
        sh "docker rmi $imagename:latest"
      }
    }
  }
}