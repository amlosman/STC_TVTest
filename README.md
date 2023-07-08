# STC_TV
   ### An example project that runs:
         1- Run tests in parallel using TestNG.
         2- Run Cucumber tests
         2- Run Tests remotely         
   ### Run tests in parallel using TestNG
   1- From the terminal used this command 
   
           mvn clean test -P localRunner
  
  2- From xml file
  
           Execute: TestNGParallel.xml
   ### Run Cucumber tests
   
        Execute TestRunner.java
   ### Remote configuration
   Start the hub

        java -jar selenium-server-standalone-2.39.0.jar -role hub

  Chrome:

           java -jar selenium-server-standalone-2.39.0.jar -role node -hub http://127.0.0.1:4444/grid/register -browser browserName=firefox,version=27,maxInstances=1,platform=LINUX

   ### Run Tests remotely
        
 1- From the terminal used this command 
   
           mvn clean test -P remoteRunner
  
  2- From xml file
  
           Execute: TestGridParallel.xml
