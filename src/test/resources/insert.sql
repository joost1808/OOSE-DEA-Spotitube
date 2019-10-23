DELETE
FROM playlists;
DELETE
FROM playlisttracks;
DELETE
FROM tracks;
DELETE
FROM userplaylists;
DELETE
FROM users;

INSERT INTO playlists
(id,
 name,
 length)
VALUES (0,
        'testPlaylist1',
        0);

INSERT INTO playlists
(id,
 name,
 length)
VALUES (1,
        'testPlaylist2',
        0);

INSERT INTO tracks
(id,
 title,
 performer,
 duration,
 album,
 playcount,
 publicationDate,
 description,
 offlineAvailable)
VALUES (0,
        'testTrack1',
        'performer1',
        400,
        'album1',
        19,
        null,
        null,
        true);

INSERT INTO tracks
(id,
 title,
 performer,
 duration,
 album,
 playcount,
 publicationDate,
 description,
 offlineAvailable)
VALUES (1,
        'testTrack2',
        'performer1',
        500,
        'album1',
        5,
        null,
        null,
        false);

INSERT INTO tracks
(id,
 title,
 performer,
 duration,
 album,
 playcount,
 publicationDate,
 description,
 offlineAvailable)
VALUES (2,
        'testTrack3',
        'performer2',
        600,
        'album2',
        17,
        null,
        null,
        false);

INSERT INTO tracks
(id,
 title,
 performer,
 duration,
 album,
 playcount,
 publicationDate,
 description,
 offlineAvailable)
VALUES (3,
        'testTrack4',
        'performer2',
        700,
        'album2',
        5,
        null,
        null,
        true);

INSERT INTO playlisttracks
(playlistid,
 trackid)
VALUES (0, 0);

INSERT INTO playlisttracks
(playlistid,
 trackid)
VALUES (0, 1);

INSERT INTO playlisttracks
(playlistid,
 trackid)
VALUES (1, 2);

INSERT INTO playlisttracks
(playlistid,
 trackid)
VALUES (1, 3);

INSERT INTO users
(token,
 username,
 password,
 name)
VALUES ('1234-1234-1234',
        'test',
        'password',
        'testName');

INSERT INTO userplaylists
(usertoken,
 playlistid,
 owner)
VALUES ('1234-1234-1234',
        0,
        true);
