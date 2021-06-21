
INSERT INTO service_book.book( id , title , author_id )
VALUES
( 1 , 'Vagabond' , 1 ) ,
( 2 , 'Watchmen' , 2 )
ON CONFLICT (id) DO NOTHING ;