## Pagination

Handling big quantities of data requires pagination. For this a small set of classes is contained in the project, offering a basic way to split the results from a repository.

### Paginated repository

![Paginated repository class tree][paginated_repository-class_tree]

The [PaginatedRepository][paginated_repository] adds pagination support to the [Repository][repository] class by making use of the [PaginationData][pagination_data] class.

The [JPARepository][jpa_repository] extends over this interface.

### Pagination data

![Pagination data class tree][pagination_data-class_tree]

The [PaginationData][pagination_data] contains all the information needed for making use of the pagination support.

A base implementation, the [DefaultPaginationData][default_pagination_data], is included.

[repository]: ./apidocs/com/wandrell/pattern/repository/Repository.html
[jpa_repository]: ./apidocs/com/wandrell/pattern/repository/jpa/JPARepository.html

[paginated_repository]: ./apidocs/com/wandrell/pattern/repository/pagination/PaginatedRepository.html
[pagination_data]: ./apidocs/com/wandrell/pattern/repository/pagination/PaginationData.html
[default_pagination_data]: ./apidocs/com/wandrell/pattern/repository/pagination/DefaultPaginationData.html

[paginated_repository-class_tree]: ./images/paginated_repository_class_tree.png
[pagination_data-class_tree]: ./images/pagination_data_class_tree.png