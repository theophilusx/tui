# tui - Text User Interface for Clojure

A vary simple Clojure library to simplify creation of basic text based user
interfaces. This is more an example rather than a real library. I use it and
find it useful, but I'm sure any reasonably competent clojure programmer could
use the same underlying library and have something which better suits their
coding style and need.

This library uses the [Clojure Lanterna library](https://github.com/sjl/clojure-lanterna)

## Usage

Have a look at the tests to get an idea of how to use this library. Read the
clojure and java Lanterna docs. If you have ever done anything with ncurses,
this should all be pretty straight-forward.

What I've really done is take the Clojure Lanterna library and just added a few
higher level abstractions to make what I do easier. What I do is probably
different to what you do, so the utility of this stuff will vary. However, I
hope this may give you some ideas.

Note that most of the time when I use this library, performance is not an
issue. I tend to use Clojure quite a bit for writing simple tools or utility
programs. In particular, I have been working on a large Identity and Access
Management project where I needed some tools to query Oracle databases, OpenLDAP
and Active Directory. I use Clojure for all these tools. Originally, the tools
were command line driven, which was fine except the JVM startup time and
creation of LDAP and JDBC connections was slow. I used this library to create a
simple user interface which allowed me to maintain a single instance of the tool
running and allow me to query the different data sources, with results formatted
nicely in a text window.

## License

Copyright © 2016 Tim Cross

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
