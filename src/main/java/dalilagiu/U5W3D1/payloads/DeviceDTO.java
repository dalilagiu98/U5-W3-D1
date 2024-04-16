package dalilagiu.U5W3D1.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DeviceDTO(@NotEmpty(message = "The type field is required.")
                        @Size(min = 3, max = 30, message = "Type field length must be between 3 and 30 characters.")
                        String type,

                        @NotEmpty(message = "The status field is required.")
                        @Size(min = 3, max = 30, message = "Status field length must be between 3 and 30 characters.")
                        String status,

                        @Min(value = 1, message = "Invalid ID value.")
                        @NotNull(message = "The author ID field must not be null.")
                        long employeeId){
}
