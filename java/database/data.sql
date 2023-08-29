BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO landmark (place_id, type, city) VALUES ('ChIJPTacEpBQwokRKwIlDXelxkA', 'tourist attraction', 'New York City');
INSERT INTO landmark (place_id, type, city) VALUES ('ChIJ9U1mz_5YwokRosza1aAk0jM', 'tourist attraction', 'New York City');
INSERT INTO landmark (place_id, type, city) VALUES ('ChIJLU7jZClu5kcR4PcOOO6p3I0', 'tourist attraction', 'Paris');

INSERT INTO itinerary (user_id,date,start_location,end_location) VALUES (1,CURRENT_DATE,'New York City','New York City');

INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES ((SELECT id FROM itinerary WHERE user_id = (SELECT user_id FROM users WHERE username = 'user') AND date = CURRENT_DATE),(SELECT id FROM landmark WHERE id = 1));
INSERT INTO itinerary_landmark (itinerary_id,landmark_id) VALUES ((SELECT id FROM itinerary WHERE user_id = (SELECT user_id FROM users WHERE username = 'user') AND date = CURRENT_DATE),(SELECT id FROM landmark WHERE id = 2));

COMMIT TRANSACTION;