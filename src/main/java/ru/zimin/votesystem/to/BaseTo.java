package ru.zimin.votesystem.to;

import ru.zimin.votesystem.HasId;

import java.io.Serializable;

public abstract class BaseTo implements HasId, Serializable {
    static final long serialVersionUID = 1L;

    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
