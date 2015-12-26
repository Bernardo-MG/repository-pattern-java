## The Query filter

![Query class tree][query_data-class_tree]

Accompanying the repositories there is the [QueryData][query_data] interface.

It is meant to be used on repositories which depend on a database, usually an SQL database. Using this interface as a filter it is possible to give the repository all the data to build a query, and then get the result from it.

To ease it's use a basic implementation, [DefaultQueryData][default_query_data], is included.

[query_data]: ./apidocs/com/wandrell/pattern/repository/query/QueryData.html
[query_data-class_tree]: ./images/query_class_tree.png
[default_query_data]: ./apidocs/com/wandrell/pattern/repository/query/DefaultQueryData.html