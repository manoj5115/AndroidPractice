package com.manz.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class WordDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.manz.db");

        Entity dic = schema.addEntity("Words");
        dic.addIdProperty();
        dic.addStringProperty("word");
        dic.addIntProperty("variant");
        dic.addStringProperty("meaning");
        dic.addDoubleProperty("ratio");

        new DaoGenerator().generateAll(schema, "../app/src/main/java");

    }
}


