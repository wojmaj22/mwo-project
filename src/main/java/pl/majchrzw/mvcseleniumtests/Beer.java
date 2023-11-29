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
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private BigDecimal price;
}
