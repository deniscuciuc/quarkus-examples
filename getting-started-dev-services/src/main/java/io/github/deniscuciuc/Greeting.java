package io.github.deniscuciuc;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Greeting extends PanacheEntity {
  public String name;
}
