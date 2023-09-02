package ru.panteleevya.backend.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

@Slf4j
@Configuration
public class MongoConfig {
    //    @Bean
//    public MongoClientFactoryBean mongo() {
//        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
//        mongo.setHost(host);
//        mongo.setPort(port);
//        mongo.setCredentials(new MongoCredential[]{MongoCredential.createCredential(username, database, password.toCharArray())});
//        return mongo;
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate(MongoClientFactoryBean mongo) throws Exception {
//        return new MongoTemplate(mongo, database);
//    }
//    @Bean
//    public MongoClientOptions mongoClientOptions() {
//        System.setProperty("javax.net.ssl.keyStore", "<<PATH TO KEYSTOR >>");
//        System.setProperty("javax.net.ssl.keyStorePassword", "PASSWORD");
//        MongoClientOptions.Builder builder = MongoClientOptions.builder();
//        MongoClientOptions options = builder.sslEnabled(true).build();
//        return options;
//    }

//    @Override
//    public MongoClient mongoClient() {
//        MongoClient client = MongoClients.create(uri);
//        ListDatabasesIterable<Document> databases = client.listDatabases();
//        databases.forEach(System.out::println);
//        return client;
//    }

    @Bean
    public MongoClientFactoryBean mongo(@Value("${mongodb_uri}") String uri) {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        ConnectionString connectionString = new ConnectionString(uri);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        log.info("Mongo settings: {}", settings);
        mongo.setMongoClientSettings(settings);

        //        mongo.setConnectionString(connectionString);
        return mongo;
    }
}
