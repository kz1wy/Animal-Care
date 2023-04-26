DROP DATABASE IF EXISTS health_tracker;
CREATE DATABASE health_tracker;

USE health_tracker;

-- Create tables
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  role ENUM('ADMIN', 'VETERINARIAN', 'ZOOKEEPER') NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE zoo_animals (
  id INT PRIMARY KEY AUTO_INCREMENT,
  species VARCHAR(255) NOT NULL,
  sex ENUM('Male', 'Female', 'Unknown') NOT NULL,
  date_of_birth DATE NOT NULL,
  age INT NOT NULL,
  weight FLOAT NOT NULL,
  date_of_arrival DATE NOT NULL,
  exhibit_id INT NOT NULL,
  date_of_departure DATE,
  cause_of_departure VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE enclosures (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  location VARCHAR(255) NOT NULL,
  capacity INT NOT NULL,
  status VARCHAR(255) NOT NULL,
  temperature DECIMAL(5,2) NOT NULL,
  humidity DECIMAL(5,2) NOT NULL,
  cleanliness DECIMAL(5,2) NOT NULL
);


CREATE TABLE feeding_schedules (
  id INT PRIMARY KEY AUTO_INCREMENT,
  animal_id INT NOT NULL,
  enclosures_id INT NOT NULL,
  feeding_time TIME NOT NULL,
  feeding_notes VARCHAR(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (animal_id) REFERENCES zoo_animals(id),
  FOREIGN KEY (enclosures_id) REFERENCES enclosures(id)
);

CREATE TABLE medical_records (
  id INT PRIMARY KEY AUTO_INCREMENT,
  animal_id INT NOT NULL,
  veterinarian_name VARCHAR(255) NOT NULL,
  date_of_visit DATE NOT NULL,
  diagnosis VARCHAR(255) NOT NULL,
  prescription VARCHAR(255) NOT NULL,
  notes VARCHAR(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (animal_id) REFERENCES zoo_animals(id)
);

CREATE TABLE breeding_programs (
  id INT PRIMARY KEY AUTO_INCREMENT,
  species VARCHAR(255) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  breeding_notes VARCHAR(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE breeding_pairs (
  id INT PRIMARY KEY AUTO_INCREMENT,
  breeding_program_id INT NOT NULL,
  male_animal_id INT NOT NULL,
  female_animal_id INT NOT NULL,
  breeding_notes VARCHAR(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (breeding_program_id) REFERENCES breeding_programs(id),
  FOREIGN KEY (male_animal_id) REFERENCES zoo_animals(id),
  FOREIGN KEY (female_animal_id) REFERENCES zoo_animals(id)
);


CREATE TABLE health_records (
  id INT PRIMARY KEY AUTO_INCREMENT,
  animal_id INT NOT NULL,
  veterinarian_id INT NOT NULL,
  date_of_visit DATE NOT NULL,
  diagnosis VARCHAR(255) NOT NULL,
  prescription VARCHAR(255) NOT NULL,
  notes VARCHAR(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (animal_id) REFERENCES zoo_animals(id),
  FOREIGN KEY (veterinarian_id) REFERENCES users(id)
);

CREATE TABLE login_logs (
  id int PRIMARY KEY AUTO_INCREMENT,
  user_id int,
  login_time datetime NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);


-- Insert data into users table
INSERT INTO users (username, password, email, role) VALUES
('admin', '$2a$10$f8zGMdWfpa/P9888bqdnruCMbrAbDY.EYmCBmxoxlI/MDw0Yi9HjG', 'admin@adzoo.com', 'ADMIN'),
('hue', '$2a$10$.itIu7T7y637QCLhPwmyYucE1i25KmXMuS4FTXSIjT77bQnZTZw0K', 'zkp@zoo.com', 'ZOOKEEPER'),
('quang', '$2a$10$HLXynca5JvOuVQmmE/BLUuFya37fU2uRdFcjagglhV2692pAVzqtW', 'vet@zoo.com', 'VETERINARIAN');

INSERT INTO login_logs (user_id, login_time) VALUES
(1, '2022-03-01 09:00:00'),
(2, '2022-03-01 10:00:00'),
(3, '2022-03-01 11:00:00');

-- Insert data into zoo_animals table
INSERT INTO zoo_animals (species, sex, date_of_birth, age, weight, date_of_arrival, exhibit_id) VALUES
  ('Tiger', 'Male', '2017-05-02', 6, 310.2, '2021-03-14', 1),
  ('Lion', 'Female', '2020-07-12', 2, 220.8, '2022-01-18', 1),
  ('Clownfish', 'Unknown', '2019-12-03', 4, 0.4, '2022-06-22', 2),
  ('Elephant', 'Male', '2012-03-25', 11, 5000.0, '2019-05-15', 3),
  ('Penguin', 'Female', '2020-02-20', 1, 5.4, '2021-02-01', 4);

-- Insert data into enclosures table
INSERT INTO enclosures (name, location, capacity, status, temperature, humidity, cleanliness) 
VALUES 
('Lion Enclosure', 'Africa Zone', 4, 'Open', 25.0, 40.0, 70.0),
('Giraffe House', 'Safari Zone', 2, 'Open', 23.0, 50.0, 80.0),
('Penguin Exhibit', 'Antarctica Zone', 10, 'Open', 5.0, 90.0, 90.0),
('Reptile Land', 'Rainforest Zone', 6, 'Closed', 28.0, 60.0, 60.0),
('Aquarium', 'Marine Zone', 20, 'Open', 20.0, 70.0, 95.0);


-- Insert data into feeding_schedules table
INSERT INTO feeding_schedules (animal_id, enclosures_id, feeding_time, feeding_notes) VALUES
  (1, 1, '10:00:00', '1 kg of raw meat'),
  (1, 1, '14:00:00', '1 kg of raw meat'),
  (2, 1, '10:30:00', '1 kg of raw meat'),
  (2, 1, '14:30:00', '1 kg of raw meat'),
  (3, 2, '11:00:00', '20 kg of hay'),
  (3, 2, '15:00:00', '20 kg of hay'),
  (4, 3, '12:00:00', '50 kg of fruits and vegetables'),
  (4, 3, '16:00:00', '50 kg of fruits and vegetables'),
  (5, 4, '09:00:00', '2 kg of fish'),
  (5, 4, '13:00:00', '2 kg of fish');

-- Insert data into medical_records table
INSERT INTO medical_records (animal_id, veterinarian_name, date_of_visit, diagnosis, prescription, notes)
VALUES 
(1, 'Dr. Thu', '2023-04-11', 'Fever', 'Acetaminophen', 'Recommended bed rest for 3 days.'),
(2, 'Dr. Hue', '2023-03-19', 'Broken leg', 'Surgery required', 'Scheduled surgery for next week.'),
(3, 'Dr. Tien', '2023-04-24', 'Ear infection', 'Antibiotics', 'Follow up in one week to monitor progress.');

-- Insert data into health_records table
INSERT INTO health_records (animal_id, veterinarian_id, date_of_visit, diagnosis, prescription, notes)
VALUES 
	(1, 2, '2022-03-25', 'Fever and cough', 'Antibiotics and cough syrup', 'Animal seems to be responding well to treatment.'),
	(2, 1, '2022-02-10', 'Broken leg', 'Pain medication and splint', 'Animal is in stable condition and will need continued monitoring.'),
	(3, 3, '2022-01-15', 'Skin rash', 'Antihistamines and topical ointment', 'Animal appears to be allergic to a certain type of plant in the enclosure.'),
	(4, 2, '2022-04-01', 'Influenza', 'Antivirals and rest', 'Animal is currently under observation and will need to be monitored closely.');

alter table feeding_schedules add column enclosure_id integer;
alter table feeding_schedules add constraint FKtf3sy2y3q5xggncckd47wk1qy foreign key (enclosure_id) references enclosures (id)