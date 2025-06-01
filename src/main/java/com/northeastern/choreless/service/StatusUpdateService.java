package com.northeastern.choreless.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.northeastern.choreless.model.*;
import com.northeastern.choreless.dao.ChoreDAO;
import com.northeastern.choreless.dao.RoommateDAO;

@EnableScheduling
@Service
public class StatusUpdateService {
	// Assuming this is a service method that updates your database.

	@Autowired
	private ChoreDAO choreDao;

	@Autowired
	private RoommateDAO roommateDao;

	public void updateDatabase() {
		// Your logic to update the database
		List<Chore> chores = choreDao.getAll();

		for (Chore chore : chores) {
			// case 1: if status complete, change it to
			if (chore != null && chore.getStatus() != null) {
				if (chore.getStatus().equals("DONE")) {
					chore.setStatus("INCOMPLETE");
				} else if (chore.getStatus().equals("INCOMPLETE")) {
					int currIndex = chore.getCurrIndex();
					int roommate_count = roommateDao.getByGroupId(chore.getChoregroup().getGroupId()).size();
					chore.setCurrIndex((currIndex + 1) % roommate_count);
				}

					choreDao.save(chore);
			}

		}
	}

	// This cron expression stands for every Monday at 12:00 AM (EST time), but here it has been adjusted according to UTC time
	// as the LINUX server has by default UTC time
	@Scheduled(cron = "0 0 5 * * MON", zone = "UTC")
	public void performScheduledDatabaseUpdate() {
		updateDatabase();
	}

}
