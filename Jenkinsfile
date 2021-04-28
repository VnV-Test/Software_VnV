pipeline {
     agent any
     stages {
         stage('Build') {
             steps {
                 echo 'Building...'
             }
             post {
                 always {
                     jiraSendBuildInfo site: 'https://cyan0fftest.atlassian.net'
                 }
             }
         }
     }
 }
