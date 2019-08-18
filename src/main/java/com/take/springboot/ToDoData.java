package com.take.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="tododata")
public class ToDoData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	private long id;
	
	@Column(length = 30)
	@Size(min=1, max=10, message= "ToDo名は1文字以上30文字以内です")
	public String todoname;
	
	@Column
//	@Future
	private Date deadline;
	
	@Column
	private Date createdate;
	
	public long getId() {
		return id;
	}
		
	public void setId (long id) {
		this.id = id;
	}
	
	public String getToDoName() {
		return todoname;
	}
		
	public void setToDoName (String todoname) {
		this.todoname = todoname;
	}
	
	public Date getDeadline() {
		return deadline;
	}
	
	public void setName (Date deadline) {
		this.deadline = deadline;
	}
	
	public Date getCreatedate() {
		return createdate;
	}
	
	public void setCreatedate (Date createdate) {
		this.createdate = createdate;
	}

}
