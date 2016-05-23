Przed uruchomieniem aplikacji zainstalowaæ lokalnie baze postgres
Nastêpnie utworzyæ bazê:
CREATE USER jobbox WITH PASSWORD 'jobbox';
CREATE DATABASE masi;
GRANT ALL PRIVILEGES ON DATABASE masi TO jobbox;