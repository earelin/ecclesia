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
                sh 'sh gradlew check | tee build.log'
            }
            post {
                always {
                    junit '*/build/test-results/test/*.xml'
                    recordIssues aggregatingResults: true, tools: [
                        checkStyle(pattern: '*/build/reports/checkstyle/*.xml'),
                        cpd(pattern: '*/build/reports/cpd/*.xml'),
                        java(pattern: 'build.log'),
                        pmdParser(pattern: '*/build/reports/pmd/*.xml'),
                        spotBugs(pattern: '*/build/reports/spotbugs/*.xml', useRankAsPriority: true)
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
