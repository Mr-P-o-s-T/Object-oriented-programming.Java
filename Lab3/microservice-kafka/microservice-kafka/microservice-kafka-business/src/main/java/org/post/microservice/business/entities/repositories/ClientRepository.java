package org.post.microservice.business.entities.repositories;

import org.post.microservice.business.entities.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {



}
