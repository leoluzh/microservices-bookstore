CREATE ROLE service_books WITH LOGIN PASSWORD 'srv-books-12345#' ;
CREATE ROLE service_prices WITH LOGIN PASSWORD 'srv-prices-12345#' ;
CREATE ROLE service_users WITH LOGIN PASSWORD 'srv-users-12345#';

CREATE SCHEMA service_books AUTHORIZATION service_books;
CREATE SCHEMA service_prices AUTHORIZATION service_prices;
CREATE SCHEMA service_users AUTHORIZATION service_users;