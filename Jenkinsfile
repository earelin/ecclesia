#!groovy

pipeline {
    agent { label 'java' }
    stages {
        stage('Build') {
            steps {
                sh 'git merge origin/master'
                sh 'gradle check'
                sh 'gradle war'
            }
            post {
                always {
                    junit 'build/reports/**/*.xml'                    
                }
            }
        }
        stage('Code QA') {
            steps {
                sh 'echo "Code quality"'
            }            
        }
    }
}
