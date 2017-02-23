#!/usr/bin/groovy
def call(body) {
  // evaluate the body block, and collect configuration into the object
  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

  // For now it is mainly focussed for UI tests
  def serverURL = config.serverURL
  def testSourceRepoURL = config.testSourceRepoURL
  def testSourceRepoBranch = config.testSourceRepoBranch ?: "master"
  def gitUser = config.gitUser ?: "fabric8-release"
  def gitEmail = config.gitEmail ?: "fabric8-admin@googlegroups.com"
  def commandToExecuteTests = config.commandToExecuteTests
  def pathToResultsDir = config.pathToResultsDir

  container(name: containerName) {
    try{
          checkout scm: [$class          : 'GitSCM',
                   useRemoteConfigs: [[url: testSourceRepoURL]],
                   branches        : [[name: "refs/remotes/origin/${testSourceRepoBranch}"]]]
          sh "ls -la"
          // install if something is required
          // run the command
          sh "$commandToExecuteTests"
          // archive the results
          // archive '$pathToResultsDir/*.xml'
          // step([$class: 'JUnitResultArchiver', testResults: '$pathToResultsDir/*.xml'])
    }
    catch (e) {
      error "${e}"
    }
  }
}