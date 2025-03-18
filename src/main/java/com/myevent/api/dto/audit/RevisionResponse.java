package com.myevent.api.dto.audit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RevisionResponse<T> {

    private T entity;
    private AuditRevResponse revision;

    public RevisionResponse(AuditRevResponse revision, T entity) {
        this.revision = revision;
        this.entity = entity;
    }

}
