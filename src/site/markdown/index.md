# Persistence

Persistence systems allow handling a source of data, usually a database, in a transparent way, which means not noticing it actually exists.

This is done by offering a model-oriented API, which stands between the application and wherever the data is being kept. This way the programmer just sends or asks for objects to a data access object, ignoring all the work done to map said objects to the information they store.

---

## Why this library?

This project does not aim to solve any big issue, it was created just to ease building persistence classes on my own Java projects. For this it includes the classes I've made or adapted for other projects, following the patterns which I am more accustomed to.

For this reason the repositories used may differ a bit from what other people expect, and may be lacking in some aspects. They are extended and modified as needs arise.

## Features

Most of the classes on this library are repositories:

- Basic [Repository pattern][repository] classes.
- [Repository for the basic JPA API][repository_impl].
- [Repository for Spring's JDBC API][repository_impl].

But some are helper classes:

- [Query][query] interface for filtering.
- [Pagination][pagination] support.
- Root [interface for persistent entities][persistence_entity].

And there are a few examples for setting up the repositories and various persistence configs:

- [Example configurations][example_configs].

## Integration tests

Several of the integration tests included in the project require running databases. While these are taken care by the continuous integration environment, when running the project in local they should be prepared manually, and so it is not recommended trying to run the integration tests, as this may end with several failure warnings.

[example_configs]: ./configs.html
[persistence_entity]: ./persistence_entity.html
[repository]: ./repository.html
[repository_impl]: ./repository_impl.html
[query]: ./query.html
[pagination]: ./pagination.html
