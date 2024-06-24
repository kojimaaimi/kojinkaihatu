package com.example.demo.entity;

import java.time.LocalDateTime;
// import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="memo",schema="kojimaaimi")
public class Memo {
    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "registration")
	private LocalDateTime registration;
	
	@Column(name = "memo_content")
	private String memo_content;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "login_id", referencedColumnName = "id", nullable = false)
	private Login login;
	// コンストラクタ
    public Memo(Long id, String memo_content, LocalDateTime registration) {
		this.id = id;
		this.memo_content = memo_content;
		this.registration = registration;
	}
	
	// public Login getLogin() {
	// 	return login;
	// }

	// public void setLogin(Login login) {
	// 	this.login = login;
	// }
}
