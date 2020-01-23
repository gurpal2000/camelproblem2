# camelproblem2

* sample csv file has 4129 rows (under src/resources)
* split rows by say 20 threads and then batch up 1000 at a time using an aggregator
* see log.txt where split batch subtotal doesn't reconcile with the file line count
* seems to be random/intermittent - it's not always the case that the first consumption reconciles fine
* if you start reducing the number of threads, things look better but then split is slower
* if you keep threads to 20, then remove the unmarshal line, things look better
* it's almost like some exchanges are in-flight and never "complete" and then sometimes re-appear in the next iteration
* the difference between the counts can be +ve or -ve; surprised to see MORE than 129 in the "last" batch