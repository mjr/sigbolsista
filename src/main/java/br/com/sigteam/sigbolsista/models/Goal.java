package br.com.sigteam.sigbolsista.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "goal")
public class Goal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 1000, columnDefinition = "TEXT", nullable = false)
	private String description;
	
	@Column(name = "date_begin")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateBegin;
	
	@Column(name = "date_end")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateEnd;
	
	@Column(name = "date_concluded")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateConcluded;
	
	private Boolean concluded;
	
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateConcluded() {
		return dateConcluded;
	}

	public void setDateConcluded(LocalDate dateConcluded) {
		this.dateConcluded = dateConcluded;
	}

	public Boolean getConcluded() {
		return concluded;
	}

	public void setConcluded(Boolean concluded) {
		this.concluded = concluded;
	}

	public LocalDate getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(LocalDate dateBegin) {
		this.dateBegin = dateBegin;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goal other = (Goal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
