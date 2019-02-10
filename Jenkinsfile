#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('Cleanup') {
            steps {
                sh 'sh gradlew clean'
            }
        }

        stage('Static code analysis and unit testing') {
            steps {
                sh 'sh gradlew check'
            }
            post {
                always {
                    junit '*/build/test-results/test/*.xml'
                    recordIssues aggregatingResults: true, tools: [
                        java(),
                        checkStyle()
                    ]
                }
                success {
                    jacoco execPattern: '*/build/jacoco/*.exec'
                }
            }
        }

        stage('Build') {
            steps {
                sh 'sh gradlew build'
            }
        }

//        stage('Publish snapshot') {
//            when { branch "2.x.x" }
//            steps {
//
//            }
//        }
//
//        stage('Publish release') {
//            when { buildingTag() }
//            steps {
//
//            }
//        }
    }
}
