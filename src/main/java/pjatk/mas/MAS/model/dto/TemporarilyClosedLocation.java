package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class TemporarilyClosedLocation {

    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private LocalDateTime openingDateTime;
}
