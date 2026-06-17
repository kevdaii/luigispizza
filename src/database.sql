psql "postgresql://postgres:postgres@localhost:5432/"
CREATE USER admin WITH PASSWORD 'admin';
CREATE DATABASE japaneseimportdb OWNER admin;
quit;