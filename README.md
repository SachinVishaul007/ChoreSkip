# choreSkip — Your Earn-Before-Spend Chore Companion

🔗 **Live Demo:** https://demo.choreskip.com

---
![image](https://github.com/user-attachments/assets/0b334fba-1efe-4e89-9af9-b718803ab016)

## 🏠 Overview

choreSkip is a lightweight web application built for shared living spaces—dorms, apartments or house-shares—where fair chore rotation meets flexible “skip” mechanics. Rather than forcing everyone to follow a rigid schedule, choreSkip lets you **earn skip-points** for helping out and **spend them** when you need a break.

---

## ✨ Key Features

### 1. Individual Chore Dashboard  
- **At-a-glance view** of “My Next Turn” for each assigned chore.  
- **Skip-point balance** displayed prominently.  
- **Debt queue preview**: who owes you, in chronological order.

### 2. Earn-Before-Spend Skip-Point System  
- **Earn Points** by taking over someone else’s chore turn.  
- **Spend Points** to skip your own turn—automatically reassigns to your earliest debtor.  
- **Debt Queue Management**: each time you earn a point, the app logs who you covered; when ou skip, that queue is popped FIFO.

  ![image](https://github.com/user-attachments/assets/942c0977-295d-4a57-a9b6-b92331d709ad)


### 3. Two Chore Types  
- **Turn-Based**  
  - Triggered on completion (e.g. “Empty Trash” whenever it’s full).  
  - Next person kicks in immediately.  
- **Week-Based**  
  - Scheduled on a fixed weekday (e.g. “Bathroom” every Wednesday).  
  - Calendar-style reminders keep everyone on track.
 
  ![image](https://github.com/user-attachments/assets/0d2e7894-522c-4b16-926a-4729860997be)


### 4. Real-Time Notifications  
- **Email & SMS alerts** to all roommates whenever any chore is marked complete or skipped.  
- Summary in each notification: chore name, who acted, next assignee, remaining skip-point balances.

### 5. Fair Round-Robin Seeding  
- On group creation, chores are **seeded in ID order** across members by join time.  
- Example with A, B, C and chores 1–5:  
  1 → A • 2 → B • 3 → C • 4 → A • 5 → B  
  ![image](https://github.com/user-attachments/assets/3cb9efb5-04a2-4ad1-bc60-f25efda42d16)

---

## 🚀 How It Works


![image](https://github.com/user-attachments/assets/93c14c8e-be04-47d3-91c0-890978f8d0fe)

1. **Sign Up & Create Group**  
   - Invite roommates; once everyone joins, chores auto-seed in round-robin fashion.  

2. **View “My Chores”**  
   - See each chore you’re up for, your current skip-points, and who’s in your debt queue.  

3. **Mark Complete**  
   - Completes the task and advances the turn to the next person in rotation.  

4. **Skip**  
   - Deducts 1 skip-point and reassigns your turn to your earliest debtor.  
   - If the debt queue is empty, falls back to normal round-robin.  

5. **Earn More Points**  
   - Pick up extra turns for someone else to grow your skip balance.  

---

## 🛠 Under the Hood

- **Auth & Groups:** Users join a shared “household” by invite link.  
- **Assignment Engine:** Tracks next-due and debt queues per user/chore.  
- **Notification Service:** Integrates SendGrid (email) & Twilio (SMS) for instant alerts.  
- **Data Model Highlights:**  
  - **Users**, **Chores**, **Assignments** (with debt queues), **SkipPoints**.

---

## 👥 Who It’s For

- **College roommates** juggling classes and part-time jobs.  
- **Flatmates** who hate surprise chore duty.  
- **Anyone** who believes in helping first, then taking a break.

---

## 🌱 Future Roadmap

- **Mobile Push Notifications**  
- **Group chat** & poll for chore swaps  
- **Analytics dashboard**: who’s most helpful, skip-point leaderboards  
- **Calendar sync** (Google/Outlook)

---

choreSkip turns the dreaded “whose turn is it?” into a cooperative game of help-then-rest. Because in shared living, teamwork should pay off—literally.  
