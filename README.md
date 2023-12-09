Steps to deploy server on Tomcat server

**For the first time:**
1) Build project
   - In terminal, cd to assignment-cnw
   - type:
     + mvn clean (to clean built project)
     + mvn install (to install package, library to local repo)
     + (optional) mvn package (compile and build war or jar file)
2) Configure server
   - Install Tomcat 10
   - Add Tomcat Server to IDE, deployment: artifact -> demo:war exploded


**After that, just run/restart server to deploy project on Tomcat Server**
