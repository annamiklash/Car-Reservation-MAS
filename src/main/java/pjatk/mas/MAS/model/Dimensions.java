package pjatk.mas.MAS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Dimensions {

    @NotNull
    Integer height;

    @NotNull
    Integer length;

    @NotNull
    Integer width;
}
