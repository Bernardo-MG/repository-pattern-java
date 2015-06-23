# Java Persistence Utils

A micro library for easing the use of persistence on Java, mostly JPA.

This has been created for my own use. Mostly this serves to create JPA repositories, using the [Java Patterns][java_patterns] library.

[![Maven Central](https://img.shields.io/maven-central/v/com.wandrell/persistence-utils.svg)][maven-repo]
[![Bintray](https://api.bintray.com/packages/bernardo-mg/maven/java-patterns/images/download.svg)][bintray-repo]

[![Release docs](https://img.shields.io/badge/docs-release-blue.svg)][site-release]
[![Development docs](https://img.shields.io/badge/docs-develop-blue.svg)][site-develop]

[![Release javadocs](https://img.shields.io/badge/javadocs-release-blue.svg)][javadoc-release]
[![Development javadocs](https://img.shields.io/badge/javadocs-develop-blue.svg)][javadoc-develop]

## Features

Offers the following helpful classes:
- Repository for the basic JPA API
- Repository for Spring's JDBC API

## Documentation
Documentation is always generated for the latest release:

- The [latest release documentation page][site-release].
- The [the latest release Javadoc site][javadoc-release].

Documentation is also generated from the latest snapshot, taken from the 'develop' branch:

- The [the latest snapshot documentation page][site-develop].
- The [the latest snapshot Javadoc site][javadoc-develop].

The site sources come along the source code, so it is always possible to generate them using the Maven site command:

```
$ mvn site
```

## Building the code
The application is coded in Java, using Maven to handle the project's configuration and tests.

### Prerequisites
The project has been tested on the following Java versions:
* JDK 7
* JDK 8
* OpenJDK 7

All other dependencies are handled through Maven, and noted in the included POM file.

### Installing

The recommended way to install the project is by setting up the dependencies manager. To get the configuration information for this check the [Bintray repository][bintray-repo], or the [Maven Central Repository][maven-repo].

If for some reason manual installation is necesary, just use the following Maven command:

```mvn install```

## Collaborate

The project is still under ongoing development, and any help will be well received.

There are two ways to help: reporting errors and asking for extensions through the issues management, or forking the repository and extending the project.

### Issues management
Issues are managed at the GitHub [project issues page][issues].

Everybody is allowed to report bugs or ask for features.

### Getting the code
The latest version of the code can be found at the [GitHub project page][scm].

Feel free to fork it, and share the changes.

## License
The project is released under the [MIT License][license].

[bintray-repo]: https://bintray.com/bernardo-mg/maven/persistence-utils/view
[maven-repo]: http://mvnrepository.com/artifact/com.wandrell/persistence-utils
[java_patterns]: https://github.com/Bernardo-MG/java-patterns
[issues]: https://github.com/bernardo-mg/persistence-utils-java/issues
[javadoc-develop]: http://docs.wandrell.com/development/maven/persistence-utils/apidocs
[javadoc-release]: http://docs.wandrell.com/maven/persistence-utils/apidocs
[license]: http://www.opensource.org/licenses/mit-license.php
[scm]: https://github.com/bernardo-mg/persistence-utils-java
[site-develop]: http://docs.wandrell.com/development/maven/persistence-utils
[site-release]: http://docs.wandrell.com/maven/persistence-utils
