# Persistence entity

The [PersistenceEntity][persistence_entity] interface is used on the persistent [repositories][repositories] as a helper interface.

[![PersitenceEntity class hierarchy tree][persistence_entity-class_tree]][persistence_entity-class_tree]

It forces the entities used by these classes to have an identifier, which can be both queried and modified.

The identifier is meant to be updated by the repositories after inserting them on the data source. If the entity still has no id assigned, then it's value will be null or negative.

[persistence_entity]: ./apidocs/com/wandrell/pattern/repository/entity/PersistenceEntity.html
[persistence_entity-class_tree]: ./images/persistence_entity_class_tree.png
[repositories]: ./repository.html