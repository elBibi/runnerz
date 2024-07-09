CREATE table if NOT EXISTS Run (
    id INT NOT Null,
    title VARCHAR(250) NOT NULL,
    started_on timestamp NOT NULL,
    completed_on timestamp NOT NULL,
    miles INT NOT NULL,
    location varchar (15) NOT NULL,
    PRIMARY KEY (id)
);