#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('Cleanup') {
            steps {
                echo sh(returnStdout: true, script: 'env')
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
                    recordIssues aggregatingResults: true, sourceCodeEncoding: 'UTF-8', tools: [
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

//        stage('Comment pull request') {
//            when { branch "PR-*" }
//            steps {
//                ViolationsToGitHub([commentOnlyChangedContent: true,
//                                    createCommentWithAllSingleFileComments: true,
//                                    credentialsId: 'jenkins-earelin-user',
//                                    minSeverity: 'INFO',
//                                    violationConfigs: [
//                                        [parser: 'CHECKSTYLE', reporter: 'Checkstyle', pattern: '*/build/reports/checkstyle/*.xml'],
//                                        [parser: 'CPD', reporter: 'CPD', pattern: '*/build/reports/cpd/*.xml'],
//                                        [parser: 'FINDBUGS', reporter: 'Spotbugs', pattern: '*/build/reports/spotbugs/*.xml'],
//                                        [parser: 'PMD', reporter: 'PMD', pattern: '*/build/reports/pmd/*.xml']
//                                   ]])
//            }
//        }

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
