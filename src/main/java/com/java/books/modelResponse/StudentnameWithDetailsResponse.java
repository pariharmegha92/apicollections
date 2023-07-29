package com.java.books.modelResponse;

import com.java.books.entity.AdmissionDetails;
import com.java.books.entity.StudentDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentnameWithDetailsResponse {
    private String branchName;
    private int noOfStudent;
    private List<StudentDetails> studentnameWithDetailsResponseList;
}
