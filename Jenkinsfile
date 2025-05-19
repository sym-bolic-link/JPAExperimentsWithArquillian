pipeline {
  agent {
    docker {
      image 'ubuntu'
    }

  }
  stages {
    stage('') {
      steps {
        sh 'node --eval "console.log(process.platform,process.env.CI)"'
      }
    }

  }
}