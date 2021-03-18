//delete
item = Jenkins.instance.getItemByFullName("job-name")
item.builds.each() { build ->
  build.delete()
}
item.updateNextBuildNumber(1)
//
Jenkins.instance.allItems(hudson.model.AbstractProject.class).each {it ->
    scm = it.getScm()
    if(scm instanceof hudson.plugins.git.GitSCM) {
        if(scm.getUserRemoteConfigs()[0].getUrl()) {
            println it.fullName + ' - '+ scm.getUserRemoteConfigs()[0].getUrl()
        }
    }
}
return
//get
Jenkins.instance.allItems.findAll {
  it instanceof hudson.model.AbstractProject
}.each
//makeDisabled
Jenkins.instance.getAllItems(hudson.model.AbstractProject.class).each {it ->
  scm = it.getScm()
  if(scm instanceof hudson.plugins.git.GitSCM)
  {
     if(scm.getUserRemoteConfigs()[0].getUrl().contains('XXXXXXXX'))
     {
        println scm.getUserRemoteConfigs()[0].getUrl()
        println it.name
        it.makeDisabled(true)
      }
  }
}
println "Done"
