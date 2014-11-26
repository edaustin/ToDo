Initial Code.

Took approx. 3 hours to write including the blind alleys (see below).

Manifest has been set to execute final version.

1.
Complete the method createNote() within TodoOverviewActivity to launch
TodoDetailsActivity.

No createNote() method, assuming createToDo().

2.
Versions without postfix and with Ex2 V1/V2 are incomplete versions.
Without postfix is as required for Step 1.

Ex2 V1/V2 are bound service/singleton models which were not completed as they used service/singleton methods and as I 
then read step3 I found that there was a requirement for usage of a Constructor (implementation method was not mentioned in
step 2). 

These two methods were then abandoned due to this constraint but working as Step 2 implementations. I then rewrote
the component as Ex3.

3.
This simply uses a local broadcast receiver to send back to the initial activity (and therefore the UI/main thread).


Enhancements TBC.

Probably would rewrite entire from scratch as a more performant implementation using:


Probably would use a Content Provider/Resolver and a Contract to abstract the data away from the implementation.

Component IPC using a bound service (couldn't do this fully as specification mandated a Constructor to populate in Step 3).

Possibly a Cursor Loader backed by a SQLite Db.

ed@ryer.ru


