package com.yash.ppm.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project",uniqueConstraints = @UniqueConstraint(columnNames = "project_identifier"))
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	long id;
	@Size(min = 4,max = 7,message = "Identifier must be between 4 to 7 characters and Unique")
	@Column(name = "project_identifier")
	String projectIdentifier;
	@NotBlank(message = "Project Name cant be blank")
	@Column(name = "project_name")
	String projectName;
	@NotBlank(message = "Description cant be blank")
	@Column(name = "project_description")
	String description;

	@Column(name = "project_createdAt",updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date createdAt;

	@Column(name = "project_updatedAt",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date updatedAt;

	@Column(name = "project_started",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date startDate;

	@Column(name = "project_ended",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date endDate;
}
