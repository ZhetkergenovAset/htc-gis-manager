package com.dilau.server.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history_street")
public class HistoryStreet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_kazpost")
    private String idKazpost;
    @Column(name="name_kaz")
    private String nameKaz;
    @Column(name="name_rus")
    private String nameRus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Objects objects;
    @Column(name = "total")
    private int total;
    @Column(name = "date_update")
    private LocalDate dateUpdate;

}
