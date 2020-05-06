# JWI Java Wordnet Interface

Extension of [Java Wordnet Interface](https://projects.csail.mit.edu/jwi/) to accept new types of pointers.

Rewritten to Java 8 with lambda expressions.

Global LexID check can be disabled.

Set global hints capability.

Set dictionary resource matcher (eg can use one index amongst many).

Set dictionary comparator (eg use different comparator for index file).

Factored out IContentType key functionality into ContentTyoe key enum.

Added Maven support.

On Maven Central as:

    <groupId>io.github.x-englishwordnet</groupId>
    <artifactId>jwi</artifactId>
    <packaging>jar</packaging>
    <version>2.4.0.1-SNAPSHOT</version>
