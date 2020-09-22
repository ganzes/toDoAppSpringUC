package ganzesCourseUdemy.model;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
public class Audit {
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
