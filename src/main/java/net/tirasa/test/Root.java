package net.tirasa.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Root implements Serializable {

    private static final long serialVersionUID = -7794833835668648504L;

    private final List<POJO> pojos = new ArrayList<>();

    public List<POJO> getPojos() {
        return pojos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.pojos);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Root other = (Root) obj;
        if (!Objects.equals(this.pojos, other.pojos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Root{" + "pojos=" + pojos + '}';
    }
}
