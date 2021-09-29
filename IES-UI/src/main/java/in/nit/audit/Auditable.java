package in.nit.audit;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    @CreatedBy
    protected U createdBy;

    @CreationTimestamp
	@Column(name = "CREATED_DATE",updatable = false)
    protected LocalDate createdDate;

    @LastModifiedBy
    protected U lastModifiedBy;

    @UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false )
    protected LocalDate lastModifiedDate;
    
}

