package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "last_maintenance_info")
@Entity(name = "last_maintenance_info")
public class LastMaintenanceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_last_maintenance_info")
    private Long id;

    @NotNull(message = "Inspection date cannot be null")
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inspectionDate;

    @ElementCollection
    @CollectionTable(name = "concern", joinColumns = @JoinColumn(name = "id_last_maintenance_info"))
    @Builder.Default
    private Set<String> concernsFoundDuringInspection = new HashSet<>();

    @NotNull(message = "Safe to drive flag cannot be null")
    private boolean isSafeToDrive;

    @NotNull
    @OneToOne
    @PrimaryKeyJoinColumn(referencedColumnName = "id_vehicle")
    private Vehicle vehicle;


}
