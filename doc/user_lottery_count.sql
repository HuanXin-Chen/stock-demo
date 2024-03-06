CREATE TABLE lottery_count (
        user_id INT NOT NULL,
        lottery_count INT NOT NULL DEFAULT 0,
        PRIMARY KEY (user_id)
);