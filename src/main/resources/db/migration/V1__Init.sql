CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL UNIQUE
);

INSERT INTO users (name) VALUES ('name1');
INSERT INTO users (name) VALUES ('name2');
INSERT INTO users (name) VALUES ('ivan');