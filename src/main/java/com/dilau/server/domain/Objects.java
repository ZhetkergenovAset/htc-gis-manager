package com.dilau.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idKazpost", "nameKaz", "nameRus", "type", "parentId"})
@Entity
@Table(name = "objects")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Objects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @Column(name = "id_kazpost")
    @JsonProperty(value = "id")
    private String idKazpost;
    @Column(name = "name_kaz")
    private String nameKaz;
    @Column(name = "name_rus")
    private String nameRus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;
    @Column(name = "total")
    private int total;
    @Column(name = "parent_id")
    private String parentId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "object")
    private List<Street> streets;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objects")
    private List<HistoryStreet> historyStreets;
    @JsonIgnore
    @Column(name = "date_update")
    private LocalDate dateUpdate;

}
