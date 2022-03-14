package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.AppUser;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.Concert_Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Concert_TicketRepository extends JpaRepository <Concert_Ticket, Long> {

    List<Concert_Ticket> findByAppUser(AppUser appUser);
//    Concert_Ticket findByAppUserAndConcert(AppUser appUser, Concert Concert);
//    void deleteConcert_TicketByAppUserAndConcert(AppUser appUser, Concert concert);

}
