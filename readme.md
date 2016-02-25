# Java Repository Pattern

Interfaces and basic implementations of the repository pattern.

This help building a persistence layer, and offers repositories prepared for JPA and JDBC, along several sample Spring-based configurations, showing how to set them up for Hibernate, Eclipselink, Spring JDBC and several databases.

[![Maven Central](https://img.shields.io/maven-central/v/com.wandrell/repository-pattern.svg)][maven-repo]
[![Bintray](https://api.bintray.com/packages/bernardo-mg/maven/repository-pattern/images/download.svg)][bintray-repo]

[![Release docs](https://img.shields.io/badge/docs-release-blue.svg)][site-release]
[![Development docs](https://img.shields.io/badge/docs-develop-blue.svg)][site-develop]

[![Release javadocs](https://img.shields.io/badge/javadocs-release-blue.svg)][javadoc-release]
[![Development javadocs](https://img.shields.io/badge/javadocs-develop-blue.svg)][javadoc-develop]

## Features

Offers the following helpful classes:
- Repository for the basic JPA API
- Repository for Spring's JDBC API
- Spring-based examples, as part of the tests, showing how to set up a persistence layer for several databases

## Documentation

Documentation is always generated for the latest release, kept in the 'master' branch:

- The [latest release documentation page][site-release].
- The [the latest release Javadoc site][javadoc-release].

Documentation is also generated from the latest snapshot, taken from the 'develop' branch:

- The [the latest snapshot documentation page][site-develop].
- The [the latest snapshot Javadoc site][javadoc-develop].

The documentation site sources come along the source code (as it is a Maven site), so it is always possible to generate them using the following Maven command:

```
$ mvn verify site
```

Pay attention to the fact that while the verify phase is required for creating all the reports, some of the integration test will not run if the environment is not correctly set. For this reason it can be removed from the command if needed.

## Usage

The application is coded in Java, using Maven to manage the project.

### Prerequisites

The project has been tested on the following Java versions:
* JDK 7
* JDK 8
* OpenJDK 7

All other dependencies are handled through Maven, and noted in the included POM file.

### Installing

The recommended way to install the project is by setting up your preferred dependencies manager. To get the configuration information for this check the [Bintray repository][bintray-repo], or the [Maven Central Repository][maven-repo].

If for some reason manual installation is necessary, just use the following Maven command:

```
$ mvn install
```

### Testing

Several of the integration tests included in the project require running databases. While these are taken care by the continuous integration environment, when running the project in local they should be prepared manually, and so it is not recommended trying to run the integration tests, as this may end with several failure warnings.

## Collaborate

Any kind of help with the project will be well received, and there are two main ways to give such help:

- Reporting errors and asking for extensions through the issues management
- or forking the repository and extending the project

### Issues management

Issues are managed at the GitHub [project issues tracker][issues], where any Github user may report bugs or ask for new features.

### Getting the code

If you wish to fork or modify the code, visit the [GitHub project page][scm], where the latest versions are always kept. Check the 'master' branch for the latest release, and the 'develop' for the current, and stable, development version.

## License

The project has been released under the [MIT License][license].

[bintray-repo]: https://bintray.com/bernardo-mg/maven/repository-pattern/view
[maven-repo]: http://mvnrepository.com/artifact/com.wandrell/repository-pattern
[issues]: https://github.com/bernardo-mg/repository-pattern-java/issues
[javadoc-develop]: http://docs.wandrell.com/development/maven/repository-pattern/apidocs
[javadoc-release]: http://docs.wandrell.com/maven/repository-pattern/apidocs
[license]: http://www.opensource.org/licenses/mit-license.php
[scm]: https://github.com/bernardo-mg/repository-pattern-java
[site-develop]: http://docs.wandrell.com/development/maven/repository-pattern
[site-release]: http://docs.wandrell.com/maven/repository-pattern
