package org.xcraftman;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        MongoClient mongoClient= new MongoClient(new MongoClientURI("mongodb://172.27.203.8:27017/test"));

        // get handle to "mydb" database
        MongoDatabase database = mongoClient.getDatabase("mydb");
        database.drop();

        GridFSBucket gridFSBucket = GridFSBuckets.create(database);

        /*
         * UploadFromStream Example
         */
        // Get the input stream
        InputStream streamToUploadFrom = new ByteArrayInputStream("Hello World".getBytes(StandardCharsets.UTF_8));

        // Create some custom options
        GridFSUploadOptions options = new GridFSUploadOptions()
                .chunkSizeBytes(1024)
                .metadata(new Document("type", "presentation"));

        ObjectId fileId = gridFSBucket.uploadFromStream("mongodb-tutorial", streamToUploadFrom, options);
        streamToUploadFrom.close();
        System.out.println("The fileId of the uploaded file is: " + fileId.toHexString());
    }
}
