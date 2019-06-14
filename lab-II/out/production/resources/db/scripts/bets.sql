create table bets
(
    id INTEGER UNSIGNED PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    client_id INTEGER UNSIGNED NOT NULL,
    race_id INTEGER UNSIGNED NOT NULL,
    horse_id INTEGER UNSIGNED NOT NULL,
    bet INTEGER,
    bet_type ENUM('Win', 'Each-way', 'Forecast', 'Tricast', 'Head-to-head'),
    scnd_horse_id INTEGER UNSIGNED,
    thrd_horse_id INTEGER UNSIGNED,
    FOREIGN KEY (client_id) references clients(id),
    FOREIGN KEY (race_id) references races(id),
    FOREIGN KEY (horse_id) references horses(id),
    FOREIGN KEY (scnd_horse_id) references horses(id),
    FOREIGN KEY (thrd_horse_id) references horses(id)
)