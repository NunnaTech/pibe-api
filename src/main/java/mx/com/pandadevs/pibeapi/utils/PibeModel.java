package mx.com.pandadevs.pibeapi.utils;

// Java
import java.time.LocalDateTime;

// Persistence
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

// Hibernate
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
@MappedSuperclass
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
