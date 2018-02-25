# JavaSamples
These are some packages with sample code for visualizing single concepts.
Each packages contains a `xxxSampleApp.java` file with a suitable main method.

## Console with Piped Streams
The package `org.ully.samples.console` conatains a swing window with two texta areas.
One acts as a keyboard and one acts as a display. Both are connected to a "machine" object
which processes data from an InputStream and pipes it to an OutputStream.

It shows the use of piped streams as "plumbing device" to connect input and output streams.

## Console with reactive Streams API
The package `org.ully.sample.reactive.console` is the reactive equivalent of the piped console.
It containes a swing window with three text areas. 
One acts as a keyboard and the other two act as displays.
One display is connected to the keyboard via a "machine" object which processes data in one way or the other.
The other display is connected directly to the keyboard and shows the raw input.

It shows the use of reactive streams as "plumbing device" to connect input and output streams
in an asynchronous way.

## UTF-8 and ISO-8859-1 encoded files ##
The class `org.ully.samples.encoding.Utf8` tries to read ITF-8 and ISO 8859-1 encoded files
with the correct and the wrong encoding and shows the effect.
The class `org.ully.samples.encoding.TolerantFileReader` is a simple -- ad hoc coded -- sample
of a file reader, that tries UTF-8 first and switches to ISO 8869-1 on an error.

## Threads using suspend/resume
The package `org.ully.samples.threads.legacy` uses the deprecated resume/suspend
mechanism to control one thread through another.

## Threads using await/signal 
The package `org.ully.samples.threads.condition` uses the await/signal mechanism to
achieve the same result.

## Examples for streams and lambdas in Java 8
The directory `test/java/java8` contains sample code for the use of lambdas and streams in Java 8.

## Examples for try-with-resources in Java 7
The directory `test/java/java9` shows how exceptions are handles in a try-with.resources construct. 

## Multiple inheritance with generics
The package `org.ully.samples.generics.inheritance` shows how to combine multiple interfaces
when acccessing an object through generics.
