pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }
    stages {
        stage("Code Checkout from Github") {
            steps {
                git branch: 'kube', url: 'https://github.com/TeamPJ1/store-master.git' //,credentialsId: 'gitlab_access_token'
                sh 'chmod +x -R  scripts'
            }
        }

        stage('Build') {
            steps {
                echo 'Building'
                sh 'scripts/jenkins/build-app.sh'
                // Run Maven on a Unix agent.
                // sh "mvn -Dmaven.test.failure.ignore=true clean package"
                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }

        stage('Test') {
            steps {
                echo 'Testing'
                sh 'scripts/jenkins/test-app.sh'
            }
        }

        stage('SonarQube analysis') {
            steps {
               // https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-maven/
                withSonarQubeEnv(installationName: 'sonarqube-container', credentialsId: 'sonarqube_access_token') {
                     sh 'mvn -Dmaven.test.skip=true   org.sonarsource.scanner.maven:sonar-maven-plugin:4.6.1.2450:sonar'
                }
            }
        }

       stage('Build Docker image') {
            steps {
                sh "scripts/jenkins/build-image.sh"
            }
       }

       stage('Push Docker image') {
            steps {
                sh "scripts/jenkins/push-image.sh"
            }
       }

       stage('Deploy app') {
            steps {
                sh "scripts/jenkins/deploy-app.sh"
            }
       }
    }
}