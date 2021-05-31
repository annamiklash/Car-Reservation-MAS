package pjatk.mas.MAS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Dimensions {

    @NotNull
    @Min(1)
    Integer height;

    @NotNull
    @Min(1)
    Integer length;

    @NotNull
    @Min(1)
    Integer width;
}
