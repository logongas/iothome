
    create table Device (
        idDevice integer not null auto_increment,
        name varchar(255),
        streamName0 varchar(255),
        streamName1 varchar(255),
        streamName2 varchar(255),
        streamName3 varchar(255),
        primary key (idDevice)
    );

    create table Measure (
        idMeasure integer not null auto_increment,
        time datetime,
        idDevice integer,
        stream0 float precision,
        stream1 float precision,
        stream2 float precision,
        stream3 float precision,
        primary key (idMeasure),
        INDEX `Indice3` (`idDevice`, `time`)
    );

    alter table Measure 
        add index FK9B263D3E29260516 (idDevice), 
        add constraint FK9B263D3E29260516 
        foreign key (idDevice) 
        references Device (idDevice);

