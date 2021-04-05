node {
    def app

    agent {
        docker {
            image 'maven:3.6.3-openjdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stage('Build image') {
        sh 'mvn clean'
        sh 'mvn install'
    }

    stage('Build image') {
        app = docker.build("getintodevops/hellonode")
    }
}