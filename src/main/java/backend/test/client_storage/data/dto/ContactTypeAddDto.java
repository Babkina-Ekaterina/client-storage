package backend.test.client_storage.data.dto;

import backend.test.client_storage.enumeration.ContactTypeEnum;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactTypeAddDto {
    @NotNull(message = "Contact type cannot be null")
    private String name;

    @AssertTrue(message = "Contact type must be 'email' or 'phoneNumber'")
    public boolean isValidContactTypeName() {
        try {
            ContactTypeEnum.fromValue(name);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
