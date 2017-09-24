# golo_monitoring

Following frameworks are used :
- Spring boot
- In-memory H2 Database
- actuator (to monitor the health of this microservice)     
  http://localhost:8080/health
  
Installation Instruction :
- java 8 should be installed
- checkout the code
- mvn clean install
- mvn spring-boot:run ( Or it can be executed directly from IDE)



Rest End points :
-  The following end point will be used to save and monitor multiple services : 
    http://localhost:8080/monitor/create
  
   Example Payload format :
    
     {          
      "hostname" : "https://api.test.paysafe.com/accountmanagement/monitor",      
      "status" : "START",       
      "timeInterval" : "60000"       
      }
 - The following endpoints will be used to get status of all the host (can be filtered using response code or/and host name) :      
   http://localhost:8080/monitor/hoststatus           
   http://localhost:8080/monitor/hoststatus?responseCode=200   
   http://localhost:8080/monitor/hoststatus?hostName=https://api.test.paysafe.com/accountmanagement/monitorr
   http://localhost:8080/monitor/hoststatus?responseCode=401&hostName=https://api.test.paysafe.com/accountmanagement/monitorr

- This end point will get the list of all host monitored by service:               
  http://localhost:8080/monitor/hostlist
  
