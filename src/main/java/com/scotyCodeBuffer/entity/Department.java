package com.scotyCodeBuffer.entity;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="departament")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotBlank(message = "Please add Department Name")
    //@Length(max = 5,min = 1)
    //@Size(max = 10,min =0)
    //@Email
    //@Positive
    //@Negative
    //@PositiveOrZero
    //@NegativeOrZero
    //@Future
    //@FutureOrPresent
    //@Past
    //@PastOrPresent
    //@NotNull
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;


}