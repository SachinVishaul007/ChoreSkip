package com.northeastern.choreless.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.northeastern.choreless.dao.ChoreDAO;
import com.northeastern.choreless.dao.GroupDAO;
import com.northeastern.choreless.dao.RoommateChoreDebtorDAO;
import com.northeastern.choreless.dao.RoommateDAO;
//import com.northeastern.choreless.service.MailService;
import com.northeastern.choreless.model.*;
import com.northeastern.choreless.service.MailService;
import com.northeastern.choreless.service.NotificationService;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class DumpController {

	private String roommates[] = { "Sachin", "Ruthvik", "Shashank", "Anirudh", "Rishikesh" };

	@Autowired
	ServletContext servletContext;
//	
	@Autowired
	private MailService mailService;

	@Autowired
	private GroupDAO group_dao;

	@Autowired
	private RoommateDAO roommateDao;
	
	@Autowired
	private ChoreDAO choredao;
	
	@Autowired
	private RoommateChoreDebtorDAO rcd_dao;
	
	
	
	
	@RequestMapping("/addRoommate")
	public String addRoommmate() {
		return "";
	}
	
	@RequestMapping("/deleteRoommate")
	public String addNewRoommmate() {
		return "";
	}
	
	
	@RequestMapping("/")
	public String landingPage() {
		
		return "landing";
	}
	
	public void sendEmailOnSkip(List<Roommate> roommates, String nextChoreDoer, String skipper, String chore) throws MessagingException {

//uncomment this 
		String emailContent = "<html>" +
			    "<body style='font-family: Arial, sans-serif; color: #333;'>" +
			    "<div style='background-color: #f2f2f2; padding: 20px; text-align: center;'>" +
			    "<h2 style='color: #007bff;'>Chore Skipping Alert!</h2>" +
			    "<p style='font-size: 16px;'>" + skipper + " has skipped the chore: <strong>" + chore + "</strong></p>" +
			    "<p style='font-size: 16px;'>The next Chore Doer is: <strong>" + nextChoreDoer + "</strong></p>" +
			    "</div>" +
			    "</body>" +
			    "</html>";
		for(Roommate roommate : roommates)
			mailService.mailWithAttachment(roommate.getEmail(),
			    emailContent,
			    "ChoreSkip Notification: " + skipper + " skipped his turn for chore: " + chore, 
			    "");
		
	}
	
	public void sendEmailOnSubmit(List<Roommate> roommates, String name, String chore, String mates[],int curr_index) throws MessagingException {
		
//		for (Roommate roommate: roommates)
//			mailService.mailWithAttachment(roommate.getEmail(),
//
//					"<p>"+name+" has done chore:"+ chore+"!</p>"+"<p><a href=\"https://localhost:8080/approve\">Click here to approve</a></p>",
//					"ChoreLess alert: "+ name+" did chore:"+chore, "");
	
		
		//CORRECT CODE:
	
		
		
//		uncomment this part:
		int nextIndex = (curr_index +1) % mates.length;
		String nextChoreDoer= mates[nextIndex];
		String emailContent = "<html>" +
			    "<body style='font-family: Arial, sans-serif; color: #333;'>" +
			    "<div style='background-color: #f2f2f2; padding: 20px; text-align: center;'>" +
			    "<h2 style='color: #007bff;'>Chore Completion Alert!</h2>" +
			    "<p style='font-size: 16px;'>" + name + " has completed the chore: <strong>" + chore + "</strong></p>" +
			    "<p style='font-size: 16px;'>The next Chore Doer is: <strong>" + nextChoreDoer + "</strong></p>" +
			    "</div>" +
			    "</body>" +
			    "</html>";
		for(Roommate roommate : roommates)
			mailService.mailWithAttachment(roommate.getEmail(),
			    emailContent,
			    "ChoreSkip alert: " + name + " did chore: " + chore, 
			    "");
		

		try {
			//uncomment this for app notification
//			notify.sendNotification("Chore Completion Alert: "+ name + " has completed the chore: "+chore, "Now it's "+nextChoreDoer+"'s turn", "all", null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	@RequestMapping("/register")
	public String group() {
		return "group";
	}
	
	
	
	
	
	
	
	@RequestMapping("/home")
	public String home(HttpSession session, Model model) {
//		ChoreGroup group = group_dao.getById(groupId);
		Integer groupId = (Integer)session.getAttribute("groupId");
	    if (groupId == null) {
	      // redirect back to login or error page if missing
	      return "redirect:/login";
	    }
	    ChoreGroup group = group_dao.getById(groupId);
		model.addAttribute("welcome", "Welcome " + group.getGroupName() + "!");
		model.addAttribute("chores", group.getChores());
		
		
		model.addAttribute("roommates", group.getRoommates());

		
		return "home";

	}
	
	
	
	
	@RequestMapping("/chore")
	public String chores() {
		
		return "add-chore";
	}
	


	@RequestMapping("/main")
	public String main() {

		return "main";
	}

	@RequestMapping("/delete")
	public String delete() {


		return "group";
	}




	@GetMapping("/choreDetails")
	public String doGet(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("DO GET() executing");

		Chore chore = choredao.getById(id);
		List<Roommate> mate_list= roommateDao.getByGroupId(chore.getChoregroup().getGroupId());
		servletContext.setAttribute("chore_name", chore.getChoreName());
		ChoreGroup group = group_dao.getById(chore.getChoregroup().getGroupId());
		
		int index = chore.getCurrIndex();
		
		List<String> names = new ArrayList<>();
		List<Integer> roommate_ids = new ArrayList<>();
		
		for (Roommate roommate : mate_list) {
			names.add(roommate.getName());
			roommate_ids.add(roommate.getId());
		}
		String lastSubmissionTime = chore.getLast_chore_sub_time();

		String mates[] = names.toArray(String[]::new);

		int sacrifice_count[] = convertCsvToIntArray(chore.getSacrifice());

		
		servletContext.setAttribute("roommates", mates);
		servletContext.setAttribute("index", index);
		servletContext.setAttribute("lastSubmissionTime", lastSubmissionTime);
		servletContext.setAttribute("roomate_list", mate_list);
		servletContext.setAttribute("debtors_next_in_line", chore.getDebtors_next_in_line());
		servletContext.setAttribute("sacrifice", sacrifice_count);
		servletContext.setAttribute("groupId", group.getGroupId());
		servletContext.setAttribute("choreId", id);
		servletContext.setAttribute("roommate_ids", roommate_ids);
		
		int skipCountMax = Math.min(1, sacrifice_count[index]);
		
		if(chore.getDebtors_next_in_line()!=null && !chore.getDebtors_next_in_line().isEmpty()) {
			skipCountMax = Math.min(1, sacrifice_count[chore.getDebtors_next_in_line().get(0)]);
		}
		
		
		servletContext.setAttribute("skipCountMax", skipCountMax);

		return "random";
	}
	
//	
	@GetMapping("/getDebtors/{id}")
    @ResponseBody
    public ResponseEntity<List<Integer>> getDebtors(@RequestParam int index,@PathVariable("id") int groupId) {
        // Assuming 'roommate_list' is available in your GroupDAO
		
		int choreId = (int)servletContext.getAttribute("choreId");
		List<Integer> debtors = rcd_dao.getDebtors(choreId,group_dao.getById(groupId).getRoommates().get(index).getId()).getDebtors();
        return ResponseEntity.ok(debtors);
    }
	
	
	public static String convertIntArrayToCSV(int[] array) {
		if (array == null || array.length == 0) {
			return "";
		}

		StringBuilder csvBuilder = new StringBuilder();

		csvBuilder.append(array[0]);

		for (int i = 1; i < array.length; i++) {
			csvBuilder.append(",").append(array[i]);
		}

		return csvBuilder.toString();
	}

	public static int[] convertCsvToIntArray(String csvString) {
		if (csvString == null || csvString.isEmpty()) {
			return new int[0];
		}

		String[] stringValues = csvString.split(",");
		int[] intArray = new int[stringValues.length];

		for (int i = 0; i < stringValues.length; i++) {
			try {
				intArray[i] = Integer.parseInt(stringValues[i].trim());
			} catch (NumberFormatException e) {

				System.err.println("Error converting to int: " + stringValues[i]);
			}
		}

		return intArray;
	}

	@RequestMapping("/forgotPassword")
	public String forgotPass() {

		return "forgotPassword";
	}

	@RequestMapping("/check")
	public String check() {

		return "check";
	}
	
	@Autowired
	NotificationService notify;

	@RequestMapping("/skip")
	public String skip(HttpServletRequest request,HttpSession session,
			@RequestParam(name = "skipCount", required = false) Integer skipCount) throws Exception {

			System.out.println("SkipCount: "+"\n"+skipCount+"\n");
		
			Integer groupId = (Integer)session.getAttribute("groupId");
		    if (groupId == null) {
		      // redirect back to login or error page if missing
		      return "redirect:/login";
		    }

		ChoreGroup group = group_dao.getById(groupId);
		
		int choreId = (int) servletContext.getAttribute("choreId");
		Chore chore = choredao.getById(choreId);
		
		int sacrifice_count[] = convertCsvToIntArray(chore.getSacrifice());
		
		
		Integer effectiveIndex = (Integer) request.getSession().getAttribute("overrideChoreDoer");
	    if (effectiveIndex == null) {
	        effectiveIndex = chore.getCurrIndex();
	    }
		
		int curr_index = chore.getCurrIndex();
		int originalIndex = chore.getCurrIndex();

		List<String> names = new ArrayList<>();
		for (Roommate roommate : group.getRoommates()) {
			names.add(roommate.getName());
		}

		String mates[] = names.toArray(String[]::new);
		int roommate_length = mates.length;
		
		
		
//		List<Integer> debtorsInLine1 = chore.getDebtors_next_in_line(); 
	    // At the first skip, this list is likely null or empty.
	    // ----------------------------------------------------------------------------------

	    // ------------------ ADD HERE: Get the list of debtors from rcd ------------------
	    // Fetch the RoommateChoreDebtor for the current doer using rcd.getDebtors()
	    Roommate currentRoommate = group.getRoommates().get(effectiveIndex);
	    RoommateChoreDebtor rcd1 = rcd_dao.getDebtors(choreId, currentRoommate.getId());
	    List<Integer> debtorsFromRcd = (rcd1 != null) ? rcd1.getDebtors() : new ArrayList<>();
	    // ----------------------------------------------------------------------------------

	    // ------------------ ADD HERE: Determine the temporary override (next doer) ------------------
	    int nextDoerIndex=-1;
	    boolean flag = true;
//	    if (debtorsInLine1 != null && !debtorsInLine1.isEmpty()) {
//	        // If an override list exists, use its first element.
//	        nextDoerIndex = debtorsInLine1.get(0);
	    if (debtorsFromRcd != null && !debtorsFromRcd.isEmpty()) {
	        // Otherwise, if rcd returns a nonempty list, use its first element.
	        nextDoerIndex = debtorsFromRcd.get(0);
	        // OPTIONAL: You might want to initialize debtorsInLine with this value so subsequent skips work uniformly.
//	        debtorsInLine1 = new ArrayList<>();
//	        debtorsInLine1.add(nextDoerIndex);
//	        chore.setDebtors_next_in_line(debtorsInLine1);
	    } 
	    else {
	        // Fall back to normal rotation if no override is available.
//	        nextDoerIndex = (effectiveIndex + 1) % roommate_length;
	    	flag=false;
	    }
	    // ----------------------------------------------------------------------------------

	    // ------------------ ADD HERE: Log the skip event into skipHistory ------------------
	    // Make sure your Chore model implements skipHistory with getSkipHistory() and setSkipHistory()
	    if(flag) {
	    String newLogEntry = mates[effectiveIndex] + " skipped to " + mates[nextDoerIndex];
	    String existingLog = chore.getSkipHistory(); // Implement this property in your model
	    if (existingLog == null || existingLog.trim().isEmpty()) {
	        chore.setSkipHistory(newLogEntry);
	    } else {
	        // Append with a separator (e.g., semicolon)
	        chore.setSkipHistory(existingLog + "; " + newLogEntry);
	    }
	    
	    
	    System.out.println(chore.getSkipHistory());
	    }
	    
	    
	    if(flag)
	    {
	    request.getSession().setAttribute("overrideChoreDoer", nextDoerIndex);
	    servletContext.setAttribute("overrideChoreDoer", nextDoerIndex);
	    }
	    
	    
	    
	    if(nextDoerIndex != -1) {
	    Roommate mate = group.getRoommates().get(nextDoerIndex);
	    RoommateChoreDebtor rcd3 = rcd_dao.getDebtors(choreId, mate.getId());
	    
	    
	    if(rcd3==null) {
	    	request.getSession().setAttribute("overrideChoreDoer", null);
		    servletContext.setAttribute("overrideChoreDoer", null);
//		    chore.setSkipHistory(null);
	    	
	    }
	    }
		
		
		List<Integer> debtorsInLine = chore.getDebtors_next_in_line();
		

		
		
	    
	    
		curr_index = (debtorsInLine == null || debtorsInLine.isEmpty())?(curr_index) :(debtorsInLine.get(0)) ;
		
		
		
		
		Roommate roommate  = group.getRoommates().get(curr_index);         //? for what ??
		

		
		RoommateChoreDebtor rcd = rcd_dao.getDebtors(choreId, roommate.getId());
		List<Integer> debtors  = rcd.getDebtors();
		

		
		if (sacrifice_count[curr_index] > 0) {

			
			
			sacrifice_count[curr_index] = sacrifice_count[curr_index] - skipCount;
			chore.setSacrifice(convertIntArrayToCSV(sacrifice_count));
			boolean isDebtor = true;

			if (debtorsInLine == null || debtorsInLine.isEmpty()) {
				servletContext.setAttribute("index", (curr_index + 1) % roommate_length);
				chore.setCurrIndex((curr_index + 1) % roommate_length);
				isDebtor = false;
			} else {
				debtorsInLine.remove(0);
				chore.setDebtors_next_in_line(debtorsInLine);
			}

			if (skipCount != null) {
				int i = 0;
				while ((skipCount--) > 0) {

					if (chore.getDebtors_next_in_line() != null) {
						if (isDebtor) {
							chore.getDebtors_next_in_line().add(i++, debtors.remove(0)); //error
						} else
							chore.getDebtors_next_in_line().add(debtors.remove(0));
					} else
						chore.setDebtors_next_in_line(new ArrayList<Integer>(Arrays.asList(debtors.remove(0))));
				}
			} else
				System.out.println("skipcount is null. Choose a value ");

			
			
			rcd.setDebtors(debtors);
			

			choredao.save(chore);
			rcd_dao.save(rcd);


			servletContext.setAttribute("debtors_in_line", chore.getDebtors_next_in_line());
			servletContext.setAttribute("roommate_list", group_dao.getById(groupId).getRoommates());
			servletContext.setAttribute("sacrifice", sacrifice_count);
			servletContext.setAttribute("skip", mates[curr_index] + " skipped their turn using their skipping points");

			int skipCountMax =  Math.min(1, sacrifice_count[curr_index]);
			if(!chore.getDebtors_next_in_line().isEmpty())
				skipCountMax =  Math.min(1, sacrifice_count[chore.getDebtors_next_in_line().get(0)]);
			servletContext.setAttribute("skipCountMax", skipCountMax);
			
			try {
			String skipper	= mates[curr_index];
			String nextChoreDoer= mates[chore.getDebtors_next_in_line().get(0)];
//       		if(!chore.isSkipActive())
				sendEmailOnSkip(group.getRoommates(), nextChoreDoer, skipper, chore.getChoreName());
			} catch(Exception e) {
				System.err.println("Failed to send mail: " + e.getMessage()); 
			}
			try {
				//uncomment this for app notification
//			notify.sendNotification("Now it's "+ mates[chore.getDebtors_next_in_line().get(0)] + "'s turn for "+chore.getChoreName(),mates[curr_index] + " skipped their turn using skipping points", "all", null, null);
			} catch(Exception e) {
				System.err.println("Failed to send notification: " + e.getMessage()); 
			}
			

		} else {
			request.setAttribute("error", mates[curr_index] + "\'s skipping points are 0! XD");
		}
		return "random";
	}

	@PostMapping("/dump")
	public String doPost(HttpSession session,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "sacrificer", required = false) Integer sacrificer) throws IOException, MessagingException {
		System.out.println("DO POST() executing");
		System.out.println(session.getAttribute("groupId"));
		Integer groupId = (Integer)session.getAttribute("groupId");
	    if (groupId == null) {
	      // redirect back to login or error page if missing
	      return "redirect:/login";
	    }
		
		response.setContentType("text/html;charset=UTF-8");

		Date date = new Date();
		String pattern = "dd:MM:yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String formattedDate = simpleDateFormat.format(date);
		
		
		
		
		LocalDateTime now = LocalDateTime.now();

		// 1) ISO‑8601 for the <time> tag’s datetime attribute
		String iso = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		// 2) “May 15, 2025 at 12:59 AM” for display
		DateTimeFormatter humanFmt =
		    DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm a");
		String human = now.format(humanFmt);
		
		
		
		
		
		
		
		
		
		
		ChoreGroup group = group_dao.getById(groupId);
		int choreId = (int) servletContext.getAttribute("choreId");
		
		Chore chore = choredao.getById(choreId);
		
		List<String> names = new ArrayList<>();
		for (Roommate roommate : group.getRoommates()) {
			names.add(roommate.getName());
		}
		
		String mates[] = names.toArray(String[]::new);
		int roommate_length =  mates.length;
		int sacrifice_count[] = convertCsvToIntArray(chore.getSacrifice());

		int curr_index = chore.getCurrIndex();

		RoommateChoreDebtor rcd = null;
		
		
		if (sacrificer != null) {
			Roommate sacrifice_mate = roommateDao.getById((int)sacrificer);
			rcd = rcd_dao.getDebtors(choreId,(int)sacrificer);
			List<Integer> debtors = rcd.getDebtors();
					
					if(debtors == null) {
						rcd.setDebtors(new ArrayList<Integer>(Arrays.asList(curr_index)));
					}
					else {
						rcd.getDebtors().add(curr_index);
						rcd.getDebtors().forEach(System.out::println);
					}
					
					List<Roommate> roommate_list = group.getRoommates();
					int j=-1;
					for(int i=0;i<roommate_list.size();i++) {
						if(roommate_list.get(i).getId() == (int)sacrificer) {
							j = i;
							break;
						}
					}
					
					sacrifice_count[j]++;
					chore.setSacrifice(convertIntArrayToCSV(sacrifice_count));
					
					


		}
		else {
			try {
				if(!chore.isSkipActive())
					sendEmailOnSubmit(group.getRoommates(),mates[curr_index],chore.getChoreName(),mates, curr_index);
			
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		
		

		int index = curr_index;

		List<Integer> debtors_next_in_line = chore.getDebtors_next_in_line();
		if (debtors_next_in_line == null || debtors_next_in_line.isEmpty())
			index = (curr_index + 1) % roommate_length;
		else {
			debtors_next_in_line.remove(0);
			chore.setDebtors_next_in_line(debtors_next_in_line);
		}
		
		chore.setCurrIndex(index);
		chore.setLast_chore_sub_time(formattedDate);
		
		if(chore.getChoreType().equals("week-based"))
			chore.setStatus("DONE");
//		group_dao.save(group);      // ???????????????
		chore.setSkipHistory(null);
		choredao.save(chore);

		if (rcd != null) {
			rcd_dao.save(rcd);
			servletContext.setAttribute("debtors", rcd.getDebtors());
		}
		System.out.println("///////////////////////// \nCurrent Index: "+ chore.getCurrIndex());
		System.out.println("/////////////////////////");
		servletContext.setAttribute("debtors_in_line", debtors_next_in_line);
		servletContext.setAttribute("index", index);
		servletContext.setAttribute("sacrifice", sacrifice_count);
		servletContext.setAttribute("lastSubmissionTime", human);
		servletContext.setAttribute("skip", "");
		
		int skipCountMax =  Math.min(1, sacrifice_count[index]);
		if(debtors_next_in_line!=null &&  !debtors_next_in_line.isEmpty())
			skipCountMax =  Math.min(1, sacrifice_count[debtors_next_in_line.get(0)]);
		servletContext.setAttribute("skipCountMax", skipCountMax);
		servletContext.setAttribute("choreId", choreId);
//		servletContext.setAttribute("roomate_list", group_dao.getById(1).getRoommates());
		return "random";
	}
}



// admin account: special privileges

