package org.jcd2052.repsonses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class DeleteEntityResponse extends BaseResponse {
    @AllArgsConstructor
    @ToString
    @Data
    static class DeleteEntity {
        private final String gameName;
    }

    public DeleteEntityResponse(String gameName) {
        super(new DeleteEntity(gameName));
    }
}

