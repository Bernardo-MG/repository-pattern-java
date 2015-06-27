# Repositories

A few repositories have been implemented for common persistence APIs.

Currently the ones included on the library offer support for the following:
- Basic JPA, with the [JPARepository][basic_jpa_repo]
- Spring JDBC, with the [SpringJDBCRepository][spring_jdbc_repo]

[![Repositories class hierarchy tree][repository-class_tree]][repository-class_tree]

Both of them implement the FilteredRepository interface from the [Java Patterns](https://github.com/Bernardo-MG/java-pattern) library. And make use of the QueryData, from that same library.

With these two interfaces it is possible to create a repository which receives queries and returns specific entities.

[repository-class_tree]: ./images/repository_class_tree.png
[basic_jpa_repo]: ./apidocs/com/wandrell/persistence/repository/JPARepository.html
[spring_jdbc_repo]: ./apidocs/com/wandrell/persistence/repository/SpringJDBCRepository.html