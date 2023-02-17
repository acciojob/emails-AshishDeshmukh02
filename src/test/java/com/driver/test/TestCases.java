package com.driver.test;

public class TestCases {
  class Email {
  constructor(emailId) {
    this.emailId = emailId;
    this.password = "Accio@123";
  }

  changePassword(oldPassword, newPassword) {
    if (oldPassword === this.password && 
        newPassword.length >= 8 &&
        newPassword.match(/[A-Z]/) &&
        newPassword.match(/[a-z]/) &&
        newPassword.match(/[0-9]/) &&
        newPassword.match(/[^\w\s]/)) {
      this.password = newPassword;
      console.log("Password changed successfully.");
    } else {
      console.log("Failed to change password. Please check the requirements and try again.");
    }
  }
}

class Gmail extends Email {
  constructor(emailId, inboxCapacity) {
    super(emailId);
    this.inboxCapacity = inboxCapacity;
    this.inbox = [];
    this.trash = [];
  }

  receiveMail(date, sender, message) {
    if (this.inbox.length >= this.inboxCapacity) {
      const oldestMail = this.inbox.shift();
      this.trash.push(oldestMail);
    }
    this.inbox.push({ date, sender, message });
  }

  deleteMail(message) {
    const index = this.inbox.findIndex(mail => mail.message === message);
    if (index !== -1) {
      const deletedMail = this.inbox.splice(index, 1)[0];
      this.trash.push(deletedMail);
    }
  }

  findLatestMessage() {
    if (this.inbox.length === 0) {
      return null;
    }
    return this.inbox[this.inbox.length - 1].message;
  }

  findOldestMessage() {
    if (this.inbox.length === 0) {
      return null;
    }
    return this.inbox[0].message;
  }

  findMailsBetweenDates(start, end) {
    let count = 0;
    for (const mail of this.inbox) {
      if (mail.date >= start && mail.date <= end) {
        count++;
      }
    }
    return count;
  }

  getInboxSize() {
    return this.inbox.length;
  }

  getTrashSize() {
    return this.trash.length;
  }

  emptyTrash() {
    this.trash = [];
  }

  getInboxCapacity() {
    return this.inboxCapacity;
  }
}

class Meeting {
  constructor(startTime, endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }
}

class Workspace extends Gmail {
  constructor(emailId) {
    super(emailId, Number.MAX_SAFE_INTEGER);
    this.calendar = [];
  }

  addMeeting(meeting) {
    this.calendar.push(meeting);
  }

  findMaxMeetings() {
    let maxMeetings = 0;
    for (let i = 0; i < this.calendar.length; i++) {
      let count = 1;
      for (let j = 0; j < this.calendar.length; j++) {
        if (i !== j && (this.calendar[j].startTime >= this.calendar[i].endTime || this.calendar[j].endTime <= this.calendar[i].startTime)) {
          count++;
        }
      }
      maxMeetings = Math.max(maxMeetings, count);
    }
    return maxMeetings;
  }
}

}
