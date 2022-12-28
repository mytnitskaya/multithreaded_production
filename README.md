The program implements a scheme for the production and consumption of resources without using java.concurrency

Conditionals:
1. There is a producerCount of threads producing the resource. Each producer produces 1 unit of resource at producerTime time
2. Producers place resources in a storage of size storageSize. More than the storageSize of resources cannot be stored in the storage
3. There is a consumerCount of threads consuming resources. Each consumer takes 1 unit of the resource from the storage at consumerTime
4. If the storage is empty, the consumer threads are waiting. As soon as the resources appeared, the threads immediately begin to consume them.
5. If the storage is full, producer threads wait until consumers make room
6. producerCount, consumerCount, producerTime, consumerTime, storageSize - configured in the .properties file
7. The resource has a unique tracking ID

Also, in the program added logger to console and file.