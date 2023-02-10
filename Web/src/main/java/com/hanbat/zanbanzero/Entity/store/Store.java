package com.hanbat.zanbanzero.Entity.store;

import com.hanbat.zanbanzero.Entity.user.Manager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    private Long id;
    private String name;
    private int location;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Manager manager;
}
