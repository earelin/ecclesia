#!groovy

pipeline {
    agent { label 'java' }
    ansiColor('xterm') {
        stages {
            stage('Merge master') {
                steps {                
                    sh 'git merge origin/master'
                }
            }
            stage('Static code analysis') {
                steps {                
                    sh 'gradle checkstyleMain pmdMain findbugsMain'
                }
                post {
                    always {
                        checkstyle canComputeNew: false, defaultEncoding: '', healthy: '', pattern: 'build/reports/checkstyle/*.xml', unHealthy: ''
                    }
                }
            }
            stage('Unit testing') {
                steps {
                    sh 'gradle test'
                }
                post {
                    always {
                        junit 'build/test-results/test/*.xml'
                    }
                }
            }
            stage('Build') {
                steps {
                    sh 'gradle war'
                }
                post {
                    always {
                        archive 'build/libs/*.war'
                    }
                }
            }        
        }
    }
}
