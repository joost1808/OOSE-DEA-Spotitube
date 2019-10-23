CREATE TABLE playlists
(
    id     int(11)     NOT NULL AUTO_INCREMENT,
    name   varchar(45) NOT NULL,
    length int(11) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tracks
(
    id               int(11)     NOT NULL AUTO_INCREMENT,
    title            varchar(45) NOT NULL,
    performer        varchar(45) NOT NULL,
    duration         int(11)     NOT NULL,
    album            varchar(45) DEFAULT NULL,
    playcount        int(11)     DEFAULT NULL,
    publicationDate  varchar(45) DEFAULT NULL,
    description      varchar(45) DEFAULT NULL,
    offlineAvailable tinyint(1)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE playlisttracks
(
    playlistid int(11) NOT NULL,
    trackid    int(11) NOT NULL,
    PRIMARY KEY (playlistid, trackid),
    CONSTRAINT FKplaylistId FOREIGN KEY (playlistid) REFERENCES playlists (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FKtrackId FOREIGN KEY (trackid) REFERENCES tracks (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE users
(
    token    varchar(45) NOT NULL,
    username varchar(45) NOT NULL,
    password varchar(45) NOT NULL,
    name     varchar(45) NOT NULL,
    PRIMARY KEY (token),
    UNIQUE (token)
);

CREATE TABLE userplaylists
(
    usertoken  varchar(45) NOT NULL,
    playlistid int(11)     NOT NULL,
    owner      tinyint(1)  NOT NULL,
    PRIMARY KEY (usertoken, playlistid),
    CONSTRAINT FKuserplaylistsPlaylistid FOREIGN KEY (playlistid) REFERENCES playlists (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FKusertoken FOREIGN KEY (usertoken) REFERENCES users (token) ON DELETE CASCADE ON UPDATE CASCADE
);

-- DELIMITER $$
-- CREATE PROCEDURE updatePlaylistLength (IN inputPlaylistid INT(11))
-- BEGIN
-- 	DECLARE sumLength INT;
--
--     SELECT SUM(duration) as length
--     INTO @sumLength
--     FROM tracks
--     WHERE id IN (SELECT trackid FROM playlisttracks WHERE playlistid = inputPlaylistid);
--
--     UPDATE playlists
--     SET length = @sumLength
--     WHERE id = inputPlaylistid;
-- END $$
-- DELIMITER ;
--
-- DROP TRIGGER onInsertCalcLength;
-- DELIMITER $$
-- CREATE TRIGGER onInsertCalcLength AFTER INSERT ON `playlisttracks`
-- FOR EACH ROW
-- BEGIN
-- 	CALL updatePlaylistLength(NEW.playlistid);
-- END $$
-- DELIMITER ;
--
-- DROP TRIGGER onUpdateCalcLength;
-- DELIMITER $$
-- CREATE TRIGGER onUpdateCalcLength AFTER UPDATE ON `playlisttracks`
-- FOR EACH ROW
-- BEGIN
-- 	CALL updatePlaylistLength(NEW.playlistid);
--     CALL updatePlaylistLength(OLD.playlistid);
-- END $$
-- DELIMITER ;
--
-- DELIMITER $$
-- CREATE TRIGGER onDeleteCalcLength AFTER DELETE ON `playlisttracks`
-- FOR EACH ROW
-- BEGIN
-- 	CALL updatePlaylistLength(OLD.playlistid);
-- END $$
-- DELIMITER ;
