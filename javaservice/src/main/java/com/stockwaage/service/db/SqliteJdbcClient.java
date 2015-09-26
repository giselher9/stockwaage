package com.stockwaage.service.db;

import com.google.common.collect.Lists;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.stockwaage.service.weights.Weight;

import java.net.UnknownHostException;
import java.util.List;

public class SqliteJdbcClient {

  public void insert(Weight weight) throws UnknownHostException {
    DB db = connection();

    DBCollection collection = db.getCollection("weights");

    BasicDBObject document = new BasicDBObject();
    document.put("weightUnit", weight.weightUnit().toString());
    document.put("value", ""+weight.value());
    document.put("timestamp", ""+weight.timestamp());

    collection.insert(document);

    int test = 0;
  }

  public List<Weight> findWeights() throws UnknownHostException {
    DB db = connection();
    DBCollection collection = db.getCollection("weights");
    DBCursor cursor = collection.find();
    List<Weight> weights = Lists.newArrayList();
    try {
      while (cursor.hasNext()) {
        System.out.println(cursor.next());
      }
    } finally {
      cursor.close();
    }
    return weights;
  }

  private DB connection() throws UnknownHostException {
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    DB db = mongoClient.getDB("local");
    return db;
  }

}
