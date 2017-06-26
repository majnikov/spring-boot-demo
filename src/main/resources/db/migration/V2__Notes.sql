CREATE TABLE notes (
  id SERIAL PRIMARY KEY,
  user_id SERIAL,
  text TEXT NOT NULL
);

INSERT INTO notes (user_id, text) VALUES ((SELECT id FROM users WHERE name='name1'), 'first note text');