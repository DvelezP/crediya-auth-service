package co.com.bancolombia.model.user;
import lombok.*;
//import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private String userId;
    private String firstname;
    private String lastname;
    private String birthday;
    private  String email;
    private String documentNumber;
    private String phoneNumber;
    private String baseSalary;

}
