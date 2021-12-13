def mysqlHost = 'chennaidevops16.conygre.com'
def projectName = 'trade-api'
def version = "0.0.${currentBuild.number}"
def dockerImageTag = "${projectName}:${version}"

pipeline {
  agent any
 
  stages {
    stage('Test') {
      steps {
        sh 'chmod a+x mvnw'
        sh './mvnw clean test'
      }
    }

    stage('Build') {
      steps {
        sh './mvnw package'
      }
    }

    stage('Build Container') {
      steps {
        sh "docker build -t ${dockerImageTag} ."
      }
    }

    stage('Deploy Container To Openshift') {
      environment {
        OPENSHIFT_CREDS = credentials('openshiftCreds')
      }
      /*// This is an alternative step to the one below, only use ONE of these
      // This step uses an external mysql server 
      steps {
        sh "oc login -u ${OPENSHIFT_CREDS_USR} -p ${OPENSHIFT_CREDS_PSW}"
        sh "oc project ${projectName} || oc new-project ${projectName}"
        sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
        sh "oc new-app -l version=${version} -e DB_HOST=${mysqlHost} -e DB_USER=conygre -e DB_PASS=C0nygre-C0nygre ${dockerImageTag}"
        sh "oc expose svc/${projectName}"
      }*/
      // This is an alternative step to one the above, only use ONE of these
      // This step creates a mysql container in openshift instead of using an external mysql server
      // In this case we have prepared a mysql container that includes the required schema.sql
      steps {
        sh "oc login -u ${OPENSHIFT_CREDS_USR} -p ${OPENSHIFT_CREDS_PSW}"
        sh "oc project ${projectName} || oc new-project ${projectName}"
        sh "oc get service mysql-shippers || oc new-app -e MYSQL_ROOT_PASSWORD=c0nygre callalyf/mysql-shippers:0.0.1"
        sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
        sh "oc new-app -l version=${version} -e DB_HOST=mysql-shippers ${dockerImageTag}"
        sh "oc expose svc/${projectName}"
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
      archiveArtifacts 'target/surefire-reports/**/*'
    }
  }
}
