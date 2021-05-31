package app.entity;

import app.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("user")
public class User {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonIgnore
    private boolean loggedIn;

    @JsonIgnore
    private List<Product> basket = new ArrayList<>();
}
