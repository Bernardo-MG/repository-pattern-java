DROP TABLE IF EXISTS test_entities;

CREATE TABLE test_entities (
	id		SERIAL PRIMARY KEY,
	name	TEXT DEFAULT '' NOT NULL
);
