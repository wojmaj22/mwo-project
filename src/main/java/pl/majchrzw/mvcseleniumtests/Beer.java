package pl.majchrzw.mvcseleniumtests;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private BigDecimal price;
}
