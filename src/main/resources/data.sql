insert IGNORE into mvc_board (bwriter, btitle, bcontent, bhit, bgroup, bstep, bindent) values('abcd', 'is title', 'is content', 0, 1, 0, 0);
insert IGNORE into mvc_board (bwriter, btitle, bcontent, bhit, bgroup, bstep, bindent) values('abcd', 'is title', 'is content', 0, 2, 0, 0);
insert IGNORE into mvc_board (bwriter, btitle, bcontent, bhit, bgroup, bstep, bindent) values('abcd', 'is title', 'is content', 0, 3, 0, 0);

/*
drop procedure insert_mvc_board_data;

DELIMITER $$
CREATE PROCEDURE insert_mvc_board_data()
BEGIN
    DECLARE max_id INT DEFAULT 0;
    DECLARE i INT DEFAULT 1;
    WHILE i <= 1000 DO
            SELECT max(bid) into max_id from mvc_board;

            INSERT IGNORE INTO mvc_board (moddate,regdate,bwriter, btitle, bcontent, bhit, bgroup, bstep, bindent) VALUES(now(),now(),'abcd', 'is title', 'is content', 0, max_id+1, 0, 0);
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;

CALL insert_mvc_board_data();*/