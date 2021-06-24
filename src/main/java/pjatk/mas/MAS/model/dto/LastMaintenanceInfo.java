package pjatk.mas.MAS.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that describes the last car maintenance information
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class LastMaintenanceInfo {

    /**
     * Car last maintenance date
     */
    @NotNull(message = "Inspection date cannot be null")
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inspectionDate;

    /**
     * Concerns and errors that were found during inspection
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<String> concernsFoundDuringInspection = new HashSet<>();

    /**
     * Flag whether the car is safe to drive after inspection
     */
    @NotNull(message = "Safe to drive flag cannot be null")
    private boolean isSafeToDrive;

}
