package com.gavin.pojo;

import com.gavin.domain.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name="transactionHistoryDetail")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TransactionHistoryDetail extends Question implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tdid")
    private Integer tdid;

    @Column(name="type")
    private String type;

    @Column(name="description")
    private String description;

    @Column(name="optionA")
    private String optionA;

    @Column(name="optionB")
    private String optionB;

    @Column(name="optionC")
    private String optionC;

    @Column(name="optionD")
    private String optionD;

    @Column(name="answerIndex")
    private Integer answerIndex;

    @ManyToOne
    @JoinColumn(name="tdid", nullable=false)
    private TransactionHistory TransactionHistory;

    public TransactionHistoryDetail(String type, String description, String optionA, String optionB, String optionC,
                                    String optionD, int answerIndex) {
        super();
        this.type = type;
        this.description = description;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerIndex = answerIndex;
    }

}