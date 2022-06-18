package mx.com.pandadevs.pibeapi.utils;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Pibe base model.
 * PibeModel acts as an abstract base class from which every
 * other model in the project will inherit. This class provides
 * every table with the following attributes:
 *  + created_at (LocalDateTime): Store the LocalDateTime the object was created.
 *  + updated_at (LocalDateTime): Store the last LocalDateTime the object was modified.
 */
public class PibeModel {
    @CreationTimestamp
    @Column(
        name = "created_at",
        nullable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
        updatable = false
    )
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(
        name = "updated_at",
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    private LocalDateTime updatedAt;

}
