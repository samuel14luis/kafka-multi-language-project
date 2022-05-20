pipeline {
  agent any
  stages {
    stage('Log Version') {
      parallel {
        stage('Log Version') {
          steps {
            sh '''mvn --version
git --version
java -version
mkdir jelowmaifren'''
          }
        }

        stage('Check for Pom') {
          steps {
            fileExists 'pom.xml'
          }
        }

      }
    }

    stage('Build with maven') {
      steps {
        sh 'mvn compile test package'
      }
    }

    stage('Post Build') {
      steps {
        writeFile(file: 'status', text: 'It Worked')
      }
    }

  }
}