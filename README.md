
Build:
 - mvn install

Start crappy webapp:
 - MAVEN_OPTS="-Xmx200m" mvn exec:java

Simulate requests:
 - mvn exec:java -Psimulate_requests -Dexec.args="http://localhost:8787/slow 3"
 - mvn exec:java -Psimulate_requests -Dexec.args="http://localhost:8787/slowing 1"   (test 1, 5 and 10 parallell requests)
 - mvn exec:java -Psimulate_requests -Dexec.args="http://localhost:8787/caching 10" 


Find process:
 - jps
 - ps aux

Threaddump:
 - jstack [pid]
 - kill -QUIT [pid]

Thread summary:
 - jstack [pid] | grep "^\"" | less

One-liners (Funkar lite sämre med exec:java): 
 - jps | grep AppMain | cut -d' ' -f1 | xargs jstack
 - ps aux | grep WebappMain | grep -v grep | cut -d' ' -f2 | xargs jstack
