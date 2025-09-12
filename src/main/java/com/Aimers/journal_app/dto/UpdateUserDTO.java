package com.aimers.journal_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {

    private String userName;

    private String password;

    private String email;

    private boolean sentimentAnalysis;

}
