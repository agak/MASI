Przed uruchomieniem aplikacji zainstalowa� lokalnie baze postgres
Nast�pnie utworzy� baz�:
CREATE USER jobbox WITH PASSWORD 'jobbox';
CREATE DATABASE masi;
GRANT ALL PRIVILEGES ON DATABASE masi TO jobbox;