package ru.zimin.votesystem.to;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public abstract class NamedTo extends BaseTo {
    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String name;

    public NamedTo() {
    }

    public NamedTo(Integer id, @NotBlank @Size(min = 2, max = 100) @SafeHtml String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
