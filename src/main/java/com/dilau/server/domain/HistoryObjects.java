package com.dilau.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history_objects")
public class HistoryObjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_kazpost")
    private String idKazpost;
    @Column(name = "name_kaz")
    private String nameKaz;
    @Column(name = "name_rus")
    private String nameRus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;
    @Column(name = "total")
    private int total;
    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "date_update")
    private LocalDate dateUpdate;
}
