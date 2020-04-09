package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "History")
@Table(name = "TB_HISTORY")
@NoArgsConstructor
public class HistoryJpo {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "HISTORY_ID")
	private String historyId;

	@Column(name = "HISTORY_QUERY")
	private String query;

	@CreationTimestamp
	@Column(name = "HISTORY_SEARCH_DATE")
	private LocalDateTime searchDate;

	@Column(name = "HISTORY_USER_ID")
	private String userId;

//	@ManyToOne
//	@JoinColumn(name="USER_ID")
//	private UserJpo userJpo;

	public HistoryJpo(History history) {

		BeanUtils.copyProperties(history, this);

	}

	public History toModel() {

		History history = new History();

		BeanUtils.copyProperties(this, history);

		return history;

	}

}
