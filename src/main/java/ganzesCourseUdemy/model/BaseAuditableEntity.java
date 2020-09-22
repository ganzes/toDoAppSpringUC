package ganzesCourseUdemy.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseAuditableEntity {

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    private void prePersist(){
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        updatedOn = LocalDateTime.now();
    }
}
