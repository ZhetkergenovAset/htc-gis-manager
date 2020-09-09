package com.dilau.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idKazpost", "nameKaz", "nameRus", "type", "object"})
@Entity
@Table(name = "street")
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_kazpost")
    private String idKazpost;
    @Column(name = "name_kaz")
    private String nameKaz;
    @Column(name = "name_rus")
    private String nameRus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Objects object;
    @Column(name = "total")
    private int total;
    @Column(name = "date_update")
    @JsonIgnore
    private LocalDate dateUpdate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "street")
    private List<Building> buildings;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "street")
    private List<HistoryBuildings> historyBuildyngs;


}
