package com.dilau.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idKazpost", "nameRus", "nameKaz", "nameLat"})
@Entity
@Table(name = "type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Type {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty("id")
    @Column(name = "id_kazpost")
    private String idKazpost;
    @Column(name = "name_rus")
    private String nameRus;
    @Column(name = "name_kaz")
    private String nameKaz;
    @Column(name = "name_lat")
    private String nameLat;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<Objects> objects;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<Building> departments;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<Street> streets;

}
