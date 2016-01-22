1) Serial Collector with the following parameters
-Xms6m 
-Xmx18m 
-Xmn2m 
-XX:PermSize=20m 
-XX:MaxPermSize=30m
-XX:+UseSerialGC

2) Parallel Collector with the following parameters
-Xms3m 
-Xmx12m 
-Xmn1m 
-XX:PermSize=20m 
-XX:MaxPermSize=20m
-XX:+UseParallelGC

5) G1 Garbage Collector with the following parameters
-Xms4m 
-Xmx16m 
-Xmn2m 
-XX:PermSize=12m 
-XX:MaxPermSize=18m
-XX:+UseG1GC