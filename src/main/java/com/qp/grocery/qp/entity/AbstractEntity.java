package com.qp.grocery.qp.entity;



import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    public static Long epochTimeNow() {
        return ZonedDateTime.now().toInstant().toEpochMilli();
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = epochTimeNow();
        this.updatedAt = epochTimeNow();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = epochTimeNow();
    }


}
