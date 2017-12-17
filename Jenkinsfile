#!groovy

pipeline {
    agent { label 'java' }
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
                    checkstyle pattern: 'build/reports/checkstyle/*.xml'
                    pmd pattern: 'build/reports/pmd/*.xml'
                    findbugs isRankActivated: true, pattern: 'build/reports/findbugs/*.xml'
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
                success {
                    archiveArtifacts 'build/libs/*.war'
                }
            }
        }        
    }
}
