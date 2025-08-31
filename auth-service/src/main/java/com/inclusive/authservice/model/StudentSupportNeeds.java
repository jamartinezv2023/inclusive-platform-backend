package com.inclusive.authservice.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter

public class StudentSupportNeeds {
    private Boolean receivesPsychologicalSupport;
    private Boolean receivesSpecialEducationSupport;
}

