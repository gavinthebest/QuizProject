package com.gavin.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name="transactionHistory")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TransactionHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Integer tid;

    @Column(name="date")
    private String date;

    @Column(name="count")
    private Integer count;

    @OneToMany(mappedBy="transactionHistoryDetail")
    private Set<TransactionHistoryDetail> TransactionHistoryDetails;

    public TransactionHistory(String date, Integer count) {
    	this.date = date;
        this.count = count;
    }
}