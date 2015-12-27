# Repository

A repository allows accessing data through CRUD operations in a similar way to a DAO. The difference is that while a DAO is meant to handle a single entity the repository works as if it were a collection of persistent entities.

## Interface

![Repository interface][repository-class_tree]

The [Repository][repository] is meant to hide the data access, so the code works only with domain model classes. It offers basic CRUD operations, and implementations of it may extend the interface and add methods as required.

An extension of this basic interface, [FilteredRepository][filtered_repository] allows executing queries on the entities.

The _getCollection_ and _getEntity_ methods take care of this. The first will return a subset of the entities contained in the repository, while the second will return a single one.

Any object can be used as a filter for the queries, but it is expected to be able to work as such. A predicate which the entities to return must validate, or an SQL query are examples of it, but the actual object, and how it is used, will depend on the implementation.

[repository]: ./apidocs/com/wandrell/pattern/repository/Repository.html
[repository-class_tree]: ./images/repository_class_tree.png
[filtered_repository]: ./apidocs/com/wandrell/pattern/repository/FilteredRepository.html