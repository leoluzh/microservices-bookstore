
INSERT INTO service_books.author( id , name , surname )
VALUES
( 1 , 'Takehiko' , 'Inoue' ) ,
( 2 , 'Alan' , 'Moore' )
ON CONFLICT (id) DO NOTHING;