# JavaSamples
These are some packages with sample code for visualizing single concepts.
Each packages contains a `xxxSampleApp.java` file with a suitable main method.

## Piped Streams
The package `org.ully.samples.console` conatains a swing window with two texta areas.
One acts as a keyboard and one acts as a display. Both are connected to a "machine" object
which processes data from an InputStream and pipes it to an OutputStream.

It shows the use of piped streams as "plumbing device" to connect input and output steams.

## Threads using suspend/resume
The package `org.ully.samples.threads.legacy` uses the deprecated resume/suspend
mechanism to control one thread through another.

## Threads using await/signal 
The package `org.ully.samples.threads.condition` uses the await/signal mechanism to
achieve the same result.
