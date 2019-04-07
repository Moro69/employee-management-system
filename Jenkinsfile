pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean install'
            }
        }

        stage('Unit tests') {
            steps {
                sh 'mvn surefire:test'
            }
        }

        stage('Integration tests') {
            steps {
                sh ' mvn failsafe:integration-test'
            }
        }
    }
}