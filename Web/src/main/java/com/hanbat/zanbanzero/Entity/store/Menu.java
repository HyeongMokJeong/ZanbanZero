package com.hanbat.zanbanzero.Entity.store;

import com.hanbat.zanbanzero.dto.store.MenuDto;
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
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String name;
    private int cost;
    private String info;
    private String image;

    public static Menu createMenu(MenuDto dto) {
        return new Menu(dto.getId(),
        new Store(dto.getStoreId()),
        dto.getName(),
        dto.getCost(),
        dto.getInfo(),
        dto.getImage());
    };

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", store=" + store +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", info='" + info + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
