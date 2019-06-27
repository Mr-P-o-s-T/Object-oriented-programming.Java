package org.post.microservice.business.entities.repositories;
;
import org.post.microservice.business.entities.Horse;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HorseRepository extends PagingAndSortingRepository<Horse, Long> {



}
