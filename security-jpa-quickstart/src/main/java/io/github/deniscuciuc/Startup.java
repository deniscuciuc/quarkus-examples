package io.github.deniscuciuc;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

@Singleton
public class Startup {

  @Transactional
  public void loadUsers(@Observes StartupEvent event) {
    User.deleteAll();
    User.add("alice", "alice", "user");
    User.add("bob", "bob", "admin");
  }
}
