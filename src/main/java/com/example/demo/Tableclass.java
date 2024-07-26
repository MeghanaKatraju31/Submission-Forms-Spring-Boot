package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Tableclass {

    @Id
    @NotEmpty(message = "College ID is required")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$", message = "College ID must be alphanumeric and 6-10 characters long")
    private String cid;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name can only contain letters and spaces")
    private String cname;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String cemail;

    @NotEmpty(message = "Date of Birth is required")
    private String dateOfBirth;

    @NotEmpty(message = "Timezone is required")
    private String timezone;
    
    @NotEmpty(message = "Time is required")
    private String time;

    private String ampm;

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "Tableclass [cid=" + cid + ", cname=" + cname + ", cemail=" + cemail + ", dateOfBirth=" + dateOfBirth + "]";
    }
}
