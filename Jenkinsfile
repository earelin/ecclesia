#!groovy

pipeline {
    agent { label 'java' }
    checkout scm
    options { 
        timestamps()
    }
    stages {
        stage('Merge master') {
            steps {                
                sh 'git merge origin/master'
            }
        }
        stage('Build') {
            steps {
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
