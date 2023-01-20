-- ---------------------------------------------------------------------------------------------------------------------


CREATE TABLE trn_campaigntype (
    n_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'Id Εγγραφής',
    v_description VARCHAR(50) COMMENT 'Περιγραφή',
    v_kind VARCHAR(15) COMMENT 'Είδος'
) COMMENT 'Τύπος Καμπάνιας';

CREATE TABLE trn_campaign (
    n_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'Id Εγγραφής',
    v_name VARCHAR(50) COMMENT 'Ονομασία',
    n_campaigntype_id BIGINT(20) COMMENT 'Id Τύπου Καμπάνιας',
    n_cost DECIMAL(17,2) COMMENT 'Κόστος',
    n_isrunning TINYINT(1) COMMENT 'Ένδειξη σε Ισχύ',
    dt_startdate DATETIME COMMENT 'Ημερομηνία Εναρξης',
    dt_enddate DATETIME COMMENT 'Ημερομηνία Λήξης',
    v_comments VARCHAR(1000) COMMENT 'Παρατηρήσεις'
) COMMENT 'Καμπάνια';
