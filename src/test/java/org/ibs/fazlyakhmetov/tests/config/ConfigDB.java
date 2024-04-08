package org.ibs.fazlyakhmetov.tests.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:C:\\Users\\Dinar\\IdeaProjects\\jdbcHomeWorkIBS\\src\\test\\resources\\conf.properties"})
public interface ConfigDB extends Config {

    @Key("h2Driver")
    String h2Driver();

    @Key("jdbcUrl")
    String jdbcUrl();

    @Key("login")
    String login();

    @Key("password")
    String password();
}
