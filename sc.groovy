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
//
Jenkins.instance.allItems.findAll {
  it instanceof hudson.model.AbstractProject
}.each
