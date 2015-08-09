# Persistence

Persistence systems allow handling a source of data, usually a database, in a transparent way, not noticing it actually exists.

This is done by offering a model-oriented API, which stands between the application and wherever the data is being kept. This way the programmer just sends or asks for objects to a data access object, ignoring all the work done to map said objects to the information they store.

---

## Why this library?

This project does not aim to solve any big issue, it was created just to ease building persistence classes on my own Java projects. For this it includes the classes I've made or adapted for other projects, following the patterns which I am more accustomed to.

Those patterns are stored on the [Java Patterns](https://github.com/Bernardo-MG/java-patterns) library. But there is not much need to talk in plural, as it is mostly the Repository pattern the one being used. You could say this library serves just to give actually usable Repositories.

## Features

Most of the classes on this library are repositories:

- [Repository for the basic JPA API][repositories].
- [Repository for Spring's JDBC API][repositories].

But some are helper classes:

- Root [interface for persistent entities][persistence_entity].

[persistence_entity]: ./persistence_entity.html
[repositories]: ./repositories.html