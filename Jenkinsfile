pipeline {
     agent any
     stages {
         stage('Build') {
             steps {
                 echo 'Building...'
             }
             post {
                 always {
                     jiraSendBuildInfo site: 'cyan0fftest.atlassian.net'
                 }
             }
         }
     }
 }
