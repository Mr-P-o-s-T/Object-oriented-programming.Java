create table race_cast
(
    jockey_firstname VARCHAR(50) NOT NULL,
    jockey_lastname VARCHAR(50) NOT NULL,
    horse_id INTEGER UNSIGNED NOT NULL,
    race_id INTEGER UNSIGNED NOT NULL,
    coefficient FLOAT UNSIGNED NOT NULL CHECK ( coefficient > 1.0 ),
    FOREIGN KEY (horse_id) references horses(id),
    FOREIGN KEY (race_id) references races(id)
)