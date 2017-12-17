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
                    script {
                        def checkstyle = scanForIssues tool: [$class: 'CheckStyle'], pattern: 'build/reports/checkstyle/*.xml'
                        publishIssues issues:[checkstyle], useStableBuildAsReference: true

                        def pmd = scanForIssues tool: [$class: 'Pmd'], pattern: 'build/reports/pmd/*.xml'
                        publishIssues issues:[pmd], useStableBuildAsReference: true

                        def findbugs = scanForIssues tool: [$class: 'FindBugs'], pattern: 'build/reports/findbugs/*.xml'
                        publishIssues issues:[findbugs], useStableBuildAsReference: true
                    }
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
