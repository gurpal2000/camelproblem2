# camelproblem2

* sample csv data file has 4129 rows
* split rows by say 20 threads and then batch up 1000 at a time using an aggregator
* see log.txt where split batch subtotal doesn't reconcile with the file line count. seems to be random/intermittent