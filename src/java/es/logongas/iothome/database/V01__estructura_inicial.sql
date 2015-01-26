
    create table Device (
        idDevice integer not null auto_increment,
        name varchar(255),
        streamName0 varchar(255),
        streamName1 varchar(255),
        streamName2 varchar(255),
        streamName3 varchar(255),
        streamName4 varchar(255),
        streamName5 varchar(255),
        streamName6 varchar(255),
        streamName7 varchar(255),
        streamName8 varchar(255),
        streamName9 varchar(255),
        primary key (idDevice)
    );

    create table Measure (
        idMeasure integer not null auto_increment,
        time datetime,
        idDevice integer,
        stream0 double precision,
        stream1 double precision,
        stream2 double precision,
        stream3 double precision,
        stream4 double precision,
        stream5 double precision,
        stream6 double precision,
        stream7 double precision,
        stream8 double precision,
        stream9 double precision,
        primary key (idMeasure)
    );

    alter table Measure 
        add index FK9B263D3E29260516 (idDevice), 
        add constraint FK9B263D3E29260516 
        foreign key (idDevice) 
        references Device (idDevice);

