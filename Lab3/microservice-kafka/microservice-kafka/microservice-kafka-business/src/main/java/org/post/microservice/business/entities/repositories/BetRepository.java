package org.post.microservice.business.entities.repositories;

import org.post.microservice.business.entities.Bet;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BetRepository extends PagingAndSortingRepository<Bet, Long> {



}
