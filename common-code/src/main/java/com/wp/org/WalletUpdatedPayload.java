package com.wp.org;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletUpdatedPayload {

    private String userEmail;

    private Double balance;

    private String requestId;
}
