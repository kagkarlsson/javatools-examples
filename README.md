
Building
====================

mvn clean install

Scenarios
====================

Hint: jetty request-threads are on the format `qtp611437735-26`

**Scenario 1**
 - Start server: `./start_webserver.sh`
 - Simulate traffic: `./run_requests.sh get_token1 5`
 - Use jstack to find out why requests are slow

**Scenario 2**
 - Start server: `./start_webserver.sh`
 - Simulate traffic: `./run_requests.sh get_token2 1/3/10`
 - Use jstack to find out why requests are slow

**Scenario 3**
 - Start server: `./start_webserver.sh`
 - Simulate traffic: `./run_requests.sh http://localhost:8787/caching 10`
 - Run for a while, and there should be an OutOfMemoryError in the webserver log
 - Manually generate a heapdump using jmap (`jmap -dump:format=b,file=[filename].hprof [pid]`)
 - Analyze heapdump in Eclipse Memory Analyzer (MAT) to find cause of OOM
 - View live heap of running JVM using `jmap histo:live [pid]`
 - Use `jhat` to find the source of the OOM
 - Also try to locate heapdump generated automatically on OOM


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

Heapdump:
  - jmap -dump:format=b,file=[filename].hprof [pid]

Heap overview:
 - jmap -histo:live [pid] | head -n20

Thread states:
 - RUNNABLE - thread is currently executing/can be executed in the jvm
 - BLOCKED - thread is blocked indefinately while waiting for a lock (typically synchronized)
 - TIMED_WAITING - thread is waiting for a period of time (triggered by e.g Thread.sleep(..))
 - WAITING - thread is waiting indefinitely for another thread to perform a certain action (i.e. Object.wait()->Object.notify, ...)
