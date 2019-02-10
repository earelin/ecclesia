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

        stage('Comment pull request') {
            when { changeRequest() }
            environment {
                REPOSITORY_NAME = "${env.GIT_URL.tokenize('/')[3].split('\\.')[0]}"
                REPOSITORY_OWNER = "${env.GIT_URL.tokenize('/')[2]}"
            }
            steps {
                ViolationsToGitHub([gitHubUrl: env.GIT_URL,
                                    repositoryName: env.REPOSITORY_NAME,
                                    repositoryOwner: env.REPOSITORY_OWNER,
                                    pullRequestId: env.CHANGE_ID,
                                    credentialsId: 'jenkins-earelin-user',

                                    createCommentWithAllSingleFileComments: true,
                                    createSingleFileComments: true,
                                    commentOnlyChangedContent: true,
                                    minSeverity: 'INFO',
                                    keepOldComments: false,

                                    commentTemplate: """
                                        **Reporter**: {{violation.reporter}}{{#violation.rule}}
                                        
                                        **Rule**: {{violation.rule}}{{/violation.rule}}
                                        **Severity**: {{violation.severity}}
                                        **File**: {{violation.file}} L{{violation.startLine}}{{#violation.source}}
                                        
                                        **Source**: {{violation.source}}{{/violation.source}}
                                        
                                        {{violation.message}}
                                        """,

                                    violationConfigs: [
                                        [parser: 'CHECKSTYLE', reporter: 'Checkstyle', pattern: '.*/build/reports/checkstyle/.*\\.xml'],
                                        [parser: 'CPD', reporter: 'CPD', pattern: '.*/build/reports/cpd/.*\\.xml'],
                                        [parser: 'FINDBUGS', reporter: 'Spotbugs', pattern: '.*/build/reports/spotbugs/.*\\.xml'],
                                        [parser: 'PMD', reporter: 'PMD', pattern: '.*/build/reports/pmd/.*\\.xml']
                                   ]])
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
