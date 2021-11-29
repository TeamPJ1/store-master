pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }
    stages {
        stage("Code Checkout from Github") {
          steps {
            git branch: 'kube', url: 'https://github.com/TeamPJ1/store-master.git' //,credentialsId: 'gitlab_access_token',
          }
         }

        stage('Build') {
            steps {
                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }

        stage('SonarQube analysis') {
            steps {
               // withSonarQubeEnv('SonarQube') {
               //     sh "./maven sonarqube"
              //  }
                withSonarQubeEnv(installationName: 'sonarqube-container', credentialsId: 'sonarqube_access_token') {
                     sh 'mvn -Dmaven.test.skip=true   sonarqube-scanner:sonar'
                }
            }
        }
    }
}