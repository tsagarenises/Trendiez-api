package com.api.trendiez;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import javax.management.Query;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
public class TrendiezApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrendiezApplication.class, args);
	}

}
