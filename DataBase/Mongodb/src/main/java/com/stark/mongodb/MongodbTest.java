package com.stark.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

/**
 * Created by Stark on 2017/7/10.
 */
public class MongodbTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("test");
        //database.createCollection("tsh");
        MongoCollection collection = database.getCollection("tsh"); //不存在 在插入数据时 会自动创建
        Document document = new Document();
        document.put("name","tansihao");
        document.put("age",23);
        Document address = new Document();
        address.put("province","四川");
        address.put("location","南丰");
        document.put("address",address);
        collection.insertOne(document);
        collection.updateMany(Filters.eq("name", "tsh"),
                             new Document("$set",new Document("age",200)));
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> iterator = findIterable.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        collection.deleteOne(Filters.all("name","tansihao"));
    }
}
