package org.post.microservice.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Once an order in the order application is created , after a while the invoice and the
 shipment should be shown in the other applications.
 The data of an order is copied - including the data of the entities and the items. So if
  a entities or item changes in the order system this does not influence existing
  shipments and invoices. It would be wrong if a change to a price would also change
  existing invoices. Also only the information needed for the shipment and the invoice
  are copied over to the other systems.*/
@SpringBootApplication
public class BusinessApp {

	public static void main(String[] args) {
		SpringApplication.run(BusinessApp.class, args);
	}

}
