package com.jqc.designpattern.builder;

public class Main {
    TerrainBuilder builder = new ComplexTerrainBuilder();
    Terrain t = builder.buildFort().buildMine().buildWall().build();
    Person p = new Person.PersonBuilder()
            .basicInfo(1, "jiaqingchqo", 24)
            .score(20)
            .weight(160)
            .loc("sz", "106")
            .builder();
}

