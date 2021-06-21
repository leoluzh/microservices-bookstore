
INSERT INTO service_prices.price ( id , book_id , price )
VALUES
( 1 , 1 , 25.00 ) ,
( 2 , 2 , 10.00 )
ON CONFLICT ( id ) DO NOTHING;
