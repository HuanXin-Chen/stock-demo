SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS dragon_activity_gold_damage;

CREATE TABLE dragon_activity_gold_damage (
    id BIGINT AUTO_INCREMENT,
    gold_amount INT,
    damage INT,
    draw_times INT,
    PRIMARY KEY (id)
);

INSERT INTO dragon_activity_gold_damage (gold_amount, damage, draw_times) VALUES (50, 500, 1),(200,2500,5),(1000,20000,30),(2000,50000,100);
