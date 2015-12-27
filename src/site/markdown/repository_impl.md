# Repositories

A few repositories have been implemented for common persistence APIs.

Currently the ones included on the library offer support for the following:
- Basic JPA, with the [JPARepository][basic_jpa_repo]
- Spring JDBC, with the [SpringJDBCRepository][spring_jdbc_repo]

[![Repositories class hierarchy tree][repository-class_tree]][repository-class_tree]

## Collection Repository

![Collection repository class tree][collection_repository-class_tree]

A basic implementation of the repository, [CollectionRepository][collection_repository], is offered. This serves as a stub to avoid using a persistence system, or for those cases where a complex repository is not needed, as it will just use a _Collection_ to store all the entities.

This repository queries the entities through the use of a Guava [Predicate][predicate], used instead of Java 8 own _Predicate_ to keep backwards compatibility. All the entities which make this predicate true will be returned.

[repository-class_tree]: ./images/repository_impl_class_tree.png
[basic_jpa_repo]: ./apidocs/com/wandrell/pattern/repository/jpa/JPARepository.html
[spring_jdbc_repo]: ./apidocs/com/wandrell/pattern/repository/spring/SpringJDBCRepository.html
[collection_repository]: ./apidocs/com/wandrell/pattern/repository/CollectionRepository.html
[collection_repository-class_tree]: ./images/collection_repository_class_tree.png
[predicate]: http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/base/Predicate.html