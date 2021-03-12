package com.gavin.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name="admin")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
    private Integer aid;
	
    @Column(name="admin_name")
    private String adminName;
    
    @Column(name="password")
    private String password;

    public Admin(String adminName, String password) {
    	this.adminName = adminName;
        this.password = password;
    }
}