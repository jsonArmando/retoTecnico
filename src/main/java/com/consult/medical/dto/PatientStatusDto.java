package com.consult.medical.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientStatusDto  implements Serializable {
    private Integer code;

    private String description;

    private Integer numberId;

    private String names;

    private String surnames;

    private Integer sugar;

    private Integer fat;

    private Integer oxygen;

    private String status;

    private Boolean active;

}
