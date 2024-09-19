package tech.saintbassanaga.stockhubapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@MappedSuperclass
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditingEntity {
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "last_updated_date", insertable = false)
    private LocalDateTime updateAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private UUID createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", insertable = false)
    private UUID lastModifiedBy;

}
