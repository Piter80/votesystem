package ru.zimin.votesystem.to;

import org.hibernate.validator.constraints.Range;
import ru.zimin.votesystem.model.Menu;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class MenuTo extends BaseTo {
    @NotNull
    private LocalDate menuDate;

    @Min(1)
    @NotNull
    private Integer price;

    public MenuTo() {
    }

    public MenuTo(Menu menu) {
        this(menu.getId(), menu.getMenuDate(), menu.getPrice());
    }

    public MenuTo(Integer id, @NotNull LocalDate menuDate, @Range(min = 1) @NotNull Integer price) {
        super(id);
        this.menuDate = menuDate;
        this.price = price;
    }

    public LocalDate getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(LocalDate menuDate) {
        this.menuDate = menuDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuTo)) return false;
        MenuTo menuTO = (MenuTo) o;
        return getId().equals(menuTO.getId()) &&
                getMenuDate().equals(menuTO.getMenuDate()) &&
                getPrice().equals(menuTO.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMenuDate(), getPrice());
    }
}
