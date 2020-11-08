drop table if exists nodes;

create table nodes (
    nodename VARCHAR(250) PRIMARY KEY,
    x DOUBLE NOT NULL,
    y DOUBLE NOT NULL,
    occupied BOOLEAN DEFAULT FALSE
);