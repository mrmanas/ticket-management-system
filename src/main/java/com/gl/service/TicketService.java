package com.gl.service;

import java.util.List;
import com.gl.bean.Ticket;

public interface TicketService {
    void addTicket(Ticket ticket);
    List<Ticket> getAllTicket();
    List<Ticket> searchTicketByTitle(String searchTitle);
    void deleteById(long id);
    void updateTicketById( Ticket ticket ,long id);
	 Ticket getTicketById(long id);
	 
	List<Ticket> getSortedList(String title,  String order);
}
