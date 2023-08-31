BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO landmark (place_id, type, city) VALUES ('ChIJPTacEpBQwokRKwIlDXelxkA', 'tourist attraction', 'New York'); --statue of liberty 1
INSERT INTO landmark (place_id, type, city) VALUES ('ChIJ9U1mz_5YwokRosza1aAk0jM', 'tourist attraction', 'New York'); --rockefeller center 2
INSERT INTO landmark (place_id, type, city) VALUES ('ChIJaXQRs6lZwokRY6EFpJnhNNE', 'museum', 'New York'); --empire state building 3
INSERT INTO landmark (place_id, type, city) VALUES ('ChIJ_5cHC_Jw44kRmALscjIXr6w', 'tourist attraction', 'Boston'); --bunker hill monument 4 
INSERT INTO landmark (place_id, type, city) VALUES ('ChIJLU7jZClu5kcR4PcOOO6p3I0', 'tourist attraction', 'Paris'); --eiffel tower 5
INSERT INTO landmark (place_id, type, city) VALUES ('ChIJjx37cOxv5kcRPWQuEW5ntdk', 'tourist attraction', 'Paris'); --arc de triomphe 6 
INSERT INTO landmark (place_id, type, city) VALUES ('ChIJAVjEb9tx5kcRxWEv0cQd29s', 'tourist attraction', 'Paris'); --luxembourge palace 7 
INSERT INTO landmark (place_id, type, city) VALUES ('ChIJB9OTMDIbdkgRp0JWbQGZsS8', 'museum', 'London'); --british museum 8


INSERT INTO itinerary (user_id,date,start_location,end_location) VALUES (1,CURRENT_DATE,'New York City','New York City'); --user in nyc 1
INSERT INTO itinerary (user_id,date,start_location,end_location) VALUES (1,CURRENT_DATE+1,'New York City','Boston'); --user in nyc->boston 2
INSERT INTO itinerary (user_id,date,start_location,end_location) VALUES (2,CURRENT_DATE,'Paris','Paris'); --admin in paris 3
INSERT INTO itinerary (user_id,date,start_location,end_location) VALUES (2,CURRENT_DATE+1,'Paris','London'); --admin in paris->london 4

INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES (1,1);
INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES (1,2);
INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES (1,3);
INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES (1,2);
INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES (2,4);
INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES (3,5);
INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES (3,7);
INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES (4,6);
INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES (4,8);


COMMIT TRANSACTION;