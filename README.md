# tui - Text User Interface for Clojure

A vary simple Clojure library to simplify creation of basic text based user
interfaces. This is more an example rather than a real library. I use it and
find it useful, but I'm sure any reasonably competent clojure programmer could
use the same underlying library and have something which better suits their
coding style and need.

This library uses the [Clojure Lanterna library](https://github.com/sjl/clojure-lanterna)

## Usage

Have a look at the tests to get an idea of how to use this library and the
example-app function in the core.clj file. Read the
clojure and java Lanterna docs. If you have ever done anything with ncurses,
this should all be pretty straight-forward.

What I've really done is take the Clojure Lanterna library and just added a few
higher level abstractions to make what I do easier. What I do is probably
different to what you do, so the utility of this stuff will vary. However, I
hope this may give you some ideas.

Note that most of the time when I use this library, performance is not an
issue. I tend to use Clojure quite a bit for writing simple tools or utility
programs. The interface requirements are quite simple and almost certainly don't
exercise the many bugs hidden here!

## License

Copyright © 2016 Tim Cross

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
