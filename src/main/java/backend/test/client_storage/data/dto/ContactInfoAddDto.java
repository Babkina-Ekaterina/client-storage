package backend.test.client_storage.data.dto;

import backend.test.client_storage.enumeration.ContactTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Information required for adding new contact")
public class ContactInfoAddDto {
    @NotNull(message = "Client id cannot be null")
    @Min(value = 1, message = "Client id must be at least 1")
    private Long clientId;
    @NotNull(message = "Contact type cannot be null")
    private String contactTypeName;
    @NotNull(message = "Value cannot be null")
    @NotBlank(message = "Value cannot be blank")
    private String value;

    @AssertTrue(message = "Contact type must be 'email' or 'phoneNumber'")
    public boolean isValidContactTypeName() {
        try {
            ContactTypeEnum.fromValue(contactTypeName);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    @AssertTrue(message = "Contact value must be a valid string depending on contact type")
    public boolean isValidValue() {
        try {
            ContactTypeEnum type = ContactTypeEnum.fromValue(contactTypeName);
            return type.isValid(value);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
