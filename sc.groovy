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
//
import hudson.model.*
def pipe_view = "Pipeline_folder"
def view = Hudson.instance.getView(pipe_view)
def successfulJobs = view.getItems().findAll{job -> job.lastBuild != null && job.lastBuild.result == hudson.model.Result.SUCCESS}
def faildJobs = view.getItems().findAll{job -> job.lastBuild != null && job.lastBuild.result == hudson.model.Result.FAILURE}
def disabledJob = view.getItems().findAll{job -> job.disabled == true}
def enabledJob = view.getItems().findAll{job -> job.disabled != true}
println "Total jobs: " + view.getItems().size +" Successful: " +successfulJobs.size+
  " Failed: " + faildJobs.size + " Enabled jobs: " +enabledJob.size + " Disabled jobs: " +disabledJob.size 

println "Current Successful job:"
successfulJobs.each{job -> printInfo(job)}
println "Current Fail job:"
faildJobs.each{job -> printInfo(job)}
println "Current disabled job:"
disabledJob.each{job -> printInfo(job)}
println "Current enabled job:"
enabledJob.each{job -> printInfo(job)}

def printInfo(job){
  println "Job: ${job.name} build on ${job.getAssignedLabelString()}, "+
    "took ${job.lastBuild.getDurationString()} to build, is disabled : ${job.disabled}"
}
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
