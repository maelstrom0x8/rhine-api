package com.rhine.redstorm.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;

    @NotNull
    private String name;
    
    private String description;
    private LocalDateTime schedule;
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private TaskList list;
}
