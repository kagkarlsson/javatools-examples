
Building
====================

mvn clean install

Scenarios
====================

Hint: jetty request-threads are on the format `qtp611437735-26`

**Scenario 1**
 - Start server: `./start_webserver.sh`
 - Simulate traffic: `./run_requests.sh http://localhost:8787/slow 5`
 - Use jstack to find out why requests are slow
 
**Scenario 2**
 - Start server: `./start_webserver.sh`
 - Simulate traffic: ./run_requests.sh http://localhost:8787/slowing 10`
 - Use jstack to find out why requests are slow

**Scenario 3**
 - Start server: `./start_webserver.sh`
 - Simulate traffic: `./run_requests.sh http://localhost:8787/caching 10`
 - Run for a while, and there should be an OutOfMemoryError in the webserver log
 - Find and analyze the heap dump using jmap/jhat
  
  
General tips
===================

Find process:
 - jps
 - ps aux

Threaddump:
 - jstack [pid]
 - kill -QUIT [pid]

Thread summary:
 - jstack [pid] | grep "^\"" | less

One-liners (Funkar lite sämre med exec:java): 
 - jps | grep WebappMain | cut -d' ' -f1 | xargs jstack
 - ps aux | grep WebappMain | grep -v grep | cut -d' ' -f2 | xargs jstack


Eclipse MAT
===================

  



