# camelproblem2

* sample csv file has 4129 rows (under src/resources)
* split rows by say 20 threads and then batch up 1000 at a time using an aggregator
* see log.txt where split batch subtotal doesn't reconcile with the file line count
* seems to be random/intermittent