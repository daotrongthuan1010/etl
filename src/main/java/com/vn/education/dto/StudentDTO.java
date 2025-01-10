package com.vn.education.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;


@Getter
@Builder
public class StudentDTO {
    
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty( "dob")
    private Date dob;

    @JsonProperty( "gender")
    private String gender;

    @JsonProperty( "address")
    private String address;

    @JsonProperty( "phone_number")
    private String phoneNumber;

    @JsonProperty( "email")
    private String email;

    @JsonProperty( "enrollment_date")
    private Date enrollmentDate;
}
